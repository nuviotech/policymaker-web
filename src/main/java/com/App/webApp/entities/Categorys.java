package com.App.webApp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Categorys {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Category_id")
	private int categoryId;
	
	@Column(name="Category_name")
	private String catergoryName;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST ,mappedBy = "categorysList")
	@JsonManagedReference
	List<Seller> sellerList=new ArrayList<>();
	
	@ManyToMany(mappedBy = "categorysListM")
	
	List<MarketPlace> marketplaceList=new ArrayList<>();
	
	public Categorys() {}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCatergoryName() {
		return catergoryName;
	}

	public void setCatergoryName(String catergoryName) {
		this.catergoryName = catergoryName;
	}
/*
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
*/

	public List<Seller> getSellerList() {
		return sellerList;
	}

	public void setSellerList(List<Seller> sellerList) {
		this.sellerList = sellerList;
	}

	public List<MarketPlace> getMarketplaceList() {
		return marketplaceList;
	}

	public void setMarketplaceList(List<MarketPlace> marketplaceList) {
		this.marketplaceList = marketplaceList;
	}
	
	
}
