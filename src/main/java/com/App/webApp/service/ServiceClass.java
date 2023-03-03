package com.App.webApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.App.webApp.entities.Categorys;
import com.App.webApp.repo.CategorysRepository;
import com.App.webApp.repo.MarketPlacerRepository;
import com.App.webApp.repo.SellerRepository;
@Component
public class ServiceClass {
	@Autowired
	static CategorysRepository categorysRepository;
	@Autowired
	static SellerRepository sellerRepository;
	@Autowired
	static MarketPlacerRepository marketPlacerRepository;
	
	public static List<String> getCategorysList(){
		List<String> list=new ArrayList<>();
		for(Categorys category: categorysRepository.findAll()) {
			list.add(category.getCatergoryName());
		}
		return list;
	}
	
	//this method helps to identify user belong wich table
	
	public LoginUser getLoginUserByTypeAndEmail(String type,String email) {
		LoginUser user=null;
		if(type.equals("seller")) {
			System.out.println(type+" | "+email);
			user=sellerRepository.findByEmailAddr(email);
		}else if(type.equals("marketplace")) {
			user=marketPlacerRepository.findByEmailAddr(email);
		}
		return user;
	}
	
	
	public LoginUser getLoginUserByTypeAndResetPasswordCode(String type,String code) {
		LoginUser user=null;
		if(type.equals("seller")) {
			user=sellerRepository.findByResetPasswordCode(code);
		}else if(type.equals("marketplace")) {
			user=marketPlacerRepository.findByResetPasswordCode(code);
		}
		return user;
	}
	
}
