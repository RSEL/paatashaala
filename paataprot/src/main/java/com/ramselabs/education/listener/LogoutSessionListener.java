package com.ramselabs.education.listener;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LogoutSessionListener implements HttpSessionListener{

	private FacesContext jsfContext;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		jsfContext=FacesContext.getCurrentInstance();
		System.out.println("Session is created");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Session is destroy");
		jsfContext.getApplication().getNavigationHandler().handleNavigation(jsfContext, null, "/views/FrontPage.xhtml?faces-redirect=true");
		
	}

}
