package com.ramselabs.education.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.PostDAO;
import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.Role;
import com.ramselabs.education.entity.SharedFile;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.model.ReplyDescriptionModel;
import com.ramselabs.education.model.SharedFileModel;

@Named
@Scope("session")
public class PostDAOImpl implements PostDAO {

	@Inject
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<PostDescriptionModel> getPostPersons(UserProfile user) {
		int userId = getUserId(user);
		if (userId == 0)
			return null;
		List<PostDescriptionModel> listPerson = new ArrayList<PostDescriptionModel>();
		Session session = sessionFactory.openSession();
		UserProfile userProfile = (UserProfile) session.get(UserProfile.class,
				userId);
		List<PostShare> posts = (List<PostShare>) userProfile
				.getUserPostShare();
		// System.out.println(posts);
		if (posts.isEmpty())
			return null;

		for (PostShare postShare : posts) {
			 if(postShare.getPost().getPostApproval()==null)
				 continue;
			PostDescriptionModel postDescription = new PostDescriptionModel();
			if (postShare.getPost().getPostApproval().getStatus()
					.equals("approved")) {
				List<Post> subPosts=(List<Post>)postShare.getPost().getSubPosts();
				List<ReplyDescriptionModel> replyPostList=new ArrayList<ReplyDescriptionModel>();
				for(Post subPost:subPosts){
					
					for(PostShare replyPostShare:subPost.getPostShare()){
						ReplyDescriptionModel replyDesc=new ReplyDescriptionModel();
						if(getPoster(replyPostShare.getPost().getPosterId()).getImagePath()==null){
							replyDesc.setImagePath("/resources/img/profile-photo/default-profile.jpg");
						}
						else
						   replyDesc.setImagePath(getPoster(replyPostShare.getPost().getPosterId()).getImagePath());
						replyDesc.setPostDescription(replyPostShare.getPost().getDescription());
						replyDesc.setSentDate(replyPostShare.getPostDate());
						replyDesc.setPostId(replyPostShare.getPost().getPostId());
						replyDesc.setPosterName(getPoster(replyPostShare.getPost().getPosterId()).getDisplayName());
						replyPostList.add(replyDesc);
						
						
					}
					
					
				}
				Collections.sort(replyPostList,new ReplyDescriptionModel());
				postDescription.setListReplies(replyPostList);
				postDescription.setPersonName(getPoster(
						postShare.getPost().getPosterId()).getDisplayName());
				postDescription.setPostDescription(postShare.getPost()
						.getDescription());
				postDescription.setPostId(postShare.getPost().getPostId());
				postDescription.setUserType(postShare.getUserType());
				postDescription.setMessageType(postShare.getPost()
						.getMessageType());
				if(!postShare.getPost().getSharedFiles().isEmpty())
				    postDescription.setListOfSharedFiles((List<SharedFile>)postShare.getPost().getSharedFiles());
				String image = getPoster(postShare.getPost().getPosterId())
						.getImagePath();
				System.out.println(image);
				if (image == null)
					image = "/resources/img/profile-photo/default-profile.jpg";
				postDescription.setUserImage(image);
				System.out.println("Date of posting" + postShare.getPostDate());
				postDescription.setDateOfPosting(postShare.getPostDate());
				postDescription.setShareToName(postShare.getPostShareUser()
						.getDisplayName());
				String shareToImage=postShare.getPostShareUser()
						.getImagePath();
				if(shareToImage==null){
					shareToImage="/resources/img/profile-photo/profile-icon.jpg";
					postDescription.setShareToImage(shareToImage);
				}
				else{
				postDescription.setShareToImage(postShare.getPostShareUser()
						.getImagePath());
				}
				postDescription.setRejectStatus("approved");

				listPerson.add(postDescription);

			}

		}
		return listPerson;
	}

