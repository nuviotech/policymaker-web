package com.App.webApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.App.webApp.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
	public Seller findByEmailAddrAndPassword(String emailId,String password);
	public Seller findByEmailAddr(String email);

}
