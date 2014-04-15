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
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.ui.ConnectPortWizard;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.PluginUtil;

import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.util.CorbaUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.omg.CORBA.SystemException;

import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

/**
 * 
 */
public class ConnectPortHandler extends AbstractHandler implements IHandler {

	private static class ConnectJob extends Job {
		private final org.omg.CORBA.Object target;
		private final ScaUsesPort usesPort;

		private String connectionID;

		public ConnectJob(final ScaUsesPort usesPort, final org.omg.CORBA.Object target, String connectionID) {
			super("Connecting");
			this.usesPort = usesPort;
			this.target = target;
			this.connectionID = connectionID;
			setPriority(Job.LONG);
			setUser(true);
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			monitor.beginTask("Connecting " + usesPort.getName(), IProgressMonitor.UNKNOWN);
			try {
				CorbaUtils.invoke(new Callable<Object>() {

					@Override
					public Object call() throws Exception {
						try {
							usesPort.connectPort(target, connectionID);
						} catch (final InvalidPort e) {
							throw new CoreException(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect " + e.msg, e));
						} catch (final OccupiedPort e) {
							throw new CoreException(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect", e));
						} catch (final SystemException e) {
							throw new CoreException(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect", e));
						}
						return null;
					}

				}, monitor);
			} catch (CoreException e) {
				return e.getStatus();
			} catch (InterruptedException e) {
				return Status.CANCEL_STATUS;
			}

			return Status.OK_STATUS;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		Shell shell = HandlerUtil.getActiveShell(event);
		if (sel instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) sel;
			ScaUsesPort usesPort = null;
			CorbaObjWrapper< ? > target = null;
			for (final Object obj : ss.toArray()) {
				
				if (PluginUtil.adapt(ScaUsesPort.class, obj, true) != null) {
					usesPort = PluginUtil.adapt(ScaUsesPort.class, obj, true);
				} else if (PluginUtil.adapt(ScaProvidesPort.class, obj, true) != null) {
					target = PluginUtil.adapt(ScaProvidesPort.class, obj, true);
				} else if (PluginUtil.adapt(ScaComponent.class, obj, true) != null) {
					target = PluginUtil.adapt(ScaComponent.class, obj, true);
				}
			}
			if (usesPort != null && target != null) {
				new ConnectJob(usesPort, target.getCorbaObj(), ConnectPortWizard.generateDefaultConnectionID()).schedule();
			} else {
				ConnectPortWizard wizard = new ConnectPortWizard();
				wizard.setSource(usesPort);
				wizard.setTarget(target);
				WizardDialog dialog = new WizardDialog(shell, wizard);
				dialog.open();
			}
		}
		return null;
	}

	@Override
	public void setEnabled(final Object evaluationContext) {
		if ((evaluationContext != null) && (evaluationContext instanceof IEvaluationContext)) {
			final IEvaluationContext context = (IEvaluationContext) evaluationContext;
			final Object sel = context.getVariable("selection");
			if (sel instanceof IStructuredSelection) {
				final IStructuredSelection ss = (IStructuredSelection) sel;
				ScaUsesPort usesPort = null;
				EObject target = null;
				for (final Object obj : ss.toArray()) {
					if (PluginUtil.adapt(ScaUsesPort.class, obj, true) != null) {
						usesPort = PluginUtil.adapt(ScaUsesPort.class, true);
					} else if (PluginUtil.adapt(ScaProvidesPort.class, obj, true) != null) {
						target = PluginUtil.adapt(ScaProvidesPort.class, true);
					} else if (PluginUtil.adapt(ScaComponent.class, obj, true) != null) {
						target = PluginUtil.adapt(ScaComponent.class, true);
					}
				}
				if (usesPort != null && usesPort.getProfileObj() != null && usesPort.getProfileObj().getInterface() != null) {
					if (target instanceof ScaProvidesPort) {
						final ScaProvidesPort providesPort = (ScaProvidesPort) target;
						setBaseEnabled(usesPort.getProfileObj().getInterface().isInstance(providesPort.getProfileObj().getInterface()));
					} else if (target instanceof ScaComponent) {
						final ScaComponent component = (ScaComponent) target;
						for (final SupportsInterface i : component.getProfileObj().getDescriptor().getComponent().getComponentFeatures().getSupportsInterface()) {
							if (usesPort.getProfileObj().getInterface().isInstance(i.getInterface())) {
								setBaseEnabled(true);
								return;
							}
						}
						setBaseEnabled(false);
					} else if (target == null) {
						setBaseEnabled(true);
					} else {
						setBaseEnabled(false);
					}
				} else {
					setBaseEnabled(true);
				}
			} else {
				super.setEnabled(evaluationContext);
			}
		}
	}

}
