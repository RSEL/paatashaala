package com.ramselabs.education.dao.service;

import java.util.List;

import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

public interface PostDAO {
	public int insertPosts(Post post,PostShare postShare,MessageApproval approval);
	public UserProfile getPoster(int userId);
	public int getUserId(UserProfile user);
	public List<PostDescriptionModel> getAllPosts(UserProfile user);
	public List<PostDescriptionModel> getAllPendingPosts(UserProfile user);
	public int updateCheckedMessageStatus(int approvalId);
	public int setRejectReason(int approvalId, String rejectReason);

}
