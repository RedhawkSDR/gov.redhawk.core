/* Java-script that is loaded to enable the RAP application to execute
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
 * Javascript code and provide the results to Java code */


 /* Utility class */
qx.Class.define( "gov.redhawk.rap.JSHelper",
{
	type : "singleton",
	extend : qx.core.Object,
	
	construct : function() {
		this.base( arguments );
		
		// Shim for IE <= 8
		// see https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Date/now
		if (!Date.now) {  
			Date.now = function now() {  
			    return +(new Date);  
		    };  
		} 
	},
	
    members :
    {
	    _evaluateScript : function (script, instanceID) {
	    	var result = eval(script);
	    	var req = org.eclipse.swt.Request.getInstance();
	    	req.addParameter("redhawk.rap.util", "evaluate");
	    	req.addParameter("redhawk.rap.util.id", instanceID);
	    	req.addParameter("redhawk.rap.util.args", JSON.stringify(result));
	    	req.send();
	    },
	    
	    _createAnnonymousFunction : function(instanceID) {
	    	return function() {
	    		var req = org.eclipse.swt.Request.getInstance();
	    		req.addParameter("redhawk.rap.util", "function");
		    	req.addParameter("redhawk.rap.util.id", instanceID);
		    	req.addParameter("redhawk.rap.util.args", JSON.stringify(arguments));
		    	req.send();
	    	}
	    }
    
    }  
});

gov.redhawk.rap.JSHelper.getInstance();  // initialize the instance by constructing the singleton
