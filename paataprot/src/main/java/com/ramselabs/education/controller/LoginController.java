package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.LoginBean;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class LoginController implements Serializable {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1738990385639378575L;
	
	@Inject
	private ManagedLoginBean login;
	@Inject
	private UserService serInface;

	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}

	public void setSerInface(UserService serInface) {
		this.serInface = serInface;
	}

	public String verifyLogin() {
		LoginBean loginBean=ManagedLoginBean.mappToLoginBean(login);
		if (serInface.doLogin(loginBean))
			return "success?faces-redirect=true";
		return "failure?faces-redirect=true";
	}
}
