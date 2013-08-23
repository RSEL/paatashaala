package com.ramselabs.education.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.UserDAO;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class UserServiceImpl implements UserService,Serializable {

	private static final long serialVersionUID = 1L;
    
	@Inject
	private UserDAO userDao;
	
	

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean doLogin(UserProfile user) {
		
		return userDao.loginAuthenticate(user);
	}

	@Override
	public List<UserProfile> getAutocompleteUserList(UserProfile user) {
		UserProfile userProfile=getPersistentUser(user);
		int userId=userProfile.getUserId();
		System.out.println("User Service"+userId);
		return userDao.getUserAutoCompleteList(userId);
	}


	@Override
	public UserProfile getPersistentUser(UserProfile user) {
		return userDao.getPersistentUser(user);
	}


	@Override
	public int getUserId(UserProfile user) {
		return userDao.getUserId(user);
	}

	@Override
	public UserProfile getUserProfile(String username,String password) {
		return userDao.getUserProfile(username,password);
	}

	@Override
	public int updateUserImage(UserProfile user) {
		return userDao.updateUserImage(user);
	}

}
