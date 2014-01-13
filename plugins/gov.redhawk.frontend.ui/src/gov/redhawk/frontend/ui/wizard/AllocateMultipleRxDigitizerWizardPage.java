package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.edit.utils.TunerUtils;
import gov.redhawk.frontend.Tuner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class AllocateMultipleRxDigitizerWizardPage extends WizardPage {

	private String[] tableCloumns = new String[] {"Tuner Instance", "RF Flow ID", "Center Frequency", "Bandwidth"};
	private Tuner[] tuners;

	protected AllocateMultipleRxDigitizerWizardPage(TunerContainer container) {
		super("Allocate Multiple RX Digitizer Tuners");
		this.tuners = (Tuner[]) TunerUtils.INSTANCE.getChildren(container);
	}
	
	protected AllocateMultipleRxDigitizerWizardPage(Tuner[] tuners) {
		super("Allocate Multiple RX Digitizer Tuners");
		this.tuners = tuners;
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		createGroupControls(comp);
		setControl(comp);
		
		setTitle("Tuner Selection");
		setDescription("Select the Tuners you would like to allocate. Click \"Next\" to specify" +
				" the allocation parameters for each selected Tuner.");
		
	}

	private void createGroupControls(Composite parent) {
		GridLayoutFactory.fillDefaults().applyTo(parent);
		Table table = new Table(parent, SWT.CHECK | SWT.MULTI);
		addColumns(table);
		table.setHeaderVisible(true);
		CheckboxTableViewer viewer = new CheckboxTableViewer(table);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		viewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public void dispose() {
				// TODO Auto-generated method stub

			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
				// TODO Auto-generated method stub

			}

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Tuner[]) {
					return (Tuner[]) inputElement;
				}
				return Collections.emptyList().toArray(new Object[0]);
			}

		});
		viewer.setLabelProvider(new ITableLabelProvider() {

			@Override
			public void addListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isLabelProperty(Object element, String property) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void removeListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub

			}

			@Override
			public Image getColumnImage(Object element, int columnIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getColumnText(Object element, int columnIndex) {
				String retVal = "";
				switch (columnIndex) {
				case 0:
					retVal = ((Tuner) element).getTunerID();
					break;
				case 1:
					retVal = ((Tuner) element).getRfFlowID();
					if ("".equals(retVal.trim())) {
						retVal = "[None]";
					}
					break;
				case 2:
					retVal = String.valueOf(((Tuner) element).getTunerStatus().getCenterFrequency());
					break;
				case 3:
					retVal = String.valueOf(((Tuner) element).getTunerStatus().getBandwidth());
					break;
				default:
				}
				return retVal;
			}

		});
		viewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (event.getChecked()) {
					((TunerAllocationWizard) getWizard()).addTuner((Tuner) event.getElement());
				} else {
					((TunerAllocationWizard) getWizard()).removeTuner((Tuner) event.getElement());
				}
				setPageComplete(validate());
			}
			
		});
		viewer.setInput(tuners);
		setPageComplete(false);
	}
	

	private void addColumns(Table table) {
		for (String name : tableCloumns) {
			TableColumn col = new TableColumn(table, SWT.NONE);
			col.setText(name);
			col.setWidth(200);
		}
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return ((TunerAllocationWizard) getWizard()).getSelectedTunerCount() > 0;
	}
	
	@Override
	public IWizardPage getPreviousPage() {
		return null;
	}
	
	private boolean validate() {
		return ((TunerAllocationWizard) getWizard()).getSelectedTunerCount() > 0;
	}		
	
}
