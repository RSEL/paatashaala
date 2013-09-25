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
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;

@Named
@Scope("session")
public class UserDAOImpl implements UserDAO {

	@Inject
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean loginAuthenticate(UserProfile user) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserProfile where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
		if(query.list().size()==0)
		    return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getUserProfile(String username, String password) {
		Session session=sessionFactory.openSession();
		UserProfile userProfile=null;
		Query query=session.createQuery("from UserProfile where username = :username and password = :password");
		query.setString("username",username);
		query.setString("password", password);
		List<UserProfile> list=(List<UserProfile>)query.list();
		if(list.isEmpty()){
			System.out.println("UserProfile is empty"+list);
			return null;
		}
		for(UserProfile profile:list)
			  userProfile=profile;
		return userProfile;
	}

	@Override
	public int updateUserImage(UserProfile user) {
		Session session=sessionFactory.openSession();
		int userId=getUserId(user);
		UserProfile userPersistent=(UserProfile)session.get(UserProfile.class,userId);
		userPersistent.setImagePath(user.getImagePath());
		session.beginTransaction();
		session.update(userPersistent);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUserId(UserProfile user) {
		Session session=sessionFactory.openSession();
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
		Session session=sessionFactory.openSession();
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
		Session session=sessionFactory.openSession();
		int userId=getUserId(user);
		System.out.println((UserProfile)session.get(UserProfile.class, userId));
    	return (UserProfile)session.get(UserProfile.class, userId);
	}

}
