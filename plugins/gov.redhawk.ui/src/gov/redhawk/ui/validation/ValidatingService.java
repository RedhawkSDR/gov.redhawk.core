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
package gov.redhawk.ui.validation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.ui.forms.IMessageManager;

public interface ValidatingService {

	/**
	 * @since 2.0
	 */
	public static final KeyMap KEY_MAP = new KeyMap();

	static class KeyMap {
		private final Map<Integer, Integer> keymap = new HashMap<Integer, Integer>();

		protected KeyMap() {
			this.keymap.put(Integer.valueOf(IStatus.ERROR), Integer.valueOf(IMessageProvider.ERROR));
			this.keymap.put(Integer.valueOf(IStatus.WARNING), Integer.valueOf(IMessageProvider.WARNING));
			this.keymap.put(Integer.valueOf(IStatus.INFO), Integer.valueOf(IMessageProvider.INFORMATION));
			this.keymap.put(Integer.valueOf(IStatus.OK), Integer.valueOf(IMessageProvider.NONE));
			this.keymap.put(Integer.valueOf(IStatus.CANCEL), Integer.valueOf(IMessageProvider.INFORMATION));
		}

		public int getMessageProviderKey(final int iStatusKey) {
			return this.keymap.get(Integer.valueOf(iStatusKey)).intValue();
		}
	}

	void analyzeDiagnostic(DataBindingContext dataBindingContext, Diagnostic diagnostic, IMessageManager messageManager);
}
