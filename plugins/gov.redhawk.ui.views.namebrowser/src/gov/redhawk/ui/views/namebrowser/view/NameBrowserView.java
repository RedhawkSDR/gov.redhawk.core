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
package gov.redhawk.ui.views.namebrowser.view;

import gov.redhawk.sca.ui.compatibility.CompatibilityFactory;
import gov.redhawk.ui.views.namebrowser.NameBrowserPlugin;
import gov.redhawk.ui.views.namebrowser.view.internal.BindingContentProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.WorkbenchJob;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import CF.DomainManager;
import CF.DomainManagerHelper;

/**
 * @since 1.1
 */
public class NameBrowserView extends ViewPart {
	/** The ID of this view */
	public static final String ID = "gov.redhawk.ui.views.namebrowserview";

	/** The ID of this view */
	public static final String HIST_MEMENTO_ID = NameBrowserView.ID + ".history";

	/** The main object of the view, the tree of applications */
	private TreeViewer treeViewer;

	/** The TreeViewers content provider */
	private BindingContentProvider contentProvider = null;

	/** The list of connected NameServers (also the tree root) */
	private final List<BindingNode> connectedHosts = new ArrayList<BindingNode>();

	/** The list of connected NameServers (also the tree root) */
	private Set<String> nameServerHistory = new HashSet<String>();

	private Combo nameServerField;

	private final IContentProposalProvider proposalProvider = new IContentProposalProvider() {

		public IContentProposal[] getProposals(final String contents, final int position) {
			final List<IContentProposal> list = new ArrayList<IContentProposal>();
			for (final String proposal : NameBrowserView.this.nameServerHistory) {
				final String regexp = ".*" + contents.replace("*", ".*") + ".*";
				final Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
				final Matcher matcher = pattern.matcher(proposal);
				if (proposal.length() >= contents.length() && matcher.matches()) {
					list.add(new ContentProposal(proposal));
				}
			}
			return list.toArray(new IContentProposal[list.size()]);
		}
	};