	@Override
	public int insertPosts(Post post, PostShare postShare,
			MessageApproval approval,Collection<SharedFile> sharedFiles) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(SharedFile shrdFile:sharedFiles)
			session.save(shrdFile);
		session.save(postShare);
		session.saveOrUpdate(post);
		session.save(approval);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return 1;
	}

	@Override
	public UserProfile getPoster(int userId) {
		Session session = sessionFactory.openSession();
		UserProfile user = (UserProfile) session.get(UserProfile.class, userId);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUserId(UserProfile user) {
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("select userId from UserProfile where username = :username and password = :password");
		query.setString("username", user.getUsername());
		query.setString("password", user.getPassword());
		List<Integer> list = (List<Integer>) query.list();
		if (list.isEmpty())
			System.out.println("List is empty for userId");
		int userId = 0;
		for (int j : list) {
			System.out.println("List is not empty");
			j = list.get(0);
			userId = j;
		}
		return userId;
	}

	@SuppressWarnings("unchecked")
	public List<PostDescriptionModel> getPostsFromSamePerson(UserProfile user) {
		List<PostDescriptionModel> list = new ArrayList<PostDescriptionModel>();
		int posterId = getUserId(user);
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from Post where posterId =:posterId");
		query.setInteger("posterId", posterId);
		List<Post> posts = (List<Post>) query.list();
		if (posts.isEmpty())
			return null;
		for (Post post : posts) {
			if(post.getPostApproval()==null)
				continue;
			List<PostShare> postShares = (List<PostShare>) post.getPostShare();
			for (PostShare pShare : postShares) {
				if (pShare.getPost().getPostApproval().getStatus()
						.equals("approved") | pShare.getPost().getPostApproval().getStatus()
						.equals("rejected")) {
					PostDescriptionModel postDescModel = new PostDescriptionModel();
					List<Post> subPosts=(List<Post>)pShare.getPost().getSubPosts();
					List<ReplyDescriptionModel> replyPostList=new ArrayList<ReplyDescriptionModel>();
					for(Post subPost:subPosts){
						
						for(PostShare replyPostShare:subPost.getPostShare()){
							ReplyDescriptionModel replyDesc=new ReplyDescriptionModel();
							if(getPoster(replyPostShare.getPost().getPosterId()).getImagePath()==null){
								replyDesc.setImagePath("/resources/img/profile-photo/default-profile.jpg");
							}
							else
							   replyDesc.setImagePath(getPoster(replyPostShare.getPost().getPosterId()).getImagePath());
							replyDesc.setPostDescription(replyPostShare.getPost().getDescription());
							replyDesc.setSentDate(replyPostShare.getPostDate());
							replyDesc.setPostId(replyPostShare.getPost().getPostId());
							replyDesc.setPosterName(getPoster(replyPostShare.getPost().getPosterId()).getDisplayName());
							replyPostList.add(replyDesc);
						}
						
						
					}
					Collections.sort(replyPostList,new ReplyDescriptionModel());
					postDescModel.setListReplies(replyPostList);
					postDescModel.setPersonName(getPoster(posterId)
							.getDisplayName());
					if(!pShare.getPost().getSharedFiles().isEmpty())
					    postDescModel.setListOfSharedFiles((List<SharedFile>)pShare.getPost().getSharedFiles());
					String image=getPoster(posterId).getImagePath();
					if(image==null)
						postDescModel.setUserImage("/resources/img/profile-photo/default-profile.jpg");
					else
					    postDescModel.setUserImage(getPoster(posterId).getImagePath());
					
					if (pShare.getPost().getPostApproval().getStatus()
							.equals("rejected")) {
						postDescModel.setPostDescription("<strike>"
								+ pShare.getPost().getDescription()
								+ "</strike>");
						postDescModel.setRejectReason("Reason:"
								+ " "
								+ pShare.getPost().getPostApproval()
										.getRejectReason());
					} else {
						postDescModel.setPostDescription(pShare.getPost()
								.getDescription());
					}
					postDescModel.setUserType(pShare.getUserType());
					postDescModel.setPostId(pShare.getPost().getPostId());
					postDescModel.setDateOfPosting(pShare.getPostDate());
					postDescModel.setMessageType(pShare.getPost()
							.getMessageType());
					if (pShare.getPostShareUser() == null) {
						postDescModel.setShareToName(pShare.getShareGroup()
								.getDisplayName());
						if (pShare.getShareGroup().getImagePath() == null)
							 pShare.getShareGroup().setImagePath("/resources/img/profile-photo/group-blank.png");
						postDescModel.setShareToImage(pShare.getShareGroup()
								.getImagePath());
					} 
					 else {
						 postDescModel.setShareToName(pShare.getPostShareUser()
									.getDisplayName());
						if (pShare.getPostShareUser().getImagePath() == null)
							pShare.getPostShareUser().setImagePath("/resources/img/profile-photo/profile-icon.jpg");
						 postDescModel.setShareToImage(pShare.getPostShareUser()
								.getImagePath());
					}
					postDescModel.setRejectStatus(pShare.getPost()
							.getPostApproval().getStatus());
					list.add(postDescModel);
				}
			}

		}
		return list;
	}

	@Override
	public List<PostDescriptionModel> getAllPosts(UserProfile user) {
		List<PostDescriptionModel> list1 = getPostPersons(user);
		List<PostDescriptionModel> list2 = getPostsFromSamePerson(user);
		if (list1 == null & list2 == null)
			return null;
		if (list1 == null) {
			Collections.sort(list2, new PostDescriptionModel());
			return list2;
		} else if (list2 == null) {
			Collections.sort(list1, new PostDescriptionModel());
			return list1;
		}

		list1.addAll(list2);
		Collections.sort(list1, new PostDescriptionModel());
		return list1;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostDescriptionModel> getAllPendingPosts(UserProfile user) {
		int posterId = getUserId(user);
		if (posterId == 0)
			return null;
		List<PostDescriptionModel> listPerson = new ArrayList<PostDescriptionModel>();
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from Post where posterId = :posterId");
		query.setInteger("posterId", posterId);
		List<Post> posts = (List<Post>) query.list();
		if (posts.isEmpty())
			return null;
		for (Post post : posts) {
			if(post.getPostApproval()==null)
				continue;
			List<PostShare> postShares = (List<PostShare>) post.getPostShare();
			for (PostShare pShare : postShares) {
				if (pShare.getPost().getPostApproval().getStatus()
						.equals("pending")) {
					PostDescriptionModel postDescModel = new PostDescriptionModel();
					postDescModel.setPersonName(getPoster(posterId)
							.getDisplayName());
					if(!pShare.getPost().getSharedFiles().isEmpty())
					     postDescModel.setListOfSharedFiles((List<SharedFile>)pShare.getPost().getSharedFiles());
					String image=getPoster(posterId).getImagePath();
					if(image==null)
						postDescModel.setUserImage("/resources/img/profile-photo/default-profile.jpg");
					else
					    postDescModel.setUserImage(getPoster(posterId).getImagePath());
					
					postDescModel.setPostDescription(pShare.getPost()
							.getDescription());
					postDescModel.setUserType(pShare.getUserType());
					postDescModel.setDateOfPosting(pShare.getPostDate());
					postDescModel.setMessageType(pShare.getPost()
							.getMessageType());
					postDescModel.setDisplayType("none");
					if (pShare.getPostShareUser() == null) {
						postDescModel.setShareToName(pShare.getShareGroup()
								.getDisplayName());
						if (pShare.getShareGroup().getImagePath() == null)
						    postDescModel.setShareToImage("/resources/img/profile-photo/group-blank.png");
						else
							postDescModel.setShareToImage(pShare.getShareGroup().getImagePath());
					} else {
						postDescModel.setShareToName(pShare.getPostShareUser()
								.getDisplayName());
						if (pShare.getPostShareUser().getImagePath() == null)
						    postDescModel.setShareToImage("/resources/img/profile-photo/profile-icon.jpg");
						else
							postDescModel.setShareToImage(pShare.getPostShareUser().getImagePath());
					}
					postDescModel.setRejectStatus(pShare.getPost()
							.getPostApproval().getStatus());
					listPerson.add(postDescModel);
				}

			}
		}
		return listPerson;
	}

	@Override
	public int updateCheckedMessageStatus(int approvalId) {
		Session session = sessionFactory.openSession();
		MessageApproval approval = (MessageApproval) session.get(
				MessageApproval.class, approvalId);
		approval.setStatus("approved");
		session.beginTransaction();
		session.update(approval);
		session.getTransaction().commit();
		return 1;
	}

	@Override
	public int setRejectReason(int approvalId, String rejectReason) {
		Session session = sessionFactory.openSession();
		MessageApproval approval = (MessageApproval) session.get(
				MessageApproval.class, approvalId);
		approval.setStatus("rejected");
		approval.setRejectReason(rejectReason);
		session.beginTransaction();
		session.update(approval);
		session.getTransaction().commit();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostDescriptionModel> getAllPostsForModeration(UserProfile user) {
		List<PostDescriptionModel> listPerson = new ArrayList<PostDescriptionModel>();
		Session session = sessionFactory.openSession();
		boolean flag = false;
		int userId = getUserId(user);
		if (userId == 0)
			return null;
		UserProfile userProfile = (UserProfile) session.get(UserProfile.class,
				userId);
		List<Role> userRole = (List<Role>) userProfile.getUserRoles();
		for (Role role : userRole) {
			if (role.equals(role.getRoleEnum())){
				if(role.getRoleName().equalsIgnoreCase("moderator"))
				flag = true;
			}
		}

		Query query = session
				.createQuery("select p from Post as p join p.postApproval as mess where mess.status = :status");
		query.setString("status", "pending");
		List<Post> posts = (List<Post>) query.list();
		if (flag) {
			for (Post post : posts) {
				if(post.getPostApproval()==null)
					continue;
				List<PostShare> postShares = (List<PostShare>) post
						.getPostShare();
				for (PostShare postShare : postShares) {
					PostDescriptionModel postDescription = new PostDescriptionModel();
					postDescription
							.setPersonName(getPoster(
									postShare.getPost().getPosterId())
									.getDisplayName());
					postDescription.setPostDescription(postShare.getPost()
							.getDescription());
					postDescription.setUserType(postShare.getUserType());
					postDescription.setMessageType(postShare.getPost()
							.getMessageType());
					if(!postShare.getPost().getSharedFiles().isEmpty())
					     postDescription.setListOfSharedFiles((List<SharedFile>)postShare.getPost().getSharedFiles());
					String image = getPoster(postShare.getPost().getPosterId())
							.getImagePath();
					System.out.println(image);
					if (image == null)
						image = "/resources/img/profile-photo/default-profile.jpg";
					postDescription.setUserImage(image);
					System.out.println("Date of posting"
							+ postShare.getPostDate());
					postDescription.setDateOfPosting(postShare.getPostDate());
					if (postShare.getPostShareUser() == null) {
						postDescription.setShareToName(postShare
								.getShareGroup().getDisplayName());
					} else {
						postDescription.setShareToName(postShare
								.getPostShareUser().getDisplayName());
					}
					postDescription.setApprovalId(postShare.getPost()
							.getPostApproval().getApprovalId());
					listPerson.add(postDescription);

				}
			}
		} else {
			listPerson = getAllPendingPosts(user);
		}
		return listPerson;
	}

	@Override
	public SharedFileModel getSharedFile(int sharedId) {
		Session session = sessionFactory.openSession();
		SharedFile sharedFile=(SharedFile)session.get(SharedFile.class, sharedId);
		SharedFileModel sharedModel=new SharedFileModel();
		sharedFile.getMetaData();
		sharedModel.setExtention(sharedFile.getExtention());
		sharedModel.setFileName(sharedFile.getFileName());
		sharedModel.setIconPath(sharedFile.getIconPath());
		sharedModel.setLink(sharedFile.getLink());
		sharedModel.setId(sharedFile.getSharedFileId());
		return sharedModel;
	}

	@Override
	public Post getPost(int postId) {
		Session session = sessionFactory.openSession();
		Post post=(Post)session.get(Post.class, postId);
		return post;
	}

	@Override
	public int insertReply(Post childPost,PostShare postShare,int postId) {
		Session session = sessionFactory.openSession();
		Post parentPost=(Post)session.get(Post.class, postId);
		UserProfile user=parentPost.getPostUser();
		childPost.setMessageType(parentPost.getMessageType());
		
		user.getPost().add(childPost);
		
		if(childPost.getPosterId()==parentPost.getPostUser().getUserId()){
			childPost.setPostUser(getPoster(parentPost.getPosterId()));
			postShare.setPostShareUser(getPoster(parentPost.getPosterId()));
		}
		else{
		   childPost.setPostUser(user);
		   postShare.setPostShareUser(user);
		}
		
		user.getUserPostShare().add(postShare);
		
		parentPost.getSubPosts().add(childPost);
		childPost.setParentPost(parentPost);
		session.beginTransaction();
		session.saveOrUpdate(childPost);
		session.saveOrUpdate(postShare);
		session.getTransaction().commit();
		session.flush();
		return 1;
	}
}
