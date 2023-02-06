package com.App.webApp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;


import com.App.webApp.entities.Categorys;
import com.App.webApp.entities.MarketPlace;
import com.App.webApp.entities.Seller;
import com.App.webApp.entities.User;
import com.App.webApp.repo.CategorysRepository;
import com.App.webApp.repo.MarketPlacerRepository;
import com.App.webApp.repo.SellerRepository;
import com.App.webApp.repo.UserRepository;
import com.App.webApp.service.LoginUser;
import com.App.webApp.service.ServiceClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import reactor.core.publisher.Mono;

@Controller
public class MainController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	MarketPlacerRepository marketPlacerRepository;
	@Autowired
	CategorysRepository  categorysRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	static public String loginType=null;
	
	
	@ModelAttribute
	public void getData(Principal p, Model model) {
		if(p != null) {
			if(MainController.loginType.equalsIgnoreCase("seller")) {
				Seller user=sellerRepository.findByEmailAddr(p.getName());
				model.addAttribute("loginUser", user);
				model.addAttribute("firstName", user.getFirstName());
				model.addAttribute("path","/user/sellerdashbord");	
			}else if(loginType.equalsIgnoreCase("marketplace")){
				MarketPlace user=marketPlacerRepository.findByEmailAddr(p.getName());
				model.addAttribute("loginUser", user);
				model.addAttribute("firstName", user.getFirstName());
				model.addAttribute("path","/user/marketplacedashbord");	
			}
		}else
			model.addAttribute("firstName","");
	}
	
	@RequestMapping("/default")
	public String dashbordManagement(Principal p, Model model) {
		if(loginType.equalsIgnoreCase("seller")) {
			return "redirect:/user/sellerdashbord";
		}else if(loginType.equalsIgnoreCase("marketplace")) {
			return "redirect:/user/marketplacedashbord";
		}else {
			return "adminLogin";
		}
	}

	@RequestMapping("/adminLogin")
	public String loginRegister(Model model){
		return "adminLogin";
	}
	
	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping("/signUp")
	public String assignUp(Model model) {
		model.addAttribute("seller",new Seller());
		model.addAttribute("msg", "none");
		model.addAttribute("categorys", categorysRepository.findAll());
		System.out.println("cat sie : "+categorysRepository.findAll().size());
		System.out.println("as seller....");
		return "signUp";
	}
	
	@PostMapping("/doSignUp")
	public String signUp(@Valid @ModelAttribute("seller") Seller seller,BindingResult result, Model model,
			HttpSession session,@RequestParam("categorys") String categorys) {

		
		String array[]=categorys.split("&");
		
		try {
			if (result.hasErrors()) {
				model.addAttribute("seller", seller);
				model.addAttribute("msg","none");
				model.addAttribute("categorys", categorysRepository.findAll());
				System.out.println("error generate "+result.toString());
				return "signUp";
			}
			if(!seller.getPassword().equals(seller.getPasswordConfirm())) {
				model.addAttribute("msg","password");
				return "sellerSignup";
			}
			
			
			List<Categorys> cList=new ArrayList<>();
			for(String cat:array) {
				System.out.println(cat);
				if(cat.length()>=1) {
					Categorys c=categorysRepository.findByCatergoryName(cat.trim());
					cList.add(c);
				}
			}
			System.out.println("categorys list : "+cList);
			if(seller.getSignUpType().equalsIgnoreCase("seller") || seller.getSignUpType().equalsIgnoreCase("both")) {
				
				seller.setCategorysList(cList);
				seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
				sellerRepository.save(seller);
			}
			if(seller.getSignUpType().equalsIgnoreCase("marketplace") || seller.getSignUpType().equalsIgnoreCase("both")) {
				MarketPlace marketPlace=new MarketPlace();
				marketPlace.setNameOfCompany(seller.getNameOfCompany());
				marketPlace.setComponyUrl(seller.getComponyUrl());
				marketPlace.setTypeOfCompany(seller.getTypeOfCompany());
				//marketPlace.setCategorysId(catString);
				marketPlace.setFirstName(seller.getFirstName());
				marketPlace.setLastName(seller.getLastName());
				marketPlace.setPhoneNumber(seller.getPhoneNumber());
				marketPlace.setEmailAddr(seller.getEmailAddr());
				marketPlace.setCountry(seller.getCountry());
				marketPlace.setState(seller.getState());
				marketPlace.setPincode(seller.getPincode());
				marketPlace.setGstNo(seller.getGstNo());
				marketPlace.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
				marketPlace.setBussinessAddr(seller.getBussinessAddr());
				marketPlace.setCategorysList(cList);
				marketPlacerRepository.save(marketPlace);
			}
			
			//sellerRepository.save(seller);
			model.addAttribute("categorys", categorysRepository.findAll());
			model.addAttribute("msg","save");
			model.addAttribute("seller",new Seller());
			System.out.println(seller);
			return "signUp";
	
		}catch (Exception e) {
			System.out.println("Error generated");
			model.addAttribute("categorys", categorysRepository.findAll());
			model.addAttribute("msg","error");
			model.addAttribute("seller",new Seller());
			e.printStackTrace();
			return "signUp";
		}
	}
		
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}
	
	@GetMapping("/forgotePassword")
	public String forgotePasswordpage(Model model){
		model.addAttribute("code","none");
		model.addAttribute("msg", "none");
		return "resetPassword";
	}
	
	
}
