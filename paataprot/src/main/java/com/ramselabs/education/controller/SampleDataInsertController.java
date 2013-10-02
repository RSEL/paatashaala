package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.GroupUploadModel;
import com.ramselabs.education.model.GroupUserUploadModel;
import com.ramselabs.education.model.UploadModel;
import com.ramselabs.education.model.UserModel;
import com.ramselabs.education.service.GroupService;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class SampleDataInsertController implements Serializable {

	private static final long serialVersionUID = -3707781021296860369L;
	@Inject
	private DialogueDataFeedController dlgController;

	public void setDlgController(DialogueDataFeedController dlgController) {
		this.dlgController = dlgController;
	}

	@Inject
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Inject
	private GroupService groupService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void insert() {
		int status = 0;
		List<UploadModel> listUploadModel = dlgController.getListModel();
		for (UploadModel uploadModel : listUploadModel) {
			if (uploadModel instanceof UserModel) {
				UserProfile user = UserModel
						.getUserProfile((UserModel) uploadModel);
				status = userService.insertUser(user);
			}
			if (uploadModel instanceof GroupUploadModel) {
				Group group = GroupUploadModel
						.getGroupEntity((GroupUploadModel) uploadModel);
				status = groupService.insertGroups(group);
			}
			if (uploadModel instanceof GroupUserUploadModel) {
				status = groupService.insertGroupUser((GroupUserUploadModel)uploadModel);
			}
		}
		FacesMessage message=null;
		if(status==1){
		      message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Data is inserted successfully", null);
	  	      FacesContext.getCurrentInstance().addMessage("serverDataInsert", message);
		    }
		    else if(status==0){
		    	  message=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data is not inserted successfully,may be unique constraint is violated", null);
		  	      FacesContext.getCurrentInstance().addMessage("serverDataInsert", message);
		    }
		    else if(status==2){
		    	  message=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data is not inserted successfully,may be User is already associated with this group", null);
		  	      FacesContext.getCurrentInstance().addMessage("serverDataInsert", message);
		    }
	}

}
