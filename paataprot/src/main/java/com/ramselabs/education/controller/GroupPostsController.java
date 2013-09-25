package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.GroupService;

@Named
@Scope("session")
public class GroupPostsController implements Serializable,ActionListener{

	private static final long serialVersionUID = -3882733305144666977L;
	
	@Inject
	private GroupService groupService;
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
    
	private List<PostDescriptionModel> listOfPosts;
	public List<PostDescriptionModel> getListOfPosts() {
		if(groupId==0)
			return null;
			listOfPosts=groupService.getAllPostsForGroup(groupId);
		
		return listOfPosts;
	}

	private int groupId;
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setGroupId(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
	    groupId = context.getApplication().evaluateExpressionGet(context, "#{groupProfileController.groupId}", Integer.class);
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		listOfPosts=getListOfPosts();
		
	}
}
