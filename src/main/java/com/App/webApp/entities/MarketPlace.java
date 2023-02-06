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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.App.webApp.service.LoginUser;

@Entity
public class MarketPlace implements LoginUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int marketPlaceID;
	
	
	@NotBlank(message = "first name not be blank!!")
	@Size(max = 30, min = 4, message = "first name must be greterthan 4 character or lessthan 30 character")
	String firstName;
	
	@NotBlank(message = "second name not be blank!!")
	@Size(max = 30, min = 1, message = "name must be greterthan 4 character or lessthan 30 character")
	String lastName;
	
	@Column(name="Email", unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please Enter valid Email Address !!")
	String emailAddr;
	
	@NotBlank(message = "compony name not be blank!!")
	String nameOfCompany;
	
	String typeOfCompany;
	String bussinessAddr;
	
	@NotBlank(message = "This field is required !!")
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "enter valid area pincode")
	String pincode;
	
	String GstNo;
	
	@NotBlank(message = "This field is required !!")
	String password;
	
	
	String componyUrl;
	
	@Transient
	String passwordConfirm;
	@Transient
	String signUpType;
	
	@NotBlank(message = "This field is required !!")
	@Pattern(message = "Please enter valid phone number", regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")
	String phoneNumber;
	
	String country;
	String state;
	String role;
	
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seller")
	private List<Categorys> categorysList = new ArrayList<>();*/
	
	@ManyToMany
	@JoinTable(name="marketplace_categorys")
	List<Categorys> categorysListM =new ArrayList<>();
	
	//String categorysId;

	public int getMarketPlaceID() {
		return marketPlaceID;
	}

	public void setMarketPlaceID(int marketPlaceID) {
		this.marketPlaceID = marketPlaceID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getNameOfCompany() {
		return nameOfCompany;
	}

	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}

	public String getTypeOfCompany() {
		return typeOfCompany;
	}

	public void setTypeOfCompany(String typeOfCompany) {
		this.typeOfCompany = typeOfCompany;
	}

	public String getBussinessAddr() {
		return bussinessAddr;
	}

	public void setBussinessAddr(String bussinessAddr) {
		this.bussinessAddr = bussinessAddr;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getGstNo() {
		return GstNo;
	}

	public void setGstNo(String gstNo) {
		GstNo = gstNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComponyUrl() {
		return componyUrl;
	}

	public void setComponyUrl(String componyUrl) {
		this.componyUrl = componyUrl;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getSignUpType() {
		return signUpType;
	}

	public void setSignUpType(String signUpType) {
		this.signUpType = signUpType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/*public String getCategorysId() {
		return categorysId;
	}

	public void setCategorysId(String categorysId) {
		this.categorysId = categorysId;
	}
	 */
	public List<Categorys> getCategorysList() {
		return categorysListM;
	}

	public void setCategorysList(List<Categorys> categorysList) {
		this.categorysListM = categorysList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
