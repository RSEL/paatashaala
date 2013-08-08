package com.ramselabs.education.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ramselabs.education.entity.Share;
import com.ramselabs.education.entity.User;

@Named
public class HibernateCRUD {
	@Inject
	private HibernateDAO hibernateDAO;
	
	Session s1=null;
	int userId;
	public void setHibernateDAO(HibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}

	public boolean loginAuthenticate(User user){
    	Session s1=hibernateDAO.getSession();
    	userId=getUserId(user);
    	Query query=s1.createQuery("from User where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
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
    	
    }
	@SuppressWarnings("unchecked")
	public static String getUserRole(LoginBean login){
		 String s2=null;
		Session s1=HibernateUtil.getSession();
		Query q1=s1.createSQLQuery("SELECT ROLE FROM SERVICE_REG_TAB REG INNER JOIN SERVICE_LOGIN LOG ON REG.LOG_ID=(SELECT ID FROM SERVICE_LOGIN WHERE USERNAME='"+login.getUsername()+"' AND PASSWORD='"+login.getPassword()+"')");
		List<String> list=(List<String>)q1.list();
		for(String i:list)
			s2=i;
		return s2;
    }*/
	@SuppressWarnings("unchecked")
	public List<Share> getUserAutoCompleteList(){
		Session s1=hibernateDAO.getSession();
		Criteria ctr=s1.createCriteria(Share.class);
		ctr.add(Restrictions.ne("userId",userId ));
		return (List<Share>)ctr.list();
	}
	@SuppressWarnings("unchecked")
	private int getUserId(User user){
		Session s1=hibernateDAO.getSession();
    	Query query=s1.createQuery("select userId from User where username='"+user.getUsername()+"' and password='"+user.getPassword()+"'");
    	
		List<Integer> i=(List<Integer>)query.list();
		Integer j=i.get(0);
		return j;
	}
}
