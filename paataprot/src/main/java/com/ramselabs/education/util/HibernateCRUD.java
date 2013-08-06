package com.ramselabs.education.util;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ramselabs.education.entity.LoginBean;

@Named
public class HibernateCRUD {
	@Inject
	private HibernateDAO hibernateDAO;
	
	Session s1=null;
	
	public void setHibernateDAO(HibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}

	public boolean loginAuthenticate(LoginBean login){
    	Session s1=hibernateDAO.getSession();
		Query query=s1.createQuery("from LoginBean where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
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
}
