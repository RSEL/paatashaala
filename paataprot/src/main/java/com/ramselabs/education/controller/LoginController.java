package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.interfaces.ServiceInterface;
import com.ramselabs.education.managedbean.ManagedLoginBean;

@Named
@Scope("session")
public class LoginController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ManagedLoginBean login;
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
	@Inject
    private ServiceInterface serInface;
	public void setSerInface(ServiceInterface serInface) {
		this.serInface = serInface;
	}

	public String verifyLogin(){
		 if(serInface.doLogin(login))
			 return "success";
		 return "failure";
	}
}
