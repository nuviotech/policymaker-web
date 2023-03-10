package com.App.webApp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.App.webApp.entities.MarketPlace;
import com.App.webApp.entities.Seller;
import com.App.webApp.repo.MarketPlacerRepository;
import com.App.webApp.repo.SellerRepository;
import com.App.webApp.service.LoginUser;

@Controller
@RequestMapping("/seller")
public class UserController {
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	MarketPlacerRepository marketPlacerRepository;
	LoginUser loginUser;
	@ModelAttribute
	public void getData(Principal p, Model model) {
			if(MainController.loginType.equalsIgnoreCase("seller")) {
				loginUser=sellerRepository.findByEmailAddr(p.getName());
				model.addAttribute("loginUser", loginUser);
				model.addAttribute("firstName", loginUser.getFirstName());
				model.addAttribute("path","/seller/sellerdashbord");	
			}else if(MainController.loginType.equalsIgnoreCase("marketplace")){
				loginUser=marketPlacerRepository.findByEmailAddr(p.getName());
				model.addAttribute("loginUser", loginUser);
				model.addAttribute("firstName", loginUser.getFirstName());
				model.addAttribute("path","/marketplace/marketplacedashbord");	
			}
			model.addAttribute("title","Nuvio seller || Seller Dashboard.");
			model.addAttribute("firstName",loginUser.getFirstName());
			model.addAttribute("loginUser", loginUser);
		
	}
	
	@GetMapping("/sellerdashbord")
	public String userDashBord(Principal p, Model model) {
		if(MainController.loginType.equalsIgnoreCase("seller")) {
			Seller user=sellerRepository.findByEmailAddr(p.getName());
			model.addAttribute("loginUser", user);
			model.addAttribute("firstName", user.getFirstName());
			model.addAttribute("path","/seller/sellerdashbord");
			
			
		}
		return "seller/sellerDashboard";
	}
}
