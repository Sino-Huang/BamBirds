package meta;

import ab.demo.other.Shot;
import ab.planner.TrajectoryPlanner;
import ab.vision.ABObject;
import ab.vision.ABType;
import helper.CustomLogger;
import planner.Node;
import planner.Target;
import shot.SavedShot;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ShotSelection {
	public enum ShotEvaluation {
		GOOD, BAD
	}

	// private shot shot;
	private Level currentLevel;
	private int executedShots;
	public boolean fallbackToDemoShot = true;

	private List<Node> filteredNodeList = null;
	private Target chosenTarget = null;
	private Random random = new Random();
    private Shot shotToVary = null;

	public ShotSelection(Level currentLevel, int shotNumber) {
		this.currentLevel = currentLevel;
		this.executedShots = shotNumber;
	}

	public Target getChosenTarget() { return chosenTarget; }

	public boolean generateAndEvaluateShotOptions(List<Target> targets, List<SavedShot> savedShots) {
        shotToVary = null;
        
		if (targets == null || targets.isEmpty())
			return false;

		fallbackToDemoShot = false;
		currentLevel.tree.createChildNodesFromTargetList(targets, savedShots);

		try {
			ArrayList<Node> childNodes = currentLevel.tree.getCurrentNode().getChildren();
			if (childNodes == null || childNodes.isEmpty())
				throw new NullPointerException("No targets");

			List<Node> childsNotLost = new ArrayList<>();
			for (Node childNode : childNodes){
				if (!childNode.gameLost){
					childsNotLost.add(childNode);
				}
			}
			CustomLogger.info("child nodes before: "+childNodes.size()+" and after removal: "+childsNotLost.size());
            if (childsNotLost.isEmpty()) {
                shotToVary = childNodes.get(random.nextInt(childNodes.size())).getShot();
				throw new NullPointerException("All nodes lead to failure");
            }

			childsNotLost.sort(Comparator.comparingDouble(n -> -n.getConfidence()));
			filteredNodeList = childsNotLost;
			return true;
		}
		catch (Exception e) {
			fallbackToDemoShot = true;
			CustomLogger.severe("[ShotSelection] Error generating Targets: " + e.getMessage()
					+ ", falling back to demo shot.");
		}
		return false;
	}

	public Shot mostPromisingShot() {
		if (fallbackToDemoShot || filteredNodeList == null || filteredNodeList.isEmpty())
			return getDemoShot();

		Node highestConfidenceNode = filteredNodeList.get(0);
		chosenTarget = highestConfidenceNode.target;
		currentLevel.tree.setCurrentNode(highestConfidenceNode);

		// do we really care if the object can not be found in scene?
//		ABObject chosenABTarget = currentLevel.currentScene.findObjectWithID(chosenTarget.getTargetId());
//		if (chosenABTarget == null)
//			throw new NullPointerException("Could not find target in scene");

		CustomLogger.info("\nChosen target: " + chosenTarget + "\n");
		return highestConfidenceNode.getShot();
	}

	public Shot getDemoShot() {
		TrajectoryPlanner tp = new TrajectoryPlanner();
		Rectangle sling = currentLevel.currentScene.slingshot.bounds;
		ABType birdOnSling = currentLevel.currentScene.getBirds().get(0).getType();
		List<ABObject> pigs = currentLevel.currentScene.getPigs();
		if (sling != null) {

			if (!pigs.isEmpty() && shotToVary==null) {

				Point releasePoint = null;
				int dx, dy;
				{
					ABObject pig = pigs.get(random.nextInt(pigs.size()));

					Point _tpt = pig.getCenter();
					ArrayList<Point> pts = tp.estimateLaunchPoint(sling, _tpt);

					if (pts.size() == 1)
						releasePoint = pts.get(0);
					else if (pts.size() == 2) {
						if (random.nextInt(6) == 0)
							releasePoint = pts.get(1);
						else
							releasePoint = pts.get(0);
					} else if (pts.isEmpty()) {
						CustomLogger.warning("No release point found for the target");
						CustomLogger.info("Try a shot with 45 degree");
						releasePoint = tp.findReleasePoint(sling, Math.PI / 4);
					}
					Point refPoint = tp.getReferencePoint(sling);
					if (releasePoint != null) {
						double releaseAngle = tp.getReleaseAngle(sling, releasePoint);
						CustomLogger.info("Release Point: " + releasePoint);
						CustomLogger.info("Release Angle: " + Math.toDegrees(releaseAngle));
						int tapInterval;
						switch (birdOnSling) {
							case RedBird:    tapInterval = 0; break;
							case YellowBird: tapInterval = 65 + random.nextInt(25); break;
							case WhiteBird:  tapInterval = 70 + random.nextInt(15); break;
							case BlackBird:  tapInterval = 90 + random.nextInt(25); break;
							case BlueBird:   tapInterval = 65 + random.nextInt(20); break;
							default:         tapInterval = 60;
						}

						int tapTime = tp.getTapTime(sling, releasePoint, _tpt, tapInterval);
						dx = (int) releasePoint.getX() - refPoint.x - 50 + random.nextInt(100);
						dy = (int) releasePoint.getY() - refPoint.y - 50 + random.nextInt(100);
						return new Shot(refPoint.x, refPoint.y, dx, dy, 0, tapTime);
					} else {
						CustomLogger.severe("No Release Point Found");
						return null;
					}
				}
            } else if (shotToVary != null) {
                int dx = shotToVary.getDx() - 80 + random.nextInt(160);
                int dy = shotToVary.getDy() - 80 + random.nextInt(160);
                int tap = (int) shotToVary.getT_tap() - 200 + random.nextInt(400);
                return new Shot(shotToVary.getX(), shotToVary.getY(), dx, dy, 0, tap);
            }
		}
		return null;
	}

	private ShotEvaluation evaluate(Target chosenTarget, Level levelInStore) {
		ShotEvaluation resultEvaluation = ShotEvaluation.GOOD;
		List<Triplet<Shot, Target, Integer>> executedShotsList = levelInStore.executedShots;

		try {
			if (executedShots + 1 <= executedShotsList.size()) {

				ABObject chosenTargetObject = currentLevel.currentScene.findObjectWithID(chosenTarget.getTargetId());
				ABObject previousTargetObject = currentLevel.currentScene.findObjectWithID(executedShotsList.get(executedShots).getTarget().getTargetId());

				if (chosenTargetObject.equals(previousTargetObject)) {

					// If the shotToBeExecuted was the only shot in the list
					if (executedShotsList.size() == 1) {
						CustomLogger.info("[Meta] Only shot in list, shot was rated as being optimal.");
						return resultEvaluation;
					} else if ((optimalShot() + calculateRatioPoints(levelInStore)) < 0.45) {
						CustomLogger.info("[Meta] shot was bad in the first round: "
								+ (optimalShot() + calculateRatioPoints(levelInStore)) + ". Choosing another one...");
						resultEvaluation = ShotEvaluation.BAD;
					}
				}
			}
		} catch (Exception e) {
			CustomLogger.warning("[Meta] Connection is lacking so no exact calculation possible.");
		}
		return resultEvaluation;
	}

	private double optimalShot() {
		int totalBirdAmount = currentLevel.currentScene.getBirds().size() + currentLevel.executedShots.size();
		int usedBirds = currentLevel.executedShots.size();
		return (1 - (usedBirds / totalBirdAmount)) * 0.3;
	}

	private double calculateRatioPoints(Level levelInStore) {
		int numberOfBirds = currentLevel.currentScene.getBirds().size();
		int totalBirdAmount = numberOfBirds + currentLevel.executedShots.size();

		int estimatedShotPoints = currentLevel.getEstimatedMaximalPoints() - 10000 * (numberOfBirds - 1);
		CustomLogger.info("Damage Points of all shots: " + estimatedShotPoints);

		int averageReachablePoints = (estimatedShotPoints / totalBirdAmount);
		CustomLogger.info("Damage Points per shot: " + averageReachablePoints);

		int reachedPoints = levelInStore.executedShots.get(executedShots).getDamage();

		return (reachedPoints / averageReachablePoints) * 0.7;
	}

	public List<Node> getAvailableTargets() {
		if(this.filteredNodeList == null) {
			return new ArrayList<Node>();
		}
		return this.filteredNodeList;
	}

	public void printAvailableTarges() {
		System.out.println();
		CustomLogger.info("Available targets: ");
		if (fallbackToDemoShot) {
			CustomLogger.info("-- none --");
		}
		else if (filteredNodeList != null) {
			CustomLogger.info(Integer.toString(filteredNodeList.size()));
			for (Node n : filteredNodeList) {
				if (n.target != null)
					CustomLogger.info(n.target.prettyPrint());
			}
		}
		System.out.println();
	}

	@Override public String toString() {
		return String.format("(ShotSelection useDemoShot: %b, targets: %d, chosen: %s)",
				fallbackToDemoShot, (filteredNodeList == null ? 0 : filteredNodeList.size()), chosenTarget);
	}
}
