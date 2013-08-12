package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.converter.UserAutocompleteConverter;
import com.ramselabs.education.entity.Share;

@Named
@Scope("session")
public class AutocompleteBeanController implements Serializable {

	private static final long serialVersionUID = 4205128731491922702L;
	private List<Share> selectedShares;
	@Inject
	private UserAutocompleteConverter userConverter;

	public UserAutocompleteConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserAutocompleteConverter userConverter) {
		this.userConverter = userConverter;
	}

	public List<Share> getSelectedShares() {
		return selectedShares;
	}

	public void setSelectedShares(List<Share> selectedShares) {
		this.selectedShares = selectedShares;
		System.out.println("Shares are set :"+selectedShares);
	}

	public List<Share> completeShare(String input) {
		List<Share> suggestions = new ArrayList<Share>();
		Map<String, Share> shares = userConverter.getShares();
		for (String key : shares.keySet()) {
			   if (StringUtils.startsWithIgnoreCase(key, input)) {
				suggestions.add(shares.get(key));
			   }
		}

		return suggestions;
	}
	 public void handleUnselect(UnselectEvent event) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected:" + event.getObject().toString(), null);  
	          
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }
}
