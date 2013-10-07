package com.ramselabs.education.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.managedbean.PostBean;

@Named
@Scope("session")
public class UplController implements Serializable,ActionListener{

	private static final long serialVersionUID = 8649124742579392057L;
    private List<UploadedFile> uplFiles=new ArrayList<UploadedFile>();
    private boolean renderFileName = false;
    @Inject
    private PostBean postBean;
    public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}

	public void handle(FileUploadEvent event) {
    	UploadedFile uploadedFile=event.getFile();
    	 InputStream inputStr = null;
 	    try {
 	        inputStr = uploadedFile.getInputstream();
 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }

 	    //create destination File
 	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

 	    String newFileName = servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"sharedFiles"+File.separator+uploadedFile.getFileName();
 	    File destFile = new File(newFileName);
 	    FacesMessage message = null;
 	    boolean flag=false;
 	    //use org.apache.commons.io.FileUtils to copy the File
 	    try {	    	
 	    	//System.out.println("FilenamePath"+FilenameUtils.getName());
 	        FileUtils.copyInputStreamToFile(inputStr, destFile);
 	        System.out.println("FileUpload is working");
 	        flag=true;
 	    } catch (IOException e) {
 	       e.printStackTrace();
 	    }
        uplFiles.add(uploadedFile);
        postBean.setUplFiles(uplFiles);
        renderFileName = true;
        // To Clear Any Error Messages
        RequestContext.getCurrentInstance().execute("jQuery(\"div.fileupload-content tr.ui-state-error\").remove();");
        if(flag){
          message=new FacesMessage(FacesMessage.SEVERITY_INFO, "File is  uploaded successfully", null);
	      FacesContext.getCurrentInstance().addMessage("serMsg", message);
        }
    }

	public List<UploadedFile> getUplFiles() {
		return uplFiles;
	}

	public void setUplFiles(List<UploadedFile> uplFiles) {
		this.uplFiles = uplFiles;
	}

	public boolean isRenderFileName() {
		return renderFileName;
	}

	public void setRenderFileName(boolean renderFileName) {
		this.renderFileName = renderFileName;
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		uplFiles.clear();
		
	}
    
    
}
