package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.service.GroupService;

@Named
@Scope("session")
public class GroupProfileController implements ActionListener,Serializable{

	private static final long serialVersionUID = -483613042315728923L;
	
	@Inject
	private GroupService groupService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@Inject
	private GroupWizardController groupWzd;
	
	public void setGroupWzd(GroupWizardController groupWzd) {
		this.groupWzd = groupWzd;
	}

	private String imagePath;
	private String displayName;
    private int groupId;
    private String groupDescription;
    
	public String getGroupDescription() {
		if(groupId==0)
			return null;
		groupDescription=groupService.getGroup(groupId).getGroupDescription();
		return groupDescription;
	}


	public int getGroupId() {
		return groupId;
	}


	public String getImagePath() {
		if(groupId==0)
			return null;
		if(groupService.getGroup(groupId).getImagePath()==null)
			imagePath="/resources/img/profile-photo/group-blank.png";
		else
			imagePath=groupService.getGroup(groupId).getImagePath();
		return imagePath;
	}


	public String getDisplayName() {
		if(groupId==0)
			return null;
		displayName=groupService.getGroup(groupId).getDisplayName();
		return displayName;
	}
	public void setGroupId(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
	    groupId = context.getApplication().evaluateExpressionGet(context, "#{group.groupId}", Integer.class);
	    System.out.println("GrpPro"+groupId);
	}


	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		
		
	}

}
