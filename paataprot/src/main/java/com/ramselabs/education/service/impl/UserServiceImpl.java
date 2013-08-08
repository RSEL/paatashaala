package com.ramselabs.education.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.ramselabs.education.entity.Share;
import com.ramselabs.education.entity.User;
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
	public boolean doLogin(User user) {
		
		return hCrud.loginAuthenticate(user);
	}

	@Override
	public List<Share> getAutocompleteUserList() {
		
		return hCrud.getUserAutoCompleteList();
	}

}
