package com.ramselabs.education.dao.service;

import java.util.Collection;
import java.util.List;

import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.SharedFile;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.model.SharedFileModel;

public interface PostDAO {
	public int insertPosts(Post post,PostShare postShare,MessageApproval approval,Collection<SharedFile> sharedFiles);
	public UserProfile getPoster(int userId);
	public int getUserId(UserProfile user);
	public List<PostDescriptionModel> getAllPosts(UserProfile user);
	public List<PostDescriptionModel> getAllPendingPosts(UserProfile user);
	public int updateCheckedMessageStatus(int approvalId);
	public int setRejectReason(int approvalId, String rejectReason);
	public List<PostDescriptionModel> getAllPostsForModeration(UserProfile user);
	public SharedFileModel getSharedFile(int sharedId);
    public Post getPost(int postId);
    public int insertReply(Post post,PostShare postShare,String postType,String shareTo,int postId);
}
