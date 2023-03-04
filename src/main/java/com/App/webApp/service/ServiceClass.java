package com.App.webApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.App.webApp.entities.Categorys;
import com.App.webApp.entities.MarketPlace;
import com.App.webApp.entities.Seller;
import com.App.webApp.repo.CategorysRepository;
import com.App.webApp.repo.MarketPlacerRepository;
import com.App.webApp.repo.SellerRepository;
@Component
public class ServiceClass {
	@Autowired
	CategorysRepository categorysRepository;
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	MarketPlacerRepository marketPlacerRepository;
	
	public List<String> getCategorysList(){
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
	
	//check first check user is exists if exists then show msg
	public String checkUserExistsOrNot(String email,String type) {
		Seller seller=null;//
		MarketPlace marketplace=null;//
		if(type.equalsIgnoreCase("seller") || type.equalsIgnoreCase("both")) {
			seller=sellerRepository.findByEmailAddr(email);
		}
		if(type.equalsIgnoreCase("marketplace") || type.equalsIgnoreCase("both")) {
			marketplace=marketPlacerRepository.findByEmailAddr(email);
		}
		
		if(seller!=null) {
			return "S";
		}else if(marketplace!=null) {
			return "M";
		}
		return "";
	}
	
}
