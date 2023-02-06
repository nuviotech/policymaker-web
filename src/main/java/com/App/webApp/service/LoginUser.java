package com.App.webApp.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface LoginUser {

	String getRole();

	String getPassword();

	String getEmailAddr();
	
	String getSignUpType();
	void setSignUpType(String str);

}
