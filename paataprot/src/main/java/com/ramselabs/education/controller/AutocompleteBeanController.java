package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.converter.UserAutocompleteConverter;
import com.ramselabs.education.model.AutocompleteTemplate;

@Named
@Scope("session")
public class AutocompleteBeanController implements Serializable {

	private static final long serialVersionUID = 4205128731491922702L;
	private AutocompleteTemplate selectedUserProfiles;
	@Inject
	private UserAutocompleteConverter userConverter;
    
	
	public UserAutocompleteConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserAutocompleteConverter userConverter) {
		this.userConverter = userConverter;
	}
    
	public AutocompleteBeanController(){
		
		System.out.println("Controller Object is created");
	}
	

	public AutocompleteTemplate getSelectedUserProfiles() {
		return selectedUserProfiles;
	}

	public void setSelectedUserProfiles(AutocompleteTemplate selectedUserProfiles) {
		this.selectedUserProfiles = selectedUserProfiles;
	}

	public List<AutocompleteTemplate> completeUserProfile(String input) {
		List<AutocompleteTemplate> suggestions = new ArrayList<AutocompleteTemplate>();
		List<AutocompleteTemplate> list=userConverter.getList();
		for (AutocompleteTemplate auto:list) {
			   if (StringUtils.startsWithIgnoreCase(auto.getDisplayName(), input)) {
				suggestions.add(auto);
			   }
			}
		
        
		return suggestions;
	}
	 public void handleUnselect(UnselectEvent event) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected:" + event.getObject().toString(), null);  
	          
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }

/*	public StreamedContent getAutoImage() {
		if(autoImage==null){
			byte[] imageInByteArray =selectedUserProfile.getUserImage();
			autoImage = new DefaultStreamedContent(new ByteArrayInputStream(imageInByteArray), "image/jpg");
             System.out.println("AutoComplete"+autoImage);
			  return autoImage;
		}
		System.out.println("AutoComplete1"+autoImage);
		return null;
	}*/
	 
}
