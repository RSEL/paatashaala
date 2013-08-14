package com.ramselabs.education.managedbean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Post;

@Named
@Scope("session")
public class PostBean implements Serializable{

	private static final long serialVersionUID = 1592538150710683174L;
    private String description;
  
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static Post mapToPost(PostBean postBean){
		Post post=new Post();
		post.setDescription(postBean.getDescription());
		return post;
	}
}
