package de.uniba.sme.bambirds.common.objects;

public class SavedShot {

	public Shot storedShot;
	public String targetID;
	public int impactAngle; // between +90 and -90
	
	@Deprecated
	public boolean reachable;
	public double[] parabola;
	public double actualAngle;

	@Deprecated
	public SavedShot(Shot storedShot, String targetID, double actualAngle, int impactAngle, double[] parabola, boolean reachable) {
		this.storedShot = storedShot;
		this.targetID = targetID;
		this.impactAngle = impactAngle;
		this.reachable = reachable;
		this.parabola = new double[]{ parabola[0], parabola[1] };
		this.actualAngle = actualAngle;
	}

	public SavedShot(Shot storedShot, String targetID, double actualAngle, int impactAngle, double[] parabola) {
		this.storedShot = storedShot;
		this.targetID = targetID;
		this.impactAngle = impactAngle;
		this.reachable = false;
		this.parabola = new double[]{ parabola[0], parabola[1] };
		this.actualAngle = actualAngle;
	}

	public Shot getStoredShot(){
		return this.storedShot;
	}

	@Override public String toString() {
		return String.format("(SavedShot [%d, %d], target %s @ %d)",
				storedShot.getDx(), storedShot.getDy(), targetID, impactAngle);
	}
}
