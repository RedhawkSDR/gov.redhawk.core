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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.sca.ui.IScaEditorDescriptor;

import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;

/**
 * 
 */
public class ScaEditorDescriptor implements IScaEditorDescriptor {

	private final IEditorDescriptor editorDescriptor;
	private final IEditorInput editorInput;
	private final Object selectedObject;

	public ScaEditorDescriptor(final IEditorDescriptor editorDescriptor, final IEditorInput editorInput, final Object selected) {
		super();
		this.editorDescriptor = editorDescriptor;
		this.editorInput = editorInput;
		this.selectedObject = selected;
	}

	@Override
	public IEditorDescriptor getEditorDescriptor() {
		return this.editorDescriptor;
	}

	@Override
	public IEditorInput getEditorInput() {
		return this.editorInput;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof IScaEditorDescriptor) {
			return this.editorDescriptor.getId().equals(((IScaEditorDescriptor) obj).getEditorDescriptor().getId());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.editorDescriptor.getId().hashCode();
	}

	@Override
	public Object getSelectedObject() {
		return this.selectedObject;
	}

}
