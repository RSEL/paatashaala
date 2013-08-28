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
import com.ramselabs.education.service.AlertService;

@Named
@Scope("session")
public class AlertController implements Serializable{
	
	private static final long serialVersionUID = 3588878517076626163L;
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

	public void setAutoCmplController(AutocompleteBeanController autoCmplController) {
		this.autoCmplController = autoCmplController;
	}

	@Inject
	private AlertService alertService;
	

	public void setAlertService(AlertService alertService) {
		this.alertService = alertService;
	}
	
	public void insertAlerts(){
	  UserProfile user1=ManagedLoginBean.mappToUserEntity(login);
	  int posterId=alertService.getUserId(user1);
	  
      PostShare postShare=new PostShare();
      postShare.setPostDate(new Date());
      postShare.setUserType("User");
      
      UserProfile user=autoCmplController.getSelectedUserProfiles();
      System.out.println("selected user profiles"+user);
      Post post=PostBean.mapToPost(postBean);
      post.setPosterId(posterId);
      post.setMessageType("alert");
      user.getPost().add(post);
      user.getUserPostShare().add(postShare);
      
      post.getPostShare().add(postShare);
      
      postShare.setPost(post);
      
      post.setPostUser(user);
      
      postShare.setPostShareUser(user);
      
      int i=alertService.insertAlert(post, postShare);
      FacesMessage message = null;
      if(i==1){
    	  message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Alert is successfully posted", null);
    	  FacesContext.getCurrentInstance().addMessage("alertSave", message);
      }
      else{
      message=new FacesMessage(FacesMessage.SEVERITY_INFO, "Your Alert is not successfully posted", null);
	  FacesContext.getCurrentInstance().addMessage("alertSave", message);
      }
      
      
      
	}


	
}
