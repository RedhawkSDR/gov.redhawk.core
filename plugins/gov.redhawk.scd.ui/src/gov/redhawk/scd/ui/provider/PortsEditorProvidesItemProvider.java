package gov.redhawk.scd.ui.provider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import gov.redhawk.scd.ui.util.PortsUtil;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.provider.ProvidesItemProvider;

public class PortsEditorProvidesItemProvider extends ProvidesItemProvider {

	public PortsEditorProvidesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index) {
		if (feature == ScdPackage.Literals.ABSTRACT_PORT__REP_ID) {
			CompoundCommand command = new CompoundCommand();
			command.append(super.createSetCommand(domain, owner, feature, value, index));
			command.appendIfCanExecute(PortsUtil.createReplaceInterfaceCommand(domain, (AbstractPort) owner, (String) value));
			return command.unwrap();
		}
		return super.createSetCommand(domain, owner, feature, value, index);
	}

}
