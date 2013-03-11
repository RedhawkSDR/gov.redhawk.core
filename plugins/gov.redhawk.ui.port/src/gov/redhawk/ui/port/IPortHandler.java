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
package gov.redhawk.ui.port;

import java.util.List;

public interface IPortHandler {

	/**
	 * @deprecated Filter needs to be seriously reevaluated
	 */
	final String FILTER_PLOT = "plot";
	/**
	 * @deprecated Filter needs to be seriously reevaluated
	 */
	final String FILTER_FFT = "fft";
	/**
	 * @since 2.0
	 * @deprecated Filter needs to be seriously reevaluated
	 */
	final String FILTER_PLAY = "play";

	/**
	 * This will connect to a given port and process the data coming out of it.
	 * The list passed in must be of either ScaPort<Uses> or
	 * CorbaConnectionSettings, anything else will not be processed.
	 * 
	 * @param portList A list of Uses ports or CorbaConnectionSettings to plot
	 * @since 5.0
	 */
	void connect(List< ? > portList);
	
	/**
	 * This will connect to a given port and process the data coming out of it.
	 * The list passed in must be of either ScaPort<Uses> or
	 * CorbaConnectionSettings, anything else will not be processed.
	 * 
	 * @param portList A list of Uses ports or CorbaConnectionSettings to plot
	 * @param filter a filter for the port data. An example is the TimingStatus
	 *            PortHandler
	 * @since 3.0
	 * @deprecated The use of filter needs to be really evaluated
	 */
	void connect(List< ? > portList, String filter);
}
