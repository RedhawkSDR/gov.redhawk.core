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
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class BasicStructPropertyComposite extends AbstractPropertyComposite {
	private ComboViewer configurationKindViewer;

	private boolean configShown;
	private ViewerFilter configFilter;

	public BasicStructPropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
	}

	protected void createConfigurationKindViewer(final Composite parent, final FormToolkit toolkit) {
		// Type
		Label kindLabel = toolkit.createLabel(parent, "Kind:");
		kindLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		kindLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		final ComboViewer viewer = new ComboViewer(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		viewer.getCombo().addListener(SWT.MouseVerticalWheel, getEventIgnorer());
		toolkit.adapt(viewer.getCombo());
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(final Object element) {
				if (element instanceof StructPropertyConfigurationType) {
					switch ((StructPropertyConfigurationType) element) {
					case PROPERTY:
						return element.toString() + " (default)";
					case CONFIGURE:
						return element.toString() + " (deprecated)";
					default:
						break;
					}
				}
				return (element == null) ? "" : element.toString();
			}
		});
		// Filter "configure" by default
		this.configShown = false;
		this.configFilter = new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof StructPropertyConfigurationType) {
					if (((StructPropertyConfigurationType) element) == StructPropertyConfigurationType.CONFIGURE) {
						return false;
					}
				}
				return true;
			}
		};
		viewer.addFilter(this.configFilter);
		viewer.setInput(new Object[] { StructPropertyConfigurationType.PROPERTY, StructPropertyConfigurationType.ALLOCATION, StructPropertyConfigurationType.MESSAGE,
			StructPropertyConfigurationType.CONFIGURE });
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

		this.configurationKindViewer = viewer;
		HelpUtil.assignTooltip(this.configurationKindViewer.getControl(), HelpConstants.prf_properties_simple_type);
	}

	public ComboViewer getConfigurationKindViewer() {
		return this.configurationKindViewer;
	}

	@Override
	public void setEditable(final boolean canEdit) {
		if (this.configurationKindViewer != null) {
			this.configurationKindViewer.getControl().setEnabled(canEdit);
		}
		super.setEditable(canEdit);
	}

	/**
	 * This method adds or removes a filter for the "configure" option in the property kind drop-down.
	 * Provides backwards-compatibility for REDHAWK project pre-2.0.
	 * @param visible If "configure" should be shown
	 */
	public void showConfigure(boolean visible) {
		if (visible != configShown) {
			if (visible) {
				this.configurationKindViewer.removeFilter(configFilter);
			} else {
				this.configurationKindViewer.addFilter(configFilter);
			}
			configShown = visible;
		}
	}
}
