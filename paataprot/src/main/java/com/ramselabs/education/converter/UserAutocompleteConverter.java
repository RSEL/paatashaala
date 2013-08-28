package com.ramselabs.education.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class UserAutocompleteConverter implements Converter {
	@Inject
	private UserService userService;
	private List<UserProfile> list;

	@Inject
	private ManagedLoginBean login;
	
	
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
	public UserAutocompleteConverter(){
		System.out.println("Converter object is created"+this);
	}
	public UserService getUserService() {
		return userService;
	}

	public List<UserProfile> getList() {
		if (list == null) {
			UserProfile user=ManagedLoginBean.mappToUserEntity(login);
			list = userService.getAutocompleteUserList(user);
		}
		return list;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)){
			System.out.println("converter String value is blank");
			return null;
		}
		else{
			System.out.println("converter String value is not blank");
			for(UserProfile user : list){
				if(user.getDisplayName().equals(value)){
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null || value.equals("")) {
			System.out.println("converter Object value is blank");
			return "";
		} else {
			System.out.println("converter Object value is not blank");
			return String.valueOf(((UserProfile) value).getDisplayName());
		}
	}

}