package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.UserProfile;


public interface UserService {
	public boolean doLogin(UserProfile user);
	public List<UserProfile> getAutocompleteUserList(UserProfile user);
	public UserProfile getPersistentUser(UserProfile user);
	public int getUserId(UserProfile user);
	public UserProfile getUserProfile(String username,String password);
	public int updateUserImage(UserProfile user);

}
