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
/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.parts;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class TreeMessageDialog.
 */
public class TreeMessageDialog extends MessageDialog {

	private ITreeContentProvider fContentProvider;
	private ILabelProvider fLabelProvider;
	private Object fInput;

	/**
	 * Instantiates a new tree message dialog.
	 * 
	 * @param parentShell the parent shell
	 * @param dialogTitle the dialog title
	 * @param dialogTitleImage the dialog title image
	 * @param dialogMessage the dialog message
	 * @param dialogImageType the dialog image type
	 * @param dialogButtonLabels the dialog button labels
	 * @param defaultIndex the default index
	 */
	public TreeMessageDialog(final Shell parentShell, final String dialogTitle, final Image dialogTitleImage, final String dialogMessage,
	        final int dialogImageType, final String[] dialogButtonLabels, final int defaultIndex) {
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createCustomArea(final Composite parent) {
		final TreeViewer viewer = new TreeViewer(parent, SWT.BORDER);
		final GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 150; // SUPPRESS CHECKSTYLE MagicNumber
		viewer.getTree().setLayoutData(gd);
		viewer.setContentProvider(this.fContentProvider);
		viewer.setLabelProvider(this.fLabelProvider);
		viewer.setInput(this.fInput);
		Dialog.applyDialogFont(viewer.getControl());
		return viewer.getControl();
	}

	/**
	 * Sets the content provider.
	 * 
	 * @param provider the new content provider
	 */
	public void setContentProvider(final ITreeContentProvider provider) {
		this.fContentProvider = provider;
	}

	/**
	 * Sets the label provider.
	 * 
	 * @param provider the new label provider
	 */
	public void setLabelProvider(final ILabelProvider provider) {
		this.fLabelProvider = provider;
	}

	/**
	 * Sets the input.
	 * 
	 * @param input the new input
	 */
	public void setInput(final Object input) {
		this.fInput = input;
	}

}
