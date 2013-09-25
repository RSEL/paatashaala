package com.ramselabs.education.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.service.GroupService;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class UserAutocompleteConverter implements Converter,ActionListener {
	@Inject
	private UserService userService;
	private List<AutocompleteTemplate> list;

	@Inject
	private ManagedLoginBean login;
	@Inject
    private GroupService groupSer;
	
	public void setGroupSer(GroupService groupSer) {
		this.groupSer = groupSer;
	}

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

	public List<AutocompleteTemplate> getList() {
		if (list == null) {
			UserProfile user=ManagedLoginBean.mappToUserEntity(login);
			list = userService.getAutocompleteUserList(user);
			List<AutocompleteTemplate> list1=groupSer.getAllGroupForAutocomplete();
			list.addAll(list1);
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
			for(Object obj : list){
				if(((AutocompleteTemplate)obj).getDisplayName().equals(value)){
					return obj;
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
			return String.valueOf(((AutocompleteTemplate) value).getDisplayName());
		}
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		list=getList();
		
	}

}