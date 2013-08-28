package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.AlertService;

@Named
@Scope("session")
public class AlertFeedController implements ActionListener,Serializable{
	
	private static final long serialVersionUID = -5373751387079793103L;

	@Inject
	private ManagedLoginBean login;
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}

	@Inject
	private AlertService alertService;
	

	public void setAlertService(AlertService alertService) {
		this.alertService = alertService;
	}
	private List<PostDescriptionModel> listofAlert;
	
	
	public List<PostDescriptionModel> getListofAlert() {
		if(listofAlert==null){
			listofAlert=getAllAlerts();
		}
		return listofAlert;
	}
	public void setListofAlert(List<PostDescriptionModel> listofAlert) {
		this.listofAlert = listofAlert;
	}
	
	public List<PostDescriptionModel> getAllAlerts(){
		if(login==null)
			return null;
		UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		return alertService.getAlerts(user);
	}
	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		listofAlert=getAllAlerts();
		
	}
}
