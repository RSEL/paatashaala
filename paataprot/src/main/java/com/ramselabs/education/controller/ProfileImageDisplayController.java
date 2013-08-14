package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class ProfileImageDisplayController implements Serializable{
	
	private static final long serialVersionUID = -8395496441295879616L;
	@Inject
	private ManagedLoginBean login;
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
    
	@Inject
	UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private String image;
	
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage(){
		if(image==null){
		UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		int userId=userService.getUserId(user);
		String displayName=userService.getDisplayName(userId);
		if(displayName!=null)
			image=displayName;
		    System.out.println("image"+image);
			return image;
		}
		return null;
	}
}
