package com.ramselabs.education.model;

import com.ramselabs.education.entity.UserProfile;

public class UserModel extends UploadModel{
	private String password;
	private String userName;
	private Integer id;
	private String displayName;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public static UserProfile getUserProfile(UserModel userModel){
		UserProfile userProfile=new UserProfile();
		userProfile.setDisplayName(userModel.getDisplayName());
		userProfile.setUsername(userModel.getUserName());
		userProfile.setPassword(userModel.getPassword());
		return userProfile;
	}
	@Override
	public String toString(){
		return displayName;
	}
}
