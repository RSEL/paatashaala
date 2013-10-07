package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.SharedFile;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.managedbean.PostBean;
import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class PostController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731304387140442749L;

	public PostController() {
		System.out.println("PostController Object is created");
	}

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

	public void setAutoCmplController(
			AutocompleteBeanController autoCmplController) {
		this.autoCmplController = autoCmplController;
	}

	@Inject
	private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void insertPosts() {
		if (autoCmplController.getSelectedUserProfiles() instanceof UserProfile) {
			UserProfile user1 = ManagedLoginBean.mappToUserEntity(login);
			int posterId = postService.getUserId(user1);

			PostShare postShare = new PostShare();
			postShare.setPostDate(new Date());
			postShare.setUserType("User");

			MessageApproval approval = new MessageApproval();
			approval.setStatus("pending");
			UserProfile user = (UserProfile) autoCmplController
					.getSelectedUserProfiles();
			System.out.println("selected user profiles" + user);
			Post post = PostBean.mapToPost(postBean);
			Collection<SharedFile> sharedFiles=PostBean.getAllSharedFiles(postBean);
			
			post.setSharedFiles(sharedFiles);
			
			post.setPosterId(posterId);
			post.setMessageType("post");

			user.getPost().add(post);
			user.getUserPostShare().add(postShare);
			user.getApprovals().add(approval);

			post.getPostShare().add(postShare);

			postShare.setPost(post);

			post.setPostUser(user);
			post.setPostApproval(approval);

			postShare.setPostShareUser(user);

			approval.setUserApproval(user);
			approval.setApprovalPost(post);

			for(SharedFile shrdFile:sharedFiles)
				shrdFile.setShredFilePost(post);
			
			int i = postService.inserPost(post, postShare, approval,sharedFiles);
			FacesMessage message = null;
			if (i == 1) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your post is successfully posted", null);
				FacesContext.getCurrentInstance().addMessage("postSave",
						message);
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your post is not successfully posted", null);
				FacesContext.getCurrentInstance().addMessage("postSave",
						message);
			}

		}
		if(autoCmplController.getSelectedUserProfiles() instanceof Group){
			UserProfile user1 = ManagedLoginBean.mappToUserEntity(login);
			int posterId = postService.getUserId(user1);

			PostShare postShare = new PostShare();
			postShare.setPostDate(new Date());
			postShare.setUserType("Group");

			MessageApproval approval = new MessageApproval();
			approval.setStatus("pending");
			Group group = (Group) autoCmplController
					.getSelectedUserProfiles();
			Post post = PostBean.mapToPost(postBean);
			Collection<SharedFile> sharedFiles=PostBean.getAllSharedFiles(postBean);
			
			post.setSharedFiles(sharedFiles);
			
			post.setPosterId(posterId);
			post.setMessageType("post");

			group.getGroupPosts().add(post);
			group.getGroupShares().add(postShare);

			post.getPostShare().add(postShare);
            post.getGroups().add(group);
            post.setPostApproval(approval);
            
			postShare.setPost(post);
			postShare.setShareGroup(group);
			
			approval.setApprovalPost(post);

			for(SharedFile sharedFile:sharedFiles)
				sharedFile.setShredFilePost(post);
			
			int i = postService.inserPost(post, postShare, approval,sharedFiles);
			FacesMessage message = null;
			if (i == 1) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your group-post is successfully posted", null);
				FacesContext.getCurrentInstance().addMessage("postSave",
						message);
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your group-post is not successfully posted", null);
				FacesContext.getCurrentInstance().addMessage("postSave",
						message);
			}
		}

	}
}
