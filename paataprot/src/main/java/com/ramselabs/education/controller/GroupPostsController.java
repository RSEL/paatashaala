package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.inject.Inject;

import com.ramselabs.education.managedbean.PostBean;

public class GroupPostsController implements Serializable{

	private static final long serialVersionUID = -3882733305144666977L;

	@Inject
	private AutocompleteBeanController autoCmplController;

	public void setAutoCmplController(AutocompleteBeanController autoCmplController) {
		this.autoCmplController = autoCmplController;
	}
	@Inject
	private PostBean postBean;
	public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}
}
