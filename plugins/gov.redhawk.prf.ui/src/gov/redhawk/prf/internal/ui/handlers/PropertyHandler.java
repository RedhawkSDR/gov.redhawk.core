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
package gov.redhawk.prf.internal.ui.handlers;

import gov.redhawk.prf.internal.ui.editor.detailspart.Property;

import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;

public class PropertyHandler {

	private PropertyHandler() {
		//Prevent instantiation
	}

	/**
	 * Adds a simple to the specified owner.
	 * 
	 * @param adapterFactory the adapterFactory to obtain the itemProvider from
	 * @param editingDomain the editingDomain
	 * @param owner the object the simple should be added to
	 * @return the new simple
	 */
	public static Simple addSimple(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final Properties properties) {
		//		Command command = null;
		final Simple simple = PrfFactory.eINSTANCE.createSimple();
		final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory
		        .adapt(simple, IEditingDomainItemProvider.class);
		Command command = editingDomainItemProvider.createCommand(properties, editingDomain, AddCommand.class, new CommandParameter(properties,
		        PrfPackage.Literals.PROPERTIES__SIMPLE, Collections.singleton(simple)));
		PropertyHandler.execute(editingDomain, command);
		return simple;
	}

	/**
	 * Adds a simple to the specified properties.
	 * 
	 * @param adapterFactory the adapterFactory to obtain the itemProvider from
	 * @param editingDomain the editingDomain
	 * @return the new simplesequence object
	 */
	public static SimpleSequence addSimpleSequence(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final Properties properties) {
		final SimpleSequence sequence = PrfFactory.eINSTANCE.createSimpleSequence();
		final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory.adapt(sequence,
		        IEditingDomainItemProvider.class);
		Command command = editingDomainItemProvider.createCommand(properties, editingDomain, AddCommand.class, new CommandParameter(properties,
		        PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE, Collections.singleton(sequence)));
		PropertyHandler.execute(editingDomain, command);
		return sequence;
	}

	/**
	 * Adds a struct to the specified owner.
	 * 
	 * @param adapterFactory the adapterFactory to obtain the itemProvider from
	 * @param editingDomain the editingDomain
	 * @return the new struct
	 */
	public static Struct addStruct(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final Properties properties) {
		final Struct struct = PrfFactory.eINSTANCE.createStruct();
		final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory
		        .adapt(struct, IEditingDomainItemProvider.class);
		Command command = editingDomainItemProvider.createCommand(properties, editingDomain, AddCommand.class, new CommandParameter(properties,
		        PrfPackage.Literals.PROPERTIES__STRUCT, Collections.singleton(struct)));
		PropertyHandler.execute(editingDomain, command);
		return struct;
	}

	/**
	 * Adds a valid structsequence to the properties.
	 * 
	 * @param adapterFactory the adapterFactory to obtain the itemProvider from
	 * @param editingDomain the editingDomain
	 * @param owner the object the simple should be added to
	 * @return the new structsequence
	 */
	public static StructSequence addStructSequence(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final Properties owner) {
		Command command = null;
		final StructSequence structSequence = PrfFactory.eINSTANCE.createStructSequence();
		final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory.adapt(structSequence,
		        IEditingDomainItemProvider.class);
		command = editingDomainItemProvider.createCommand(owner, editingDomain, AddCommand.class, new CommandParameter(owner,
		        PrfPackage.Literals.PROPERTIES__STRUCT_SEQUENCE, Collections.singleton(structSequence)));
		PropertyHandler.execute(editingDomain, command);
		return structSequence;
	}

	/**
	 * Removes the objects in the selection from the properties.
	 * 
	 * @param editingDomain the editingDomain
	 * @param properties the properties to remove from
	 * @param selected the objects to remove
	 */
	public static void removeProperty(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final Properties properties,
	        final List<Object> selected) {
		final CompoundCommand command = new CompoundCommand("Remove property command");
		for (final Object selection : selected) {
			final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory.adapt(selection,
			        IEditingDomainItemProvider.class);
			if (selection instanceof AbstractProperty) {
				final Property prop = Property.getProperty((EObject) selection);
				//Check for props that can belong to other props.
				if (prop == Property.SIMPLE || prop == Property.STRUCT) {
					final EObject container = ((EObject) selection).eContainer();
					if (container instanceof Struct) {
						command.append(editingDomainItemProvider.createCommand(container, editingDomain, RemoveCommand.class, new CommandParameter(container,
						        PrfPackage.Literals.STRUCT__SIMPLE, Collections.singleton(selection))));
					} else if (container instanceof StructSequence) {
						command.append(SetCommand.create(editingDomain, container, PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT, SetCommand.UNSET_VALUE));
					} else {
						command.append(RemoveCommand.create(editingDomain, properties, prop.getPropertyFeature(), selection));
					}
				} else {
					command.append(RemoveCommand.create(editingDomain, properties, prop.getPropertyFeature(), selection));
				}
			} else if (selection instanceof StructValue) {
				command.append(RemoveCommand.create(editingDomain, ((StructValue) selection).eContainer(), PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT_VALUE,
				        selection));
			} else if (selection instanceof SimpleRef) {
				command.append(RemoveCommand.create(editingDomain, ((SimpleRef) selection).eContainer(), PrfPackage.Literals.STRUCT_VALUE__SIMPLE_REF,
				        selection));
			}
		}
		PropertyHandler.execute(editingDomain, command);
	}

	/**
	 * Executes the specified command on the editing domain's command stack if it can be executed.
	 * 
	 * @param editingDomain the editingDomain
	 * @param command the command to execute
	 */
	private static void execute(final EditingDomain editingDomain, final Command command) {
		if (command != null && command.canExecute()) {
			editingDomain.getCommandStack().execute(command);
		}
	}

	/**
	 * 
	 * @param adapterFactory
	 * @param editingDomain
	 * @param input
	 * @return
	 */
	public static SimpleRef addSimpleRef(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final StructValue input) {
		Command command = null;
		final SimpleRef simpleRef = PrfFactory.eINSTANCE.createSimpleRef();
		final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory.adapt(simpleRef,
		        IEditingDomainItemProvider.class);
		command = editingDomainItemProvider.createCommand(input, editingDomain, AddCommand.class, new CommandParameter(input,
		        PrfPackage.Literals.STRUCT_VALUE__SIMPLE_REF, Collections.singleton(simpleRef)));
		PropertyHandler.execute(editingDomain, command);
		return simpleRef;
	}

	/**
	 * 
	 * @param adapterFactory
	 * @param editingDomain
	 * @param input
	 * @return
	 */
	public static StructValue addStructValue(final AdapterFactory adapterFactory, final EditingDomain editingDomain, final StructSequence input) {
		Command command = null;
		final StructValue structValue = PrfFactory.eINSTANCE.createStructValue();
		final IEditingDomainItemProvider editingDomainItemProvider = (IEditingDomainItemProvider) adapterFactory.adapt(structValue,
		        IEditingDomainItemProvider.class);
		command = editingDomainItemProvider.createCommand(input, editingDomain, AddCommand.class, new CommandParameter(input,
		        PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT_VALUE, Collections.singleton(structValue)));
		PropertyHandler.execute(editingDomain, command);
		return structValue;
	}

}
