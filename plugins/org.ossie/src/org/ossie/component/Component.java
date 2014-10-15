package org.ossie.component;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import org.omg.CORBA.ORB;
import CF.ResourceOperations;
import CF.Application;
import CF.ApplicationRegistrar;
import CF.ApplicationRegistrarHelper;
import CF.DomainManager;
import org.ossie.component.ThreadedResource;
import org.ossie.component.ThreadedComponent;

public abstract class Component extends ThreadedResource implements ResourceOperations,ThreadedComponent {
    protected CF.Application _app;
    protected CF.DomainManager _domMgr;
    
    public Component() {
        super();
        this._app = null;
        this._domMgr = null;
    }
    
    public void setAdditionalParameters(String ApplicationRegistrarIOR) {
        final org.omg.CORBA.Object obj = this.orb.string_to_object(ApplicationRegistrarIOR);
        NamingContext nc = NamingContextHelper.narrow(obj);
        ApplicationRegistrar appReg = null;
        try {
            appReg = ApplicationRegistrarHelper.narrow(obj);
        } catch (Exception e) {}
        if (appReg!=null) {
            this._app = appReg.app();
            this._domMgr = appReg.domMgr();
        }
    }
};
