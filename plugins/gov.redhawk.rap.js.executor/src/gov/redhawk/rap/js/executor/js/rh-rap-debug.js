/* Java-script that is loaded to enable the RAP application to execute
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
