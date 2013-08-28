package com.ramselabs.education.dao.service;

import java.util.List;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

public interface AlertDAO {
       public List<PostDescriptionModel> getAllAlert(UserProfile user);
       public int insertAlerts(Post post,PostShare pShare);
       public int getUserId(UserProfile user);
}
