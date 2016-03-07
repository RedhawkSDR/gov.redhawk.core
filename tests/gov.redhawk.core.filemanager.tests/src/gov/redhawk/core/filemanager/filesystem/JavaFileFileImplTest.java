package gov.redhawk.core.filemanager.filesystem;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.junit.Test;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.FileException;
import CF.FileHelper;
import gov.redhawk.sca.util.OrbSession;
import junit.framework.Assert;

public class JavaFileFileImplTest {

	/**
	 * IDE-1536 Ensure closing the file object deactivates it in the POA
	 * @throws IOException
	 * @throws CoreException
	 * @throws WrongPolicy
	 * @throws ServantNotActive
	 * @throws FileException
	 * @throws WrongAdapter
	 * @throws ObjectNotActive
	 */
	@Test
	public void close() throws IOException, ServantNotActive, WrongPolicy, CoreException, FileException, WrongAdapter, ObjectNotActive {
		File file = null;
		try {
			file = File.createTempFile(JavaFileFileImplTest.class.getSimpleName(), ".tmp");
			JavaFileFileImpl fileImpl = new JavaFileFileImpl(file, true);

			OrbSession session = OrbSession.createSession();
			org.omg.CORBA.Object obj = session.getPOA().servant_to_reference(fileImpl);
			CF.File fileObj = FileHelper.narrow(obj);

			// Should be active
			session.getPOA().reference_to_servant(fileObj);

			fileObj.close();

			// Should not be active any more
			try {
				session.getPOA().reference_to_servant(fileObj);
			} catch (ObjectNotActive e) {
				return;
			}
			Assert.fail("Object should no longer be active");
		} finally {
			if (file != null && file.exists()) {
				file.delete();
			}
		}
	}

}
