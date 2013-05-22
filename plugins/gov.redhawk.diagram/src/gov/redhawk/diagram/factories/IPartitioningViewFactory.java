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
package gov.redhawk.diagram.factories;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public interface IPartitioningViewFactory extends IViewProvider {

	Edge basicCreateConnectInterface(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint);

	View basicCreateUsesPortStub(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint);

	View basicCreateProvidesPortStub(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint);

	View basicCreateComponentSupportedInterfaceStub(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint);

	View basicCreateComponentPlacement(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint);

}
