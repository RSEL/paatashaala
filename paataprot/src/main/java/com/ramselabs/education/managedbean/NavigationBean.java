package com.ramselabs.education.managedbean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
 
/**
 * Simple navigation bean
 * 
 *
 */
@Named
@Scope("session")
public class NavigationBean implements Serializable {
 
    private static final long serialVersionUID = 1520318172495977648L;
 
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/views/FrontPage.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/views/FrontPage.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/secured/userHomePage.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/secured/userHomePage.xhtml";
    }
     
}
