/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
//TODO: Add copyright for DiagramEditorActionBarContributor, since we borrow heavily from their code
package gov.redhawk.core.graphiti.sad.ui.editor;

import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

import gov.redhawk.core.graphiti.sad.ui.internal.editor.SadActionBarContributor;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiMultipageEditorActionBarContributor;
import gov.redhawk.ui.editor.SCAFormEditor;

public class GraphitiWaveformMultipageEditorActionBarContributor extends AbstractGraphitiMultipageEditorActionBarContributor {

	@Override
	protected IEditorActionBarContributor getSubActionBarContributor(final IEditorPart activeEditor) {
		if (activeEditor instanceof DiagramEditor) {
			return new EditorActionBarContributor();
		} else if (activeEditor instanceof SCAFormEditor) {
			return new SadActionBarContributor();
		}
		return super.getSubActionBarContributor(activeEditor);
	}

	@Override
	protected String getType(final IEditorPart activeEditor) {
		if (activeEditor == null || activeEditor instanceof SCAFormEditor) {
			return "SAD Editor";
		} else if (activeEditor instanceof TextEditor) {
			return "Text Editor";
		} else if (activeEditor instanceof DiagramEditor) {
			return "SAD Diagram Editor";
		} else {
			try {
				if (activeEditor instanceof StructuredTextEditor) {
					return "XML Editor";
				}
			} catch (final NoClassDefFoundError e) {
				// PASS
			}
		}
		return "";
	}
}
