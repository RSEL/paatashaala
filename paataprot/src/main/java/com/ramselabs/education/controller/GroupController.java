package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.GroupService;

@Named
@Scope("session")
public class GroupController implements Serializable {

	private static final long serialVersionUID = 8871616461855421261L;

	@Inject
	private GroupWizardController groupWzrd;

	public void setGroupWzrd(GroupWizardController groupWzrd) {
		this.groupWzrd = groupWzrd;
	}

	@Inject
	private GroupService groupService;
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
    @Inject
    private ManagedLoginBean login;
    public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}



	public void save(ActionEvent actionEvent) {  
        Group group=GroupWizardController.mappToGroupEntity(groupWzrd); 
        UserProfile user=groupService.getUserProfile(login);
        System.out.println(user);
        group.getGroupUsers().add(user);
        user.getGroups().add(group);
        int status=groupService.insertGroups(group);
        FacesMessage msg=null;
        if(status==1){
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Group is Created" ,null);  
        FacesContext.getCurrentInstance().addMessage("groupStatus", msg); 
        }
        else{
        	msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Group is not Created" ,null);  
            FacesContext.getCurrentInstance().addMessage("groupStatus", msg); 
        }
    }
}
