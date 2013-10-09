package com.ramselabs.education.service;

import java.util.Collection;
import java.util.List;

import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.SharedFile;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.model.SharedFileModel;

public interface PostService {
	public int inserPost(Post post,PostShare postShare,MessageApproval approval,Collection<SharedFile> sharedFiles);
	public List<PostDescriptionModel> getAllPostsForUser(UserProfile user);
	public String getDisplayName(int userId);
	public int getUserId(UserProfile user);
	public List<PostDescriptionModel> getAllPendingPostsForUser(UserProfile user);
	public int updateMessageStatus(int approvalId);
	public int updateMessageStatusReason(int approvalId, String rejectReason);
	public List<PostDescriptionModel> getAllPostsForModeration(UserProfile user);
	public SharedFileModel getSharedFileModel(int shareId);
	public Post getPost(int postId);
	public int insertReply(Post post,PostShare postShare,int postId);
}
