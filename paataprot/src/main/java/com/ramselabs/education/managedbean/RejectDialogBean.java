package com.ramselabs.education.managedbean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope
public class RejectDialogBean implements Serializable{

	private static final long serialVersionUID = 1100464881551219497L;
	
	private String rejectReason;

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	

}
