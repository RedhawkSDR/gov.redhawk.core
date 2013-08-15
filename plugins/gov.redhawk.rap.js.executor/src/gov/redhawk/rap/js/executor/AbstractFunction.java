/**
 * 
 */
package gov.redhawk.rap.js.executor;

import org.eclipse.core.runtime.ListenerList;

/**
 *
 */
public abstract class AbstractFunction implements IFunction, IDisposeListener {

	private final ListenerList listenerList = new ListenerList();
	private boolean disposed = false;

	@Override
	public void dispose() {
		if (this.disposed) {
			return;
		}
		this.disposed = true;
		final Object[] listeners = this.listenerList.getListeners();
		this.listenerList.clear();
		final DisposeEvent event = new DisposeEvent(this);
		for (final Object obj : listeners) {
			if (obj instanceof IDisposeListener) {
				((IDisposeListener) obj).disposed(event);
			}
		}
	}

	@Override
	public boolean isDisposed() {
		return this.disposed;
	}

	@Override
	public void disposed(DisposeEvent event) {
		dispose();
	}

	@Override
	public void addDisposeListener(IDisposeListener listener) {
		this.listenerList.add(listener);
	}

	@Override
	public void removeDisposeListener(IDisposeListener listener) {
		this.listenerList.remove(listener);
	}

}
