/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.ui.properties;

import gov.redhawk.model.sca.ScaPort;
import mil.jpeojtrs.sca.scd.AbstractPort;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @since 10.0
 */
public class PortDetailsPropertySection extends AbstractPropertySection {

	private ScaPort<? extends AbstractPort, ?> scaPort;
	private Text descriptionText;

	/**
	 *
	 */
	public PortDetailsPropertySection() {
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		 Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		 FormData formLayoutData;

		 formLayoutData = new FormData();
		 Label label = getWidgetFactory().createLabel(composite, "Description:");
		 label.setLayoutData(formLayoutData);

		 formLayoutData = new FormData();
		 formLayoutData.top    = new FormAttachment(label, 0, SWT.BOTTOM);                  // put below label
		 formLayoutData.left   = new FormAttachment(0,    ITabbedPropertyConstants.HSPACE); // start at left of parent +HSPACE pixels
		 formLayoutData.right  = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE); // fill 100% to width of parent -HSPACE pixels
		 formLayoutData.bottom = new FormAttachment(100, -ITabbedPropertyConstants.VMARGIN);
		 this.descriptionText = getWidgetFactory().createText(composite, "", SWT.MULTI | SWT.WRAP);
		 this.descriptionText.setLayoutData(formLayoutData);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		ScaPort<? extends AbstractPort, ?> newScaPort = null;
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final Object obj = ss.getFirstElement();
			final Object adapter = Platform.getAdapterManager().getAdapter(obj, ScaPort.class);
			if (adapter instanceof ScaPort< ? , ? >) {
				newScaPort = (ScaPort< ? , ? >) adapter;
			}
		}

		this.scaPort = newScaPort;
	}

	@Override
	public void refresh() {
		super.refresh();
		if (this.scaPort != null) {
			AbstractPort port = this.scaPort.getProfileObj();
			if (port != null) {
				String str = port.getDescription();
				if (str == null) { // description is an optional field
					str = "";      // but Text.setText() input has to non-null, so set to empty string
				}
				this.descriptionText.setText(str);
			}
		}
	}

}
