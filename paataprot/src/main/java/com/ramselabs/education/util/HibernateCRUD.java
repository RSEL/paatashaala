package com.ramselabs.education.util;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ramselabs.education.managedbean.ManagedLoginBean;


public class HibernateCRUD {
	static Session s1=null;
	static{
		s1=HibernateUtil.getSession();
	}
    public static boolean loginAuthenticate(ManagedLoginBean login){
    	Session s1=HibernateUtil.getSession();
		Query query=s1.createSQLQuery("select * from loginbean where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
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
