/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.jacorb.naming.Name;
import org.omg.CosNaming.NamingContextPackage.InvalidName;

/**
 * Validates a string representing a naming service reference. Can be a corbaname, corbaloc or IOR.
 * @since 9.0
 */
public class NamingServiceValidator implements IValidator {

	private final Pattern hexString = Pattern.compile("^[0-9a-fA-F]+$");
	private final Pattern ipv4String = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
	private final Pattern ipv6Chars = Pattern.compile("^[:0-9a-fA-F]+$");
	private final Pattern hostNameString = Pattern.compile("^[0-9a-zA-Z][-.0-9a-zA-Z]*$");
	private final Pattern versionString = Pattern.compile("^[0-9]+\\.[0-9]+$");

	@Override
	public IStatus validate(Object value) {
		if (value == null) {
			return ValidationStatus.error("Invalid reference. Valid URLs start with 'corbaname:', 'corbaloc:', or 'IOR:'");
		}

		String namingService = (String) value;
		if (namingService.startsWith("corbaname:")) {
			return validateCorbaname(namingService.substring(10));
		} else if (namingService.startsWith("corbaloc:")) {
			return validateCorbaloc(namingService.substring(9));
		} else if (namingService.startsWith("IOR:")) {
			return validateIOR(namingService.substring(4));
		} else {
			return ValidationStatus.error("Invalid reference. Valid URLs start with 'corbaname:', 'corbaloc:', or 'IOR:'");
		}
	}

	private IStatus validateCorbaname(String corbaname) {
		int index = corbaname.indexOf("#");
		if (index == -1) {
			return validateCorbaloc(corbaname);
		} else {
			IStatus status = validateCorbaloc(corbaname.substring(0, index));
			if (!status.isOK()) {
				return status;
			}
			return validateStringName(corbaname.substring(index + 1));
		}
	}

	/**
	 * <code>[objAddr ","]* objAddr ["/" keyString]</code>
	 * @param corbaloc
	 * @return
	 */
	private IStatus validateCorbaloc(String corbaloc) {
		String objAddrList, keyString;
		int index = corbaloc.indexOf('/');
		if (index == -1) {
			objAddrList = corbaloc;
			keyString = null;
		} else {
			objAddrList = corbaloc.substring(0, index);
			keyString = corbaloc.substring(index + 1);
		}

		for (String objAddr : objAddrList.split(",")) {
			IStatus status = validateObjAddr(objAddr);
			if (!status.isOK()) {
				return status;
			}
		}

		if (keyString != null) {
			IStatus status = validateKeyString(keyString);
			if (!status.isOK()) {
				return status;
			}
		}

		return ValidationStatus.ok();
	}

	/**
	 * <ul>
	 * <li><code>"rir:"</code></li>
	 * <li><code>"iiop:" iiopAddr</code></li>
	 * <li><code>":" iiopAddr</code></li>
	 * </ul>
	 * @param objAddr
	 * @return
	 */
	private IStatus validateObjAddr(String objAddr) {
		if (objAddr.isEmpty()) {
			return ValidationStatus.error("Expected a protcol. Valid protocols are 'iiop:', ':', and 'rir:'.");
		}

		if (objAddr.startsWith("rir:")) {
			if (objAddr.length() == 4) {
				return ValidationStatus.ok();
			}
			return ValidationStatus.error("Invalid text after rir protocol '" + objAddr.substring(4) + "'");
		} else if (objAddr.startsWith("iiop:")) {
			return validateIiopAddr(objAddr.substring(5));
		} else if (objAddr.startsWith(":")) {
			return validateIiopAddr(objAddr.substring(1));
		} else {
			return ValidationStatus.error("Invalid protocol '" + objAddr + "'. Valid protocols are 'iiop:', ':', and 'rir:'.");
		}
	}

	/**
	 * <code>[version "@"] hostAndPort</code>
	 * @param iiopAddr
	 * @return
	 */
	private IStatus validateIiopAddr(String iiopAddr) {
		int index = iiopAddr.indexOf('@');
		if (index == -1) {
			return validateHostAndPort(iiopAddr);
		} else {
			IStatus status = validateVersion(iiopAddr.substring(0, index));
			if (!status.isOK()) {
				return status;
			}
			return validateHostAndPort(iiopAddr.substring(index + 1));
		}
	}

	/**
	 * <code>digit+ "." digit+</code>
	 * @param version
	 * @return
	 */
	private IStatus validateVersion(String version) {
		if (!versionString.matcher(version).matches()) {
			return ValidationStatus.error("Invalid verison: " + version);
		}
		return ValidationStatus.ok();
	}

