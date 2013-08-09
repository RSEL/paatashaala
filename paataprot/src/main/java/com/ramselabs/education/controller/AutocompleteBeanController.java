package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.converter.UserAutocompleteConverter;
import com.ramselabs.education.entity.Share;

@Named
@Scope("session")
public class AutocompleteBeanController implements Serializable {

	private static final long serialVersionUID = 4205128731491922702L;
	private Share selectedShare;
	@Inject
	private UserAutocompleteConverter userConverter;

	public UserAutocompleteConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserAutocompleteConverter userConverter) {
		this.userConverter = userConverter;
	}

	public Share getSelectedShare() {
		return selectedShare;
	}

	public void setSelectedShare(Share selectedShare) {
		this.selectedShare = selectedShare;
	}

	public List<Share> completeShare(String input) {
		List<Share> suggestions = new ArrayList<Share>();
		Map<String, Share> shares = userConverter.getShares();
		for (String key : shares.keySet()) {
			if (key.startsWith(input)) {
				suggestions.add(shares.get(key));
			}
		}
		return suggestions;
	}
}
