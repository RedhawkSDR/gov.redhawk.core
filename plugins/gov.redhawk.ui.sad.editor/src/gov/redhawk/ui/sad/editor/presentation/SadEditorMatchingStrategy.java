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

package gov.redhawk.ui.sad.editor.presentation;

import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

/**
 * @since 3.0
 */
public class SadEditorMatchingStrategy implements IEditorMatchingStrategy {

	public boolean matches(final IEditorReference editorRef, final IEditorInput input) {
		if (input instanceof ScaFileStoreEditorInput) {
			final ScaFileStoreEditorInput inp1 = (ScaFileStoreEditorInput) input;
			IEditorInput inp;
			try {
				inp = editorRef.getEditorInput();
			} catch (final PartInitException e) {
				return false;
			}
			if (inp instanceof ScaFileStoreEditorInput) {
				final ScaFileStoreEditorInput inp2 = (ScaFileStoreEditorInput) inp;
				if (inp1.getScaObject() instanceof ScaWaveform && inp2.getScaObject() instanceof ScaWaveform) {
					final ScaWaveform sca1 = (ScaWaveform) inp1.getScaObject();
					final ScaWaveform sca2 = (ScaWaveform) inp2.getScaObject();
					return sca1 == sca2;
				}
			}
		}
		return false;
	}
}
