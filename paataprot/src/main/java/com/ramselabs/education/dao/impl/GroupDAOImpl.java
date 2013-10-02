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

import com.ramselabs.education.dao.service.GroupDAO;
import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.model.GroupUserUploadModel;
import com.ramselabs.education.model.PostDescriptionModel;

@Named
@Scope("session")
public class GroupDAOImpl implements GroupDAO {

	@Inject
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int createGroup(Group group) {
		Session session = sessionFactory.openSession();
		Query query=session.createQuery("from Group");
		List<Group> groups=(List<Group>)query.list();
		for(Group grp:groups){
			if(grp.getDisplayName().equalsIgnoreCase(group.getDisplayName())){
				return 0;
			}
		}
		session.beginTransaction();
		session.saveOrUpdate(group);
		session.getTransaction().commit();
		session.flush();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getUserProfile(ManagedLoginBean login) {
		Session session = sessionFactory.openSession();
		UserProfile userProfile = null;
		Query query = session
				.createQuery("from UserProfile where username = :username and password = :password");
		query.setString("username", login.getUsername());
		query.setString("password", login.getPassword());
		List<UserProfile> list = (List<UserProfile>) query.list();
		if (list.isEmpty()) {
			System.out.println("UserProfile is empty" + list);
			return null;
		}
		for (UserProfile profile : list)
			userProfile = profile;
		return userProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Group getGroupEntity(int groupId) {
		Group grp = null;
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from Group where groupId = :groupId");
		query.setInteger("groupId", groupId);
		List<Group> list = (List<Group>) query.list();
		if (list.isEmpty()) {
			System.out.println("Group is empty" + list);
			return null;
		}
		for (Group group : list)
			grp = group;
		return grp;
	}

	@Override
	public boolean groupNameVerifyResponse(String value) {
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from Group where displayName = :name");
		query.setString("name", value);
		@SuppressWarnings("unchecked")
		List<Group> list = (List<Group>) query.list();
		if (list.isEmpty())
			return true;
		else
			return false;
	}

	@Override
	public List<Group> getAllGroupsForCurrentUser(int userId) {
		Session session = sessionFactory.openSession();
		UserProfile user = (UserProfile) session.get(UserProfile.class, userId);
		if (user == null)
			return null;
		return (List<Group>) user.getGroups();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AutocompleteTemplate> getAllGroupForAutocomplete() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Group");
		List<AutocompleteTemplate> list = (List<AutocompleteTemplate>) query
				.list();
		for (AutocompleteTemplate auto : list) {
			if (auto.getImagePath() == null)
				auto.setImagePath("/resources/img/profile-photo/group-blank.png");
		}
		return list;
	}

	@Override
	public int updateImage(int groupId, String imagePath) {
		Session session = sessionFactory.openSession();
		Group group = (Group) session.get(Group.class, groupId);
		group.setImagePath(imagePath);
		session.beginTransaction();
		session.update(group);
		session.getTransaction().commit();
		return 1;
	}

	@Override
	public List<PostDescriptionModel> getAllMessagesForGroup(int groupId) {
		Session session = sessionFactory.openSession();
		Group group = (Group) session.get(Group.class, groupId);
		List<PostDescriptionModel> listPerson = new ArrayList<PostDescriptionModel>();
		List<PostShare> postShares = (List<PostShare>) group.getGroupShares();
		for (PostShare postShare : postShares) {
			PostDescriptionModel postDescription = new PostDescriptionModel();
			if (postShare.getPost().getPostApproval().getStatus()
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
				postDescription.setShareToName(postShare.getShareGroup()
						.getDisplayName());
				String shareImage=postShare.getShareGroup().getImagePath();
				if(shareImage==null)
					postDescription.setShareToImage("/resources/img/profile-photo/group-blank.png");
				else
				    postDescription.setShareToImage(postShare.getShareGroup().getImagePath());
				
				postDescription.setRejectStatus("approved");

				listPerson.add(postDescription);

			}
		}
		Collections.sort(listPerson, new PostDescriptionModel());
		return listPerson;
	}

	public UserProfile getPoster(int userId) {
		Session session = sessionFactory.openSession();
		UserProfile user = (UserProfile) session.get(UserProfile.class, userId);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int createGroupUser(GroupUserUploadModel groupUser) {
		Session session = sessionFactory.openSession();
		Query queryUser=session.createQuery("from UserProfile where username = :userName");
		queryUser.setString("userName", groupUser.getUserName());
		Collection<UserProfile> users=(Collection<UserProfile>)queryUser.list();
		Query queryGroup=session.createQuery("from Group where displayName = :displayName");
		queryGroup.setString("displayName", groupUser.getGroupName());
		Collection<Group> groups=(Collection<Group>)queryGroup.list();
		boolean flag=false;
		for(UserProfile user:users){
			for(Group group:groups){
				Collection<UserProfile> userProfiles=group.getGroupUsers();
				if(userProfiles.isEmpty())
					flag=true;
				for(UserProfile userProfile:userProfiles){
					if(userProfile.getUsername().equalsIgnoreCase(user.getUsername())){
						flag=false;
						return 2;
					}
					else
						flag=true;
				}
				if(flag){
				user.getGroups().add(group);
				group.getGroupUsers().add(user);
				session.beginTransaction();
				session.saveOrUpdate(group);
				session.getTransaction().commit();
				}
			}
		}
		return 1;
	}
}
