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
package gov.redhawk.core.graphiti.ui.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

public class AbstractPropertiesSection extends GFPropertySection {

	private List<Object> input;

	private TransactionalEditingDomain editingDomain = null;

	private EObject eObject;

	private List<Object> eObjectList = new ArrayList<Object>();

	public AbstractPropertiesSection() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		if (!(selection instanceof IStructuredSelection) || selection.equals(getInput())) {
			return;
		}

		/*
		 * Update editing domain
		 */
		IEditingDomainProvider provider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);
		if (provider != null) {
			EditingDomain theEditingDomain = provider.getEditingDomain();
			if (theEditingDomain instanceof TransactionalEditingDomain) {
				setEditingDomain((TransactionalEditingDomain) theEditingDomain);
			}
		}

		input = new ArrayList<Object>();

		eObjectList = new ArrayList<Object>();
		for (Iterator it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
			Object next = it.next();

			if (digIntoGroups() && next instanceof GraphitiShapeEditPart && ((AbstractEditPart) next).getModel() instanceof ContainerShape) {
				for (Iterator<Shape> iter = ((ContainerShape) ((GraphitiShapeEditPart) next).getModel()).getChildren().iterator(); iter.hasNext();) {
					Shape childEP = iter.next();
					// unwrap down to EObject and add to the eObjects list
					for (EObject bo : childEP.getLink().getBusinessObjects()) {
						if (addToEObjectList(bo)) {
							input.add(bo);
						}
					}
					continue;
				}
			}

			// unwrap down to EObject and add to the eObjects list
			if (addToEObjectList(next)) {
				input.add(next);
			}
		}

		// RATLC000524513 Sometimes there is no eobject. For example if user
		// creates a constraint,
		// on a class there will be a connection shown on the diagram which
		// connects the constraint
		// with the class. The user can select this connection, even though it
		// does not have an
		// underlying eobject. Comments are similar. In this case we show only
		// the appearanced tab.
		if (false == eObjectList.isEmpty()) {
			setEObject((EObject) eObjectList.get(0));
		}
	}

	public List<Object> getInput() {
		return input;
	}

	protected void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	protected TransactionalEditingDomain getEditingDomain() {
		if (editingDomain == null) {
			EObject eObjectInput = getEObject();
			if (eObjectInput != null) {
				editingDomain = TransactionUtil.getEditingDomain(eObjectInput);
			} else if (!getEObjectList().isEmpty()) {
				editingDomain = TransactionUtil.getEditingDomain(getEObjectList().get(0));
			}
		}
		return editingDomain;
	}

	protected EObject getEObject() {
		return eObject;
	}

	/**
	 * Override to return true to have this property section work on the shapes
	 * in a <code>GroupEditPart</code> as if the shapes were multi-selected.
	 * 
	 * @return true if this property section is to dig into the shapes of groups
	 */
	protected boolean digIntoGroups() {
		return false;
	}

	/**
	 * Add next object in the selection to the list of EObjects if this object
	 * could be adapted to an <code>EObject</code>
	 * @param object the object to add
	 * @return - true if the object is added, false otherwise
	 */
	protected boolean addToEObjectList(Object object) {
		EObject adapted = unwrap(object);
		if (adapted != null) {
			getEObjectList().add(adapted);
			return true;
		}
		return false;

	}

	protected void setEObject(EObject object) {
		this.eObject = object;
	}

	protected EObject unwrap(Object object) {
		return adapt(object);
	}

	/**
	 * Adapt the object to an EObject - if possible
	 * 
	 * @param object
	 * object from a diagram or ME
	 * @return EObject
	 */
	protected EObject adapt(Object object) {
		if (object instanceof IAdaptable) {
			return (EObject) ((IAdaptable) object).getAdapter(EObject.class);
		}

		return null;
	}

	protected List<Object> getEObjectList() {
		return eObjectList;
	}

	@Override
	public void dispose() {
		this.input = null;
		super.dispose();
	}

}
