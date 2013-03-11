/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.waveformviewer.ui.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.omg.CORBA.Object;

public class ViewWaveform extends AbstractHandler implements IHandler {
	public static final String ID = "gov.redhawk.ui.waveform.commands.view";

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		//		System.out.println("Checking Handlers");
		//		IPortHandlerRegistry reg = gov.redhawk.ui.port.Activator.getPortHandlerRegistry();
		//		System.out.println("got: " + reg.getPortHandlers().length);
		//		IScaDomainConnection[] conns = ScaPlugin.getDefault().getDomainConnectionManager().getConnections();
		//
		//		// Loop through all domain managers, looking for running applications
		//		for (final IScaDomainConnection conn : conns) {
		//			System.out.println("Checking dom: " + conn.getDomainManager());
		//			if (conn.getDomainManager() != null) {
		//				ScaWaveformsContainer waveforms = conn.getDomainManager().getWaveforms();
		//				EList<ScaWaveform> waveList = waveforms.getWaveforms();
		//				for (int i = 0; i < waveList.size(); ++i) {
		//					ScaWaveform wave = waveList.get(i);
		//					EList<ScaComponent> comps = wave.getComponents().getComponents();
		//					for (int j = 0; j < comps.size(); ++j) {
		//						ScaComponent comp = comps.get(j);

		//						SoftPkg spd = comp.getSpd();
		//						Descriptor desc = spd.getDescriptor();
		//						SoftwareComponent cmp = desc.getComponent();
		//						ComponentFeatures features = cmp.getComponentFeatures();
		//						EList<Uses> uses = features.getPorts().getUses();
		//						for (int k = 0; k < uses.size(); ++k) {
		//							Uses port = uses.get(k);
		//							System.out.println("Port: " + port.toString());
		//							for (PortTypeContainer cont : port.getPortType()) {
		//								System.out.println("Port type: " + cont.getType().getName());
		//							}
		//							try {
		//								Object usesPort = comp.getPort(port.getUsesName());
		//								System.out.println("IOR: " + usesPort.toString());
		//								if (usesPort != null) {
		//									Port p = PortHelper.narrow(usesPort);
		//									for (IPortHandler var : reg.getPortHandlers()) {
		//										var.connect(p, "SF");
		//									}
		//								}
		//							} catch (UnknownPort e) {
		//								// TODO Auto-generated catch block
		//								e.printStackTrace();
		//							}
		//						}
		//
		//						try {
		//							Object port1 = comp.getPort("sample_out");
		//							if (port1 != null) {
		//								Port p1 = PortHelper.narrow(port1);
		//								System.out.println("port1: " + p1);
		//								for (IPortHandler var : reg.getPortHandlers()) {
		//									System.out.println("PortHandler: " + var);
		//									var.connect(p1, "SF");
		//								}
		//							}
		//							Object port2 = comp.getPort("sample_out_s");
		//							Object port3 = comp.getPort("sample_in");
		//							System.out.println("p1: " + port1);
		//							System.out.println("p2: " + port2);
		//							System.out.println("p3: " + port3);
		//						} catch (UnknownPort e1) {
		//							// TODO Auto-generated catch block
		//							e1.printStackTrace();
		//						}
		//						try {
		//							System.out.println("IOR: " + comp.getPort("sample_out"));
		//						} catch (UnknownPort e) {
		//							// TODO Auto-generated catch block
		//							e.printStackTrace();
		//						}
		//						System.out.println("compId: " + comp.identifier());
		//					}
		//				}
		//			}
		//		}

		// TODO Auto-generated method stub
		return null;
	}

}
