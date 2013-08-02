package com.ramselabs.education.themes.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ramselabs.education.themes.model.AvailableThemes;
import com.ramselabs.education.themes.model.Theme;

@FacesConverter("com.syntel.training.jsf.converter.ThemeConverter")
public class ThemeConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return AvailableThemes.instance().getTheme(value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Theme) value).getName();
	}
}
