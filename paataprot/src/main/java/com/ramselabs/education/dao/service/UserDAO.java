package com.ramselabs.education.dao.service;

import java.util.List;

import com.ramselabs.education.entity.UserProfile;

public interface UserDAO {
	public boolean loginAuthenticate(UserProfile user);
	public UserProfile getUserProfile(String username,String password);
	public int updateUserImage(UserProfile user);
	public int getUserId(UserProfile user);
	public UserProfile getPersistentUser(UserProfile user);
	public List<UserProfile> getUserAutoCompleteList(int userId);

}
