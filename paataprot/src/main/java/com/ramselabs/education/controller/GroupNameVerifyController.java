package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.service.GroupService;

@Named
@Scope("session")
public class GroupNameVerifyController implements Serializable{

	private static final long serialVersionUID = 787541183669385563L;
	
	@Inject
	private GroupService groupService;
	
	private String strResponse;
	
	private String groupName;
	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getStrResponse() {
		return strResponse;
	}


	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	
	public void verify(AjaxBehaviorEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
	    groupName = context.getApplication().evaluateExpressionGet(context, "#{groupWizardController.group.groupName}", String.class);
		boolean status = groupService.groupNameVerifyResponse(groupName);
		System.out.println(groupName);
		if(status)
			strResponse="Group name is accepted";
		else
			strResponse="Group name exits try different";
	}
	

}
