package com.ramselabs.education.listener;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class PendingPostCheckedListener implements Serializable{

	private static final long serialVersionUID = 3000547694043310234L;
	
	@Inject
	private PostService postService;
	
	 public void setPostService(PostService postService) {
		this.postService = postService;
	}
	public void checkPost(ActionEvent event) {
	        int approvalId = getActionAttribute(event, "approvalId");
	        System.out.println("approvalId:"+approvalId);
	        int status=postService.updateMessageStatus(approvalId);
	        System.out.println("appStatus:"+status);
	        FacesMessage message = null;
	        if(status==1){
	      	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Message is successfully accepted", null);
	      	  FacesContext.getCurrentInstance().addMessage("approvalStatus", message);
	        }
	        else{
	        message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Message is not successfully accepted", null);
	  	  FacesContext.getCurrentInstance().addMessage("approvalStatus", message);
	        }
	        
	    }
	 private int getActionAttribute(ActionEvent event, String name) {
	        return (Integer) event.getComponent().getAttributes().get(name);
	    }
}
