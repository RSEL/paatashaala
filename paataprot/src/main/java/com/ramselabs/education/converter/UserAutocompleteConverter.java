package com.ramselabs.education.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Share;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class UserAutocompleteConverter implements Converter {
	@Inject
	private UserService userService;
	private List<Share> list;
	public Map<String, Share> shares;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public Map<String, Share> getShares() {
		if (shares == null) {
			shares = new HashMap<String, Share>();
			for (Share sh : getList()) {
				shares.put(sh.getName(), sh);
			}
		}

		return shares;
	}

	public List<Share> getList() {
		if (list == null) {
			list = userService.getAutocompleteUserList();
		}
		return list;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value))
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