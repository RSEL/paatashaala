package com.ramselabs.education.dao.service;

import java.util.List;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

public interface PostDAO {
	public List<PostDescriptionModel> getPostPersons(int userId);
	public int insertPosts(Post post,PostShare postShare);
	public UserProfile getPoster(int userId);
	public int getUserId(UserProfile user);

}
