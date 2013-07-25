package com.ramselabs.education.interfaces.implementation;

import javax.inject.Named;

import com.ramselabs.education.interfaces.ServiceInterface;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.util.HibernateCRUD;

@Named
public class ServiceImplementation implements ServiceInterface {
	@Override
	public boolean doLogin(ManagedLoginBean login) {
		
		return HibernateCRUD.loginAuthenticate(login);
	}

}
