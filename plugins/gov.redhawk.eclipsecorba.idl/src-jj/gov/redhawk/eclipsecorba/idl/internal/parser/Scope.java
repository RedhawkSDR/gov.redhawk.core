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

// BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.internal.parser;

import gov.redhawk.eclipsecorba.idl.FileRegion;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlException;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.types.StructType;
import gov.redhawk.eclipsecorba.idl.types.UnionType;

import java.util.HashMap;
import java.util.Map;

public class Scope {
	private final Scope root;
	private final String name;
	private final Scope parent;
	private final Identifiable type;
	private final Map<String, Scope> subScopes = new HashMap<String, Scope>();
	private final Map<Integer, Scope> library;

	public Scope() {
		this.parent = null;
		this.type = null;
		this.root = this;
		this.library = new HashMap<Integer, Scope>();
		this.name = "ROOT";

		// Add Default Library references
		addDefaultLibraryReferences(this);
	}

	public Scope(final Identifiable type, final Scope parent, final Token token) throws ParseException {
		this.parent = parent;
		this.type = type;
		this.root = parent.root;
		this.library = this.root.library;
		if (type != null) {
			this.name = this.type.getName();
			parent.addSubScope(this, token);
			this.root.library.put(System.identityHashCode(type), this);
		} else {
			this.name = null;
		}
	}

	public String getName() {
		return this.name;
	}

	private void addSubScope(final Scope child, final Token token) throws ParseException {
		final Scope currentLocalScope = getLocalScope(child.getName());
		if (currentLocalScope != null) {
			String message = "Duplicate name: " + child.getName() + " collides with " + currentLocalScope.type.getName();
			if (currentLocalScope.type instanceof FileRegion && currentLocalScope.type.eResource() != null) {
				final FileRegion region = (FileRegion) currentLocalScope.type;
				message += " found at " + region.eResource().getURI().path() + "[" + region.getStartLine() + ":" + region.getStartColumn() + "]";
			}
			throw new ScopeException(message, token);
		}
		if (child.getName().startsWith("_")) {
			this.subScopes.put(child.getName().substring(1), child);
		}
		this.subScopes.put(child.getName(), child);
	}

	public void addScope(final Identifiable type, final Token token) throws ParseException {
		new Scope(type, this, token);
	}

	public void addMember(final Identifiable member, final Token token) throws ParseException {
		addScope(member, token);
	}

	public IdlTypeDcl findType(final String name, final Token token) throws ParseException {
		final Identifiable type = findIdentifiable(name, token);
		if (!(type instanceof IdlTypeDcl)) {
			throw new ScopeException("Invalid type reference: " + name, token);
		}
		return (IdlTypeDcl) type;
	}

	public IdlInterfaceDcl findInterfaceDclStub(final String name) {
		final Identifiable type = findIdentifiableNull(name);

		if (!(type instanceof IdlInterfaceDcl)) {
			return null;
		}
		return (IdlInterfaceDcl) type;
	}

	public UnionType findUnionTypeStub(final String name) {
		final Identifiable type = findIdentifiableNull(name);

		if (!(type instanceof UnionType)) {
			return null;
		}
		return (UnionType) type;
	}

	public StructType findStructTypeStub(final String name) {
		final Identifiable type = findIdentifiableNull(name);

		if (!(type instanceof StructType)) {
			return null;
		}
		return (StructType) type;
	}

	public Identifiable findIdentifiable(final String name, final Token token) throws ParseException {
		final Identifiable retVal = findIdentifiableNull(name);
		if (retVal == null) {
			throw new ScopeException("Unknown reference type: " + name, token);
		}
		return retVal;
	}

	public Identifiable findIdentifiableNull(final String name) {
		// Check absolute name
		if (name.startsWith("::")) {
			return this.root.findIdentifiableNull(name.substring(2));
		}

		// Check Sub Scopes
		if (name.contains("::")) {
			final String[] split = name.split("::", 2);
			final Scope subScope = getLocalScope(split[0]);
			if (subScope != null) {
				return subScope.findIdentifiableNull(split[1]);
			}
		} else {
			// Check Local Scope
			final Scope subScope = getLocalScope(name);
			if (subScope != null) {
				return subScope.type;
			}
		}

		// Check inherited Scopes
		if (this.type instanceof IdlInterfaceDcl) {
			final IdlInterfaceDcl dcl = (IdlInterfaceDcl) this.type;
			for (final IdlInterfaceDcl inherited : dcl.getInheritedInterfaces()) {
				final Scope inheritedScope = getScope(inherited);
				if (inheritedScope != null) {
					final Identifiable retVal = inheritedScope.findIdentifiableNull(name);
					if (retVal != null) {
						return retVal;
					}
				} else {
					throw new IllegalStateException("Unable to find previous Identifiable: " + inherited.getRepId());
				}
			}
		}

		// Pass up to parent
		if (this.parent != null) {
			return this.parent.findIdentifiableNull(name);
		}

		return null;
	}

	private Scope getLocalScope(final String scopeName) {
		return this.subScopes.get(scopeName);
	}

	public IdlException findException(final String name, final Token token) throws ParseException {
		final Identifiable type = findIdentifiable(name, token);
		if (!(type instanceof IdlException)) {
			throw new ScopeException("Invalid exception reference: " + name, token);
		}
		return (IdlException) type;
	}

	@Override
	public String toString() {
		if (this.parent != null) {
			return this.parent.toString() + "::" + this.name;
		} else {
			return "";
		}

	}

	public Scope getScope(final Identifiable val) {
		final Scope retVal = this.library.get(System.identityHashCode(val));
		return retVal;
	}

	private static void addDefaultLibraryReferences(final Scope scope) {
		final IdlInterfaceDcl typeCode = IdlFactory.eINSTANCE.createIdlInterfaceDcl();
		typeCode.setName("TypeCode");
		typeCode.setPrefix("omg.org");

		final Module corbaModule = IdlFactory.eINSTANCE.createModule();
		corbaModule.setName("CORBA");
		corbaModule.getDefinitions().add(typeCode);

		try {
			final Scope corbaModuleScope = new Scope(corbaModule, scope, null);
			new Scope(typeCode, corbaModuleScope, null);
		} catch (final ParseException e) {
			// PASS Will not happen
		}

	}
}
