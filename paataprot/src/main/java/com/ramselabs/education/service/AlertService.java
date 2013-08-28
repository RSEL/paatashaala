package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

public interface AlertService {
	public List<PostDescriptionModel> getAlerts(UserProfile user);
	public int insertAlert(Post post , PostShare pShare);
	public int getUserId(UserProfile user);

}