	/**
	 * <code>host [":" port]</code>
	 * <p/>
	 * @param hostAndPort
	 * @return
	 */
	private IStatus validateHostAndPort(String hostAndPort) {
		// An IPv6 address has colons in it, but must be surrounded by [ ]
		int bracketIndex = hostAndPort.lastIndexOf(']');
		if (bracketIndex == -1) {
			bracketIndex = 0;
		}

		int index = hostAndPort.indexOf(':', bracketIndex);
		if (index == -1) {
			return validateHost(hostAndPort);
		} else {
			IStatus status = validateHost(hostAndPort.substring(0, index));
			if (!status.isOK()) {
				return status;
			}
			return validatePort(hostAndPort.substring(index + 1));
		}
	}

	/**
	 * <ul>
	 * <li><code>IPv4_address</code></li>
	 * <li><code>"[" IPv6_address "]"</code></li>
	 * <li><code>hostname</code></li>
	 * <ul>
	 * @param host
	 * @return
	 */
	private IStatus validateHost(String host) {
		if (host.isEmpty()) {
			return ValidationStatus.error("Expected a hostname or IP address");
		}

		Matcher matcher = ipv4String.matcher(host);
		if (matcher.matches()) {
			for (int i = 1; i <= 4; i++) {
				int octet = Integer.parseInt(matcher.group(i));
				if (octet < 1 || octet > 255) {
					if (octet == 0 && i > 1) {
						continue;
					}
					return ValidationStatus.error("Invalid IPv4 address '" + host + "'");
				}
			}
			return ValidationStatus.ok();
		}

		// IPv6 are surrounded by brackets, i.e. [ ]
		if (host.charAt(0) == '[' && host.charAt(host.length() - 1) == ']') {
			// Strip brackets
			host = host.substring(1, host.length() - 1);

			// Check for correct characters (colon and hex digits), and not too many consecutive colons
			matcher = ipv6Chars.matcher(host);
			if (!matcher.matches()) {
				return ValidationStatus.error("Invalid IPv6 address");
			}
			if (host.contains(":::")) {
				return ValidationStatus.error("Invalid IPv6 address. Too many consecutive colons.");
			}

			// Split into segments. There should be no more than 8.
			String[] segments = host.split(":");
			if (segments.length > 8) {
				return ValidationStatus.error("Invalid IPv6 address. Too many hexadecimal groups.");
			}

			// Check that each segment is no more than 4 digits
			for (String segment : segments) {
				if (segment.length() > 4) {
					return ValidationStatus.error("Invalid IPv6 address. Too many hex digits in a group.");
				}
			}

			return ValidationStatus.ok();
		}

		matcher = hostNameString.matcher(host);
		if (matcher.matches()) {
			if (host.endsWith("-")) {
				return ValidationStatus.error("Host names cannot end  with '-'");
			}
			return ValidationStatus.ok();
		}

		return ValidationStatus.error("Invalid hostname IP address: '" + host + "'");
	}

	/**
	 * Port number (1-65535)
	 * @param port
	 * @return
	 */
	private IStatus validatePort(String port) {
		if (port.isEmpty()) {
			return ValidationStatus.error("Expected a port number");
		}

		try {
			int portNumber = Integer.parseInt(port);
			if (portNumber < 1 || portNumber > 65535) {
				return ValidationStatus.error("Port number out of range (1 - 65535)");
			}
		} catch (NumberFormatException e) {
			return ValidationStatus.error("Illegal port number: '" + port + "'");
		}

		return ValidationStatus.ok();
	}

	/**
	 * Just about anything suffices for a key, even an empty string.
	 * @param keyString
	 * @return
	 */
	private IStatus validateKeyString(String keyString) {
		return ValidationStatus.ok();
	}

	/**
	 * Valid stringified name per the naming service.
	 * @param substring
	 * @return
	 */
	private IStatus validateStringName(String stringName) {
		try {
			Name.toName(stringName);
		} catch (InvalidName e) {
			return ValidationStatus.error("Invalid name: " + e.getMessage());
		}
		return ValidationStatus.ok();
	}

	/**
	 * Even number of hexadecimal digits.
	 * @param ior
	 * @return
	 */
	private IStatus validateIOR(String ior) {
		if (ior.length() == 4) {
			return ValidationStatus.error("Missing hex octets");
		}
		if ((ior.length() % 2) != 0) {
			return ValidationStatus.error("Odd number of hex digits (incomplete hex octet)");
		}
		if (!hexString.matcher(ior).matches()) {
			return ValidationStatus.error("IOR body contains non-hexadecimal characters");
		}
		return ValidationStatus.ok();
	}
}
