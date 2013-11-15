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
package gov.redhawk.ui.views.domainbrowser.view;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryLabelProvider;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.compatibility.CompatibilityFactory;
import gov.redhawk.sca.ui.properties.AbstractPropertyEditingSupport;
import gov.redhawk.sca.ui.properties.ScaPropertiesContentProvider;
import gov.redhawk.ui.views.domainbrowser.Activator;
import gov.redhawk.ui.views.domainbrowser.extend.ScaItemProviderAdapterFactoryExtended;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dmd.DomainManagerConfiguration;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeColumnViewerLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;


public class DomainBrowserView extends ViewPart {

	/** The ID of this view */
	public static final String ID = "gov.redhawk.ui.views.domainbrowserview";

	private ComboViewer domainCombo;
	private TreeViewer domainTree;

	private boolean showAdvancedProperties = false;
	
	private final IContentProposalProvider proposalProvider = new IContentProposalProvider() {

		@Override
		public IContentProposal[] getProposals(final String contents, final int position) {
			final List<IContentProposal> list = new ArrayList<IContentProposal>();
			final Set<String> domains = new HashSet<String>(Arrays.asList(DomainBrowserView.this.domainCombo.getCombo().getItems()));

			for (final String proposal : domains) {
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

	// #2498 - Creating the Adapter so that the Combo list will update when new domains are added.
	private AdapterImpl domainChangeAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof ScaDomainManagerRegistry) {
				switch (msg.getFeatureID(ScaDomainManagerRegistry.class)) {
				case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
					switch (msg.getEventType()) {

					case Notification.ADD:
						final ScaDomainManager newDomain = (ScaDomainManager) msg.getNewValue();
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								domainCombo.add(newDomain);
								}
						});
						break;

					case Notification.REMOVE:
						final ScaDomainManager domainRemoved = (ScaDomainManager) msg.getOldValue();
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								domainCombo.remove(domainRemoved);
							}
						});
						break;

					default:
						// Do nothing if not an Add or a Remove
						break;
					}
					break;

				default:
					// Do nothing
					break;
				}
			}
		}
	};
	
	private static final TransactionalEditingDomain EDITING_DOMAIN = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID);

	private ScaDomainManager domainManager;
	private ComposedAdapterFactory adapterFactory;

	private static Resource resource = DomainBrowserView.EDITING_DOMAIN.getResourceSet().createResource(URI.createURI("mem:///" + DomainBrowserView.ID));

	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new GridLayout(3, false));

		final Label label = new Label(parent, SWT.NONE);
		label.setText("Domain:");
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));

		this.domainCombo = new ComboViewer(parent, SWT.BORDER);
		this.domainCombo.getCombo().setToolTipText("Valid input: \n <DOMAIN_NAME> ie: REDHAWK_DEV \n <DOMAIN_NAME>@<HOST:PORT> ie: REDHAWK_DEV@localhost:2809");
		this.domainCombo.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		this.domainCombo.setContentProvider(ArrayContentProvider.getInstance());
		
		// Creating a comparator so that the objects within the ComboViewer will be sorted correctly.
		this.domainCombo.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				String s1 = null, s2 = null;
				
				if (e1 instanceof String) { 
					s1 = (String) e1;
				}
				
				if (e1 instanceof ScaDomainManager) {
					ScaDomainManager dom = ((ScaDomainManager) e1);
					String[] tokens = dom.getConnectionProperties().get(ScaDomainManager.NAMING_SERVICE_PROP).split("::");
					s1 = ((ScaDomainManager) e1).getName() + "@" + tokens[1];
				}
				
				if (e2 instanceof String) { 
					s2 = (String) e2;
				}
				
				if (e2 instanceof ScaDomainManager) {
					ScaDomainManager dom = ((ScaDomainManager) e2);
					String[] tokens = dom.getConnectionProperties().get(ScaDomainManager.NAMING_SERVICE_PROP).split("::");
					s2 = dom.getName() + "@" + tokens[1];
				}
				
				if (s1 != null && s2 != null) {
					return s1.compareToIgnoreCase(s2);
				} else {
					return 0;
				}
			}
		});
		
		this.domainCombo.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof String) {
					return (String) element;
				} else if (element instanceof ScaDomainManager) {
					String[] tokens;
					ScaDomainManager domain = (ScaDomainManager) element;
					tokens = domain.getConnectionProperties().get(ScaDomainManager.NAMING_SERVICE_PROP).split("::");
					if (tokens.length == 2) {
						return domain.getName() + "@" + tokens[1];  // Is this a safe way of doing this?
					} else {
						return domain.getName();
					}
				}
				return super.getText(element);
			}
		});

		// If there are any domains within the domain registry we want to set them onto our combo list
		this.domainCombo.setInput(ScaPlugin.getDefault().getDomainManagerRegistry(domainCombo.getControl().getDisplay()).getDomains());

		// If the domain register logs any new domains or removes any we want to add / remove them from the combo
		ScaPlugin.getDefault().getDomainManagerRegistry(domainCombo.getControl().getDisplay()).eAdapters().add(domainChangeAdapter);

		// Now to make it so that the text box in the combo will have an auto-complete like feel to it.
		final ComboContentAdapter controlAdapter = new ComboContentAdapter();
		final ContentProposalAdapter contentProposalAdapter = CompatibilityFactory.createContentProposalAdapter(this.domainCombo.getCombo(),
				controlAdapter,
				this.proposalProvider,
				null);
		
		contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		
		// We want to connect when there is a selection of when the user hits enter.
		this.domainCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				connect();
			}
		});

		createDomainViewer(parent);

		final MenuManager menuManager = new MenuManager();
		final Menu menu = menuManager.createContextMenu(this.domainTree.getTree());
		this.domainTree.getTree().setMenu(menu);
		getSite().registerContextMenu(DomainBrowserView.ID + ".popupMenu", menuManager, this.domainTree);
		getSite().setSelectionProvider(this.domainTree);

		final AdvancedPropertiesAction action = new AdvancedPropertiesAction("Advanced Properties", IAction.AS_CHECK_BOX);
		final IActionBars actionBars = getViewSite().getActionBars();
		final IMenuManager dropDownMenu = actionBars.getMenuManager();
		dropDownMenu.add(action);
	}

	public void setDomainAndConnect(String domain)	{
		this.domainCombo.getCombo().setText(domain);
		connect();
	}
	
	private void createDomainViewer(final Composite parent) {
		final Composite treeComposite = new Composite(parent, SWT.NULL);
		final TreeColumnLayout treeLayout = new TreeColumnLayout();
		final Tree tree = new Tree(treeComposite, SWT.MULTI | SWT.BORDER);
		treeComposite.setLayout(treeLayout);
		treeComposite.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, true).create());// SUPPRESS CHECKSTYLE MagicNumber
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(GridDataFactory.fillDefaults().span(1, 3).grab(true, true).create()); // SUPPRESS CHECKSTYLE MagicNumber

		final ScaPropertiesContentProvider contentProvider = new ScaPropertiesContentProvider(getAdapterFactory()) {
			@Override
			public Object[] getChildren(final Object object) {
				final Object result = DomainBrowserUtil.delayedContent(object, DomainBrowserView.this.domainTree);
				if (result != null) {
					return new Object[] {
						result
					};
				}

				return super.getChildren(object);
			}
		};

		this.domainTree = new TreeViewer(tree);
		this.domainTree.setContentProvider(contentProvider);
		this.domainTree.addFilter(new ViewerFilter() {

			@Override
			public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
				if (element instanceof ScaFileStore || element instanceof DomainManagerConfiguration || element instanceof SoftwareAssembly
				        || element instanceof DeviceConfiguration || element instanceof DelegatingWrapperItemProvider) {
					return DomainBrowserView.this.showAdvancedProperties;
				}
				return true;
			}
		});

		final TreeColumnViewerLabelProvider lp = new TreeColumnViewerLabelProvider(new ScaModelAdapterFactoryLabelProvider(this.adapterFactory, this.domainTree) {
			@Override
			public String getColumnText(final Object object, final int columnIndex) {
				switch (columnIndex) {
				case 0:
					return getText(object);
				default:
					return super.getColumnText(object, columnIndex);
				}
			}

			@Override
			public Image getColumnImage(final Object object, final int columnIndex) {
				switch (columnIndex) {
				case 0:
					return getImage(object);
				default:
					return super.getColumnImage(object, columnIndex);
				}
			}
		});
		
		TreeColumn column = new TreeColumn(tree, SWT.NONE);
		column.setText("Object");
		treeLayout.setColumnData(column, new ColumnWeightData(2, 200, true));
		TreeViewerColumn viewerColumn = new TreeViewerColumn(this.domainTree, column);
		viewerColumn.setLabelProvider(lp);

		column = new TreeColumn(tree, SWT.NONE);
		column.setText("Value");
		treeLayout.setColumnData(column, new ColumnWeightData(3, true));
		viewerColumn = new TreeViewerColumn(this.domainTree, column);
		viewerColumn.setLabelProvider(lp);
		viewerColumn.setEditingSupport(new AbstractPropertyEditingSupport(this.domainTree, contentProvider) {

			@Override
			protected Object getPropertyID(final Object object) {
				if (object instanceof ScaSimpleProperty) {
					return ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE.getName();
				} else if (object instanceof ScaSimpleSequenceProperty) {
					return ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES.getName();
				} else if (object instanceof ScaStructProperty) {
					return ScaPackage.Literals.SCA_STRUCT_PROPERTY__SIMPLES.getName();
				} else if (object instanceof ScaStructSequenceProperty) {
					return ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS.getName();
				}
				return null;
			}

		});

		this.domainTree.getTree().setEnabled(false);
		this.domainTree.setLabelProvider(lp);
		this.domainTree.setSorter(new ViewerSorter());
		
		getSite().setSelectionProvider(this.domainTree);
	}

	private AdapterFactory getAdapterFactory() {
		if (this.adapterFactory == null) {
			this.adapterFactory = new ComposedAdapterFactory();
			this.adapterFactory.addAdapterFactory(new ScaItemProviderAdapterFactoryExtended());
			this.adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			this.adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		}
		return this.adapterFactory;
	}

	@Override
	public void setFocus() {
		if (this.domainTree != null && !this.domainTree.getControl().isDisposed()) {
			this.domainTree.getControl().setFocus();
		}
	}

	private void connect() {
		String domainName = this.domainCombo.getCombo().getText().trim();
				
		
		if (this.domainManager != null) {
			disposeDomainManager();
			
			// It looks like this line is removing the current domain manager from the Editing Domain / contents in a thread safe way
			DomainBrowserView.EDITING_DOMAIN.getCommandStack().execute(new RemoveCommand(DomainBrowserView.EDITING_DOMAIN,
			        DomainBrowserView.resource.getContents(),
			        this.domainManager));
		}
		
		if (domainName != null && domainName != "") {
			String namingService = DomainBrowserUtil.getNamingService(domainName);

			if (namingService == "") {
				namingService = ScaUiPlugin.getDefault().getScaPreferenceStore().getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
			} else {
				domainName = domainName.split("@")[0];
			}

			final ScaDomainManager newDomain = ScaFactory.eINSTANCE.createScaDomainManager();
			newDomain.setName(domainName);
			newDomain.setAutoConnect(true);

			final Map<String, String> connectionProperties = Collections.singletonMap(ScaDomainManager.NAMING_SERVICE_PROP, namingService);
			newDomain.getConnectionProperties().putAll(connectionProperties);

			this.domainManager = newDomain;
			
			boolean nameFound = false;
			String[] domainNamesInCombo = this.domainCombo.getCombo().getItems();
			
			for (String name : domainNamesInCombo) {
				String[] tokens = newDomain.getConnectionProperties().get(ScaDomainManager.NAMING_SERVICE_PROP).split("::");
				if (name.equals(newDomain.getName() + "@" + tokens[1])) {
					nameFound = true;
				}
			}
			
			if (!nameFound) {
				this.domainCombo.add(newDomain);
			}
			
			// Looks like this command is adding the new domain into the Domain Browsers resource
			DomainBrowserView.EDITING_DOMAIN.getCommandStack().execute(new AddCommand(DomainBrowserView.EDITING_DOMAIN,
			        DomainBrowserView.resource.getContents(),
			        newDomain));

			this.connectJob.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(final IJobChangeEvent event) {
					if (DomainBrowserView.this.domainManager != null && DomainBrowserView.this.domainManager.getProfileObj() != null) {
						// TODO: This doesn't seem like it does anything does it?
						final List<ScaWaveformFactory> factories = DomainBrowserView.this.domainManager.fetchWaveformFactories(new NullProgressMonitor());
						factories.toArray();  

						DomainBrowserView.this.postConnectUiJob.schedule(1000);
					} else {
						DomainBrowserView.this.failedConnectUiJob.schedule();
					}
					
					super.done(event);
				}
			});

			this.connectJob.schedule();
		}
	}

	@Override
	public void dispose() {
		if (this.domainManager != null) {
			disposeDomainManager();

			DomainBrowserView.EDITING_DOMAIN.getCommandStack().execute(new RemoveCommand(DomainBrowserView.EDITING_DOMAIN,
			        DomainBrowserView.resource.getContents(),
			        this.domainManager));
		}
		ScaPlugin.getDefault().getDomainManagerRegistry(getSite().getShell().getDisplay()).eAdapters().remove(this.domainChangeAdapter);
		super.dispose();
	}

	private final Job connectJob = new Job("Connecting to Domain") {

		@Override
		public IStatus run(final IProgressMonitor monitor) {
			try {
				DomainBrowserView.this.domainManager.connect(monitor, RefreshDepth.FULL);
			} catch (final DomainConnectionException e) {
				// TODO: This is not causing any sort of pop-up.  It should since it is an ERROR status.
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Problems experienced while connecting to domain "
				        + DomainBrowserView.this.domainManager.getName(), e);
			}
			return Status.OK_STATUS;
		}
	};
	private final UIJob failedConnectUiJob = new UIJob("FailedConnection") {
		{
			setSystem(true);
		}
		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			MessageDialog.openError(getSite().getShell(), "Error Connecting", "Cannot connect to the specified domain.  Please make sure you have specified the domain correctly");
			return Status.OK_STATUS;
		}
	};
	private final UIJob postConnectUiJob = new UIJob("Update") {
		{
			setSystem(true);
		}

		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			if (DomainBrowserView.this.domainManager.isConnected() && DomainBrowserView.this.domainManager.getProfileObj() != null) {
				updateWidgets(true);
				setContentDescription("Domain: " + DomainBrowserView.this.domainManager.getName() + " (" + DomainBrowserView.this.domainManager.getIdentifier()
				        + ")");
				setTitleToolTip("ID: " + DomainBrowserView.this.domainManager.getIdentifier());

				return Status.OK_STATUS;
			} else {
				updateWidgets(false);
				setContentDescription("Error Connecting to Domain");
				setTitleToolTip("Error Connecting to Domain");
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Problems experienced while connecting to domain", null);
			}
		}
	};

	private void updateWidgets(final Boolean connected) {
		if (connected) {
			this.domainTree.setInput(this.domainManager);
			this.domainTree.getTree().setEnabled(true);
		} else {
			this.domainTree.getTree().setEnabled(false);
		}
	}

	public TreeViewer getTreeViewer() {
		return this.domainTree;
	}

	private void disposeDomainManager() {
		if (!this.domainManager.isDisposed()) {
			ScaModelCommand.execute(this.domainManager, new ScaModelCommand() {

				@Override
				public void execute() {
					DomainBrowserView.this.domainManager.dispose();
				}
			});
		}
	}

	private class AdvancedPropertiesAction extends Action {

		public AdvancedPropertiesAction(final String string, final int check) {
			super(string, check);
		}

		@Override
		public void run() {
			DomainBrowserView.this.showAdvancedProperties = this.isChecked();
			DomainBrowserView.this.domainTree.refresh();
		}
	}
}
