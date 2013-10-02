package com.ramselabs.education.dao.service;

import java.util.List;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;

public interface UserDAO {
	public String loginAuthenticate(UserProfile user);
	public UserProfile getUserProfile(UserProfile user);
	public int updateUserImage(UserProfile user);
	public int getUserId(UserProfile user);
	public UserProfile getPersistentUser(UserProfile user);
	public List<AutocompleteTemplate> getUserAutoCompleteList(int userId);
	public int insertUser(UserProfile user); 

}
