package com.App.webApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.App.webApp.entities.Categorys;
import com.App.webApp.repo.CategorysRepository;
import com.App.webApp.repo.MarketPlacerRepository;
import com.App.webApp.repo.SellerRepository;
@Service
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
	
	
}
