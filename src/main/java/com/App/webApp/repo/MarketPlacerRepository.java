package com.App.webApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.webApp.entities.MarketPlace;
import com.App.webApp.entities.Seller;

public interface MarketPlacerRepository extends JpaRepository<MarketPlace, Integer> {
	public MarketPlace findByEmailAddrAndPassword(String emailId,String password);
	public MarketPlace findByEmailAddr(String email);


}
