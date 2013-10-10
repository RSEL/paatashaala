package com.ramselabs.education.serviceImpl;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.PostDAO;
import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.SharedFile;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.model.SharedFileModel;
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
	public int inserPost(Post post, PostShare postShare,MessageApproval approval,Collection<SharedFile> sharedFiles) {
		return postDao.insertPosts(post, postShare,approval,sharedFiles);
	}

	@Override
	public List<PostDescriptionModel> getAllPostsForUser(UserProfile user) {
	return postDao.getAllPosts(user);
	}

	@Override
	public String getDisplayName(int userId) {
		return postDao.getPoster(userId).getDisplayName();
	}

	@Override
	public int getUserId(UserProfile user) {
		return postDao.getUserId(user);
	}

	@Override
	public List<PostDescriptionModel> getAllPendingPostsForUser(UserProfile user) {
		return postDao.getAllPendingPosts(user);
	}

	@Override
	public int updateMessageStatus(int approvalId) {
		
		return postDao.updateCheckedMessageStatus(approvalId);
	}

	@Override
	public int updateMessageStatusReason(int approvalId, String rejectReason) {
		return postDao.setRejectReason(approvalId, rejectReason);
	}

	@Override
	public List<PostDescriptionModel> getAllPostsForModeration(UserProfile user) {
		return postDao.getAllPostsForModeration(user);
	}

	@Override
	public SharedFileModel getSharedFileModel(int shareId) {
		return postDao.getSharedFile(shareId);
	}

	@Override
	public Post getPost(int postId) {
		return postDao.getPost(postId);
	}

	@Override
	public int insertReply(Post post,PostShare postShare,String postType,String shareTo,int postId) {
		return postDao.insertReply(post,postShare,postType,shareTo,postId);
	}

}
