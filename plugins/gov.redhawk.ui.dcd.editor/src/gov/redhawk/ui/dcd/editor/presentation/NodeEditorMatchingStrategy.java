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

package gov.redhawk.ui.dcd.editor.presentation;

import gov.redhawk.sca.ui.ScaFileStoreEditorInput;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

/**
 * @since 3.0
 */
public class NodeEditorMatchingStrategy implements IEditorMatchingStrategy {

	public boolean matches(final IEditorReference editorRef, final IEditorInput input) {
		if (input instanceof ScaFileStoreEditorInput) {
			try {
				final IEditorInput inp = editorRef.getEditorInput();
				if (inp instanceof ScaFileStoreEditorInput) {
					final ScaFileStoreEditorInput input1 = (ScaFileStoreEditorInput) input;
					final ScaFileStoreEditorInput input2 = (ScaFileStoreEditorInput) inp;

					return input1.getScaObject() == input2.getScaObject();
				}
			} catch (final PartInitException e) {
				return false;
			}
		}
		return false;
	}

}
