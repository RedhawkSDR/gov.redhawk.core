package gov.redhawk.rap.js.executor;

public class DisposeEvent {
	private final Object source;

	public DisposeEvent(Object source) {
		super();
		this.source = source;
	}

	public Object getSource() {
		return this.source;
	}

}
