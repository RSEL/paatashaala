package com.ramselabs.education.dao.service;

import java.util.List;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;

public interface UserDAO {
	public boolean loginAuthenticate(UserProfile user);
	public UserProfile getUserProfile(String username,String password);
	public int updateUserImage(UserProfile user);
	public int getUserId(UserProfile user);
	public UserProfile getPersistentUser(UserProfile user);
	public List<AutocompleteTemplate> getUserAutoCompleteList(int userId);

}
