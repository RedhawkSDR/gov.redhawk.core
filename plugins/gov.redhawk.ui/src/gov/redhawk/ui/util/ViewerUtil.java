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
package gov.redhawk.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * @since 8.0
 */
public class ViewerUtil {

	/**
	 * Utility class, cannot instantiate
	 */
	private ViewerUtil() {
	}

	private static class EmptySelection implements ISelection {

		@Override
		public boolean isEmpty() {
			return false;
		}

	}

	/**
	 * Returns the equivalent selection in a viewer for the given items. If an item cannot be found in the viewer,
	 * it is not included in the returned selection.
	 *
	 * @param viewer the viewer
	 * @param items the items to select
	 * @return selection
	 */
	public static ISelection itemsToSelection(final Viewer viewer, final Collection< ? > items) {
		if (viewer instanceof TreeViewer) {
			return ViewerUtil.itemsToSelection((TreeViewer) viewer, items);
		}
		return new EmptySelection();
	}

	/**
	 * Returns the equivalent selection in a tree viewer for the given items. If an item cannot be found via the viewer's,
	 * content provider, it is not included in the returned selection.
	 *
	 * @param viewer the tree viewer
	 * @param items the items to select
	 * @return selection
	 */
	public static ISelection itemsToSelection(final TreeViewer viewer, final Collection< ? > items) {
		final List<Object> targets = new ArrayList<Object>();
		// A TreeViewer's content provider always has to be an ITreeContentProvider
		final ITreeContentProvider provider = (ITreeContentProvider) viewer.getContentProvider();
		final Object input = viewer.getInput();
		for (final Object object : items) {
			final Object target = ViewerUtil.findItemInProvider(provider, input, object);
			if (target != null) {
				targets.add(target);
			}
		}
		return new StructuredSelection(targets.toArray());
	}

	private static Object findItemInProvider(ITreeContentProvider provider, Object input, Object object) {
		if (ViewerUtil.checkEquivalent(input,  object)) {
			return input;
		}
		for (final Object child : provider.getChildren(input)) {
			final Object target = findItemInProvider(provider, child, object);
			if (target != null) {
				return target;
			}
		}
		return null;
	}
	
	private static boolean checkEquivalent(Object first, Object second) {
		if (first == null) {
			return (second == null);
		} else {
			return AdapterFactoryEditingDomain.unwrap(first).equals(AdapterFactoryEditingDomain.unwrap(second));
		}
	}
}
