package com.ramselabs.education.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.PostDAO;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
@Named
@Scope("session")
public class PostDAOImpl implements PostDAO {

	@Inject
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<PostDescriptionModel> getPostPersons(int userId) {
		List<PostDescriptionModel> listPerson=new ArrayList<PostDescriptionModel>();
		Session session=sessionFactory.openSession();
		UserProfile userProfile=(UserProfile)session.get(UserProfile.class,userId);
		List<PostShare> posts=(List<PostShare>)userProfile.getUserPostShare();
//		System.out.println(posts);
		if(posts.isEmpty())
			return null;
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

	@Override
	public int insertPosts(Post post, PostShare postShare) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(postShare);
		session.save(post);
		session.getTransaction().commit();
		session.flush();
		session.close();
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getPoster(int userId) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserProfile where userId = :userId");
		query.setInteger("userId", userId);
		List<UserProfile> list=(List<UserProfile>)query.list();
		if(list.isEmpty())
			System.out.println("List is empty for display name");
		return list.get(0);
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

}
