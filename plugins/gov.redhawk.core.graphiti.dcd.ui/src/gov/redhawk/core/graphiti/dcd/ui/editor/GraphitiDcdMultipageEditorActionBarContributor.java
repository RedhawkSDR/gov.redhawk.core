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
package gov.redhawk.core.graphiti.dcd.ui.editor;

import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.DiagramEditorActionBarContributor;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

import gov.redhawk.core.graphiti.dcd.ui.internal.editor.DcdActionBarContributor;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaMultipageActionBarContributor;

public class GraphitiDcdMultipageEditorActionBarContributor extends ScaMultipageActionBarContributor {

	@Override
	protected IEditorActionBarContributor getSubActionBarContributor(final IEditorPart activeEditor) {
		if (activeEditor instanceof DiagramEditor) {
			return new DiagramEditorActionBarContributor();
		} else if (activeEditor instanceof SCAFormEditor) {
			return new DcdActionBarContributor();
		}
		return super.getSubActionBarContributor(activeEditor);
	}

	@Override
	protected String getType(final IEditorPart activeEditor) {
		if (activeEditor == null || activeEditor instanceof SCAFormEditor) {
			return "DCD Editor";
		} else if (activeEditor instanceof TextEditor) {
			return "Text Editor";
		} else if (activeEditor instanceof DiagramEditor) {
			return "DCD Diagram Editor";
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
