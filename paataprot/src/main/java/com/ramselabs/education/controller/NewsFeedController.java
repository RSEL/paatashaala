package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.PostService;

@Named
@Scope("request")
public class NewsFeedController implements ActionListener, Serializable{

	private static final long serialVersionUID = 4322730194350739587L;
   
	@Inject
	private PostService postService;
	
	@Inject
	private ManagedLoginBean login;

	

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void setLogin(ManagedLoginBean login) {
		this.login = login;
	}
	private List<PostDescriptionModel> listOfPost;
	
	public List<PostDescriptionModel> getListOfPost() {
		if(listOfPost==null){
			listOfPost=getAllPosts();
		}
		return listOfPost;
	}

	public List<PostDescriptionModel> getAllPosts(){
		if(login==null)
			return null;
		UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		return postService.getAllPostsForUser(user);
	}
	
	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		listOfPost=getAllPosts();
		
	}
	
}
