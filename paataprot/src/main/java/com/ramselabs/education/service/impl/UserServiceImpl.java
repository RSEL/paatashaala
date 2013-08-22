package com.ramselabs.education.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.UserService;
import com.ramselabs.education.util.HibernateCRUD;

@Named
@Scope("session")
public class UserServiceImpl implements UserService,Serializable {

	private static final long serialVersionUID = 1L;
    
	@Inject
	private HibernateCRUD hCrud;
	
	public void sethCrud(HibernateCRUD hCrud) {
		this.hCrud = hCrud;
	}

	@Override
	public boolean doLogin(UserProfile user) {
		
		return hCrud.loginAuthenticate(user);
	}

	@Override
	public List<UserProfile> getAutocompleteUserList(UserProfile user) {
		UserProfile userProfile=getPersistentUser(user);
		int userId=userProfile.getUserId();
		System.out.println("User Service"+userId);
		return hCrud.getUserAutoCompleteList(userId);
	}

	@Override
	public List<PostDescriptionModel> getPostPersons(UserProfile user) {
		int userId=hCrud.getUserId(user);
		if(userId==0)
			return null;
		return hCrud.getPostPersons(userId);
	}

	@Override
	public UserProfile getPersistentUser(UserProfile user) {
		return hCrud.getPersistentUser(user);
	}

	@Override
	public int inserPost(Post post, PostShare postShare) {
		return hCrud.insertPosts(post,postShare);
	}

	@Override
	public int getUserId(UserProfile user) {
		return hCrud.getUserId(user);
	}

	@Override
	public String getDisplayName(int userId) {
		return hCrud.getPoster(userId).getDisplayName();
	}
	@Override
	public UserProfile getUserProfile(String username,String password) {
		return hCrud.getUserProfile(username,password);
	}

	@Override
	public int updateUserImage(UserProfile user) {
		return hCrud.updateUserImage(user);
	}

}
