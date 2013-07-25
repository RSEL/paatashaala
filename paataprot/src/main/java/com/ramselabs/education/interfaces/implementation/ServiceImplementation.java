package com.ramselabs.education.interfaces.implementation;

import java.io.Serializable;

import javax.inject.Named;

import com.ramselabs.education.interfaces.ServiceInterface;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.util.HibernateCRUD;

@Named
public class ServiceImplementation implements ServiceInterface,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean doLogin(ManagedLoginBean login) {
		
		return HibernateCRUD.loginAuthenticate(login);
	}

}
