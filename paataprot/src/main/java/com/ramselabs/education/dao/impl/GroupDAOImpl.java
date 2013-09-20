package com.ramselabs.education.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.GroupDAO;
import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.AutocompleteTemplate;

@Named
@Scope("session")
public class GroupDAOImpl implements GroupDAO {

	@Inject
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public int createGroup(Group group) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(group);
		session.getTransaction().commit();
		session.flush();
		return 1;
	}


	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getUserProfile(ManagedLoginBean login) {
		Session session=sessionFactory.openSession();
		UserProfile userProfile=null;
		Query query=session.createQuery("from UserProfile where username = :username and password = :password");
		query.setString("username",login.getUsername());
		query.setString("password", login.getPassword());
		List<UserProfile> list=(List<UserProfile>)query.list();
		if(list.isEmpty()){
			System.out.println("UserProfile is empty"+list);
			return null;
		}
		for(UserProfile profile:list)
			  userProfile=profile;
		return userProfile;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Group getGroupEntity(int groupId) {
		Group grp=null;
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Group where groupId = :groupId");
		query.setInteger("groupId",groupId);
		List<Group> list=(List<Group>)query.list();
		if(list.isEmpty()){
			System.out.println("Group is empty"+list);
			return null;
		}
		for(Group group:list)
			  grp=group;
		return grp;
	}


	@Override
	public boolean groupNameVerifyResponse(String value) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Group where displayName = :name");
		query.setString("name",value);
		@SuppressWarnings("unchecked")
		List<Group> list=(List<Group>)query.list();
		if(list.isEmpty())
			return true;
		else
		  return false;
	}


	@Override
	public List<Group> getAllGroupsForCurrentUser(int userId) {
		Session session=sessionFactory.openSession();
		UserProfile user=(UserProfile)session.get(UserProfile.class, userId);
		if(user==null)
			return null;
		return (List<Group>)user.getGroups();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<AutocompleteTemplate> getAllGroupForAutocomplete() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Group");
		List<AutocompleteTemplate> list=(List<AutocompleteTemplate>)query.list();
		for(AutocompleteTemplate auto:list){
			if(auto.getImagePath()==null)
				auto.setImagePath("/resources/img/profile-photo/group-blank.png");
		}
		return list;
	}


	@Override
	public int updateImage(int groupId,String imagePath) {
		Session session=sessionFactory.openSession();
		Group group=(Group)session.get(Group.class, groupId);
		group.setImagePath(imagePath);
		session.beginTransaction();
		session.update(group);
		session.getTransaction().commit();
		return 1;
	}

}
