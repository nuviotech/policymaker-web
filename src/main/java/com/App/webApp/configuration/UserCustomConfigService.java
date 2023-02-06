package com.App.webApp.configuration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.App.webApp.controllers.MainController;
import com.App.webApp.repo.MarketPlacerRepository;
import com.App.webApp.repo.SellerRepository;
import com.App.webApp.service.LoginUser;


public class UserCustomConfigService implements UserDetailsService {
	@Autowired 
	SellerRepository sellerRepository;
	
	@Autowired
	MarketPlacerRepository marketPlacerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("full Username include & :  "+username);
		LoginUser user=null;
		String array[]=username.split("&");
		String email=array[0];
		String type=array[1];
		MainController.loginType=null;
		try {
			if(type.equalsIgnoreCase("seller")) {
				 user = sellerRepository.findByEmailAddr(email);
			}else if(type.equalsIgnoreCase("marketplace")){
				user = marketPlacerRepository.findByEmailAddr(email);
			}
			System.out.println("User name :  "+email);
			if (user == null) {
				throw new UsernameNotFoundException("Invalid User name or password !!");
			} else {
				user.setSignUpType(type);
				MainController.loginType=type;
				return new UserCustomConfig(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return null;
	}

}
