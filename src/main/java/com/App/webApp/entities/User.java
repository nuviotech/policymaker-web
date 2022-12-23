package com.App.webApp.entities;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;




@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	int id;
	
	@Column(name="LOGIN_ID")
	String loginId;
	
	@Column(name="PASSWORD")
	String password;
	
	@Column(name="GROUP_ID")
	int groupId;
	
	@Column(name="USER_NAME",length=100)
	String userName;
	
	@Column(name="ENABLE")
	String enable;
	
	@Column(name="CONTACT_NO" , length=16)
	String contactNo;
	
	@Column(name="EMAIL")
	String email;
	
	@Column(name="GENDER")
	String gender;
	
	@Column(name="USER_ID")
	String userId;
	
	@Column(name="CUD")
	String cud;
	
	//@Column(name="CREATED_DT_TIME")
//	Date createdDateTime;
	
	@Column(name="JIO_CLIENT_ID")
	String jioClientId;
	
	@Column(name="JIO_CLIENT_SKEY")
	String jioClientSkey;
	
	@Column(name="IMAGE_URL")
	String imageUrl;
	
	@Column(name="GSTIN")
	String gsTin;
	
	@Column(name="STATUS",length=511)
	String status;
	
	@Column(name="GST_FILING_METHOD")
	String gstFilingmethod;
	
	@Column(name="PURCHASE_INFO_MAIL")
	String purchaseInfoMail;
	
	@Column(name="WMS")
	String wms;
	
	@Column(name="PRODUCT_STOCK_MAPPING")
	String productStockMapping;
	
	@Column(name="COMPANY_NAME")
	String componyName;
	
	@Column(name="LOGO_URL")
	String logoUrl;
	
	@Column(name="SUPPORT_CONTACT_NO")
	String supportContactNo;
	
	@Transient
	String role="ROLE_ADMIN";
	
	public User() {}
	

	public User(int id, String loginId, String password, int groupId, String userName, boolean enable, String contactNo,
			String email, String gender, String userId, String cud, Date createdDateTime, String jioClientId,
			String jioClientSkey, String imageUrl, String gsTin, String status, String gstFilingmethod,
			String purchaseInfoMail, String wms, String productStockMapping, String componyName, String logoUrl,
			String supportContactNo) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.groupId = groupId;
		this.userName = userName;
	//	this.enable = enable;
		this.contactNo = contactNo;
		this.email = email;
		this.gender = gender;
		this.userId = userId;
		this.cud = cud;
		//this.createdDateTime = createdDateTime;
		this.jioClientId = jioClientId;
		this.jioClientSkey = jioClientSkey;
		this.imageUrl = imageUrl;
		this.gsTin = gsTin;
		this.status = status;
		this.gstFilingmethod = gstFilingmethod;
		this.purchaseInfoMail = purchaseInfoMail;
		this.wms = wms;
		this.productStockMapping = productStockMapping;
		this.componyName = componyName;
		this.logoUrl = logoUrl;
		this.supportContactNo = supportContactNo;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String isEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCud() {
		return cud;
	}

	public void setCud(String cud) {
		this.cud = cud;
	}

	/*public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
*/
	public String getJioClientId() {
		return jioClientId;
	}

	public void setJioClientId(String jioClientId) {
		this.jioClientId = jioClientId;
	}

	public String getJioClientSkey() {
		return jioClientSkey;
	}

	public void setJioClientSkey(String jioClientSkey) {
		this.jioClientSkey = jioClientSkey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGsTin() {
		return gsTin;
	}

	public void setGsTin(String gsTin) {
		this.gsTin = gsTin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGstFilingmethod() {
		return gstFilingmethod;
	}

	public void setGstFilingmethod(String gstFilingmethod) {
		this.gstFilingmethod = gstFilingmethod;
	}

	public String getPurchaseInfoMail() {
		return purchaseInfoMail;
	}

	public void setPurchaseInfoMail(String purchaseInfoMail) {
		this.purchaseInfoMail = purchaseInfoMail;
	}

	public String getWms() {
		return wms;
	}

	public void setWms(String wms) {
		this.wms = wms;
	}

	public String getProductStockMapping() {
		return productStockMapping;
	}

	public void setProductStockMapping(String productStockMapping) {
		this.productStockMapping = productStockMapping;
	}

	public String getComponyName() {
		return componyName;
	}

	public void setComponyName(String componyName) {
		this.componyName = componyName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getSupportContactNo() {
		return supportContactNo;
	}

	public void setSupportContactNo(String supportContactNo) {
		this.supportContactNo = supportContactNo;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getEnable() {
		return enable;
	}
	
	
	
	
	
}
