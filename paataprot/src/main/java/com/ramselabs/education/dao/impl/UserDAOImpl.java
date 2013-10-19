package com.ramselabs.education.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.UserDAO;
import com.ramselabs.education.entity.Role;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;

@Named
@Scope("session")
public  class UserDAOImpl implements UserDAO {

	@Inject
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String loginAuthenticate(UserProfile user) {
		UserProfile userProfile=getUserProfile(user);
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from UserProfile where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
		if(query.list().size()!=0){
			
			System.out.println(user);
			List<Role> roles=(List<Role>)userProfile.getUserRoles();
			for(Role role:roles){
				if(role.getRoleName().equalsIgnoreCase("admin"))
					return "admin";
				if(role.getRoleName().equalsIgnoreCase("moderator"))
					return "moderator";
				if(role.getRoleName().equalsIgnoreCase("user"))
					return "user";
			}
		}
		    
			return "notLoggedIn";
		
	}

	@Override
	public UserProfile getUserProfile(UserProfile user) {
		int userId=getUserId(user);
		Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
		if(userId==0)
			return null;
		UserProfile userProfile=(UserProfile)session.get(UserProfile.class, userId);
		return userProfile;
	}

	@Override
	public int updateUserImage(UserProfile user) {
		Session session=sessionFactory.getCurrentSession();
		int userId=getUserId(user);
		session.beginTransaction();
		UserProfile userPersistent=(UserProfile)session.get(UserProfile.class,userId);
		userPersistent.setImagePath(user.getImagePath());
		session.beginTransaction();
		session.update(userPersistent);
		session.getTransaction().commit();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUserId(UserProfile user) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("select userId from UserProfile where username = :username and password = :password");
		query.setString("username",user.getUsername());
		query.setString("password", user.getPassword());
		List<Integer> list=(List<Integer>)query.list();
		if(list.isEmpty())
			System.out.println("List is empty for userId");
		int userId=0;
		  for(int j:list){
			  System.out.println("List is not empty");
			  j=list.get(0);
			  userId=j;
		  }
		  return userId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AutocompleteTemplate> getUserAutoCompleteList(int userId) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria ctr=session.createCriteria(UserProfile.class);
		ctr.add(Restrictions.ne("userId",userId));
		List<AutocompleteTemplate> listUsers=(List<AutocompleteTemplate>)ctr.list();
		for(AutocompleteTemplate listUser:listUsers){
			if(((UserProfile)listUser).getImagePath()==null)
				((UserProfile)listUser).setImagePath("/resources/img/profile-photo/default-profile.jpg");
		}
		return listUsers;
	}

	@Override
	public UserProfile getPersistentUser(UserProfile user){
		Session session=sessionFactory.getCurrentSession();
		int userId=getUserId(user);
		session.beginTransaction();
		UserProfile userProfile=(UserProfile)session.get(UserProfile.class, userId);
    	return userProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insertUser(UserProfile user) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from UserProfile");
		List<UserProfile> users=(List<UserProfile>)query.list();
		for(UserProfile userProfile:users){
			if(userProfile.getUsername().equalsIgnoreCase(user.getUsername())){
				return 0;
			}
		}
		Role role=new Role();
		role.getRoleUsers().add(user);
		role.setRoleName("user");
		user.getUserRoles().add(role);
		
		session.saveOrUpdate(user);
		session.saveOrUpdate(role);
		session.getTransaction().commit();
		return 1;
	}


}
