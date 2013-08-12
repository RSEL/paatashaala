package com.ramselabs.education.managedbean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.controller.AutocompleteBeanController;
import com.ramselabs.education.entity.Post;

@Named
@Scope("session")
public class PostBean implements Serializable{

	private static final long serialVersionUID = 1592538150710683174L;
    private String description;
    @Inject
    private AutocompleteBeanController autocompleteBeanController;
    public void setAutocompleteBeanController(
			AutocompleteBeanController autocompleteBeanController) {
		this.autocompleteBeanController = autocompleteBeanController;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AutocompleteBeanController getAutocompleteBeanController() {
		return autocompleteBeanController;
	}
	public static Post mapToPost(PostBean postBean){
		Post post=new Post();
		post.setDescription(postBean.getDescription());
		post.setUserId(postBean.autocompleteBeanController.getSelectedShares().get(0).getUserId());
		return post;
	}
}
