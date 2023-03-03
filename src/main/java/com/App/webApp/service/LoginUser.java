package com.App.webApp.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface LoginUser {

	String getRole();
	String getPassword();
	void setPassword(String password);
	String getEmailAddr();
    String getLastName();
	String getFirstName();
	String getResetPasswordCode();
	void setResetPasswordCode(String code);

	String getSignUpType();
	void setSignUpType(String str);

}
