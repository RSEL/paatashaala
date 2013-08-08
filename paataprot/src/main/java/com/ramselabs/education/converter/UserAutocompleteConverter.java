package com.ramselabs.education.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.ramselabs.education.entity.Share;
import com.ramselabs.education.service.UserService;

@FacesConverter(value="com.ramselabs.education.converter.UserAutocompleteConverter")
public class UserAutocompleteConverter implements Converter{
    @Inject
	private UserService userService;
    
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private List<Share> list=userService.getAutocompleteUserList();
    
	private Map<String, Share> shares=new HashMap<String, Share>();
	{
		for(Share sh:list){
			shares.put(sh.getName(), sh);
		}
	}
	
    public Map<String, Share> getShares() {
		return shares;
	}

	public void setShares(Map<String, Share> shares) {
		this.shares = shares;
	}

	public List<Share> getList() {
		return list;
	}

	public void setList(List<Share> list) {
		this.list = list;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(StringUtils.isBlank(value))
		      return null;
		else
			  return shares.get(value);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		 if (value == null || value.equals("")) {
	            return "";
	        } else {
	            return String.valueOf(((Share) value).getName());
	        }
	}

}
