package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.managedbean.NavigationBean;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class LoginController implements Serializable {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1738990385639378575L;
	private boolean loggedIn;
	@Inject
	private ManagedLoginBean login;
	@Inject
	private UserService serInface;
    @Inject
    private NavigationBean navigationBean;
    
    public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}

	public void setSerInface(UserService serInface) {
		this.serInface = serInface;
	}
    
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String verifyLogin() {
		UserProfile userBean=ManagedLoginBean.mappToUserEntity(login);
		System.out.println("Login-verify");
		if (serInface.doLogin(userBean)){
			loggedIn=true;
			return navigationBean.redirectToWelcome();
		}
		else{
			    FacesContext facesContext = FacesContext.getCurrentInstance();
		        facesContext.addMessage(null, new FacesMessage("Invalid username or password"));
		        return navigationBean.toLogin();
		}
	}
     public String doLogout() {
	        // Set the paremeter indicating that user is logged in to false
    	 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    	 session.invalidate();
	        loggedIn = false;
            return navigationBean.toLogin();
	    }
}
