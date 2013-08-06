package com.ramselabs.education.service.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import com.ramselabs.education.entity.LoginBean;
import com.ramselabs.education.service.UserService;
import com.ramselabs.education.util.HibernateCRUD;

@Named
public class UserServiceImpl implements UserService,Serializable {

	private static final long serialVersionUID = 1L;
    
	@Inject
	private HibernateCRUD hCrud;
	
	public void sethCrud(HibernateCRUD hCrud) {
		this.hCrud = hCrud;
	}

	@Override
	public boolean doLogin(LoginBean login) {
		
		return hCrud.loginAuthenticate(login);
	}

}
