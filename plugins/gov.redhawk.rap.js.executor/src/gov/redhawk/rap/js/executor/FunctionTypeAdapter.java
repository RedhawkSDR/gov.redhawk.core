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
package gov.redhawk.rap.js.executor;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FunctionTypeAdapter implements JsonSerializer<IFunction> {

	@Override
	public JsonElement serialize(IFunction src, Type typeOfSrc, JsonSerializationContext context) {
		final IFunctionRef ref = ServiceUtil.createAnnonymousFunction(src);
		return new JsonPrimitive(ref.getCodeString(), true);
	}

}
