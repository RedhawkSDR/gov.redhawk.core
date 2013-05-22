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
package gov.redhawk.prf.internal.ui.dialogs;

import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * 
 */
public class SimpleRefSelectorDialog extends ElementListSelectionDialog {

	/**
	 * @param parent
	 * @param renderer
	 */
	public SimpleRefSelectorDialog(final Shell parent, final SimpleRef input) {
		super(parent, SimpleRefSelectorDialog.createLabelProvider());
		setTitle("Select Property");
		setBlockOnOpen(true);
		setEmptyListMessage("No Properties to select.");
		setEmptySelectionMessage("No property selected.");
		this.setMessage("Select a property:");
		final StructSequence sequence = (StructSequence) ((StructValue) input.eContainer()).eContainer();
		if (sequence.getStruct() != null) {
			setElements(sequence.getStruct().getSimple().toArray());
		}
		setAllowDuplicates(true);
		setMultipleSelection(false);
	}

	/**
	 * @return
	 */
	private static ILabelProvider createLabelProvider() {
		return new AdapterFactoryLabelProvider(new PrfItemProviderAdapterFactory());
	}

}
