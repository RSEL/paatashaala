package com.ramselabs.education.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.hibernate.Criteria;

import com.ramselabs.education.entity.Share;
import com.ramselabs.education.service.UserService;
import com.ramselabs.education.util.HibernateCRUD;

@FacesConverter(value="com.ramselabs.education.converter.UserAutocompleteConverter")
public class UserAutocompleteConverter implements Converter{
    @Inject
	private UserService userService;
    
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private  List<Share> list=userService.getAutocompleteUserList();;
    
    public List<Share> getList() {
		return list;
	}

	public void setList(List<Share> list) {
		this.list = list;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
