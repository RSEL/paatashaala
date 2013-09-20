package com.ramselabs.education.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.service.GroupService;

@Named
@Scope("session")
public class GroupFileUploadController implements Serializable{
	
	private static final long serialVersionUID = 2880569277626690405L;
    
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
	private int groupId;
	
	public int getGroupId() {
		return groupId;
	}

	public void handleFileUpload(FileUploadEvent event) {  
		FacesContext context = FacesContext.getCurrentInstance();
	    groupId = context.getApplication().evaluateExpressionGet(context, "#{groupProfileController.groupId}", Integer.class);
	    System.out.println(groupId);
	    //get uploaded file from the event
	    UploadedFile uploadedFile = (UploadedFile)event.getFile();
        String fileName=event.getFile().getFileName();
	    //create an InputStream from the uploaded file
	    InputStream inputStr = null;
	    try {
	        inputStr = uploadedFile.getInputstream();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    //create destination File
	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator+"img"+File.separator+"profile-photo"+File.separator+fileName;
	    File destFile = new File(newFileName);
	    FacesMessage message = null;
	    String imagePath="/resources/img/profile-photo/"+fileName;
	    int status=groupService.updateGroupImage(groupId, imagePath);
	    //use org.apache.commons.io.FileUtils to copy the File
	    try {                    
	        FileUtils.copyInputStreamToFile(inputStr, destFile);
	        System.out.println("FileUpload is working");
	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    if(status==1){
	      message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File is uploaded successfully", null);
  	      FacesContext.getCurrentInstance().addMessage("serverMsg", message);
	    }
	    else{
	    	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File is not  uploaded successfully", null);
	  	      FacesContext.getCurrentInstance().addMessage("serverMsg", message);
	    }
	}

}
