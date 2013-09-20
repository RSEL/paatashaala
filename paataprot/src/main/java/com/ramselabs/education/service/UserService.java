package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;


public interface UserService {
	public boolean doLogin(UserProfile user);
	public List<AutocompleteTemplate> getAutocompleteUserList(UserProfile user);
	public UserProfile getPersistentUser(UserProfile user);
	public int getUserId(UserProfile user);
	public UserProfile getUserProfile(String username,String password);
	public int updateUserImage(UserProfile user);

}
