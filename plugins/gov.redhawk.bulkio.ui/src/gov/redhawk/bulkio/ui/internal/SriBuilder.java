package gov.redhawk.bulkio.ui.internal;

import org.eclipse.jdt.annotation.NonNull;

public class SriBuilder {
	private String name;
	private Object value;
	private SriWrapper wrapper;

	public SriBuilder(@NonNull String name, @NonNull Object value, SriWrapper wrapper) {
		this.name = name;
		this.value = value;
		this.wrapper = wrapper;
	}

	public SriWrapper getParent() {
		return this.wrapper;
	}

	@NonNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NonNull
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
//		System.out.println("SRI Obj: " + obj);
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SriBuilder)) {
			return false;
		}
		SriBuilder other = (SriBuilder) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
