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

// BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.types.provider;

import gov.redhawk.eclipsecorba.idl.types.util.TypesAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesItemProviderAdapterFactory extends TypesAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;
	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();
	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.TypeDef} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeDefItemProvider typeDefItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.TypeDef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTypeDefAdapter() {
		if (typeDefItemProvider == null) {
			typeDefItemProvider = new TypeDefItemProvider(this);
		}

		return typeDefItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.VoidType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VoidTypeItemProvider voidTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.VoidType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createVoidTypeAdapter() {
		if (voidTypeItemProvider == null) {
			voidTypeItemProvider = new VoidTypeItemProvider(this);
		}

		return voidTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.UnionType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionTypeItemProvider unionTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.UnionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUnionTypeAdapter() {
		if (unionTypeItemProvider == null) {
			unionTypeItemProvider = new UnionTypeItemProvider(this);
		}

		return unionTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Switch} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchItemProvider switchItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Switch}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSwitchAdapter() {
		if (switchItemProvider == null) {
			switchItemProvider = new SwitchItemProvider(this);
		}

		return switchItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Case} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CaseItemProvider caseItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Case}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCaseAdapter() {
		if (caseItemProvider == null) {
			caseItemProvider = new CaseItemProvider(this);
		}

		return caseItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultCaseLabelItemProvider defaultCaseLabelItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDefaultCaseLabelAdapter() {
		if (defaultCaseLabelItemProvider == null) {
			defaultCaseLabelItemProvider = new DefaultCaseLabelItemProvider(this);
		}

		return defaultCaseLabelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExprCaseLabelItemProvider exprCaseLabelItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExprCaseLabelAdapter() {
		if (exprCaseLabelItemProvider == null) {
			exprCaseLabelItemProvider = new ExprCaseLabelItemProvider(this);
		}

		return exprCaseLabelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.ElementSpec} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementSpecItemProvider elementSpecItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.ElementSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createElementSpecAdapter() {
		if (elementSpecItemProvider == null) {
			elementSpecItemProvider = new ElementSpecItemProvider(this);
		}

		return elementSpecItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.EnumType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumTypeItemProvider enumTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.EnumType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEnumTypeAdapter() {
		if (enumTypeItemProvider == null) {
			enumTypeItemProvider = new EnumTypeItemProvider(this);
		}

		return enumTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.StructType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructTypeItemProvider structTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.StructType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStructTypeAdapter() {
		if (structTypeItemProvider == null) {
			structTypeItemProvider = new StructTypeItemProvider(this);
		}

		return structTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.SequenceType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceTypeItemProvider sequenceTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.SequenceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSequenceTypeAdapter() {
		if (sequenceTypeItemProvider == null) {
			sequenceTypeItemProvider = new SequenceTypeItemProvider(this);
		}

		return sequenceTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.IdlString} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdlStringItemProvider idlStringItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.IdlString}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIdlStringAdapter() {
		if (idlStringItemProvider == null) {
			idlStringItemProvider = new IdlStringItemProvider(this);
		}

		return idlStringItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.WString} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WStringItemProvider wStringItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.WString}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWStringAdapter() {
		if (wStringItemProvider == null) {
			wStringItemProvider = new WStringItemProvider(this);
		}

		return wStringItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.PrimitiveType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrimitiveTypeItemProvider primitiveTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.PrimitiveType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPrimitiveTypeAdapter() {
		if (primitiveTypeItemProvider == null) {
			primitiveTypeItemProvider = new PrimitiveTypeItemProvider(this);
		}

		return primitiveTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Short} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShortItemProvider shortItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Short}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createShortAdapter() {
		if (shortItemProvider == null) {
			shortItemProvider = new ShortItemProvider(this);
		}

		return shortItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Long} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LongItemProvider longItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Long}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLongAdapter() {
		if (longItemProvider == null) {
			longItemProvider = new LongItemProvider(this);
		}

		return longItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Octet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OctetItemProvider octetItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Octet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOctetAdapter() {
		if (octetItemProvider == null) {
			octetItemProvider = new OctetItemProvider(this);
		}

		return octetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Float} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatItemProvider floatItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Float}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFloatAdapter() {
		if (floatItemProvider == null) {
			floatItemProvider = new FloatItemProvider(this);
		}

		return floatItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Double} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DoubleItemProvider doubleItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Double}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDoubleAdapter() {
		if (doubleItemProvider == null) {
			doubleItemProvider = new DoubleItemProvider(this);
		}

		return doubleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.IdlChar} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdlCharItemProvider idlCharItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.IdlChar}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIdlCharAdapter() {
		if (idlCharItemProvider == null) {
			idlCharItemProvider = new IdlCharItemProvider(this);
		}

		return idlCharItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.IdlWChar} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdlWCharItemProvider idlWCharItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.IdlWChar}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIdlWCharAdapter() {
		if (idlWCharItemProvider == null) {
			idlWCharItemProvider = new IdlWCharItemProvider(this);
		}

		return idlWCharItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.WChar} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WCharItemProvider wCharItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.WChar}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWCharAdapter() {
		if (wCharItemProvider == null) {
			wCharItemProvider = new WCharItemProvider(this);
		}

		return wCharItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Boolean} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanItemProvider booleanItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Boolean}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createBooleanAdapter() {
		if (booleanItemProvider == null) {
			booleanItemProvider = new BooleanItemProvider(this);
		}

		return booleanItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.LongLong} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LongLongItemProvider longLongItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.LongLong}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLongLongAdapter() {
		if (longLongItemProvider == null) {
			longLongItemProvider = new LongLongItemProvider(this);
		}

		return longLongItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.LongDouble} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LongDoubleItemProvider longDoubleItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.LongDouble}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLongDoubleAdapter() {
		if (longDoubleItemProvider == null) {
			longDoubleItemProvider = new LongDoubleItemProvider(this);
		}

		return longDoubleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.UShort} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UShortItemProvider uShortItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.UShort}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUShortAdapter() {
		if (uShortItemProvider == null) {
			uShortItemProvider = new UShortItemProvider(this);
		}

		return uShortItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.ULong} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ULongItemProvider uLongItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.ULong}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createULongAdapter() {
		if (uLongItemProvider == null) {
			uLongItemProvider = new ULongItemProvider(this);
		}

		return uLongItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.ULongLong} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ULongLongItemProvider uLongLongItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.ULongLong}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createULongLongAdapter() {
		if (uLongLongItemProvider == null) {
			uLongLongItemProvider = new ULongLongItemProvider(this);
		}

		return uLongLongItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Any} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnyItemProvider anyItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Any}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAnyAdapter() {
		if (anyItemProvider == null) {
			anyItemProvider = new AnyItemProvider(this);
		}

		return anyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.IdlObject} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdlObjectItemProvider idlObjectItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.IdlObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIdlObjectAdapter() {
		if (idlObjectItemProvider == null) {
			idlObjectItemProvider = new IdlObjectItemProvider(this);
		}

		return idlObjectItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionForwardDclItemProvider unionForwardDclItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUnionForwardDclAdapter() {
		if (unionForwardDclItemProvider == null) {
			unionForwardDclItemProvider = new UnionForwardDclItemProvider(this);
		}

		return unionForwardDclItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.StructForwardDcl} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructForwardDclItemProvider structForwardDclItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.StructForwardDcl}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStructForwardDclAdapter() {
		if (structForwardDclItemProvider == null) {
			structForwardDclItemProvider = new StructForwardDclItemProvider(this);
		}

		return structForwardDclItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.FixedPtType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FixedPtTypeItemProvider fixedPtTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.FixedPtType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFixedPtTypeAdapter() {
		if (fixedPtTypeItemProvider == null) {
			fixedPtTypeItemProvider = new FixedPtTypeItemProvider(this);
		}

		return fixedPtTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.ValueBaseType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueBaseTypeItemProvider valueBaseTypeItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.ValueBaseType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createValueBaseTypeAdapter() {
		if (valueBaseTypeItemProvider == null) {
			valueBaseTypeItemProvider = new ValueBaseTypeItemProvider(this);
		}

		return valueBaseTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link gov.redhawk.eclipsecorba.idl.types.Enumeration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationItemProvider enumerationItemProvider;

	/**
	 * This creates an adapter for a {@link gov.redhawk.eclipsecorba.idl.types.Enumeration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEnumerationAdapter() {
		if (enumerationItemProvider == null) {
			enumerationItemProvider = new EnumerationItemProvider(this);
		}

		return enumerationItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void dispose() {
		if (typeDefItemProvider != null) typeDefItemProvider.dispose();
		if (voidTypeItemProvider != null) voidTypeItemProvider.dispose();
		if (unionTypeItemProvider != null) unionTypeItemProvider.dispose();
		if (switchItemProvider != null) switchItemProvider.dispose();
		if (caseItemProvider != null) caseItemProvider.dispose();
		if (defaultCaseLabelItemProvider != null) defaultCaseLabelItemProvider.dispose();
		if (exprCaseLabelItemProvider != null) exprCaseLabelItemProvider.dispose();
		if (elementSpecItemProvider != null) elementSpecItemProvider.dispose();
		if (enumTypeItemProvider != null) enumTypeItemProvider.dispose();
		if (structTypeItemProvider != null) structTypeItemProvider.dispose();
		if (sequenceTypeItemProvider != null) sequenceTypeItemProvider.dispose();
		if (idlStringItemProvider != null) idlStringItemProvider.dispose();
		if (wStringItemProvider != null) wStringItemProvider.dispose();
		if (primitiveTypeItemProvider != null) primitiveTypeItemProvider.dispose();
		if (shortItemProvider != null) shortItemProvider.dispose();
		if (longItemProvider != null) longItemProvider.dispose();
		if (octetItemProvider != null) octetItemProvider.dispose();
		if (floatItemProvider != null) floatItemProvider.dispose();
		if (doubleItemProvider != null) doubleItemProvider.dispose();
		if (idlCharItemProvider != null) idlCharItemProvider.dispose();
		if (idlWCharItemProvider != null) idlWCharItemProvider.dispose();
		if (wCharItemProvider != null) wCharItemProvider.dispose();
		if (booleanItemProvider != null) booleanItemProvider.dispose();
		if (longLongItemProvider != null) longLongItemProvider.dispose();
		if (longDoubleItemProvider != null) longDoubleItemProvider.dispose();
		if (uShortItemProvider != null) uShortItemProvider.dispose();
		if (uLongItemProvider != null) uLongItemProvider.dispose();
		if (uLongLongItemProvider != null) uLongLongItemProvider.dispose();
		if (anyItemProvider != null) anyItemProvider.dispose();
		if (idlObjectItemProvider != null) idlObjectItemProvider.dispose();
		if (unionForwardDclItemProvider != null) unionForwardDclItemProvider.dispose();
		if (structForwardDclItemProvider != null) structForwardDclItemProvider.dispose();
		if (fixedPtTypeItemProvider != null) fixedPtTypeItemProvider.dispose();
		if (valueBaseTypeItemProvider != null) valueBaseTypeItemProvider.dispose();
		if (enumerationItemProvider != null) enumerationItemProvider.dispose();
	}

}
