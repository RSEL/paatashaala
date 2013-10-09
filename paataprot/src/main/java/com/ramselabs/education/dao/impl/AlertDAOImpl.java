package com.ramselabs.education.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.AlertDAO;
import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

@Named
@Scope("session")
public class AlertDAOImpl implements AlertDAO {

	@Inject
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<PostDescriptionModel> getAllAlert(UserProfile user) {
		List<PostDescriptionModel> list1 = getAlertOtherPersons(user);
		List<PostDescriptionModel> list2 = getAlertsFromSamePerson(user);
		if (list1 == null & list2 == null)
			return null;
		if (list1 == null) {
			Collections.sort(list2, new PostDescriptionModel());
			return list2;
		} else if (list2 == null) {
			Collections.sort(list1, new PostDescriptionModel());
			System.out.println(list1.size());
			return list1;
		}

		list1.addAll(list2);
		Collections.sort(list1, new PostDescriptionModel());
		return list1;

	}

	@Override
	public int insertAlerts(Post post, PostShare pShare,
			MessageApproval approval) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(pShare);
		session.saveOrUpdate(post);
		session.save(approval);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return 1;
	}

	@SuppressWarnings("unchecked")
	private List<PostDescriptionModel> getAlertsFromSamePerson(UserProfile user) {
		List<PostDescriptionModel> list = new ArrayList<PostDescriptionModel>();
		int posterId = getUserId(user);
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from Post where messageType =:messageType and posterId =:posterId");
		query.setString("messageType", "alert");
		query.setInteger("posterId", posterId);
		List<Post> posts = (List<Post>) query.list();
		if (posts.isEmpty())
			return null;
		for (Post post : posts) {
			if(post.getPostApproval()==null)
				continue;
			List<PostShare> postShares = (List<PostShare>) post.getPostShare();

			for (PostShare pShare : postShares) {
				PostDescriptionModel postDescModel = new PostDescriptionModel();
				postDescModel.setPersonName(getPoster(posterId)
						.getDisplayName());
				postDescModel.setUserImage(getPoster(posterId).getImagePath());
				
				if(pShare.getPost().getPostApproval().getStatus().equals("rejected")){
                	postDescModel.setPostDescription("<strike>"+pShare.getPost()
    						.getDescription()+"</strike>");
                	postDescModel.setRejectReason("Reason:"+" "+pShare.getPost().getPostApproval().getRejectReason());
				  }
                else{
				postDescModel.setPostDescription(pShare.getPost()
						.getDescription());
                }
				postDescModel.setUserType(pShare.getUserType());
				postDescModel.setDateOfPosting(pShare.getPostDate());
				postDescModel.setMessageType(pShare.getPost().getMessageType());
				if(pShare.getPostShareUser()==null){
					postDescModel.setShareToName(pShare.getShareGroup().getDisplayName());
				}
				else{
				postDescModel.setShareToName(pShare.getPostShareUser().getDisplayName());
				}
				postDescModel.setRejectStatus(pShare.getPost().getPostApproval().getStatus());
				if(pShare.getPostShareUser()==null){
					if(pShare.getShareGroup().getImagePath()==null)
						pShare.getShareGroup().setImagePath("/resources/img/profile-photo/group-blank.png");
					postDescModel.setShareToImage(pShare.getShareGroup().getImagePath());
				}
				else{
					if(pShare.getPostShareUser().getImagePath()==null)
						pShare.getPostShareUser().setImagePath("/resources/img/profile-photo/profile-icon.jpg");
				    postDescModel.setShareToImage(pShare.getPostShareUser().getImagePath());
				}
				list.add(postDescModel);
			}
		}
		return list;
	}

	private List<PostDescriptionModel> getAlertOtherPersons(UserProfile user) {
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
			if (postShare.getPost().getMessageType().equals("alert")
					& postShare.getPost().getPostApproval().getStatus()
							.equals("approved")) {
				
				postDescription.setPersonName(getPoster(
						postShare.getPost().getPosterId()).getDisplayName());
				postDescription.setPostDescription(postShare.getPost()
						.getDescription());
				postDescription.setUserType(postShare.getUserType());
				postDescription.setMessageType(postShare.getPost()
						.getMessageType());
				String image = getPoster(postShare.getPost().getPosterId())
						.getImagePath();
				System.out.println(image);
				if (image == null)
					image = "/resources/img/profile-photo/default-profile.jpg";
				postDescription.setUserImage(image);
				System.out.println("Date of posting" + postShare.getPostDate());
				postDescription.setDateOfPosting(postShare.getPostDate());
				postDescription.setShareToName(postShare.getPostShareUser().getDisplayName());
				postDescription.setRejectStatus("approved");
				postDescription.setShareToImage(postShare.getPostShareUser().getImagePath());
				listPerson.add(postDescription);
			}
			if(postShare.getPost().getMessageType().equals("alert")
					& postShare.getPost().getPostApproval().getStatus()
							.equals("rejected")){
				postDescription.setPersonName(getPoster(
						postShare.getPost().getPosterId()).getDisplayName());
				postDescription.setPostDescription(postShare.getPost()
						.getDescription());
				postDescription.setUserType(postShare.getUserType());
				postDescription.setMessageType(postShare.getPost()
						.getMessageType());
				String image = getPoster(postShare.getPost().getPosterId())
						.getImagePath();
				System.out.println(image);
				if (image == null)
					image = "/resources/img/profile-photo/default-profile.jpg";
				postDescription.setUserImage(image);
				System.out.println("Date of posting" + postShare.getPostDate());
				postDescription.setDateOfPosting(postShare.getPostDate());
				postDescription.setShareToName(postShare.getPostShareUser().getDisplayName());
				postDescription.setRejectStatus("rejected");
				postDescription.setPostDescription("<strike>"+postShare.getPost()
						.getDescription()+"</strike>");
				postDescription.setRejectReason("Reason"+":"+postShare.getPost().getPostApproval().getRejectReason());
				postDescription.setShareToImage(postShare.getPostShareUser().getImagePath());
				listPerson.add(postDescription);

			}
			
				
			
		}

		return listPerson;
	}

	public UserProfile getPoster(int userId) {
		Session session = sessionFactory.openSession();
		UserProfile user = (UserProfile) session.get(UserProfile.class, userId);
		return user;
	}

	@SuppressWarnings("unchecked")
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
}
