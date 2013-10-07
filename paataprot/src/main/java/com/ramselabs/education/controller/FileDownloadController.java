package com.ramselabs.education.controller;

import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.SharedFileModel;
import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class FileDownloadController implements Serializable{

	private static final long serialVersionUID = 8530704017630052219L;
	private int shareFileId;
	private StreamedContent file;
	@Inject
	private PostService postServices;
	
	public void setPostServices(PostService postServices) {
		this.postServices = postServices;
	}
	public FileDownloadController() { 
		
    }  
	public int getShareFileId() {
		return shareFileId;
	}



	public void setShareFileId(int shareFileId) {
		this.shareFileId = shareFileId;
	}



	public StreamedContent getFile() {
		return file;
	}
	public void setSharedLink(){
        String contentType=null;
    	FacesContext context = FacesContext.getCurrentInstance();
    	shareFileId = context.getApplication().evaluateExpressionGet(context, "#{shrdFile.sharedFileId}", Integer.class);
    	SharedFileModel shrdFileModel=postServices.getSharedFileModel(shareFileId);
    	InputStream stream =FileDownloadController.class.getResourceAsStream("/../"+shrdFileModel.getLink());
    	String extention=shrdFileModel.getExtention();
    	if(extention.equalsIgnoreCase("txt"))
    		contentType="text/plain";
    	if(extention.equalsIgnoreCase("docx"))
    		contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    	if(extention.equalsIgnoreCase("xls"))
    		contentType="application/vnd.ms-excel";
    	if(extention.equalsIgnoreCase("xlsx"))
    		contentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    	if(extention.equalsIgnoreCase("pdf"))
    		contentType="application/pdf";
    	if(extention.equalsIgnoreCase("jpg") || extention.equalsIgnoreCase("jpeg"))
    		contentType="image/jpeg";
    	if(extention.equalsIgnoreCase("png"))
    		contentType="image/png";
    	if(extention.equalsIgnoreCase("gif"))
    		contentType="image/gif";
    	if(extention.equalsIgnoreCase("pptx") || extention.equalsIgnoreCase("ppt"))
    	   contentType="application/vnd.openxmlformats-officedocument.presentationml.presentation";
        file = new DefaultStreamedContent(stream,contentType,shrdFileModel.getFileName());  
        System.out.println(file);
    }
}
