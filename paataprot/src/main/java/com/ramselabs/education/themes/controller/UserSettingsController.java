package com.ramselabs.education.themes.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.themes.model.AvailableThemes;
import com.ramselabs.education.themes.model.Theme;
import com.ramselabs.education.themes.model.UserPreferences;

@Named
@Scope("session")
public class UserSettingsController implements Serializable {

	// Stateful Switcher (AJAX)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> themes;
	private String theme;
	private UserPreferences userPreferences = new UserPreferences();

	public Map<String, String> getThemes() {
		return themes;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void saveTheme() {
		userPreferences.setTheme(theme);
	}

	@PostConstruct
	public void init() {
		theme = userPreferences.getTheme();

		themes = new TreeMap<String, String>();
		themes.put("Home", "home");
		themes.put("Afterdark", "afterdark");
		themes.put("Dot-Luv", "dot-luv");
		themes.put("Glass-X", "glass-x");
		themes.put("Hot-Sneaks", "hot-sneaks");
	}

	// Stateful Switcher (Full page refresh)

	private List<Theme> availableThemes;
	private Theme currentTheme;

	public UserSettingsController() {
		currentTheme = AvailableThemes.instance().getTheme("home");
		availableThemes = AvailableThemes.instance().getThemes();
	}

	public List<Theme> getAvailableThemes() {
		return availableThemes;
	}

	public void setAvailableThemes(List<Theme> availableThemes) {
		this.availableThemes = availableThemes;
	}

	public Theme getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(Theme currentTheme) {
		this.currentTheme = currentTheme;
	}
}
