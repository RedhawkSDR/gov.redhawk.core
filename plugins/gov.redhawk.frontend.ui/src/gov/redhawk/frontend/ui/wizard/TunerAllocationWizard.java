package gov.redhawk.frontend.ui.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

public class TunerAllocationWizard extends Wizard {

	private Tuner tuner;
	private Tuner[] tuners = new Tuner[0];
	private AllocateRxDigitizerWizardPage allocateRxDigitizerPage;
	private AllocateMultipleRxDigitizerWizardPage allocatemultipleRxDigitizersPage;
	private List<Tuner> selectedTuners = new ArrayList<Tuner>();
	private Map<Tuner, IWizardPage> tunerMap = new HashMap<Tuner, IWizardPage>();
	private Map<IWizardPage, Tuner> pageMap = new HashMap<IWizardPage, Tuner>();

	public TunerAllocationWizard(Tuner tuner) {
		this.tuner = tuner;
	}

	public TunerAllocationWizard(TunerContainer container) {
		this.tuners = container.getTuners().toArray(new Tuner[0]);
	}

	public TunerAllocationWizard(Tuner[] tuners) {
		this.tuners  = tuners;
	}

	@Override
	public void addPages() {
		if (tuner != null) {
			allocateRxDigitizerPage = new AllocateRxDigitizerWizardPage(tuner);
			addPage(allocateRxDigitizerPage);
		} else if (tuners != null && tuners.length > 0) {
			allocatemultipleRxDigitizersPage = new AllocateMultipleRxDigitizerWizardPage(tuners);
			addPage(allocatemultipleRxDigitizersPage);
			for (Tuner tuner : tuners) {
				AllocateRxDigitizerWizardPage page = new AllocateRxDigitizerWizardPage(tuner);
				addPage(page);
				tunerMap.put(tuner, page);
				pageMap.put(page,  tuner);
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
			System.err.println(selectedTuners.size() + " tuners selected");
			if (selectedTuners.size() == 0) {
				return null;
			}
		}
		return getNextTunerPage(page);
	}
	
	public void addTuner(Tuner tuner) {
		this.selectedTuners.add(tuner);
	}
	
	public void removeTuner(Tuner tuner) {
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
			Tuner t = pageMap.get(page);
			index = Integer.parseInt(t.getTunerID()) + 1;
		}
		if (index >= tuners.length) {
			return null;
		}
		while (index < tuners.length) {
			if (tunerSelected(getTunerById(index))) {
				break;
			}
			index++;
		}
		Tuner t = getTunerById(index);
		if (t == null || !tunerSelected(t)) {
			return null;
		} else {
			return tunerMap.get(t);
		}
	}
	
	private boolean tunerSelected(Tuner tuner) {
		if (tuner == null) {
			return false;
		}
		for (Tuner t : selectedTuners) {
			if (t.getTunerID().equals(tuner.getTunerID())) {
				return true;
			}
		}
		return false;
	}

	private Tuner getTunerById(int index) {
		for (Tuner t : tuners) {
			if (Integer.parseInt(t.getTunerID()) == index ) {
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
		return  canFinish && selectedTunerPagesComplete();
	}
	
	
	private boolean selectedTunerPagesComplete() {
		for (Tuner tuner : selectedTuners) {
			IWizardPage page = tunerMap.get(tuner);
			if (!page.isPageComplete()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
