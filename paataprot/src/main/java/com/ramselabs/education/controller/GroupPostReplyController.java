package com.ramselabs.education.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.service.GroupService;
import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class GroupPostReplyController implements Serializable{

	
	private static final long serialVersionUID = 6367201485416506726L;
	@Inject
	private ManagedLoginBean login;
	
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
    @Inject
    private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void insertReplies(){
		UserProfile user=null;
		FacesContext context = FacesContext.getCurrentInstance();
	    int postId = context.getApplication().evaluateExpressionGet(context, "#{post.postId}", Integer.class);
	}

}
