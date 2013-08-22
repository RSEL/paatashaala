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

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class FileUploadController implements Serializable{

	private static final long serialVersionUID = -4723390169525048578L;
	@Inject
	ManagedLoginBean login;
	
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
    
	@Inject
	UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void handleFileUpload(FileUploadEvent event) {  

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
	    UserProfile user=ManagedLoginBean.mappToUserEntity(login);
	    user.setImagePath("/resources/img/profile-photo/"+fileName);
	    int status=userService.updateUserImage(user);
	    //use org.apache.commons.io.FileUtils to copy the File
	    try {                    
	        FileUtils.copyInputStreamToFile(inputStr, destFile);
	        System.out.println("FileUpload is working");
	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    if(status==1){
	      message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File is uploaded in path /resources/img/profile-photo", null);
  	      FacesContext.getCurrentInstance().addMessage("serverMsg", message);
	    }
	    else{
	    	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File uploaded is not successfull", null);
	  	      FacesContext.getCurrentInstance().addMessage("serverMsg", message);
	    }
	}

}
