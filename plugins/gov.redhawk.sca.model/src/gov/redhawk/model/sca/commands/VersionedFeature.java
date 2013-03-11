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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaPackage;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 14.0
 * 
 */
public class VersionedFeature {

	public class Transaction {
		private CompoundCommand compoundCommand = new CompoundCommand();
		private int oldRevision;

		private Transaction(int oldRevision) {
			this.oldRevision = oldRevision;
		}

		public void addCommand(Command command) {
			this.compoundCommand.append(command);
		}

		public void commit() {
			if (compoundCommand.isEmpty() || !compoundCommand.canExecute()) {
				return;
			}
			if (oldRevision == currentRevision.get()) {
				ScaModelCommand.execute(context, new CommandWrapper("Version checked command", "", compoundCommand) {
					
					@Override
					protected boolean prepare() {
						if (context instanceof IDisposable) {
							if (((IDisposable) context).isDisposed()) {
								return false;
							}
						}
					    return super.prepare();
					}

					@Override
					public void execute() {
						if (context instanceof IDisposable) {
							if (((IDisposable) context).isDisposed()) {
								return;
							}
						}
						if (currentRevision.compareAndSet(oldRevision, oldRevision + 1)) {
							ignoreNotification = true;
							try {
								super.execute();
							} finally {
								ignoreNotification = false;
							}
						}
					}
				});
			}
		}

		public void append(Command command) {
	        addCommand(command);
        }
	}
	private boolean ignoreNotification;
	
	private final Adapter versionListener = new AdapterImpl() {
		public void notifyChanged(org.eclipse.emf.common.notify.Notification msg) {
			if (msg.getFeature() == ScaPackage.Literals.IDISPOSABLE__DISPOSED) {
				if (msg.getNotifier() instanceof Notifier) {
					((Notifier) msg.getNotifier()).eAdapters().remove(this);
					return;
				}
			} else if (!ignoreNotification && msg.getFeature() == feature && !msg.isTouch()) {  
				currentRevision.incrementAndGet();
			}
		}
	};

	private final AtomicInteger currentRevision = new AtomicInteger();
	private final EObject context;
	private EStructuralFeature feature;

	public VersionedFeature(EObject context, EStructuralFeature feature) {
		this.context = context;
		this.feature = feature;
		this.context.eAdapters().add(versionListener);
	}

	public Transaction createTransaction() {
		return new Transaction(currentRevision.get());
	}

}
