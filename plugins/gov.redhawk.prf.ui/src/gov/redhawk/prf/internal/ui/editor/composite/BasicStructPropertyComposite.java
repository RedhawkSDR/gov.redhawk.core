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
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.ui.doc.HelpUtil;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class BasicStructPropertyComposite extends AbstractPropertyComposite {
	private CheckboxTableViewer configurationKindViewer;
	private Label kindLabel;

	public BasicStructPropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
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
		super.setEditable(canEdit);

		if (this.configurationKindViewer != null) {
			this.kindLabel.setEnabled(canEdit);
			this.configurationKindViewer.getControl().setEnabled(canEdit);
		}
	}

}
