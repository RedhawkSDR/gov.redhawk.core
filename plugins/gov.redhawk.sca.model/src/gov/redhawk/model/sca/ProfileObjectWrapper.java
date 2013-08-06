/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

// BEGIN GENERATED CODE
package gov.redhawk.model.sca;

import gov.redhawk.model.sca.commands.SetProfileObjectCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Wrapper</b></em>'.
 * @since 5.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI <em>Profile URI</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj <em>Profile Obj</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getRootFileStore <em>Root File Store</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getProfileObjectWrapper()
 * @model interface="true" abstract="true" OBounds="org.eclipse.emf.ecore.EJavaObject"
 *        extendedMetaData="name='ProfileObjectWrapper' kind='empty'"
 * @generated
 */
public interface ProfileObjectWrapper< O extends Object > extends IStatusProvider {
	/**
	 * Returns the value of the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile URI</em>' attribute.
	 * @see #isSetProfileURI()
	 * @see #unsetProfileURI()
	 * @see #setProfileURI(URI)
	 * @see gov.redhawk.model.sca.ScaPackage#getProfileObjectWrapper_ProfileURI()
	 * @model unsettable="true" dataType="mil.jpeojtrs.sca.spd.URI" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='profile'"
	 * @generated
	 */
	URI getProfileURI();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI <em>Profile URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile URI</em>' attribute.
	 * @see #isSetProfileURI()
	 * @see #unsetProfileURI()
	 * @see #getProfileURI()
	 * @generated
	 */
	void setProfileURI(URI value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI <em>Profile URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProfileURI()
	 * @see #getProfileURI()
	 * @see #setProfileURI(URI)
	 * @generated
	 */
	void unsetProfileURI();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI <em>Profile URI</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Profile URI</em>' attribute is set.
	 * @see #unsetProfileURI()
	 * @see #getProfileURI()
	 * @see #setProfileURI(URI)
	 * @generated
	 */
	boolean isSetProfileURI();

	/**
	 * Returns the value of the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile Obj</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile Obj</em>' reference.
	 * @see #isSetProfileObj()
	 * @see #unsetProfileObj()
	 * @see #setProfileObj(Object)
	 * @see gov.redhawk.model.sca.ScaPackage#getProfileObjectWrapper_ProfileObj()
	 * @model kind="reference" unsettable="true" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='profileObj'"
	 * @generated
	 */
	O getProfileObj();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile Obj</em>' reference.
	 * @see #isSetProfileObj()
	 * @see #unsetProfileObj()
	 * @see #getProfileObj()
	 * @generated
	 */
	void setProfileObj(O value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProfileObj()
	 * @see #getProfileObj()
	 * @see #setProfileObj(Object)
	 * @generated
	 */
	void unsetProfileObj();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj <em>Profile Obj</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Profile Obj</em>' reference is set.
	 * @see #unsetProfileObj()
	 * @see #getProfileObj()
	 * @see #setProfileObj(Object)
	 * @generated
	 */
	boolean isSetProfileObj();

	/**
	 * Returns the value of the '<em><b>Root File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root File Store</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root File Store</em>' attribute.
	 * @see gov.redhawk.model.sca.ScaPackage#getProfileObjectWrapper_RootFileStore()
	 * @model dataType="gov.redhawk.model.sca.IFileStore" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	IFileStore getRootFileStore();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	O fetchProfileObject(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="mil.jpeojtrs.sca.spd.URI" monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	URI fetchProfileURI(IProgressMonitor monitor);

	/**
	 * @since 14.0
	 */
	final class Util {

		private Util() {

		}

		/**
		 * Fetch the profile object from the remote File Store
		 * @param <T> The type of the profile Object
		 * @param monitor The monitor to report status in
		 * @param wrapper The wrapper to load from
		 * @param type The type of the returned profile object
		 * @param rootPath The path within the XML Object to the root element
		 */
		public static < T extends EObject > Command fetchProfileObject(final IProgressMonitor monitor, final ProfileObjectWrapper<T> wrapper,
		        final Class<T> type, final String rootPath) {
			if (wrapper.isSetProfileObj()) {
				return UnexecutableCommand.INSTANCE;
			}

			SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Profile Object", 2);
			try {
				URI uri = wrapper.fetchProfileURI(subMonitor.newChild(1));

				if (uri != null) {
					uri = uri.appendFragment(rootPath);
					ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();

					T profileObject = null;
					IStatus status = null;
					try {
						profileObject = type.cast(fetchProfileObject(resourceSet, uri));
						subMonitor.worked(1);
					} catch (Exception e) {
						status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch profile object from profile path: '" + uri + "'", e);
					}

					return new SetProfileObjectCommand<T>(wrapper, profileObject, status);
				} else {
					return new UnsetLocalAttributeCommand(wrapper, Status.OK_STATUS, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);
				}
			} finally {
				subMonitor.done();
			}
		}

		private static EObject fetchProfileObject(final ResourceSet resourceSet, final org.eclipse.emf.common.util.URI objectUri) throws Exception {
			EObject newProfile = null;
			if (resourceSet != null && objectUri != null) {
				newProfile = resourceSet.getEObject(objectUri, true);
			}
			return newProfile;
		}

		public static void disposeResourceSet(ResourceSet oldValue) {
			if (oldValue != null) {
				oldValue.getResources().clear();
			}

		}
	}

} // ScaObjectWrapper
