package gov.redhawk.sca.ui.internal;

import gov.redhawk.sca.ScaPlugin;

import org.eclipse.core.runtime.Status;
import org.eclipse.rwt.service.ISettingStore;
import org.eclipse.rwt.service.ISettingStoreFactory;

public class ScaSettingStoreFactory  implements ISettingStoreFactory {

	@Override
	public ISettingStore createSettingStore(String storeId) {
		 ISettingStore result = new ScaSettingStore();
		 System.err.println("CREATING CUSTOM SETTINGSTORE");
		    try {
		      result.loadById( storeId );
		    } catch( Exception exception ) {
		      ScaPlugin.getDefault().getLog().log(new Status(Status.ERROR, ScaPlugin.PLUGIN_ID, "Unable to create SettingStoreFactory"));
		    }
		    return result;
	}

}
