package com.ramselabs.education.managedbean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;

@Named
@Scope("session")
public class ManagedLoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static UserProfile mappToUserEntity(ManagedLoginBean mngBean){
		UserProfile userBean=new UserProfile();
		userBean.setUsername(mngBean.getUsername());
		userBean.setPassword(mngBean.getPassword());
		return userBean;
	}
}
