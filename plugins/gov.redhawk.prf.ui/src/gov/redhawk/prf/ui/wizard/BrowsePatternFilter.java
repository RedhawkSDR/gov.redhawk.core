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
 * 
 * This code was borrowed from org.eclipse.ui.dialogs.PatternFilter since
 * it prohibits extending required functions.
 * 
 */
package gov.redhawk.prf.ui.wizard;

import gov.redhawk.sca.properties.Category;
import gov.redhawk.sca.properties.IPropertiesProvider;

import java.util.HashSet;
import java.util.Set;

import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.internal.misc.StringMatcher;
import org.eclipse.ui.progress.PendingUpdateAdapter;

/**
 * A filter used in conjunction with <code>FilteredTree</code>. In order to
 * determine if a node should be filtered it uses the content and label provider
 * of the tree to do pattern matching on its children. This causes the entire
 * tree structure to be realized. Note that the label provider must implement
 * ILabelProvider.
 * 
 * @see org.eclipse.ui.dialogs.FilteredTree
 * @since 4.0
 */
@SuppressWarnings("restriction")
public class BrowsePatternFilter extends PatternFilter {

	/**
	 * The string pattern matcher used for this pattern filter.  
	 */
	private StringMatcher myMatcher;

	private final Set<Category> categories = new HashSet<Category>();

	/**
	 * The pattern string for which this filter should select 
	 * elements in the viewer.
	 * 
	 * @param patternString
	 */
	@Override
	public void setPattern(final String patternString) {
		super.setPattern(patternString);
		// these 2 strings allow the PatternFilter to be extended in
		// 3.3 - https://bugs.eclipse.org/bugs/show_bug.cgi?id=186404
		if ("org.eclipse.ui.keys.optimization.true".equals(patternString)) { //$NON-NLS-1$
			return;
		} else if ("org.eclipse.ui.keys.optimization.false".equals(patternString)) { //$NON-NLS-1$
			return;
		}
		if (patternString == null || patternString.isEmpty()) {
			this.myMatcher = null;
		} else {
			final String pattern = patternString + "*"; //$NON-NLS-1$
			this.myMatcher = new StringMatcher(pattern, true, false);
		}
	}

	/**
	 * Answers whether the given String matches the pattern.
	 * 
	 * @param string the String to test
	 * 
	 * @return whether the string matches the pattern
	 */
	private boolean myMatch(final String string) {
		if (this.myMatcher == null) {
			return true;
		}
		return this.myMatcher.match(string);
	}

	/**
	 * Answers whether the given element in the given viewer matches
	 * the filter pattern.  This is a default implementation that will 
	 * show a leaf element in the tree based on whether the provided  
	 * filter text matches the text of the given element's text, or that 
	 * of it's children (if the element has any).  
	 * 
	 * Subclasses may override this method.
	 * 
	 * @param viewer the tree viewer in which the element resides
	 * @param element the element in the tree to check for a match
	 * 
	 * @return true if the element matches the filter pattern
	 */
	@Override
	public boolean isElementVisible(final Viewer viewer, final Object element) {
		String myName = "";
		if (element instanceof PendingUpdateAdapter || element instanceof IPropertiesProvider) {
			return true;
		} else if (element instanceof Category) {
			final Category cat = (Category) element;
			this.categories.add(cat);
			myName = cat.getName();
			return isParentMatch(viewer, element) || wordMatches(myName);
		} else if (element instanceof Struct || element instanceof Simple || element instanceof SimpleSequence) {
			for (final Category catagory : this.categories) {
				if (catagory.containsProperty((EObject) element)) {
					myName = catagory.getName();
				}
			}
			return isLeafMatch(viewer, element) || wordMatches(myName);
		}
		return false;
	}

	/**
	 * Return whether or not if any of the words in text satisfy the
	 * match criteria.
	 * 
	 * @param text the text to match
	 * @return boolean <code>true</code> if one of the words in text 
	 * 					satisfies the match criteria.
	 */
	@Override
	protected boolean wordMatches(final String text) {
		if (text == null) {
			return false;
		}

		//If the whole text matches we are all set
		if (myMatch(text)) {
			return true;
		}

		// Otherwise check if any of the words of the text matches
		final String[] words = text.split("\\s");
		for (int i = 0; i < words.length; i++) {
			final String word = words[i];
			if (myMatch(word)) {
				return true;
			}
		}

		return false;
	}
}
