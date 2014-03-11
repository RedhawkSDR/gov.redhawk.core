/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

public class TunerAllocationDetailWizard extends Wizard {

	private TunerStatus tuner;
	private TunerStatus[] tuners = new TunerStatus[0];
	private AllocateRxDigitizerWizardPage allocateRxDigitizerPage;
	private AllocateMultipleRxDigitizerWizardPage allocatemultipleRxDigitizersPage;
	private List<TunerStatus> selectedTuners = new ArrayList<TunerStatus>();
	private Map<TunerStatus, IWizardPage> tunerMap = new HashMap<TunerStatus, IWizardPage>();
	private Map<IWizardPage, TunerStatus> pageMap = new HashMap<IWizardPage, TunerStatus>();

	public TunerAllocationDetailWizard(TunerStatus tuner) {
		this.tuner = tuner;
	}

	public TunerAllocationDetailWizard(TunerContainer container) {
		this.tuners = container.getTunerStatus().toArray(new TunerStatus[0]);
	}

	public TunerAllocationDetailWizard(TunerStatus[] tuners) {
		this.tuners = tuners;
	}

	@Override
	public void addPages() {
		if (tuner != null) {
			allocateRxDigitizerPage = new AllocateRxDigitizerWizardPage(tuner);
			addPage(allocateRxDigitizerPage);
			this.selectedTuners.add(tuner);
			tunerMap.put(tuner, allocateRxDigitizerPage);
			pageMap.put(allocateRxDigitizerPage, tuner);
		} else if (tuners != null && tuners.length > 0) {
			allocatemultipleRxDigitizersPage = new AllocateMultipleRxDigitizerWizardPage(tuners);
			addPage(allocatemultipleRxDigitizersPage);
			for (TunerStatus t : tuners) {
				AllocateRxDigitizerWizardPage page = new AllocateRxDigitizerWizardPage(t);
				addPage(page);
				tunerMap.put(t, page);
				pageMap.put(page, t);
			}
		}
	}

	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage prevPage = getPreviousTunerPage(page);
		if (prevPage == null) {
			return allocatemultipleRxDigitizersPage;
		}

		return prevPage;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == allocatemultipleRxDigitizersPage) {
			if (selectedTuners.size() == 0) {
				return null;
			}
		}
		return getNextTunerPage(page);
	}

	public void addTuner(TunerStatus tuner) {
		this.selectedTuners.add(tuner);
	}

	public void removeTuner(TunerStatus tuner) {
		this.selectedTuners.remove(tuner);
	}

	public int getSelectedTunerCount() {
		return this.selectedTuners.size();
	}

	private IWizardPage getNextTunerPage(IWizardPage page) {
		int index;
		if (page == allocatemultipleRxDigitizersPage) {
			index = 0;
		} else {
			TunerStatus t = pageMap.get(page);
			index = Integer.parseInt(t.getTunerID()) + 1;
		}
		if (index > getMaxIndex()) {
			return null;
		}
		while (index <= getMaxIndex()) {
			if (tunerSelected(getTunerById(index))) {
				break;
			}
			index++;
		}
		TunerStatus t = getTunerById(index);
		if (t == null || !tunerSelected(t)) {
			return null;
		} else {
			return tunerMap.get(t);
		}
	}

	private int getMaxIndex() {
		int max = 0;
		for (TunerStatus t : tuners) {
			int index = Integer.parseInt(t.getTunerID());
			if (index > max) {
				max = index;
			}
		}
		return max;
	}

	private boolean tunerSelected(TunerStatus tuner) {
		if (tuner == null) {
			return false;
		}
		for (TunerStatus t : selectedTuners) {
			if (t.getTunerID().equals(tuner.getTunerID())) {
				return true;
			}
		}
		return false;
	}

	private TunerStatus getTunerById(int index) {
		for (TunerStatus t : tuners) {
			if (Integer.parseInt(t.getTunerID()) == index) {
				return t;
			}
		}
		return null;
	}

	private IWizardPage getPreviousTunerPage(IWizardPage page) {
		if (page == allocatemultipleRxDigitizersPage) {
			return null;
		}
		int index = Integer.parseInt(pageMap.get(page).getTunerID());
		if (index == 0) {
			return allocatemultipleRxDigitizersPage;
		}
		index--;
		while (!tunerSelected(getTunerById(index)) && index >= 0) {
			index--;
		}
		if (tunerSelected(getTunerById(index))) {
			return tunerMap.get(getTunerById(index));
		} else {
			return allocatemultipleRxDigitizersPage;
		}
	}

	@Override
	public boolean canFinish() {
		boolean canFinish = true;
		if (allocatemultipleRxDigitizersPage != null) {
			canFinish = allocatemultipleRxDigitizersPage.isPageComplete();
		}
		return canFinish && selectedTunerPagesComplete();
	}

	private boolean selectedTunerPagesComplete() {
		for (TunerStatus t : selectedTuners) {
			IWizardPage page = tunerMap.get(t);
			if (!page.isPageComplete()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean performFinish() {
		ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(selectedTuners.get(0), ScaDevice.class);

		boolean result = true;
		StringBuilder sb = new StringBuilder();
		for (TunerStatus t : selectedTuners) {
			DataType[] props = createAllocationProperties(t);
			String delim = "";
			try {
				if (!device.allocateCapacity(props)) {
					sb.append(delim + "The allocation requested for Tuner " + t.getTunerID()
						+ " was not accepted because resources matching all aspects of the request were not available.");
					delim = "\n\n";
					result = false;
				}
				device.refresh(null, RefreshDepth.SELF);
			} catch (InvalidCapacity e) {
				sb.append(delim + "The Allocation Request for Tuner " + t.getTunerID()
					+ " was invalid. Please contact your System Administrator. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			} catch (InvalidState e) {
				sb.append(delim + "The Allocation Request for Tuner " + t.getTunerID() + " failed because the"
					+ " device is in an invalid state. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			} catch (InsufficientCapacity e) {
				sb.append(delim + "The Allocation Request for Tuner " + t.getTunerID() + " failed because the"
					+ " device has insufficient capacity. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			} catch (InterruptedException e) {
				sb.append(delim + "The Allocation Request for Tuner " + t.getTunerID() + " was interrupted. Message: " + e.getMessage());
				delim = "\n\n";
				result = false;
			}
		}
		if (!result) {
			MessageDialog.openError(getShell(), "The Allocation was not successful", sb.toString());
		} else {
			MessageDialog.openInformation(getShell(), "Successful allocation", "The requested allocation has been acepted.");
		}
		return result;
	}

	private DataType[] createAllocationProperties(TunerStatus tuner) {
		List<DataType> props = new ArrayList<DataType>();
		AllocateRxDigitizerWizardPage page = ((AllocateRxDigitizerWizardPage) tunerMap.get(tuner));
		ScaStructProperty struct;
		DataType dt = new DataType();
		if (page.getAllocationMode() == ALLOCATION_MODE.TUNER) {
			struct = page.getTunerAllocationStruct();
			dt.id = "FRONTEND::tuner_allocation";
		} else {
			struct = page.getListenerAllocationStruct();
			dt.id = "FRONTEND::listener_allocation";
		}
		dt.value = struct.toAny();
		props.add(dt);
		return props.toArray(new DataType[0]);
	}

}
