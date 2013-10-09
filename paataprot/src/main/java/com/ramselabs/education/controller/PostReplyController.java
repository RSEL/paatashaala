package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.managedbean.ReplyBean;
import com.ramselabs.education.service.PostService;
@Named
@Scope("session")
public class PostReplyController implements Serializable{

	private static final long serialVersionUID = -6762249616598752940L;
    @Inject
    private PostService postService;
    
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
    @Inject
    private ManagedLoginBean login;
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
	
	private int postId;
	private int posterId;
	@Inject
	private ReplyBean replyBean;
	public void setReplyBean(ReplyBean replyBean) {
		this.replyBean = replyBean;
	}

	public int getPosterId() {
		return posterId;
	}

	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}


	public void insertReplies(){
		FacesContext context = FacesContext.getCurrentInstance();
	    postId = context.getApplication().evaluateExpressionGet(context, "#{post.postId}", Integer.class);
	    String userType=context.getApplication().evaluateExpressionGet(context, "#{post.userType}", String.class);
	    UserProfile user=ManagedLoginBean.mappToUserEntity(login);
	    posterId=postService.getUserId(user);
	    
		
	    PostShare postShare = new PostShare();
	    postShare.setUserType(userType);
		postShare.setPostDate(new Date());
		
		Post childPost=new Post();
		childPost.setDescription(replyBean.getReplyDescription());
		childPost.setPosterId(posterId);
		childPost.getPostShare().add(postShare);
		
		postShare.setPost(childPost);
		
		int status=postService.insertReply(childPost,postShare,postId);
		FacesMessage message = null;
		if (status == 1) {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Your reply is successfully posted", null);
			FacesContext.getCurrentInstance().addMessage("replySave",
					message);
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Your reply is not successfully posted", null);
			FacesContext.getCurrentInstance().addMessage("replySave",
					message);
		}

	}

}
