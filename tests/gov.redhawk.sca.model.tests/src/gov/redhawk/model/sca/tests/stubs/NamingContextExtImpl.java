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
package gov.redhawk.model.sca.tests.stubs;

import java.util.Iterator;
import java.util.Map;

import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtPOA;
import org.omg.CosNaming.NamingContextExtPackage.InvalidAddress;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class NamingContextExtImpl extends NamingContextExtPOA implements NamingContextExt {

	private static final long serialVersionUID = -6975281815172891130L;

	private Map<String, org.omg.CORBA.Object> nameToObject;

	public NamingContextExtImpl(Map<String, org.omg.CORBA.Object> eventChannelRefs) {
		this.nameToObject = eventChannelRefs;
	}

	@Override
	public String to_string(NameComponent[] n) throws InvalidName {
		return null;
	}

	@Override
	public NameComponent[] to_name(String sn) throws InvalidName {
		return null;
	}

	@Override
	public String to_url(String addr, String sn) throws InvalidAddress, InvalidName {
		return null;
	}

	@Override
	public Object resolve_str(String sn) throws NotFound, CannotProceed, InvalidName {
		// Provide ourself for any requested child naming context
		return this;
	}

	@Override
	public void bind(NameComponent[] n, Object obj) throws NotFound, CannotProceed, InvalidName, AlreadyBound {
	}

	@Override
	public void bind_context(NameComponent[] n, NamingContext nc) throws NotFound, CannotProceed, InvalidName, AlreadyBound {
	}

	@Override
	public void rebind(NameComponent[] n, Object obj) throws NotFound, CannotProceed, InvalidName {
	}

	@Override
	public void rebind_context(NameComponent[] n, NamingContext nc) throws NotFound, CannotProceed, InvalidName {
	}

	@Override
	public Object resolve(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {
		return nameToObject.get(n[n.length - 1].id);
	}

	@Override
	public void unbind(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {
	}

	@Override
	public void list(int howMany, BindingListHolder bl, BindingIteratorHolder bi) {
		bl.value = new Binding[nameToObject.size()];
		Iterator<String> nameIter = nameToObject.keySet().iterator();
		for (int i = 0; i < nameToObject.size(); i++) {
			bl.value[i] = new Binding(new NameComponent[] { new NameComponent(nameIter.next(), "") }, null);
		}
	}

	@Override
	public NamingContext new_context() {
		return null;
	}

	@Override
	public NamingContext bind_new_context(NameComponent[] n) throws NotFound, AlreadyBound, CannotProceed, InvalidName {
		return null;
	}

	@Override
	public void destroy() throws NotEmpty {
	}

	@Override
	public boolean _is_equivalent(Object other) {
		return false;
	}

	@Override
	public int _hash(int maximum) {
		return 0;
	}

	@Override
	public Object _duplicate() {
		return null;
	}

	@Override
	public void _release() {
	}

	@Override
	public Request _request(String operation) {
		return null;
	}

	@Override
	public Request _create_request(Context ctx, String operation, NVList argList, NamedValue result) {
		return null;
	}

	@Override
	public Request _create_request(Context ctx, String operation, NVList argList, NamedValue result, ExceptionList exclist, ContextList ctxlist) {
		return null;
	}

	@Override
	public Policy _get_policy(int policyType) {
		return null;
	}

	@Override
	public DomainManager[] _get_domain_managers() {
		return null;
	}

	@Override
	public Object _set_policy_override(Policy[] policies, SetOverrideType setAdd) {
		return null;
	}

}
