/**
 * 
 */
package gov.redhawk.frontend.ui;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.sca.ui.ScaViewerSorter;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;

/**
 * @author dch
 *
 */
public class FrontEndViewerSorter extends ScaViewerSorter {

	/**
	 * 
	 */
	public FrontEndViewerSorter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param collator
	 */
	public FrontEndViewerSorter(Collator collator) {
		super(collator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof TunerStatus) {
			TunerStatus t1 = (TunerStatus) e1;
			String id1 = t1.getAllocationID();
			if (e2 instanceof TunerStatus) {
				TunerStatus t2 = (TunerStatus) e2;
				String id2 = t2.getAllocationID();
				if (id1 == null || "".equals(id1) && !(id2 == null || "".equals(id2))) {
					return 1;
				}
				if (id2 == null || "".equals(id2) && !(id1 == null || "".equals(id1))) {
					return -1;
				}
//				return 0;
			}
		}
		return super.compare(viewer, e1, e2);
	}
}
