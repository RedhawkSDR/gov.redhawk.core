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

package gov.redhawk.sca.waveform.controlpanel.editor;

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import gov.redhawk.sca.ui.editors.AbstractScaContentEditor;
import gov.redhawk.sca.waveform.controlpanel.WaveformControlPanelPlugin;
import gov.redhawk.sca.waveform.controlpanel.propertyEditors.PropertyEditor;
import gov.redhawk.sca.waveform.controlpanel.propertyEditors.PropertyEditorFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.progress.UIJob;

/**
 * This class uses an editor input of type IFileStoreEditorInput, while the superclass AbstractContentEditor uses
 * an input of type EObjectEditorInput. To support this, the class MUST override init(), getInput(), getInputType(),
 * and getEditorInput().
 */

public class ScaWaveformControlPanel< T extends EObject > extends AbstractScaContentEditor<EObject> {

	public static final String ID = "gov.redhawk.sca.sad.controlpanel.editorID";

	private static final int MIN_NUM_COLUMNS = 1;
	private static final int MAX_NUM_COLUMNS = 3;
	private static final String RAP_INIT_CLASS = "gov.redhawk.sca.rap.RapInit";
	private static final String RAP_INIT_METHOD = "init";
	private final Adapter disposeListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(gov.redhawk.model.sca.IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				if (msg.getNewBooleanValue()) {
					if (!ScaWaveformControlPanel.this.parent.isDisposed()) {
						if (ScaWaveformControlPanel.this.parent == null) {
							return;
						}
						final UIJob job = new UIJob(ScaWaveformControlPanel.this.parent.getDisplay(), "Close Editor job") {
	
							@Override
							public IStatus runInUIThread(final IProgressMonitor monitor) {
								getSite().getPage().closeEditor(ScaWaveformControlPanel.this, true);
								return Status.OK_STATUS;
							}
						};
						job.schedule();
					}
					if (msg.getNotifier() instanceof Notifier) {
						Notifier n = (Notifier) msg.getNotifier();
						n.eAdapters().remove(this);
					}
				}
				break;
			default:
				break;
			}
		}
	};
	private Composite parent;
	private ScaWaveform waveform;
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Composite body;
	private final List<PropertyEditor> editors = new ArrayList<PropertyEditor>();

	public ScaWaveformControlPanel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(final Composite parent) {
		if (SWT.getPlatform().startsWith("rap")) {
			try {
				final Class< ? > clazz = Class.forName(ScaWaveformControlPanel.RAP_INIT_CLASS);
				final Method m = clazz.getMethod(ScaWaveformControlPanel.RAP_INIT_METHOD, new Class< ? >[] { IEditorSite.class });
				m.invoke(null, getSite());
			} catch (final SecurityException e) {
				WaveformControlPanelPlugin.logError("Unable to instantiate RAP initializaion class", e);
			} catch (final NoSuchMethodException e) {
				WaveformControlPanelPlugin.logError("Unable to instantiate RAP initializaion class", e);
			} catch (final IllegalArgumentException e) {
				WaveformControlPanelPlugin.logError("Unable to instantiate RAP initializaion class", e);
			} catch (final IllegalAccessException e) {
				WaveformControlPanelPlugin.logError("Unable to instantiate RAP initializaion class", e);
			} catch (final InvocationTargetException e) {
				WaveformControlPanelPlugin.logError("Unable to instantiate RAP initializaion class", e);
			} catch (final ClassNotFoundException e) {
				WaveformControlPanelPlugin.logError("Unable to instantiate RAP initializaion class", e);
			}
		}

		this.parent = parent;
		this.toolkit = new FormToolkit(parent.getDisplay());
		this.form = this.toolkit.createScrolledForm(parent);
		this.body = this.form.getBody();
		final ColumnLayout layout = new ColumnLayout();
		layout.minNumColumns = ScaWaveformControlPanel.MIN_NUM_COLUMNS;
		layout.maxNumColumns = ScaWaveformControlPanel.MAX_NUM_COLUMNS;
		this.body.setLayout(layout);
		createControls();
	}

	private void createControls() {
		final EObject input = getInput();

		if (input instanceof ScaWaveform) {
			final ScaWaveform inputWaveform = (ScaWaveform) input;
			try {
				inputWaveform.refresh(null, RefreshDepth.FULL);
			} catch (final InterruptedException e) {
				// PASS
			}
			final String name = (inputWaveform.getName() == null) ? inputWaveform.getIdentifier() : inputWaveform.getName();
			/* Previously there was a bug with GTK on Linux, wherein last word in title is not displayed. Seems to be
			 * fixed now, but leaving the workaround as comment in case the bug re-appears
			 **/
			//			String title = "";
			//			if (SWT.getPlatform().startsWith("gtk")) {
			//				title = "Waveform " + name + " Properties x";
			//			} else {
			//				title = "Waveform " + name + " Properties";
			//			}
			final String title = "Waveform " + name + " Properties";
			this.form.setText(title);

			//ensure assembly controller is first component displayed
			final ScaComponent assemblyController = inputWaveform.getAssemblyController();
			Section compSection = this.toolkit.createSection(this.body, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED
			        | ExpandableComposite.TWISTIE);
			Composite propertyComp = createScaComponentPropertyComp(compSection, assemblyController);
			compSection.setText(assemblyController.getProfileObj().getName());
			compSection.setClient(propertyComp);
			GridDataFactory.fillDefaults().grab(true, false).applyTo(propertyComp);

			final EList<ScaComponent> components = inputWaveform.getComponents();
			for (final Iterator<ScaComponent> it = components.iterator(); it.hasNext();) {
				final ScaComponent comp = it.next();
				if (!comp.equals(assemblyController)) {
					compSection = this.toolkit.createSection(this.body, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED
					        | ExpandableComposite.TWISTIE);
					propertyComp = createScaComponentPropertyComp(compSection, comp);
					compSection.setText(comp.getProfileObj().getName());
					compSection.setClient(propertyComp);
					GridDataFactory.fillDefaults().grab(true, false).applyTo(propertyComp);
				}
			}
		}
	}

	private Composite createScaComponentPropertyComp(final Composite parent, final ScaComponent component) {
		final Composite comp = this.toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));
		final EList<ScaAbstractProperty< ? >> props = component.getProperties();
		for (final Iterator<ScaAbstractProperty< ? >> it = props.iterator(); it.hasNext();) {
			final ScaAbstractProperty< ? > prop = it.next();
			final PropertyEditor editor = PropertyEditorFactory.getPropertyEditor(prop);
			if (editor != null) {
				this.editors.add(editor);
				editor.renderNameValuePair(comp);
				editor.setupBindings();
			}
		}
		return comp;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		if (input instanceof ScaFileStoreEditorInput) {
			this.waveform = (ScaWaveform) ((ScaFileStoreEditorInput) input).getScaObject();
		}
		if (this.waveform != null) {
			if (getInputType() != null && this.waveform != null && !getInputType().isInstance(this.waveform)) {
				throw new IllegalArgumentException("Input must be of type " + getInputType() + " but found " + this.waveform.getClass());
			}
			setSite(site);
			setInput(input);
			this.waveform.eAdapters().add(this.disposeListener);
			setPartName("Waveform " + this.waveform.getName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class<EObject> getInputType() {
		if (this.waveform != null) {
			return (Class<EObject>) this.waveform.getClass();
		}
		return null;
	}

	@Override
	public EObject getInput() {
		return this.waveform;
	}
	
	@Override
	public int hashCode() {
		if (this.getPartName() != null) {
			return getPartName().hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof ScaWaveformControlPanel< ? >) {
			final ScaWaveformControlPanel< ? > other = (ScaWaveformControlPanel< ? >) obj;
			return other.getPartName().equals(this.getPartName());
		}
		return super.equals(obj);
	}

	@Override
	public void dispose() {
		if (this.waveform != null) {
			this.waveform.eAdapters().remove(this.disposeListener);
		}
		if (this.toolkit != null) {
			this.toolkit.dispose();
		}
		if (this.editors != null) {
			for (final PropertyEditor editor : this.editors) {
				editor.dispose();
			}
		}
		super.dispose();
	}

}
