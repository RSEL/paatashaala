package com.ramselabs.education.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class DataFileUploadController implements Serializable{
    
	private static final long serialVersionUID = 2945827749279241229L;
	private String visibility="none";
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public void handleFileUpload(FileUploadEvent event) {  
        boolean flag=false;
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

	    String newFileName = servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+fileName;
	    File destFile = new File(newFileName);
	    FacesMessage message = null;
	    //use org.apache.commons.io.FileUtils to copy the File
	    try {	    	
	    	//System.out.println("FilenamePath"+FilenameUtils.getName());
	        FileUtils.copyInputStreamToFile(inputStr, destFile);
	        System.out.println("FileUpload is working");
	        visibility="block";
	        flag=true;
	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    if(flag){
	      message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File is uploaded successfully", null);
  	      FacesContext.getCurrentInstance().addMessage("serverMsg", message);
	    }
	    else{
	    	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File is not  uploaded successfully", null);
	  	      FacesContext.getCurrentInstance().addMessage("serverMsg", message);
	    }
	}
	public void checkUploadedFile(){
		 ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		 File folder=new File(servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"classes");
		 File [] listOfFiles=folder.listFiles();
	    	for(int i=0;i<listOfFiles.length;i++){
	    		if(listOfFiles[i].isFile()){
	    			
	    			if(listOfFiles[i].getName().equalsIgnoreCase("Book.xlsx")){
	    				 visibility="block";
	    			}
	    		}
	    	}
	}
}
