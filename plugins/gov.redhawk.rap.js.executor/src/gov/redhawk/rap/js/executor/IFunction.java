package gov.redhawk.rap.js.executor;

import com.google.gson.JsonElement;

public interface IFunction {
	void dispose();

	boolean isDisposed();

	void handle(JsonElement args);

	void addDisposeListener(IDisposeListener listener);

	void removeDisposeListener(IDisposeListener listener);

}
