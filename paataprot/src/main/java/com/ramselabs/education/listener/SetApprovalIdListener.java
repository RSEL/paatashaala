package com.ramselabs.education.listener;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope
public class SetApprovalIdListener implements Serializable{

	private static final long serialVersionUID = -4892926131766555137L;
    private int approvalId;
    
	public int getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}
    
	public void setApprovalIdForMessage(ActionEvent event){
		 approvalId = getActionAttribute(event, "approvalId");
		 System.out.println("AppIdFor Dialog:"+approvalId);
	}
	private int getActionAttribute(ActionEvent event, String name) {
        return (Integer) event.getComponent().getAttributes().get(name);
    }
}
