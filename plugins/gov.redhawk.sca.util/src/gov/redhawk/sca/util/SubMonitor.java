/************************ 
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
package gov.redhawk.sca.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * A class with the same functionality of {@link org.eclipse.core.runtime.SubMonitor}, but with a handy function to
 * give back work you realized you won't perform. See {@link #notWorked(int)}.
 */
public class SubMonitor implements IProgressMonitorWithBlocking {
	
	/**
	 * Minimum number of ticks to allocate when calling beginTask on an unknown IProgressMonitor.
	 * Pick a number that is big enough such that, no matter where progress is being displayed,
	 * the user would be unlikely to notice if progress were to be reported with higher accuracy.
	 */
	private static final int MINIMUM_RESOLUTION = 1000;

	/**
	 * The RootInfo holds information about the root progress monitor. A SubMonitor and
	 * its active descendents share the same RootInfo.
	 */
	private static final class RootInfo {
		private final IProgressMonitor root;

		/**
		 * Remembers the last task name. Prevents us from setting the same task name multiple
		 * times in a row.
		 */
		private String taskName = null;

		/**
		 * Remembers the last subtask name. Prevents the SubMonitor from setting the same
		 * subtask string more than once in a row.
		 */
		private String subTask = null;

		/**
		 * Creates a RootInfo struct that delegates to the given progress 
		 * monitor. 
		 * 
		 * @param root progress monitor to delegate to
		 */
		public RootInfo(IProgressMonitor root) {
			this.root = root;
		}

		public boolean isCanceled() {
			return root.isCanceled();
		}

		public void setCanceled(boolean value) {
			root.setCanceled(value);
		}

		public void setTaskName(String taskName) {
			if (eq(taskName, this.taskName)) {
				return;
			}
			this.taskName = taskName;
			root.setTaskName(taskName);
		}

		public void subTask(String name) {
			if (eq(subTask, name)) {
				return;
			}

			this.subTask = name;
			root.subTask(name);
		}

		public void worked(int i) {
			root.worked(i);
		}

		public void clearBlocked() {
			if (root instanceof IProgressMonitorWithBlocking)
				((IProgressMonitorWithBlocking) root).clearBlocked();
		}

		public void setBlocked(IStatus reason) {
			if (root instanceof IProgressMonitorWithBlocking)
				((IProgressMonitorWithBlocking) root).setBlocked(reason);
		}

	}

	/**
	 * Total number of ticks that this progress monitor is permitted to consume
	 * from the root.
	 */
	private int totalParent;

	/**
	 * Number of ticks that this progress monitor has already reported in the root.
	 */
	private int usedForParent = 0;

	/**
	 * Number of ticks that have been consumed by this instance's children.
	 */
	private double usedForChildren = 0.0;

	/**
	 * Number of ticks allocated for this instance's children. This is the total number
	 * of ticks that may be passed into worked(int) or newChild(int). 
	 */
	private int totalForChildren;

	/**
	 * Children created by newChild will be completed automatically the next time
	 * the parent progress monitor is touched. This points to the last incomplete child 
	 * created with newChild.
	 */
	private IProgressMonitor lastSubMonitor = null;

	/**
	 * Used to communicate with the root of this progress monitor tree
	 */
	private final RootInfo root;

	/**
	 * A bitwise combination of the SUPPRESS_* flags.
	 */
	private final int flags;

	/**
	 * May be passed as a flag to newChild. Indicates that the calls
	 * to subTask on the child should be ignored. Without this flag,
	 * calling subTask on the child will result in a call to subTask
	 * on its parent.
	 */
	public static final int SUPPRESS_SUBTASK = 0x0001;

	/**
	 * May be passed as a flag to newChild. Indicates that strings
	 * passed into beginTask should be ignored. If this flag is
	 * specified, then the progress monitor instance will accept null 
	 * as the first argument to beginTask. Without this flag, any 
	 * string passed to beginTask will result in a call to
	 * setTaskName on the parent.
	 */
	public static final int SUPPRESS_BEGINTASK = 0x0002;

