package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.managedbean.RejectDialogBean;
import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class PendingPostRejectController implements Serializable{

	private static final long serialVersionUID = 8198885834878865593L;
	
	@Inject
	private PostService postService;
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	@Inject
	private RejectDialogBean rejectDlgBean;
	
	public void setRejectDlgBean(RejectDialogBean rejectDlgBean) {
		this.rejectDlgBean = rejectDlgBean;
	}
	
	public void reject(ActionEvent event){
		int approvalId = getActionAttribute(event, "approvalId");
		System.out.println("From dlg:"+approvalId);
		String rejectReason=rejectDlgBean.getRejectReason();
		System.out.println("rejectReason"+rejectReason);
		int status=postService.updateMessageStatusReason(approvalId, rejectReason);
		FacesMessage message = null;
        if(status==1){
      	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Message is rejected", null);
      	  FacesContext.getCurrentInstance().addMessage("rejectStatus", message);
        }
        else{
        message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Message is not rejected", null);
  	  FacesContext.getCurrentInstance().addMessage("rejectStatus", message);
        }
        
	}
	private int getActionAttribute(ActionEvent event, String name) {
        return (Integer) event.getComponent().getAttributes().get(name);
    }

}
