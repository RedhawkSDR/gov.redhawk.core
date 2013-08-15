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