	/**
	 * May be passed as a flag to newChild. Indicates that strings
	 * passed into setTaskName should be ignored. If this string
	 * is omitted, then a call to setTaskName on the child will 
	 * result in a call to setTaskName on the parent.
	 */
	public static final int SUPPRESS_SETTASKNAME = 0x0004;

	/**
	 * May be passed as a flag to newChild. Indicates that strings
	 * passed to setTaskName, subTask, and beginTask should all be ignored.
	 */
	public static final int SUPPRESS_ALL_LABELS = SUPPRESS_SETTASKNAME | SUPPRESS_BEGINTASK | SUPPRESS_SUBTASK;

	/**
	 * May be passed as a flag to newChild. Indicates that strings
	 * passed to setTaskName, subTask, and beginTask should all be propagated
	 * to the parent.
	 */
	public static final int SUPPRESS_NONE = 0;

	/**
	 * Creates a new SubMonitor that will report its progress via
	 * the given RootInfo.
	 * @param rootInfo the root of this progress monitor tree
	 * @param totalWork total work to perform on the given progress monitor
	 * @param availableToChildren number of ticks allocated for this instance's children
	 * @param flags a bitwise combination of the SUPPRESS_* constants
	 */
	private SubMonitor(RootInfo rootInfo, int totalWork, int availableToChildren, int flags) {
		root = rootInfo;
		totalParent = (totalWork > 0) ? totalWork : 0;
		this.totalForChildren = availableToChildren;
		this.flags = flags;
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#convert(IProgressMonitor)
	 */
	public static SubMonitor convert(IProgressMonitor monitor) {
		return convert(monitor, "", 0); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#convert(IProgressMonitor, int)
	 */
	public static SubMonitor convert(IProgressMonitor monitor, int work) {
		return convert(monitor, "", work); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#convert(IProgressMonitor, String, int)
	 */
	public static SubMonitor convert(IProgressMonitor monitor, String taskName, int work) {
		if (monitor == null)
			monitor = new NullProgressMonitor();

		// Optimization: if the given monitor already a SubMonitor, no conversion is necessary
		if (monitor instanceof SubMonitor) {
			monitor.beginTask(taskName, work);
			return (SubMonitor) monitor;
		}

		monitor.beginTask(taskName, MINIMUM_RESOLUTION);
		return new SubMonitor(new RootInfo(monitor), MINIMUM_RESOLUTION, work, SUPPRESS_NONE);
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#setWorkRemaining(int)
	 */
	public SubMonitor setWorkRemaining(int workRemaining) {
		// Ensure we don't try to allocate negative ticks
		workRemaining = Math.max(0, workRemaining);

		// Ensure we don't cause division by zero
		if (totalForChildren > 0 && totalParent > usedForParent) {
			// Note: We want the following value to remain invariant after this method returns
			double remainForParent = totalParent * (1.0d - (usedForChildren / totalForChildren));
			usedForChildren = (workRemaining * (1.0d - remainForParent / (totalParent - usedForParent)));
		} else
			usedForChildren = 0.0d;

		totalParent = totalParent - usedForParent;
		usedForParent = 0;
		totalForChildren = workRemaining;
		return this;
	}

	/**
	 * Consumes the given number of child ticks, given as a double. Must only 
	 * be called if the monitor is in floating-point mode.
	 *  
	 * @param ticks the number of ticks to consume
	 * @return ticks the number of ticks to be consumed from parent
	 */
	private int consume(double ticks) {
		if (totalParent == 0 || totalForChildren == 0) // this monitor has no available work to report
			return 0;

		usedForChildren += ticks;

		if (usedForChildren > totalForChildren)
			usedForChildren = totalForChildren;
		else if (usedForChildren < 0.0)
			usedForChildren = 0.0;

		int parentPosition = (int) (totalParent * usedForChildren / totalForChildren);
		int delta = parentPosition - usedForParent;

		usedForParent = parentPosition;
		return delta;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#isCanceled()
	 */
	public boolean isCanceled() {
		return root.isCanceled();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#setTaskName(java.lang.String)
	 */
	public void setTaskName(String name) {
		if ((flags & SUPPRESS_SETTASKNAME) == 0)
			root.setTaskName(name);
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#beginTask(String, int)
	 */
	public void beginTask(String name, int totalWork) {
		if ((flags & SUPPRESS_BEGINTASK) == 0 && name != null)
			root.setTaskName(name);
		setWorkRemaining(totalWork);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#done()
	 */
	public void done() {
		cleanupActiveChild();
		int delta = totalParent - usedForParent;
		if (delta > 0)
			root.worked(delta);

		totalParent = 0;
		usedForParent = 0;
		totalForChildren = 0;
		usedForChildren = 0.0d;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#internalWorked(double)
	 */
	public void internalWorked(double work) {
		cleanupActiveChild();

		int delta = consume((work > 0.0d) ? work : 0.0d);
		if (delta != 0)
			root.worked(delta);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#subTask(java.lang.String)
	 */
	public void subTask(String name) {
		if ((flags & SUPPRESS_SUBTASK) == 0)
			root.subTask(name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#worked(int)
	 */
	public void worked(int work) {
		internalWorked(work);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitor#setCanceled(boolean)
	 */
	public void setCanceled(boolean b) {
		root.setCanceled(b);
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#newChild(int)
	 */
	public SubMonitor newChild(int totalWork) {
		return newChild(totalWork, SUPPRESS_BEGINTASK);
	}

	/**
	 * @see org.eclipse.core.runtime.SubMonitor#newChild(int, int)
	 */
	public SubMonitor newChild(int totalWork, int suppressFlags) {
		double totalWorkDouble = (totalWork > 0) ? totalWork : 0.0d;
		totalWorkDouble = Math.min(totalWorkDouble, totalForChildren - usedForChildren);
		cleanupActiveChild();

		// Compute the flags for the child. We want the net effect to be as though the child is
		// delegating to its parent, even though it is actually talking directly to the root.
		// This means that we need to compute the flags such that - even if a label isn't 
		// suppressed by the child - if that same label would have been suppressed when the
		// child delegated to its parent, the child must explicitly suppress the label. 
		int childFlags = SUPPRESS_NONE;

		if ((flags & SUPPRESS_SETTASKNAME) != 0) {
			// If the parent was ignoring labels passed to setTaskName, then the child will ignore
			// labels passed to either beginTask or setTaskName - since both delegate to setTaskName
			// on the parent
			childFlags |= SUPPRESS_SETTASKNAME | SUPPRESS_BEGINTASK;
		}

		if ((flags & SUPPRESS_SUBTASK) != 0) {
			// If the parent was suppressing labels passed to subTask, so will the child.
			childFlags |= SUPPRESS_SUBTASK;
		}

		// Note: the SUPPRESS_BEGINTASK flag does not affect the child since there
		// is no method on the child that would delegate to beginTask on the parent.
		childFlags |= suppressFlags;

		SubMonitor result = new SubMonitor(root, consume(totalWorkDouble), (int) totalWorkDouble, childFlags);
		lastSubMonitor = result;
		return result;
	}

	private void cleanupActiveChild() {
		if (lastSubMonitor == null)
			return;

		IProgressMonitor child = lastSubMonitor;
		lastSubMonitor = null;
		child.done();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitorWithBlocking#clearBlocked()
	 */
	public void clearBlocked() {
		root.clearBlocked();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IProgressMonitorWithBlocking#setBlocked(org.eclipse.core.runtime.IStatus)
	 */
	public void setBlocked(IStatus reason) {
		root.setBlocked(reason);
	}

	protected static boolean eq(Object o1, Object o2) {
		if (o1 == null)
			return (o2 == null);
		if (o2 == null)
			return false;
		return o1.equals(o2);
	}

	/**
	 * Reduces the work remaining for this SubMonitor. The remaining space on the progress monitor is redistributed
	 * into the remaining number of ticks.
	 * 
	 * @param work The amount of work that will no longer be reported via {@link #worked(int)} and should be
	 * redistributed amongst remaining ticks.
	 */
	public void notWorked(int work) {
		setWorkRemaining(totalForChildren - work);
	}
	
}
