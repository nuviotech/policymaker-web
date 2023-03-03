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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Seller implements LoginUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int SellerID;
	
	@NotBlank(message = "first name not be blank!!")
	@Size(max = 30, min = 1, message = "first name must be greterthan 1 character or lessthan 30 character")
	String firstName;
	
	@NotBlank(message = "second name not be blank!!")
	@Size(max = 30, min = 1, message = "name must be greterthan 1 character or lessthan 30 character")
	String lastName;
	
	@Column(name="Email", unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please Enter valid Email Address !!")
	String emailAddr;
	
	@NotBlank(message = "compony name not be blank!!")
	String nameOfCompany;
	
	String typeOfCompany;
	
	@Column(length=3000)
	@NotBlank(message="fill the bussiness address here!!")
	String bussinessAddr;
	
	@NotBlank(message = "This field is required !!")
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "enter valid area pincode")
	String pincode;
	

	@Pattern(regexp="^[a-zA-Z0-9][a-zA-Z0-9]{14}$" , message = "Invalid GSTIN !!")

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
	int is_active;//1 for active and 0 for deactive
	String state;
	String role;//admin or normal user
	String type;//
	String resetPasswordCode; //help for reset the password
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seller")
	private List<Categorys> categorysList = new ArrayList<>();*/
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name="seller_categorys")
	List<Categorys> categorysList =new ArrayList<>();
	
	//String categorysId;
	
	public Seller() {}

	

	public int getSellerID() {
		return SellerID;
	}



	public void setSellerID(int sellerID) {
		SellerID = sellerID;
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
	


/*
	public List<Categorys> getCategorysList() {
		return categorysList;
	}



	public void setCategorysList(List<Categorys> categorysList) {
		this.categorysList = categorysList;
	}*/



	



	public List<Categorys> getCategorysList() {
		return categorysList;
	}



	public int getIs_active() {
		return is_active;
	}



	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}



	public void setCategorysList(List<Categorys> categorysList) {
		this.categorysList = categorysList;
	}



/*	public String getCategorysId() {
		return categorysId;
	}



	public void setCategorysId(String categorysId) {
		this.categorysId = categorysId;
	}*/



	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
	
	
	public String getComponyUrl() {
		return componyUrl;
	}



	public void setComponyUrl(String componyUrl) {
		this.componyUrl = componyUrl;
	}



	public String getSignUpType() {
		return signUpType;
	}



	public void setSignUpType(String signUpType) {
		this.signUpType = signUpType;
	}
	



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}

	

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}
	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}



	@Override
	public String toString() {
		return "Seller [id=" +SellerID+ ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddr=" + emailAddr
				+ ", nameOfCompany=" + nameOfCompany + ", typeOfCompany=" + typeOfCompany + ", bussinessAddr="
				+ bussinessAddr + ", pincode=" + pincode + ", GstNo=" + GstNo + ", password=" + password + ", category="
				 + "]";
	}
	
	
	
	
}
