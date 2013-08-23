package com.ramselabs.education.dao.datasource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ramselabs.education.security.EncryptDecryptUtil;

public class EncryptedDataSource extends DriverManagerDataSource{

	@Override
      public String getPassword() { 
		String password = super.getPassword();
		return EncryptDecryptUtil.decrypt(password); 
		}
	@Override
	public String getUsername(){
		String username=super.getUsername();
		return EncryptDecryptUtil.decrypt(username);
	}
}
