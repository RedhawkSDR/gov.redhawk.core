package gov.redhawk.scd.ui.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ViewerNotification;

import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.provider.PortsItemProvider;

public class PortsEditorPortsItemProvider extends PortsItemProvider {

	public PortsEditorPortsItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == ScdPackage.Literals.PORTS__GROUP) {
			// Override notification just for changes to the group feature to handle the way bi-directional ports are
			// handled in the editor. Both the content--new or removed children--and the labels--in/out/bidir--must be
			// updated.
			updateChildren(notification);
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
			return;
		}
		super.notifyChanged(notification);
	}

}
