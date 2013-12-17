package gov.redhawk.frontend.internal;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.edit.utils.TunerUtils;
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryContentProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import CF.DeviceHelper;

public class FrontEndContentProvider extends ScaModelAdapterFactoryContentProvider implements ICommonContentProvider {

	public FrontEndContentProvider() {
		super(FrontEndContentProvider.createAdapterFactory());
	}

	protected static AdapterFactory createAdapterFactory() {
		return new FrontendItemProviderAdapterFactory();
	}

	@Override
	public Object[] getElements(Object object) {
		return super.getElements(object);
	}

	@Override
	public Object[] getChildren(Object object) {
		if (object instanceof ScaDevice< ? >) {
			ScaDevice< ? > device = (ScaDevice< ? >) object;
			if (device._is_a(DeviceHelper.id())) {
				return TunerUtils.INSTANCE.getTunerContainer(device);
			}
		}
		if (object instanceof TunerContainer) {
			TunerContainer tuners = (TunerContainer) object;
			return TunerUtils.INSTANCE.getChildren(tuners);
		}
		return super.getChildren(object);
	}

	@Override
	public boolean hasChildren(Object object) {
		if (object instanceof TunerContainer) {
			return true;
		}
		return super.hasChildren(object);
	}

	@Override
	public void restoreState(IMemento aMemento) {

	}

	@Override
	public void saveState(IMemento aMemento) {

	}

	@Override
	public void init(ICommonContentExtensionSite aConfig) {

	}

}
