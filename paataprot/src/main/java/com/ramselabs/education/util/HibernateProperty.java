package com.ramselabs.education.util;

import java.util.ArrayList;
import java.util.Properties;

public class HibernateProperty {
	private ArrayList<Class> annotatedClass=new ArrayList<Class>();
	private Properties prop=new Properties();
	public void putProp(String key,String value) {
		prop.put(key, value);
	}
	public Properties getHibernateProps() {
		return prop;
	}
	public ArrayList<Class> getAnnotatedClass() {
		return annotatedClass;
	}
	public void setAnnotatedClass(String value) {
		Class c1=null;
		try {
			c1 = Class.forName(value);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		annotatedClass.add(c1);
	}
	
}
