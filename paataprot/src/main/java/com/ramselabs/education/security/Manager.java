package com.ramselabs.education.security;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.util.HibernateDAO;

public class Manager {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*String str = EncryptDecryptUtil.encrypt("root");
		System.out.println(str);*/
		HibernateDAO hdao=new HibernateDAO();
		Session s1=hdao.getSession();
		List<Object[]> list=(List<Object[]>)s1.createCriteria(Post.class,"t1")
				.add(Restrictions.eq("t1.userId",3))
				.createCriteria("t1.postId" , "t2")
				.createCriteria("t2.shareId" , "t3")
				.setProjection(Projections.projectionList()
				.add(Projections.property("t1.description"))
				.add(Projections.property("t2.userType"))
				.add(Projections.property("t3.name")))
				.list();
	for(Object[] obj:list){
		System.out.println(obj[0]);
		System.out.println(obj[1]);
		System.out.println(obj[2]);
	}
 }
}
