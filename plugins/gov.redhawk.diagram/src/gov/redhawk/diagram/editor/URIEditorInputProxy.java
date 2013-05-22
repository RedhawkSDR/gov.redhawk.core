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
package gov.redhawk.diagram.editor;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.EditorInputProxy;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

/**
 * 
 */
public class URIEditorInputProxy extends EditorInputProxy implements IPersistableElement {

	/**
	 * @param input
	 * @param domain
	 */
	public URIEditorInputProxy(final URIEditorInput proxied, final TransactionalEditingDomain domain) {
		super(proxied, domain);
		assert proxied != null && domain != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getFactoryId() {
		final IPersistableElement persistable = getPersistableElement();
		if (persistable != null) {
			return persistable.getFactoryId();
		}
		assert false;
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveState(final IMemento memento) {
		final IPersistableElement persistable = getPersistableElement();
		if (persistable != null) {
			persistable.saveState(memento);
		}
	}

	/**
	 * @return Persistable Element
	 */
	private IPersistableElement getPersistableElement() {
		if (this.fProxied instanceof IPersistableElement) {
			return (IPersistableElement) this.fProxied;
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	public URI getURI() {
		return ((URIEditorInput) this.fProxied).getURI();
	}

	public URIEditorInput getProxied() {
		return (URIEditorInput) this.fProxied;
	}

}
