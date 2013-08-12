package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.Post_Share;
import com.ramselabs.education.managedbean.PostBean;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class PostController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731304387140442749L;

	@Inject
	private PostBean postBean;
	
	@Inject
	UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}
	public void insertPosts(){
      Post post=PostBean.mapToPost(postBean);
      int po=userService.inserPost(post);
      Post_Share post_share=new Post_Share();
      
      
	}
}
