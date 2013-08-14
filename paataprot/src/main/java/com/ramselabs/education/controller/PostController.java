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
	public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}
	
	@Inject
	private ManagedLoginBean login;
	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}

	@Inject
	private AutocompleteBeanController autoCmplController;
	
	public UserService getUserService() {
		return userService;
	}

	public void setAutoCmplController(AutocompleteBeanController autoCmplController) {
		this.autoCmplController = autoCmplController;
	}

	@Inject
	UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void insertPosts(){
	  UserProfile user1=ManagedLoginBean.mappToUserEntity(login);
	  int posterId=userService.getUserId(user1);
	  
      PostShare postShare=new PostShare();
      postShare.setPostDate(new Date());
      postShare.setUserType("User");
      
      UserProfile user=autoCmplController.getSelectedUserProfile();
      Post post=PostBean.mapToPost(postBean);
      post.setPosterId(posterId);
      
      user.getPost().add(post);
      user.getUserPostShare().add(postShare);
      
      post.getPost_share().add(postShare);
      
      postShare.setPost(post);
      
      post.setPostUser(user);
      
      postShare.setPostShareUser(user);
      
      int i=userService.inserPost(post, postShare);
   
      FacesMessage message = null;
      if(i==1){
    	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "You post is successfully posted", null);
    	  FacesContext.getCurrentInstance().addMessage(null, message);
      }
      else{
      message=new FacesMessage(FacesMessage.SEVERITY_INFO, "You post is not successfully posted", null);
	  FacesContext.getCurrentInstance().addMessage(null, message);
      }
      
      
      
	}
}
