package com.ramselabs.education.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.PostDAO;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class PostServiceImpl implements PostService {

	@Inject
	private PostDAO postDao;
	
	public void setPostDao(PostDAO postDao) {
		this.postDao = postDao;
	}

	@Override
	public int inserPost(Post post, PostShare postShare) {
		return postDao.insertPosts(post, postShare);
	}

	@Override
	public List<PostDescriptionModel> getPostPersons(UserProfile user) {
		int userId=postDao.getUserId(user);
     if(userId==0)
	   return null;
	return postDao.getPostPersons(userId);
	}

	@Override
	public String getDisplayName(int userId) {
		return postDao.getPoster(userId).getDisplayName();
	}

	@Override
	public int getUserId(UserProfile user) {
		return postDao.getUserId(user);
	}

}
