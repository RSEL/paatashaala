package com.ramselabs.education.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;

@Named
public class HibernateCRUD {
	@Inject
	private HibernateDAO hibernateDAO;
	public void setHibernateDAO(HibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}

	public boolean loginAuthenticate(UserProfile user){
    	Session session=hibernateDAO.getSession();
    	Query query=session.createQuery("from UserProfile where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
		if(query.list().size()==0)
		    return false;
		else
			return true;
    }
/*	@SuppressWarnings("rawtypes")
	public static List getAllQueriedResult(String qurey){
    	Session s1=HibernateUtil.getSession();
    	Query q1=s1.createQuery(qurey);
    	return q1.list();
    	
    }*/
	@SuppressWarnings("unchecked")
	public UserProfile getUserProfile(String username,String password){
		System.out.println("Hcrud"+username);
		System.out.println("Hcrud"+password);
		UserProfile userProfile=null;
		Session session=hibernateDAO.getSession();
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
	@SuppressWarnings("unchecked")
	public List<UserProfile> getUserAutoCompleteList(int userId){
		Session session=hibernateDAO.getSession();
		Criteria ctr=session.createCriteria(UserProfile.class);
		ctr.add(Restrictions.ne("userId",userId));
		
		return (List<UserProfile>)ctr.list();
	}

	public UserProfile getPersistentUser(UserProfile user){
		Session session=hibernateDAO.getSession();
		int userId=getUserId(user);
		System.out.println((UserProfile)session.get(UserProfile.class, userId));
    	return (UserProfile)session.get(UserProfile.class, userId);
	}
	public int insertPosts(Post post,PostShare postShare){
		Session session=hibernateDAO.getSession();
		session.beginTransaction();
		session.save(postShare);
		session.save(post);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return 1;
	}
	
	public List<PostDescriptionModel> getPostPersons(int userId){
		List<PostDescriptionModel> listPerson=new ArrayList<PostDescriptionModel>();
		Session hSession=hibernateDAO.getSession();
		UserProfile userProfile=(UserProfile)hSession.get(UserProfile.class,userId);
		List<PostShare> posts=(List<PostShare>)userProfile.getUserPostShare();
		Collections.sort(posts, new Comparator<PostShare>() {
		    public int compare(PostShare s1, PostShare s2) {
		        return s2.getPostDate().compareTo(s1.getPostDate());
		    }
		});
		for(PostShare postShare:posts){
			PostDescriptionModel postDescription=new PostDescriptionModel();
			postDescription.setPersonName(getPoster(postShare.getPost().getPosterId()).getDisplayName());
			postDescription.setPostDescription(postShare.getPost().getDescription());
			postDescription.setUserType(postShare.getUserType());
			postDescription.setUserImage(getPoster(postShare.getPost().getPosterId()).getImagePath());
			System.out.println("Date of posting"+postShare.getPostDate());
			postDescription.setDateOfPosting(postShare.getPostDate());
			listPerson.add(postDescription);
		}
		   
		return listPerson;
	}
	@SuppressWarnings("unchecked")
	public int getUserId(UserProfile user){
		Session session=hibernateDAO.getSession();
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
	public UserProfile getPoster(int userId){
		Session session=hibernateDAO.getSession();
		Query query=session.createQuery("from UserProfile where userId = :userId");
		query.setInteger("userId", userId);
		List<UserProfile> list=(List<UserProfile>)query.list();
		if(list.isEmpty())
			System.out.println("List is empty for display name");
		return list.get(0);
	}
	public int updateUserImage(UserProfile user){
		int userId=getUserId(user);
		Session session=hibernateDAO.getSession();
		UserProfile userPersistent=(UserProfile)session.get(UserProfile.class,userId);
		userPersistent.setImagePath(user.getImagePath());
		session.beginTransaction();
		session.update(userPersistent);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return 1;
	}
}