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
package gov.redhawk.eclipsecorba.library.impl;

import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryPackage;
import gov.redhawk.eclipsecorba.library.LibraryPlugin;
import gov.redhawk.eclipsecorba.library.PreferenceNodePathSet;
import gov.redhawk.eclipsecorba.library.util.RefreshIdlLibraryJob;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Preference Node Path Set</b></em>'.
 * @since 4.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl#getKey <em>Key</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl#getDelimiter <em>Delimiter</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl#isFileUri <em>File Uri</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl#isReplaceEnv <em>Replace Env</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PreferenceNodePathSetImpl extends PathImpl implements PreferenceNodePathSet {
	/**
	 * The default value of the '{@link #getQualifier() <em>Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALIFIER_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected String qualifier = QUALIFIER_EDEFAULT;
	/**
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;
	/**
	 * The default value of the '{@link #getDelimiter() <em>Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelimiter()
	 * @generated
	 * @ordered
	 */
	protected static final String DELIMITER_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getDelimiter() <em>Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelimiter()
	 * @generated
	 * @ordered
	 */
	protected String delimiter = DELIMITER_EDEFAULT;
	/**
	 * The default value of the '{@link #isFileUri() <em>File Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFileUri()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FILE_URI_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isFileUri() <em>File Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFileUri()
	 * @generated
	 * @ordered
	 */
	protected boolean fileUri = FILE_URI_EDEFAULT;
	/**
	 * The default value of the '{@link #isReplaceEnv() <em>Replace Env</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReplaceEnv()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REPLACE_ENV_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isReplaceEnv() <em>Replace Env</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReplaceEnv()
	 * @generated
	 * @ordered
	 */
	protected boolean replaceEnv = REPLACE_ENV_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PreferenceNodePathSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LibraryPackage.Literals.PREFERENCE_NODE_PATH_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifier(String newQualifier) {
		String oldQualifier = qualifier;
		qualifier = newQualifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.PREFERENCE_NODE_PATH_SET__QUALIFIER, oldQualifier, qualifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.PREFERENCE_NODE_PATH_SET__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDelimiter(String newDelimiter) {
		String oldDelimiter = delimiter;
		delimiter = newDelimiter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.PREFERENCE_NODE_PATH_SET__DELIMITER, oldDelimiter, delimiter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFileUri() {
		return fileUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileUri(boolean newFileUri) {
		boolean oldFileUri = fileUri;
		fileUri = newFileUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.PREFERENCE_NODE_PATH_SET__FILE_URI, oldFileUri, fileUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReplaceEnv() {
		return replaceEnv;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReplaceEnv(boolean newReplaceEnv) {
		boolean oldReplaceEnv = replaceEnv;
		replaceEnv = newReplaceEnv;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.PREFERENCE_NODE_PATH_SET__REPLACE_ENV, oldReplaceEnv, replaceEnv));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__QUALIFIER:
				return getQualifier();
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__KEY:
				return getKey();
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__DELIMITER:
				return getDelimiter();
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__FILE_URI:
				return isFileUri();
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__REPLACE_ENV:
				return isReplaceEnv();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__QUALIFIER:
				setQualifier((String)newValue);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__KEY:
				setKey((String)newValue);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__DELIMITER:
				setDelimiter((String)newValue);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__FILE_URI:
				setFileUri((Boolean)newValue);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__REPLACE_ENV:
				setReplaceEnv((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__QUALIFIER:
				setQualifier(QUALIFIER_EDEFAULT);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__DELIMITER:
				setDelimiter(DELIMITER_EDEFAULT);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__FILE_URI:
				setFileUri(FILE_URI_EDEFAULT);
				return;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__REPLACE_ENV:
				setReplaceEnv(REPLACE_ENV_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__QUALIFIER:
				return QUALIFIER_EDEFAULT == null ? qualifier != null : !QUALIFIER_EDEFAULT.equals(qualifier);
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__DELIMITER:
				return DELIMITER_EDEFAULT == null ? delimiter != null : !DELIMITER_EDEFAULT.equals(delimiter);
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__FILE_URI:
				return fileUri != FILE_URI_EDEFAULT;
			case LibraryPackage.PREFERENCE_NODE_PATH_SET__REPLACE_ENV:
				return replaceEnv != REPLACE_ENV_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (qualifier: ");
		result.append(qualifier);
		result.append(", key: ");
		result.append(key);
		result.append(", delimiter: ");
		result.append(delimiter);
		result.append(", fileUri: ");
		result.append(fileUri);
		result.append(", replaceEnv: ");
		result.append(replaceEnv);
		result.append(')');
		return result.toString();
	}

	{
		// Handle changes to our members
		eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(final Notification msg) {
				switch (msg.getFeatureID(PreferenceNodePathSet.class)) {
				case LibraryPackage.PREFERENCE_NODE_PATH_SET__QUALIFIER:
					if (msg.getOldStringValue() != null) {
						removeQualifierPreferenceChangeListener(msg.getOldStringValue());
					}
					addQualifierPreferenceChangeListener(msg.getNewStringValue());
					// Keep going (no break) - common code below
				case LibraryPackage.PREFERENCE_NODE_PATH_SET__DELIMITER:
				case LibraryPackage.PREFERENCE_NODE_PATH_SET__KEY:
				case LibraryPackage.PREFERENCE_NODE_PATH_SET__FILE_URI:
				case LibraryPackage.PREFERENCE_NODE_PATH_SET__REPLACE_ENV:
					updateDerivedPaths();
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * Stores a reference to our preference change listener. We need this in order to remove it if the qualifier gets
	 * changed.
	 */
	private IPreferenceChangeListener qualifterPreferenceChangeListener = null;

	/**
	 * Removes the preference change listener for the previous (old) qualifier.
	 * 
	 * @param oldQualifier The previous (old) qualifier
	 */
	private void removeQualifierPreferenceChangeListener(final String oldQualifier) {
		Assert.isNotNull(this.qualifterPreferenceChangeListener);
		final ScopedPreferenceAccessor accessor = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, oldQualifier);
		accessor.removePreferenceChangeListener(this.qualifterPreferenceChangeListener);
		this.qualifterPreferenceChangeListener = null;
	}

	/**
	 * Adds a preference change listener for a new qualifier.
	 * 
	 * @param newQualifier The new qualifier
	 */
	private void addQualifierPreferenceChangeListener(final String newQualifier) {
		Assert.isTrue(this.qualifterPreferenceChangeListener == null);
		final ScopedPreferenceAccessor accessor = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, newQualifier);
		this.qualifterPreferenceChangeListener = new IPreferenceChangeListener() {
			public void preferenceChange(final PreferenceChangeEvent event) {
				if (event.getKey().equals(getKey())) {
					ScaModelCommand.execute(PreferenceNodePathSetImpl.this, new ScaModelCommand() {

						public void execute() {
							updateDerivedPaths();
						}
					});

					// Because the preference has changed, we'll initiate a refresh job
					if (eContainer() instanceof IdlLibrary) {
						final RefreshIdlLibraryJob job = new RefreshIdlLibraryJob((IdlLibrary) eContainer());
						job.setSystem(true);
						job.schedule();
					}
				}
			}
		};
		accessor.addPreferenceChangeListener(this.qualifterPreferenceChangeListener);
	}

	/**
	 * Helper method used to update the derived paths based on the current object's settings.
	 */
	private void updateDerivedPaths() {
		getDerivedPath().clear();
		if (getKey() != null && getDelimiter() != null && getQualifier() != null) {
			final ScopedPreferenceAccessor accessor = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, getQualifier());
			final String value = accessor.getString(getKey());
			final String[] values = value.split(getDelimiter());
			for (String v : values) {
				if ((v == null) || (v.trim().length() == 0)) {
					continue;
				}

				if (isReplaceEnv()) {
					// Let Eclipse perform any variable substitution it can
					try {
						v = VariablesPlugin.getDefault().getStringVariableManager().performStringSubstitution(v, false);
					} catch (final CoreException e) {
						// This shouldn't happen ever (we ask for no error reports above)
						LibraryPlugin
						        .getPlugin()
						        .getLog()
						        .log(new Status(IStatus.ERROR, LibraryPlugin.getPlugin().getSymbolicName(),
						                "Unexpected error while resolving variables in preference node path (" + v + ")", e));
						continue;
					}

					// Resolve remaining variable references using environment variables
					v = PluginUtil.replaceEnvIn(v, null);
				}

				final URI uri;
				if (isFileUri()) {
					uri = URI.createFileURI(v);
				} else {
					uri = URI.createURI(v);
				}
				getDerivedPath().add(uri);
			}
		}
	}

} //PreferenceNodePathSetImpl
