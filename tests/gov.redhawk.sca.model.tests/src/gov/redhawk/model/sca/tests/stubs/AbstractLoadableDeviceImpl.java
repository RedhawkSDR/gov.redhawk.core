package gov.redhawk.model.sca.tests.stubs;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.FileSystem;
import CF.InvalidFileName;
import CF.LoadableDeviceOperations;
import CF.DevicePackage.InvalidState;
import CF.LoadableDevicePackage.InvalidLoadKind;
import CF.LoadableDevicePackage.LoadFail;
import CF.LoadableDevicePackage.LoadType;

public class AbstractLoadableDeviceImpl extends AbstractDeviceImpl implements
		LoadableDeviceOperations {

	public AbstractLoadableDeviceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractLoadableDeviceImpl(String compId, String compName, ORB orb,
			POA poa) throws ServantNotActive, WrongPolicy {
		super(compId, compName, orb, poa);
		// TODO Auto-generated constructor stub
	}

	public void load(FileSystem fs, String fileName, LoadType loadKind)
			throws InvalidState, InvalidLoadKind, InvalidFileName, LoadFail {
		// TODO Auto-generated method stub

	}

	public void unload(String fileName) throws InvalidState, InvalidFileName {
		// TODO Auto-generated method stub

	}

}
