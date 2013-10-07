package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope
public class BrowserContentDisplayController implements Serializable{

	private static final long serialVersionUID = 8992501819985328402L;
	private String toRedirect;
	public String getToRedirect() {
		return toRedirect;
	}
	public void setToRedirect(String toRedirect) {
		this.toRedirect = toRedirect;
	}
	public void setContentId(){
		FacesContext context = FacesContext.getCurrentInstance();
    	int shareFileId = context.getApplication().evaluateExpressionGet(context, "#{shrdFile.sharedFileId}", Integer.class);
    	toRedirect="/secured/displayFile.xhtml?faces-redirect=true&sharedFileId="+shareFileId;
	}

	public String toRedirect(){
		return toRedirect;
	}
}
