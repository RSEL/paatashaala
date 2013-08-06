package com.ramselabs.education.util;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ramselabs.education.parser.ConfigReader;
import com.ramselabs.education.security.EncryptDecryptUtil;

/**
 * 
 * @author root
 * 
 */
@Named
@SuppressWarnings("rawtypes")
public class HibernateDAO {
	static private ConfigReader configReader = null;
	static SessionFactory sf = null;
	static {
		InputStream is = HibernateDAO.class.getResourceAsStream("/config.xml");
		try {
			configReader = new ConfigReader(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateProperty hProps = configReader
				.getConfigHibernate("hibernateProps");
		Properties props = hProps.getHibernateProps();
		String username = props.getProperty("hibernate.connection.username");
		String password = props.getProperty("hibernate.connection.password");
		props.setProperty("hibernate.connection.username",
				EncryptDecryptUtil.decrypt(username));
		props.setProperty("hibernate.connection.password",
				EncryptDecryptUtil.decrypt(password));
		Configuration config = new Configuration();
		config.setProperties(props);
		List<Class> list = hProps.getAnnotatedClass();
		for (Class c1 : list)
			config.addAnnotatedClass(c1);
		sf = config.buildSessionFactory();
	}

	public Session getSession() {
		return sf.openSession();
	}

}
