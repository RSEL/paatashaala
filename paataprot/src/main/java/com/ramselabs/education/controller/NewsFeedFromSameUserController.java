package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
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
@Scope("session")
public class NewsFeedFromSameUserController implements ActionListener ,Serializable{

	private static final long serialVersionUID = 1218523565246357221L;


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
		
		UserProfile user=ManagedLoginBean.mappToUserEntity(login);
		List<PostDescriptionModel> postDescs=postService.getAllPostsForUser(user);
		if(login==null || postDescs==null)
			return null;
		Collections.sort(postDescs, new Comparator<PostDescriptionModel>() {
		    public int compare(PostDescriptionModel s1, PostDescriptionModel s2) {
		        return s2.getDateOfPosting().compareTo(s1.getDateOfPosting());
		    }
		});
		return postDescs;
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		listOfPost=getAllPosts();
		
	}
	
}
