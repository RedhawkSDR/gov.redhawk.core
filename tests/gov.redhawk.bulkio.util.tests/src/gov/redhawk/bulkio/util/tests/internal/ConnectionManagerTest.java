package gov.redhawk.bulkio.util.tests.internal;

import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.bulkio.util.IBulkIOPortConnectionManager;
import gov.redhawk.sca.util.OrbSession;
import junit.framework.Assert;

import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.CORBA.Object;
import org.omg.PortableServer.POA;

import BULKIO.PrecisionUTCTime;
import BULKIO.dataDoubleOperations;
import CF.PortOperations;
import CF.PortPOATie;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

public class ConnectionManagerTest {

	private static OrbSession session;

	private static class MyPort implements PortOperations {

		public int connections = 0;

		@Override
		public void connectPort(Object connection, String connectionId) throws InvalidPort, OccupiedPort {
			connections++;
		}

		@Override
		public void disconnectPort(String connectionId) throws InvalidPort {
			connections--;
		}

	}

	private static class TestDataDoublePort extends AbstractBulkIOPort implements dataDoubleOperations {
		@Override
		public void pushPacket(double[] data, PrecisionUTCTime T, boolean EOS, String streamID) {
		}
	}

	@BeforeClass
	public static void init() throws Exception {
		session = OrbSession.createSession();
	}

	private MyPort port;
	private Object portRef;

	@Before
	public void initPort() throws Exception {
		POA poa = session.getPOA();
		port = new MyPort();
		portRef = poa.servant_to_reference(new PortPOATie(port));
	}

	@After
	public void disposePort() throws Exception {
		portRef._release();
		portRef = null;
		port = null;
	}

	@AfterClass
	public static void dispose() {
		session.dispose();
	}

	@Test
	public void testConnectionManagerConnectDisconnect() throws CoreException {
		IBulkIOPortConnectionManager connectionManager = BulkIOUtilActivator.getBulkIOPortConnectionManager();
		TestDataDoublePort receivePort = new TestDataDoublePort();
		connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		TestDataDoublePort receivePort2 = new TestDataDoublePort();
		connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort2);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		TestDataDoublePort receivePort3 = new TestDataDoublePort();
		connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort3);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		connectionManager.disconnect(portRef.toString(), BulkIOType.DOUBLE, receivePort);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		connectionManager.disconnect(portRef.toString(), BulkIOType.DOUBLE, receivePort2);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		connectionManager.disconnect(portRef.toString(), BulkIOType.DOUBLE, receivePort3);
		Assert.assertEquals("No connections expected", 0, port.connections);
	}

	@Test
	public void testConnectionManagerConnectDisconnectWithConnectionID() throws CoreException {
		IBulkIOPortConnectionManager connectionManager = BulkIOUtilActivator.getBulkIOPortConnectionManager();
		TestDataDoublePort receivePort = new TestDataDoublePort();
		String connectionID = "connection";
		String connection1 = connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort, connectionID);
		Assert.assertEquals(connectionID, connection1);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		TestDataDoublePort receivePort2 = new TestDataDoublePort();
		String connection2 = connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort2, connectionID);
		Assert.assertEquals(connectionID, connection2);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		TestDataDoublePort receivePort3 = new TestDataDoublePort();
		String connection3 = connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort3, connectionID);
		Assert.assertEquals(connectionID, connection3);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		connectionManager.disconnect(portRef.toString(), BulkIOType.DOUBLE, receivePort, connectionID);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		connectionManager.disconnect(portRef.toString(), BulkIOType.DOUBLE, receivePort2, connectionID);
		Assert.assertEquals("Only one connection expected", 1, port.connections);

		connectionManager.disconnect(portRef.toString(), BulkIOType.DOUBLE, receivePort3, connectionID);
		Assert.assertEquals("No connections expected", 0, port.connections);
	}

	@Test
	public void testGetExternalPort() throws CoreException {
		IBulkIOPortConnectionManager connectionManager = BulkIOUtilActivator.getBulkIOPortConnectionManager();

		Assert.assertNull(connectionManager.getExternalPort(null, null));
		Assert.assertNull(connectionManager.getExternalPort(null, BulkIOType.DOUBLE));
		Assert.assertNull(connectionManager.getExternalPort(portRef.toString(), BulkIOType.DOUBLE));

		TestDataDoublePort receivePort = new TestDataDoublePort();
		connectionManager.connect(portRef.toString(), BulkIOType.DOUBLE, receivePort);
		Assert.assertEquals("Only one connection expected", 1, port.connections);
		Assert.assertNotNull(connectionManager.getExternalPort(portRef.toString(), BulkIOType.DOUBLE));
	}

}
