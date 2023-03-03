package com.App.webApp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.App.webApp.helperClasses.EmailActions;
import com.App.webApp.helperClasses.StringEncodeDecode;
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
	@Autowired 
	ServiceClass serviceClass;
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private StringEncodeDecode stringEncodeDecode;
	
	static public String loginType=null;
	LoginUser loginUser=null;
	@ModelAttribute
	public void getData(Principal p, Model model) {
		if(p != null) {
			if(MainController.loginType.equalsIgnoreCase("seller")) {
				 loginUser=sellerRepository.findByEmailAddr(p.getName());
				model.addAttribute("loginUser", loginUser);
				model.addAttribute("firstName", loginUser.getFirstName());
				model.addAttribute("path","/seller/sellerdashbord");	
			}else if(loginType.equalsIgnoreCase("marketplace")){
				loginUser =marketPlacerRepository.findByEmailAddr(p.getName());
				model.addAttribute("loginUser", loginUser);
				model.addAttribute("firstName", loginUser.getFirstName());
				model.addAttribute("path","/marketplace/marketplacedashbord");	
			}
		}else {
			model.addAttribute("title","Nuvio seller || Better For Grow.");
			model.addAttribute("firstName","");
		}
	}
	
	@RequestMapping("/default")
	public String dashbordManagement(Principal p, Model model) {
		try {
			if(loginType.equalsIgnoreCase("seller")) {
				return "redirect:/seller/sellerdashbord";
			}else if(loginType.equalsIgnoreCase("marketplace")) {
				return "redirect:/marketplace/marketplacedashbord";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "adminLogin";

	}

	@RequestMapping("/adminLogin")
	public String loginRegister(Model model){
		model.addAttribute("title","Nuvio Seller || Login Page");
		return "adminLogin";
	}
	
	@RequestMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("title","Nuvio seller || Better For Grow.");
		return "index";
	}
	
	@RequestMapping("/signUp")
	public String assignUp(Model model) {
		model.addAttribute("title","Nuvio seller || Sign Up .");
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
				seller.setRole("ROLE_SELLER");
				seller.setIs_active(1);
				seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
				sellerRepository.save(seller);
			}
			if(seller.getSignUpType().equalsIgnoreCase("marketplace") || seller.getSignUpType().equalsIgnoreCase("both")) {
				MarketPlace marketPlace=new MarketPlace();
				marketPlace.setNameOfCompany(seller.getNameOfCompany());
				marketPlace.setComponyUrl(seller.getComponyUrl());
				marketPlace.setRole("ROLE_MARKETPLACE");
				marketPlace.setTypeOfCompany(seller.getTypeOfCompany());
				//marketPlace.setCategorysId(catString);
				marketPlace.setFirstName(seller.getFirstName());
				marketPlace.setLastName(seller.getLastName());
				marketPlace.setIs_active(1);
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
			System.out.println("123 :"+seller);
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
	public String aboutPage(Model model) {
		model.addAttribute("title","Nuvio seller || About Page.");
		return "about";
	}
	@GetMapping("/contact")
	public String contactPage(Model model) {
		model.addAttribute("title","Nuvio seller || Contact Page.");
		return "contact";
	}
	
	@GetMapping("/pricing")
	public String pricingPage(Model model) {
		model.addAttribute("title","Nuvio seller || Pricing Page.");
		return "pricing";
	}
	
	@GetMapping("/forgotePassword")
	public String forgotePasswordpage(Model model){
		model.addAttribute("title","Nuvio seller || Forgote password.");
		model.addAttribute("code","none");
		model.addAttribute("msg", "none");
		return "resetPassword";
	}
	
	//reset password code write here
	
	@PostMapping("/sendResetPasswordEmail")
	public String sendResetPasswordEmail(@RequestParam("email") String email,@RequestParam("type") String type, Model model) {
		
		//LoginUser user=serviceClass.getLoginUserByTypeAndEmail(type,email);
		LoginUser user=null;
		if(type.equals("seller")) {
			System.out.println(type+" | "+email);
			user=sellerRepository.findByEmailAddr(email);
		}else if(type.equals("marketplace")) {
			user=marketPlacerRepository.findByEmailAddr(email);
		}
		
		System.out.println("Loginuser is : "+user);

		model.addAttribute("msg", "none");

		if (user == null) {
			model.addAttribute("msg", "invalidEmail");
		} else {
			
			String encodedString = stringEncodeDecode.getEncodedString("nuvio"+user.getEmailAddr()+"&"+type);
			
			user.setResetPasswordCode(encodedString);
			
			if(type.equals("seller")) {
				sellerRepository.save((Seller)user);
			}else if(type.equals("marketplace")) {
				marketPlacerRepository.save((MarketPlace)user);
			}
			
			boolean flag = EmailActions.sendResetPasswordEmailMessage(user,encodedString, emailSender);
			System.out.println("reset password email : " + flag);
			model.addAttribute("msg", "sendEmail");
		}

		model.addAttribute("code", "none");
		return "resetPassword";
	}

	@RequestMapping("/resetPasswordcodeVerification")
	public String resetPassword(@RequestParam("code") String code, Model model) {
		String array[]=stringEncodeDecode.getDecodeString(code).split("&");
		//LoginUser user=serviceClass.getLoginUserByTypeAndResetPasswordCode(array[1], code);
		LoginUser user=null;
		if(array[1].equals("seller")) {
			user=sellerRepository.findByResetPasswordCode(code);
		}else if(array[1].equals("marketplace")) {
			user=marketPlacerRepository.findByResetPasswordCode(code);
		}
		if (user == null) {
			model.addAttribute("code", "none");
			model.addAttribute("msg", "wrong");
		} else {
			model.addAttribute("msg", "password");
			model.addAttribute("code", code);
		}
		System.out.println("Reset password code : " + code);

		return "resetPassword";
	}

	@PostMapping("/saveNewPassword")
	public String saveNewPassword(@RequestParam("code") String code, @RequestParam("password") String password,
			Model model) {
		String array[]=stringEncodeDecode.getDecodeString(code).split("&");
	//	LoginUser user=serviceClass.getLoginUserByTypeAndResetPasswordCode(array[1], code);
		System.out.println("String is "+stringEncodeDecode.getDecodeString(code));
		LoginUser user=null;
		if(array[1].equals("seller")) {
			user=sellerRepository.findByResetPasswordCode(code);
		}else if(array[1].equals("marketplace")) {
			user=marketPlacerRepository.findByResetPasswordCode(code);
		}
		
		if (user == null) {

			model.addAttribute("code", "none");
			model.addAttribute("msg", "wrong");
			return "resetPassword";
		} else {
			user.setPassword(bCryptPasswordEncoder.encode(password));
			//userRepository.save(user);
			if(array[1].equals("seller")) {
				sellerRepository.save((Seller)user);
			}else if(array[1].equals("marketplace")) {
				marketPlacerRepository.save((MarketPlace)user);
			}
			model.addAttribute("msg", "success");
			model.addAttribute("code", "none");
		}

		return "resetPassword";
	}		
}
