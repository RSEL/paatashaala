package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.GroupService;
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
	@Inject
	private GroupService groupService;
	
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	private String displayName;
	private String image ; 
	private String userType;
	private List<Group> listOfGroups;
	
	public List<Group> getListOfGroups() {
		UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		int userId=userService.getUserId(user);
		listOfGroups=groupService.getAllGrpsForCurrentUser(userId);
		if(listOfGroups==null)
			return null;
		for(Group group:listOfGroups){
			if(group.getImagePath()==null)
				group.setImagePath("/resources/img/profile-photo/group-blank.png");
		}
		return listOfGroups;
	}
	public String getDisplayName() {
		if(displayName==null){
			if(login.getUsername() != null & login.getPassword()!=null){
				UserProfile user=userService.getUserProfile(ManagedLoginBean.mappToUserEntity(login));
				if(user==null)
					return null;
			displayName=user.getDisplayName();
		   }
		}
		System.out.println("UserProfile"+displayName);
		return displayName;
	}
	public String getImage() {
		  System.out.println("UserService"+userService);
		  System.out.println(login.getUsername()+login.getPassword());
		  UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		  if(login.getUsername() != null & login.getPassword()!=null){
			  System.out.println(userService);
			  System.out.println(userService.getUserProfile(user));
			  UserProfile dataBaseUser=userService.getUserProfile(user);
			  if(dataBaseUser==null)
				  return null;
			  image=dataBaseUser.getImagePath();
		      if(image==null)
		    	  image="/resources/img/profile-photo/default-profile.jpg";
		  }
			  return image;
	}
	public String getUserType() {
		if(login.getUsername() != null & login.getPassword()!=null){
			 UserProfile user=userService.getUserProfile(ManagedLoginBean.mappToUserEntity(login));
			 if(user==null)
				 return null;
			 Collection<PostShare> pShare=user.getUserPostShare();
			 for (PostShare postShare:pShare){
				 if(postShare.getUserType().equals("User")){
					 userType=postShare.getUserType();
				 }
			 }
			 
		}
		   return userType;
	}
}
