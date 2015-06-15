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

import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.PortTypeContainer;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.Uses;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @since 10.0
 */
public class PortDetailsPropertySection extends AbstractPropertySection {

	private AbstractPort port;
	private Text infoText;
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
		FormData layoutData;
		Label label;

		// Port info: <uses/provides> interface / IDL (repID)
		layoutData = new FormData();
		layoutData.left = new FormAttachment(0);
		layoutData.right = new FormAttachment(100); // fill 100% to width of parent -HSPACE pixels
		this.infoText = getWidgetFactory().createText(composite, "", SWT.SINGLE | SWT.READ_ONLY);
		this.infoText.setData(FormToolkit.KEY_DRAW_BORDER, Boolean.FALSE);
		this.infoText.setLayoutData(layoutData);

		// Port Description
		layoutData = new FormData();
		layoutData.left = new FormAttachment(0);
		layoutData.top = new FormAttachment(this.infoText, 0, SWT.BOTTOM); // put below Port interface/IDL info
		label = getWidgetFactory().createLabel(composite, "Description:");
		label.setLayoutData(layoutData);

		layoutData = new FormData();
		layoutData.top = new FormAttachment(label, 0, SWT.BOTTOM); // put below label
		layoutData.left = new FormAttachment(0, ITabbedPropertyConstants.HSPACE); // start at left of parent +HSPACE
																					// pixels
		layoutData.right = new FormAttachment(100, -ITabbedPropertyConstants.HSPACE); // fill 100% to width of parent
																						// -HSPACE pixels
		layoutData.bottom = new FormAttachment(100, -ITabbedPropertyConstants.VMARGIN);
		this.descriptionText = getWidgetFactory().createText(composite, "", SWT.MULTI | SWT.WRAP);
		this.descriptionText.setLayoutData(layoutData);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		AbstractPort newPort = null;
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final Object obj = ss.getFirstElement();
			Object adapter = Platform.getAdapterManager().getAdapter(obj, AbstractPort.class);
			if (adapter instanceof AbstractPort) {
				newPort = (AbstractPort) adapter;
			}
		}

		this.port = newPort;
	}

	@Override
	public void refresh() {
		super.refresh();
		final AbstractPort currentPort = this.port;
		if (currentPort != null) {
			// Port Info: input/output direction and IDL
			StringBuilder sb = new StringBuilder("Direction: ");
			if (currentPort instanceof Uses) {
				sb.append("out <uses> "); // is an output Port
			}
			if (currentPort instanceof Provides) {
				sb.append("in <provides> "); // is an input Port
			}
			sb.append(' ').append(currentPort.getRepID()); // IDL/RepID

			// Port Type(s): data, control, and/or responses
			EList<PortTypeContainer> elist = currentPort.getPortType();
			if (elist != null && !elist.isEmpty()) {
				sb.append("  Type: ");
				for (int ii = 0; ii < elist.size(); ii++) {
					PortTypeContainer portTypeContainer = elist.get(ii);
					if (ii > 0) {
						sb.append(", ");
					}
					sb.append(portTypeContainer.getType());
				}
			}
			this.infoText.setText(sb.toString());

			// Port Description
			String str = currentPort.getDescription();
			if (str == null) { // description is an optional field
				str = ""; // but Text.setText() input has to non-null, so set to empty string
			}
			this.descriptionText.setText(str);
		}
	}

}
