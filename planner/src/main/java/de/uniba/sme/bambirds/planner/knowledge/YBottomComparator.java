package de.uniba.sme.bambirds.planner.knowledge;

import java.util.Comparator;

import de.uniba.sme.bambirds.common.objects.ab.ABObject;

/**
 * <p>
 * Compare two {@link ABObject}s by their position on the y-axis using the
 * bottommost y-coordinate of the {@link ABObject}.
 * </p>
 * <p>
 * Note: {@link YComparator} uses the upper-left corner of the {@link ABObject}
 * and is thus not equivalent to this.
 * </p>
 */
public class YBottomComparator implements Comparator<ABObject> {
	public int compare(ABObject a, ABObject b) {
		return Double.compare(b.getMinY(), a.getMinY());
	}
}