	@Override
	public void createPartControl(final Composite parent) {

		final GridLayout gridLayout = new GridLayout(3, false);
		parent.setLayout(gridLayout);

		final Label nameServerLabel = new Label(parent, SWT.NONE);
		nameServerLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 1, 1));
		nameServerLabel.setText("Name Server:");

		this.nameServerField = new Combo(parent, SWT.BORDER);
		final ComboContentAdapter controlAdapter = new ComboContentAdapter();
		final ContentProposalAdapter contentProposalAdapter = CompatibilityFactory.createContentProposalAdapter(this.nameServerField,
		        controlAdapter,
		        this.proposalProvider,
		        null);
		contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		this.nameServerField.setToolTipText("The CORBA URI of the NameServer");
		this.nameServerField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.nameServerField.setText("");
		this.nameServerField.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				final String newRef = NameBrowserView.this.nameServerField.getText().trim();
				if ("".equals(newRef)) {
					return;
				}
				addConnection(newRef);
			}
		});

		final Button button = new Button(parent, SWT.PUSH);
		button.setImage(NameBrowserPlugin.getDefault().getImageRegistry().get(NameBrowserPlugin.CONNECT));
		button.setToolTipText("Connect to the specified host");
		button.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final String newRef = NameBrowserView.this.nameServerField.getText().trim();
				addConnection(newRef);
			}
		});

		this.treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.VIRTUAL);
		this.treeViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 3, 1)); // SUPPRESS CHECKSTYLE MagicNumber

		getSite().setSelectionProvider(this.treeViewer);
		this.contentProvider = new BindingContentProvider();
		this.treeViewer.setContentProvider(this.contentProvider);
		this.treeViewer.setLabelProvider(new WorkbenchLabelProvider());
		this.treeViewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(final Viewer viewer, final Object e1, final Object e2) {
				if (e1 instanceof BindingNode && e2 instanceof BindingNode) {
					final BindingNode b1 = (BindingNode) e1;
					final BindingNode b2 = (BindingNode) e2;
					if (b1.getType() != b2.getType()) {
						switch (b1.getType()) {
						case CONTEXT:
							return -1;
						case OBJECT:
							switch (b2.getType()) {
							case CONTEXT:
								return 1;
							default:
								break;
							}
							break;
						case ROOT:
							return -1;
						default:
							break;
						}
					}
				}
				return super.compare(viewer, e1, e2);
			}
		});
		this.treeViewer.setInput(this.connectedHosts);

		// Setup right click menu
		final MenuManager manager = new MenuManager();
		final Menu menu = manager.createContextMenu(this.treeViewer.getTree());
		this.treeViewer.getTree().setMenu(menu);
		getSite().registerContextMenu(NameBrowserView.ID, manager, this.treeViewer);

		for (final String nameServer : this.nameServerHistory) {
			this.nameServerField.add(nameServer);
		}

	}

	@Override
	public void setFocus() {
		this.treeViewer.getControl().setFocus();
	}

	/**
	 * This method connects to the selected Host to retrieve the name listing
	 * 
	 * @param newRef the corba ref of the host to connect to
	 * @param monitor the monitor for job tracking
	 * @param refresh true to refresh the tree after connecting
	 */
	private void connect(final String newRef) {
		// Create the job to connect and get the name list
		final Job connectJob = new Job("Connect to Name Server") {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {

				final BindingNode newRootNode = new BindingNode(newRef);
				try {
					newRootNode.connect();
				} catch (final Exception e) {
					newRootNode.dispose();
					return new Status(IStatus.ERROR, NameBrowserPlugin.PLUGIN_ID, "Error connecting to the name server, see Error Log for details.", e);
				}
				NameBrowserView.this.connectedHosts.add(newRootNode);

				final WorkbenchJob job = new WorkbenchJob(getSite().getShell().getDisplay(), "Adding Corba Name Service") {

					@Override
					public IStatus runInUIThread(final IProgressMonitor monitor) {
						NameBrowserView.this.treeViewer.refresh(NameBrowserView.this.connectedHosts);
						return Status.OK_STATUS;
					}
				};
				job.schedule();
				return Status.OK_STATUS;
			}
		};
		connectJob.setUser(true);
		connectJob.setPriority(Job.LONG);
		connectJob.schedule(10); // SUPPRESS CHECKSTYLE MagicNumber
	}

	/**
	 * This method disconnects from the specified NameServer
	 * 
	 * @param host the NameServer to disconnect from
	 */
	public void disconnect(final BindingNode node) {
		node.dispose();
		this.connectedHosts.remove(node.getRoot());

		final WorkbenchJob job = new WorkbenchJob(getSite().getShell().getDisplay(), "Destroy Orb Job") {

			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				NameBrowserView.this.treeViewer.remove(node.getRoot());
				return Status.OK_STATUS;
			}

		};
		job.schedule();
	}

	@Override
	public void init(final IViewSite site, final IMemento memento) throws PartInitException {
		super.init(site, memento);
		if (memento != null) {
			final String nameHistoryString = memento.getString(NameBrowserView.HIST_MEMENTO_ID);
			if (nameHistoryString != null) {
				final String[] values = nameHistoryString.split(",");
				this.nameServerHistory = new HashSet<String>(Arrays.asList(values));
			}
		}
	}

	@Override
	public void init(final IViewSite site) throws PartInitException {
		this.nameServerHistory = new HashSet<String>();
		super.init(site);
	}

	@Override
	public void saveState(final IMemento memento) {
		final StringBuffer sb = new StringBuffer();
		for (final String nameServer : this.nameServerHistory) {
			sb.append(nameServer);
			sb.append(',');
		}

		memento.putString(NameBrowserView.HIST_MEMENTO_ID, sb.toString());
		super.saveState(memento);
	}

	private void addConnection(final String initRef) {
		String newRef = this.nameServerField.getText().trim();
		if (!(newRef.startsWith("IOR:") || newRef.startsWith("corbaloc::") || newRef.startsWith("corbaname::"))) {
			newRef = "corbaname::" + newRef;
		}

		// No need to reconnect to the same thing
		for (final BindingNode node : this.connectedHosts) {
			if (node.getHost().equals(newRef)) {
				return;
			}
		}

		connect(newRef);
		this.nameServerHistory.add(newRef);
		final List<String> tmpList = new ArrayList<String>(this.nameServerHistory);
		Collections.sort(tmpList);
		this.nameServerField.setItems(tmpList.toArray(new String[tmpList.size()]));
	}

	/**
	 * This will refresh the displayed NameService(s).
	 * 
	 * @param node the node to refresh, or null for all of them
	 */
	public void refresh(final BindingNode... nodes) {
		if (nodes == null || nodes.length == 0) {
			for (final BindingNode node : this.connectedHosts) {
				node.clearContents();
			}
			this.treeViewer.refresh();
		} else {
			for (final BindingNode node : nodes) {
				node.clearContents();
				this.treeViewer.refresh(node);
			}
		}

	}

	/**
	 * This will a naming object from the NameService. This does nothing for naming contexts.
	 * 
	 * @param node the node to unbind
	 */
	public void unbind(final List<BindingNode> unbindingList) {
		final Set<BindingNode> nodes = new HashSet<BindingNode>();
		for (final BindingNode node : unbindingList) {
			// You can remove only a context, doesn't work for an object
			if (node.getType() == BindingNode.Type.OBJECT) {
				final NamingContextExt rootContext = node.getNamingContext();
				try {
					// Unbind the name
					rootContext.unbind(rootContext.to_name(node.getPath()));
				} catch (final UserException e) {
					// PASS Ignore unbind exceptions
				} catch (final SystemException e) {
					// PASS Ignore unbind exceptions
				}
				nodes.add(node.getParent());
			}
		}
		final WorkbenchJob job = new WorkbenchJob(getSite().getShell().getDisplay(), "Unbind Refresh") {

			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				refresh(nodes.toArray(new BindingNode[nodes.size()]));
				return Status.OK_STATUS;
			}
		};
		job.schedule();

	}

	/**
	 * This will remove a naming context node from the NameService. This does nothing for naming objects.
	 * 
	 * @param node the node to remove
	 */
	public void removeContext(final List<BindingNode> removeList) {
		final Set<BindingNode> nodes = new HashSet<BindingNode>();
		for (final BindingNode node : removeList) {
			// Get the path to the node and narrow to the context
			final String path = node.getPath();

			// You can remove only a context, doesn't work for an object
			if (node.getType() == BindingNode.Type.CONTEXT) {
				final NamingContextExt rootContext = node.getNamingContext();

				try {
					final org.omg.CORBA.Object obj = rootContext.resolve_str(path);
					final NamingContextExt context = NamingContextExtHelper.narrow(obj);

					// Recursively remove all the children from this node
					removeChildren(context, path + "/");
					// Destroy the context
					context.destroy();
				} catch (final UserException e) {
					// PASS - this was probably removed before
				} catch (final SystemException e) {
					// PASS - this was probably removed before
				}

				// Unbind the name
				try {
					rootContext.unbind(rootContext.to_name(path));
				} catch (final NotFound e) {
					// PASS - this was probably removed before
				} catch (final CannotProceed e) {
					// PASS - this was probably removed before
				} catch (final InvalidName e) {
					// PASS - this was probably removed before
				}

				// Refresh the list for this host
				nodes.add(node.getRoot());
			}
		}

		final WorkbenchJob job = new WorkbenchJob(getSite().getShell().getDisplay(), "Refresh") {

			@Override
			public IStatus runInUIThread(final IProgressMonitor monitor) {
				refresh(nodes.toArray(new BindingNode[nodes.size()]));
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	private void removeChildren(final NamingContextExt context, final String path) {
		final BindingListHolder bl = new BindingListHolder();
		final BindingIteratorHolder bi = new BindingIteratorHolder();
		// Get a list of everything in this context
		try {
			context.list(-1, bl, bi);
		} catch (final SystemException e) {
			// PASS Skip all errors
		}

		// Remove everything in this context
		if (bl.value != null) {
			for (final Binding b : bl.value) {
				final String newPath = path + b.binding_name[0].id;

				// Recursively unbind/delete context types
				// Only need to unbind object types
				if (b.binding_type == BindingType.ncontext) {
					try {
						// Resolve the naming context
						final org.omg.CORBA.Object obj = context.resolve(b.binding_name);
						final NamingContextExt c = NamingContextExtHelper.narrow(obj);

						// Remove all its children
						removeChildren(c, newPath + "/");

						// Destroy the context
						c.destroy();
					} catch (final UserException e) {
						// PASS 
					} catch (final SystemException e) {
						// PASS 
					}
				}

				// Unbind the name
				try {
					context.unbind(b.binding_name);
				} catch (final UserException e) {
					// PASS 
				} catch (final SystemException e) {
					// PASS
				}
			}
		}
	}

	public static boolean isDomainManager(final BindingNode node) {
		// You can remove only a context, doesn't work for an object
		if (node.getType() == BindingNode.Type.OBJECT) {
			final NamingContextExt rootContext = node.getNamingContext();
			try {
				// Get the path to the node and narrow to the context
				final String path = node.getPath();
				final org.omg.CORBA.Object obj = rootContext.resolve_str(path);
				final DomainManager man = DomainManagerHelper.narrow(obj);

				return man != null;
			} catch (final UserException e) {
				// PASS 
			} catch (final Exception e) {
				// PASS
			}

		}
		return false;
	}

}
