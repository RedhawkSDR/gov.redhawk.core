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
package gov.redhawk.sca.ui.editors;

import java.io.IOException;

import org.eclipse.ui.IEditorInput;

/**
 * Used to check an object for a given content type
 * @since 2.2
 */
public interface IScaContentDescriber {
	/**
	 * Description result constant, indicating that it was not possible
	 * to determine whether the contents were valid for
	 * the intended content type.
	 * 
	 * @see #describe
	 */
	public static final int INDETERMINATE = 1;
	/**
	 * Description result constant, indicating the contents are invalid for
	 * the intended content type.
	 * 
	 * @see #describe
	 */
	public static final int INVALID = 0;
	/**
	 * Description result constant, indicating the contents are valid for
	 * the intended content type.
	 * 
	 * @see #describe
	 */
	public static final int VALID = 2;

	/**
	 * @param contents the contents to be examined
	 *
	 * @return one of the following:<ul>
	 * <li>{@link #VALID}</li>
	 * <li>{@link #INVALID}</li>
	 * <li>{@link #INDETERMINATE}</li>
	 * </ul>
	 * @throws IOException if an I/O error occurs
	 */
	public int describe(Object contents) throws IOException;

	/**
	 * Creates a appropriate editor input for the given contents
	 * @param contents The contents to wrap in the editor input
	 * @return Editor input, or null if invalid contents
	 */
	public IEditorInput getEditorInput(Object contents);
}
