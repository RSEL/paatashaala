package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.UserService;
@Named
@Scope("session")
public class UserProfileController implements Serializable{

	private static final long serialVersionUID = 3502114518110612435L;
	@Inject
	private ManagedLoginBean login;
	
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
	@Inject
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private String displayName;
	private String image ; 
	public String getDisplayName() {
		if(displayName==null){
			if(login.getUsername() != null & login.getPassword()!=null){
			displayName=userService.getUserProfile(login.getUsername(), login.getPassword()).getDisplayName();
		   }
		}
		System.out.println("UserProfile"+displayName);
		return displayName;
	}
	public String getImage() {
		  System.out.println("UserService"+userService);
		  if(login.getUsername() != null & login.getPassword()!=null){
			  image=userService.getUserProfile(login.getUsername(),login.getPassword()).getImagePath();
		    
		  }
			  return image;
	}
	
	
	
	

}