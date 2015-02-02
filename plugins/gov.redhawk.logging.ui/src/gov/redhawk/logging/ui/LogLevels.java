/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.logging.ui;


public enum LogLevels {
	  OFF("OFF", CF.LogLevels.OFF),
	  FATAL("FATAL", CF.LogLevels.FATAL),
	  ERROR("ERROR", CF.LogLevels.ERROR),
	  WARN("WARN", CF.LogLevels.WARN),
	  INFO("INFO", CF.LogLevels.INFO),
	  DEBUG("DEBUG", CF.LogLevels.DEBUG),
	  TRACE("TRACE", CF.LogLevels.TRACE),
	  ALL("ALL", CF.LogLevels.ALL);
	  
	  private int level;
	  private String label;
	  
	  private LogLevels(String label, int level) {
		  this.label = label;
		  this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public String getLabel() {
		return label;
	}
	
	public static LogLevels intToLogLevel(int value) {
		LogLevels retVal = ALL; // If the value is below 0, we assume it's ALL.
		
		for (LogLevels logLevel : LogLevels.values()) {
			if (value >= logLevel.getLevel()) {
				retVal = logLevel;
				break;
			}
		}
		
		return retVal;
	}
}
