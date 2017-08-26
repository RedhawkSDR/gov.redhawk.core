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
package gov.redhawk.core.graphiti.ui.util;

import mil.jpeojtrs.sca.partitioning.DomainFinderType;
import mil.jpeojtrs.sca.partitioning.FindByStub;

/**
 * @since 7.0
 */
public class FindByStubUtil {

	private FindByStubUtil() {
	}

	/**
	 * Return true if findByStub represents findBy domain manager
	 * @param findByStub
	 * @return
	 */
	public static boolean isFindByStubDomainManager(FindByStub findByStub) {
		if (findByStub.getDomainFinder() != null && findByStub.getDomainFinder().getType().equals(DomainFinderType.DOMAINMANAGER)) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if findByStub represents findBy file manager
	 * @param findByStub
	 * @return
	 */
	public static boolean isFindByStubFileManager(FindByStub findByStub) {
		if (findByStub.getDomainFinder() != null && findByStub.getDomainFinder().getType().equals(DomainFinderType.FILEMANAGER)) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if findByStub represents findBy event channel
	 * @param findByStub
	 * @return
	 */
	public static boolean isFindByStubEventChannel(FindByStub findByStub) {
		if (findByStub.getDomainFinder() != null && findByStub.getDomainFinder().getType().equals(DomainFinderType.EVENTCHANNEL)) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if findByStub represents findBy service
	 * @param findByStub
	 * @return
	 */
	public static boolean isFindByStubService(FindByStub findByStub) {
		if (findByStub.getDomainFinder() != null && (findByStub.getDomainFinder().getType().equals(DomainFinderType.SERVICENAME)
			|| findByStub.getDomainFinder().getType().equals(DomainFinderType.SERVICETYPE))) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if findByStub represents findBy name
	 * @param findByStub
	 * @return
	 */
	public static boolean isFindByStubName(FindByStub findByStub) {
		if (findByStub.getNamingService() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Return the expected IDL interface of a given FindBy, or null if it can be any type
	 * @param findByStub
	 * @return
	 */
	public static String getFindByStubRepId(FindByStub findByStub) {
		if (findByStub.getNamingService() != null) {
			// Naming service lookup can return any object type
			return null;
		} else if (findByStub.getDomainFinder() != null) {
			switch (findByStub.getDomainFinder().getType()) {
			case DOMAINMANAGER:
				return CF.DomainManagerHelper.id();
			case EVENTCHANNEL:
				return org.omg.CosEventChannelAdmin.EventChannelHelper.id();
			case FILEMANAGER:
				return CF.FileManagerHelper.id();
			case SERVICETYPE:
				return findByStub.getDomainFinder().getName();
			case LOG: // Not supported
			case NAMINGSERVICE: // Any object type
			case SERVICENAME: // Any object type
				break;
			default:
				break;
			}
		}
		return null;
	}
}
