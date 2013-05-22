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
package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.ui.FlasherJob;
import gov.redhawk.sca.waveform.controlpanel.WaveformControlPanelPlugin;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * An intermediary binding context to decorate a control in order to show the user local and server value changes. 
 * <p>
 * Example usage:
 * <br/>
 * <code>
	DataBindingContext context = new EMFDataBindingContext();

	Text text= ...;
	ScaSimpleProperty property= ...;
//  This is the way it would be done directly
//	context.bindValue(SWTObservables.observeText(text, SWT.Modify), EMFObservables.observeValue(property, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE));

	ScaSimplePropertyControl control = new ScaSimplePropertyControl(property, text);
	ISWTObservableValue textObservable = WidgetProperties.text(new int[] {SWT.FocusOut, SWT.DefaultSelection}).observe(text);
	context.bindValue(textObservable, control.getModel());
	context.bindValue(SWTObservables.observeText(text, SWT.Modify), control.getEditingObserable());
	context.bindValue(control.getTarget(), EMFObservables.observeValue(property, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE));
	</code>
	</p>
 */
public class ScaSimplePropertyControl {

	public static final String FLASH_ID = "FLASH_ID";
	private final WritableValue localValue;
	private final WritableValue serverValue;
	private final WritableValue editingValue = new WritableValue();

	private final ScaSimpleProperty property;
	private final FlasherJob job;
	private boolean editing = false;
	private boolean ignoreSet = false;

	public ScaSimplePropertyControl(final Control control, final ScaSimpleProperty property) {
		this.property = property;
		final ScaSimpleProperty simpleProperty = property;
		final EAttribute proxyFeature = EcoreUtil.copy(ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE);
		proxyFeature.setEType(simpleProperty.getDefinition().getType().toDataType());
		this.localValue = new WritableValue(null, proxyFeature);
		this.serverValue = new WritableValue(null, proxyFeature);

		this.job = new FlasherJob(control);

		this.localValue.addValueChangeListener(new IValueChangeListener() {

			public void handleValueChange(final ValueChangeEvent event) {
				if (!ScaSimplePropertyControl.this.ignoreSet) {
					ScaSimplePropertyControl.this.editing = false;
					try {
						setValue(ScaSimplePropertyControl.this.property, event.getObservableValue().getValue());
					} catch (final PartialConfiguration e) {
						StatusManager.getManager().handle(new Status(IStatus.ERROR, WaveformControlPanelPlugin.PLUGIN_ID, "Property Configure failed.", e),
						        StatusManager.LOG | StatusManager.SHOW);
					} catch (final InvalidConfiguration e) {
						StatusManager.getManager().handle(new Status(IStatus.ERROR, WaveformControlPanelPlugin.PLUGIN_ID, "Property Configure failed.", e),
						        StatusManager.LOG | StatusManager.SHOW);
					}
					control.setBackground(control.getDisplay().getSystemColor(SWT.COLOR_CYAN));
				}
			}

		});
		this.serverValue.addValueChangeListener(new IValueChangeListener() {

			//don't flash the first time the control's value is set
			private boolean initialSet = true;

			public void handleValueChange(final ValueChangeEvent event) {
				if (!ScaSimplePropertyControl.this.editing) {
					if (!this.initialSet) {
						startFlash();
					}
					this.initialSet = false;
					ScaSimplePropertyControl.this.ignoreSet = true;
					ScaSimplePropertyControl.this.localValue.setValue(event.getObservableValue().getValue());
					ScaSimplePropertyControl.this.ignoreSet = false;
				}
			}
		});
		this.editingValue.addValueChangeListener(new IValueChangeListener() {

			public void handleValueChange(final ValueChangeEvent event) {
				if (!ScaSimplePropertyControl.this.ignoreSet) {
					ScaSimplePropertyControl.this.editing = !event.getObservableValue().getValue().equals(ScaSimplePropertyControl.this.localValue.getValue());
				}
			}
		});
	}

	private void startFlash() {
		this.job.reset();
	}

	private void setValue(final ScaSimpleProperty property, final Object value) throws PartialConfiguration, InvalidConfiguration {
		property.setRemoteValue(AnyUtils.toAny(value, property.getDefinition().getType().getLiteral()));
	}

	public IObservableValue getModel() {
		return this.localValue;
	}

	public IObservableValue getTarget() {
		return this.serverValue;
	}

	public IObservableValue getEditingObserable() {
		return this.editingValue;
	}

}
