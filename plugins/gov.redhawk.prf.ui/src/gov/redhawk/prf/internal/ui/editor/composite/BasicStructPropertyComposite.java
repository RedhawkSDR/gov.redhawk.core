/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.ui.doc.HelpUtil;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class BasicStructPropertyComposite extends AbstractPropertyComposite {
	private CheckboxTableViewer configurationKindViewer;
	private Label kindLabel;
	private Label messageLabel;
	private Button messageButton;
	public BasicStructPropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
	}

	
	protected void createMessage(final Composite parent, final FormToolkit toolkit) {
		// Message checkbox
		this.messageLabel = toolkit.createLabel(parent, "Message:");
		this.messageLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.messageLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		this.messageButton = toolkit.createButton(parent,  "Enable", SWT.CHECK);
		this.messageButton.setLayoutData(AbstractPropertyComposite.FACTORY.create());
	}

	protected Label getMessageLabel() {
		return this.messageLabel;
	}
	
	public Button getMessageButton() {
		return this.messageButton;
	}
	
	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected void createConfigurationKindViewer(final Composite parent, final FormToolkit toolkit) {
		// Type
		this.kindLabel = toolkit.createLabel(parent, "Kind:");
		this.kindLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.kindLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		final CheckboxTableViewer viewer = new CheckboxTableViewer(toolkit.createTable(parent, SWT.CHECK | SWT.READ_ONLY));
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getText(final Object element) {
				if (element instanceof StructPropertyConfigurationType) {
					final StructPropertyConfigurationType type = (StructPropertyConfigurationType) element;
					if (type == StructPropertyConfigurationType.CONFIGURE) {
						return element.toString() + " (default)";
					}
				}
				return element == null ? "" : element.toString(); //$NON-NLS-1$ // SUPPRESS CHECKSTYLE AvoidInLine
			}
		});
		viewer.setInput(StructPropertyConfigurationType.values());
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
		
		// In response to Bug 289 the Message property type was moved from the viewer into a stand alone checkbox so the Message type
		// must be filtered out of the viewer.
		viewer.addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof StructPropertyConfigurationType 
						&& ((StructPropertyConfigurationType) element).equals(StructPropertyConfigurationType.MESSAGE)) {
					
					return false;
				}
				return true;
			}
		});
		
		this.configurationKindViewer = viewer;
		HelpUtil.assignTooltip(this.configurationKindViewer.getControl(), HelpConstants.prf_properties_simple_type);
	}

	public CheckboxTableViewer getConfigurationKindViewer() {
		return this.configurationKindViewer;
	}
	
	public Label getKindLabel() {
		return this.kindLabel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(final boolean canEdit) {
		if (this.configurationKindViewer != null) {
			this.configurationKindViewer.getControl().setEnabled(canEdit);
		}
		if (this.messageButton != null) {
			this.messageButton.setEnabled(canEdit);
		}
		super.setEditable(canEdit);
	}

}
