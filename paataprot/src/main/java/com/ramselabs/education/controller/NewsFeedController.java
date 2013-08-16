package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.UserService;

@Named
@Scope("request")
public class NewsFeedController implements Serializable{

	private static final long serialVersionUID = 4322730194350739587L;
   
	@Inject
	private UserService userService;
	
	@Inject
	private ManagedLoginBean login;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
	private List<PostDescriptionModel> listOfPost;
	
	public List<PostDescriptionModel> getListOfPost() {
		if(listOfPost==null){
			listOfPost=getAllPosts();
		}
		return listOfPost;
	}

	public List<PostDescriptionModel> getAllPosts(){
		if(login==null)
			return null;
		UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		return userService.getPostPersons(user);
	}
	
}
