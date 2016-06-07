/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.config;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import gov.redhawk.logging.ui.LogLevels;

public class Log4JConfigGenerator {

	private Log4JConfigGenerator() {
	}

	/**
	 * Parses a log4j config and returns a list of the appenders for a given logger. For example, if the config had:
	 * <p/>
	 * <code>log4j.logger.com.foo=BAR,WARN,BAZ</code>
	 * <p/>
	 * then this method would return ["BAR", "BAZ"] for logger "com.foo". Any error will return an empty list.
	 * @param logConfig The log4j config as a string
	 * @param logger The logger, or null/empty for the root logger
	 * @return A list of appenders for the logger; empty if none
	 */
	public static List<String> getExistingAppenders(String logConfig, String logger) {
		// Parse properties
		Properties props = new Properties();
		try {
			props.load(new StringReader(logConfig));
		} catch (IOException e) {
			return Collections.emptyList();
		}

		// Find all appenders
		String key;
		if (logger == null || logger.isEmpty()) {
			key = "log4j.rootLogger";
		} else {
			key = "log4j.logger." + logger;
		}
		List<String> appenders = new ArrayList<String>();
		for (String element : props.getProperty(key, "").split(",")) {
			element = element.trim();
			if (element.isEmpty()) {
				continue;
			}
			try {
				LogLevels.valueOf(element);
			} catch (IllegalArgumentException e) {
				// It's not a log level; must be an appender
				appenders.add(element);
			}
		}
		return appenders;
	}

	/**
	 * Create a log4j configuration snippet that adds a Redhawk log event appender
	 * @param namingContext The naming context to place the event channel in (e.g. 'REDHAWK_DEV')
	 * @param eventChannelName The event channel name to use
	 * @param logger The logger to attach the appender to; null/empty for the root logger
	 * @param logLevel The log level to use for the logger
	 * @param appenders Additional appenders (besides the new event channel appender) to add
	 * @return the log4j configuration snippet
	 */
	public static String createLog4jConfig(String namingContext, String eventChannelName, String logger, LogLevels logLevel, List<String> appenders) {
		String prefix = "log4j.appender." + eventChannelName;

		StringBuilder sb = new StringBuilder("\n");
		sb.append(createStartTag(eventChannelName));
		sb.append(" - Do not modify these lines\n");

		sb.append("log4j.");
		if (logger == null || logger.isEmpty()) {
			sb.append("rootLogger=");
		} else {
			sb.append("logger.");
			sb.append(logger);
			sb.append('=');
		}
		sb.append(logLevel.getLabel());
		sb.append(',');
		sb.append(eventChannelName);
		if (appenders != null && appenders.size() > 0) {
			for (String appender : appenders) {
				sb.append(',');
				sb.append(appender);
			}
		}
		sb.append('\n');

		sb.append(prefix);
		sb.append("=org.ossie.logging.RH_LogEventAppender\n");

		sb.append(prefix);
		sb.append(".event_channel=" + eventChannelName);
		sb.append('\n');

		sb.append(prefix);
		sb.append(".name_context=");
		sb.append(namingContext);
		sb.append('\n');

		sb.append(prefix);
		sb.append(".threshold=");
		sb.append(logLevel.toString());
		sb.append('\n');

		sb.append(prefix);
		sb.append(".layout=org.apache.log4j.PatternLayout\n");

		sb.append(prefix);
		sb.append(".layout.ConversionPattern=%c:%L - %m\n");

		sb.append(createEndTag(eventChannelName));
		sb.append('\n');

		return sb.toString();
	}

	/**
	 * Creates a unique comment tag which precedes the IDE's changes to a logging configuration
	 * @param eventChannelName
	 * @return
	 */
	public static String createStartTag(String eventChannelName) {
		return "### BEGIN IDE_" + eventChannelName;
	}

	/**
	 * Creates a unique comment tag which follows the IDE's changes to a logging configuration
	 * @param eventChannelName
	 * @return
	 */
	public static String createEndTag(String eventChannelName) {
		return "### END IDE_" + eventChannelName;
	}

}
