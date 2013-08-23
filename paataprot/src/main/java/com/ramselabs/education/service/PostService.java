package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

public interface PostService {
	public int inserPost(Post post,PostShare postShare);
	public List<PostDescriptionModel> getPostPersons(UserProfile user);
	public String getDisplayName(int userId);
	public int getUserId(UserProfile user);
}
