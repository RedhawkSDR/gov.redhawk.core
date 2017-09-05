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
package gov.redhawk.sca.jobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import CF.DomainManagerHelper;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.util.CorbaUtils;

/**
 * Finds domains in a naming service.
 * @since 8.1
 */
public class FindDomainsJob extends Job {

	private String namingServiceRef;
	private boolean warningNotError;
	private List<String> domainNames = null;

	/**
	 * @param namingServiceRef The naming service reference, e.g. <code>corbaname::localhost:2809</code>
	 * @param warningNotError True to return a {@link IStatus#WARNING WARNING} status instead of
	 * {@link IStatus#ERROR ERROR} status if there is a problem. This helps avoid Eclipse popping an error dialog.
	 */
	public FindDomainsJob(String namingServiceRef, boolean warningNotError) {
		super(Messages.FindDomainsJob_JobTitle);
		setUser(false);
		setSystem(true);
		this.namingServiceRef = namingServiceRef;
		this.warningNotError = warningNotError;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final int WORK_ROOT = 3;
		final int WORK_ALL_BINDINGS = 10;
		final int WORK_FINISH = 1;
		final SubMonitor progress = SubMonitor.convert(monitor, Messages.FindDomainsJob_JobMessage, WORK_ROOT + WORK_ALL_BINDINGS + WORK_FINISH);
		final List<String> retVal = new ArrayList<String>();

		OrbSession session = OrbSession.createSession();
		NamingContextExt rootContext = null;
		try {
			final org.omg.CORBA.Object rootContextRef = CorbaUtils.string_to_object(session.getOrb(), namingServiceRef, progress.newChild(1));
			rootContext = CorbaUtils.invoke(() -> {
				return NamingContextExtHelper.narrow(rootContextRef);
			}, progress.newChild(1));

			// Get a listing of root names
			final BindingListHolder bl = new BindingListHolder();
			rootContext.list(-1, bl, new BindingIteratorHolder());
			progress.worked(1);

			final SubMonitor progressAllBindings = progress.newChild(WORK_ALL_BINDINGS).setWorkRemaining(bl.value.length);
			for (Binding b : bl.value) {
				final SubMonitor progressBinding = progressAllBindings.newChild(1).setWorkRemaining(4);
				// Domains are always bound in a context with the same name as the domain
				if (b.binding_type.value() == BindingType._ncontext) {
					String guessedDomainName = b.binding_name[0].id + "/" + b.binding_name[0].id; //$NON-NLS-1$
					org.omg.CORBA.Object object = null;
					try {
						object = CorbaUtils.resolve_str(rootContext, guessedDomainName, progressBinding.newChild(1));
						if (!CorbaUtils.non_existent(object, progressBinding.newChild(1))
							&& CorbaUtils.is_a(object, DomainManagerHelper.id(), progressBinding.newChild(1))) {
							retVal.add(b.binding_name[0].id);
						}
					} catch (CoreException ex) {
						// Ignore the guessed domain
					} finally {
						ORBUtil.release(object);
						object = null;
						progressBinding.worked(1);
					}
				}
			}

			domainNames = retVal;
			return Status.OK_STATUS;
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} catch (CoreException e) {
			int severity = (warningNotError) ? IStatus.WARNING : IStatus.ERROR;
			return new Status(severity, ScaPlugin.PLUGIN_ID, Messages.FindDomainsJob_ErrorMessage, e);
		} finally {
			if (rootContext != null) {
				ORBUtil.release(rootContext);
			}
			session.dispose();
			progress.worked(WORK_FINISH);
		}
	}

	/**
	 * @return The naming service reference that was used to locate domains.
	 */
	public String getNamingServiceRef() {
		return namingServiceRef;
	}

	/**
	 * @return The list of domain names that were found, or an empty list if none.
	 */
	public List<String> getDomainNames() {
		return (domainNames != null) ? domainNames : Collections.emptyList();
	}

}
