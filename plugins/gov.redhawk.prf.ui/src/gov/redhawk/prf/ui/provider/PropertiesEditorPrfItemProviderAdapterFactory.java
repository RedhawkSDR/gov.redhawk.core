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
package gov.redhawk.prf.ui.provider;

import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;

/**
 * @since 1.2
 */
public class PropertiesEditorPrfItemProviderAdapterFactory extends PrfItemProviderAdapterFactory {

	@Override
	public Adapter createPropertiesAdapter() {
		if (this.propertiesItemProvider == null) {
			this.propertiesItemProvider = new PropertiesEditorPropertiesItemProvider(this);
		}
		return this.propertiesItemProvider;
	}

	@Override
	public Adapter createSimpleAdapter() {
		return new PropertiesEditorSimpleItemProvider(this);
	}

	@Override
	public Adapter createSimpleSequenceAdapter() {
		return new PropertiesEditorSimpleSequenceItemProvider(this);
	}

	@Override
	public Adapter createStructAdapter() {
		return new PropertiesEditorStructItemProvider(this);
	}

	@Override
	public Adapter createStructSequenceAdapter() {
		return new PropertiesEditorStructSequenceItemProvider(this);
	}

	@Override
	public Adapter createSimpleRefAdapter() {
		if (this.simpleRefItemProvider == null) {
			this.simpleRefItemProvider = new PropertiesEditorSimpleRefItemProvider(this);
		}
		return this.simpleRefItemProvider;
	}

	@Override
	public Adapter createSimpleSequenceRefAdapter() {
		if (this.simpleSequenceRefItemProvider == null) {
			this.simpleSequenceRefItemProvider = new PropertiesEditorSimpleSequenceRefItemProvider(this);
		}
		return this.simpleSequenceRefItemProvider;
	}

	@Override
	public Adapter createStructValueAdapter() {
		if (this.structValueItemProvider == null) {
			this.structValueItemProvider = new PropertiesEditorStructValueItemProvider(this);
		}
		return this.structValueItemProvider;
	}
}
