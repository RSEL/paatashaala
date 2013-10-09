package com.ramselabs.education.managedbean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class ReplyBean implements Serializable{

	private static final long serialVersionUID = -1285837599616618014L;

	private String replyDescription;

	public String getReplyDescription() {
		return replyDescription;
	}

	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
	}
}
