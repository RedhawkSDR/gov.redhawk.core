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
package gov.redhawk.spd.internal.validation;

import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.spd.Code;
import mil.jpeojtrs.sca.spd.CodeFileType;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.LocalFile;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaUriHelpers;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

public class LocalFileConstraint extends AbstractModelConstraint {

	public static final String SCD_ID = "gov.redhawk.spd.validation.scdfile_constraint";
	public static final String PROPERTY_ID = "gov.redhawk.spd.validation.propertyfile_constraint";
	public static final String PROPERTYIF_ID = "gov.redhawk.spd.validation.propertyinterface_constraint";
	public static final String CODE_ID = "gov.redhawk.spd.validation.codelocalfile_constraint";

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		IStatus retVal = null;
		String constraintId = ctx.getCurrentConstraintId();
		switch (constraintId) {
		case LocalFileConstraint.PROPERTY_ID:
			if (target instanceof Implementation) {
				final Implementation impl = (Implementation) target;
				if (impl.getPropertyFile() != null && impl.getPropertyFile().getLocalFile() != null) {
					final LocalFile localFile = impl.getPropertyFile().getLocalFile();
					retVal = validateLocalFile(ctx, localFile, SpdPackage.Literals.IMPLEMENTATION__PROPERTY_FILE);
				}
			} else if (target instanceof SoftPkg) {
				final SoftPkg softPkg = (SoftPkg) target;
				if (softPkg.getPropertyFile() != null && softPkg.getPropertyFile().getLocalFile() != null) {
					final LocalFile localFile = softPkg.getPropertyFile().getLocalFile();
					retVal = validateLocalFile(ctx, localFile, SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE);
				}
			}
			break;

		case LocalFileConstraint.PROPERTYIF_ID:
			final SoftPkg softPkg = (SoftPkg) target;
			if (softPkg.getPropertyFile() != null && softPkg.getPropertyFile().getLocalFile() != null) {
				final LocalFile localFile = softPkg.getPropertyFile().getLocalFile();
				retVal = validatePrfInterface(ctx, localFile, SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE, softPkg);
			}
			break;

		case LocalFileConstraint.SCD_ID:
			final SoftPkg spd = (SoftPkg) target;
			if (spd.getDescriptor() != null && spd.getDescriptor().getLocalfile() != null) {
				final LocalFile localFile = spd.getDescriptor().getLocalfile();
				retVal = validateLocalFile(ctx, localFile, SpdPackage.Literals.SOFT_PKG__DESCRIPTOR);
			}
			break;

		case LocalFileConstraint.CODE_ID:
			final Code code = (Code) target;
			Implementation impl = null;
			if (code.eContainer() instanceof Implementation) {
				impl = (Implementation) code.eContainer();
			}
			boolean isValidType = code.getType().equals(CodeFileType.EXECUTABLE) || (impl != null && impl.isExecutable());
			if ((code.getLocalFile() != null) && isValidType) {
				final LocalFile localFile = code.getLocalFile();
				retVal = validateLocalFile(ctx, localFile, SpdPackage.Literals.CODE__LOCAL_FILE);
			}
			break;

		default:
			break;
		}

		if (retVal != null) {
			return retVal;
		} else {
			return ctx.createSuccessStatus();
		}
	}

	private ConstraintStatus validateLocalFile(final IValidationContext ctx, final LocalFile file, final EStructuralFeature eReference) {
		URI uri = ScaUriHelpers.getLocalFileURI(file.getName(), file, null);
		if (uri != null) {
			try {
				if (uri.isPlatform()) {
					uri = CommonPlugin.resolve(uri);
				}
				final IFileStore store = EFS.getStore(java.net.URI.create(uri.toString()));
				final IFileInfo info = store.fetchInfo();
				if (info.exists()) {
					return null;
				}
			} catch (final CoreException e) {
				// PASS
			}
		}
		return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(file.getName()), eReference);
	}

	private ConstraintStatus validatePrfInterface(final IValidationContext ctx, final LocalFile file, final EStructuralFeature eReference, SoftPkg spkg) {
		if (spkg != null && spkg.getDescriptor() != null && spkg.getDescriptor().getComponent() != null) {
			for (Interface iface: spkg.getDescriptor().getComponent().getInterfaces().getInterface()) {
				if ("PropertySet".equals(iface.getName())) {
					return null;
				}
			}
			return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(file.getName()), eReference);
		}
		return null;
	}

}
