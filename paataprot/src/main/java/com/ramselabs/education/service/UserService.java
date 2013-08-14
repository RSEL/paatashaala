package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;


public interface UserService {
	public boolean doLogin(UserProfile user);
	public List<UserProfile> getAutocompleteUserList(UserProfile user);
	public int inserPost(Post post,PostShare postShare);
	public UserProfile getPersistentUser(UserProfile user);
	public List<PostDescriptionModel> getPostPersons(UserProfile user);
	public int getUserId(UserProfile user);
	public String getDisplayName(int userId);

}
