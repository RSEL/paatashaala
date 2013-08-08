package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.converter.UserAutocompleteConverter;
import com.ramselabs.education.entity.Share;

@Named
@Scope("session")
public class AutocompleteBeanController implements Serializable{
	
	private static final long serialVersionUID = 4205128731491922702L;
	private Share selectedShare;
	@Inject
	private UserAutocompleteConverter converter;
	
	public void setConverter(UserAutocompleteConverter converter) {
		this.converter = converter;
	}
	public Share getSelectedShare() {
		return selectedShare;
	}
	public void setSelectedShare(Share selectedShare) {
		this.selectedShare = selectedShare;
	}

    public List<Share> completeCar(String input) {
        List<Share> suggestions = new ArrayList<Share>();
        Set<String> keys = converter.getShares().keySet();

        for (String key : keys) {
            if (key.startsWith(input)) {
                suggestions.add(converter.getShares().get(key));
            }
        }
        return suggestions;
    }
	
	

}
