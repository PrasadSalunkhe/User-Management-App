package com.usermanagementapp.service;

import java.util.Map;

import com.usermanagementapp.domain.UserAccount;



public interface UserService {
 
	/**
	 * as First screen is login screen so we took method to check.
	 we have taken String as return type because there may be 3 conditions 
	 either login success, login failed- 1. Due to invalid credentials
	                                     2. Due to account is locked.
	 
	 */
	public String loginCheck(String email, String pwd);
	
	/**
     return type as Map<Key,Value> because based on key I need to loading the states.
     
     all below methods are to load data in form.
	 */
	public Map<Integer,String> loadCountries();
	
	public Map<Integer,String> loadStatesByCountryId(Integer countryId);
	
	public Map<Integer,String> loadCitiesByStateId(Integer stateId);
	
	//To get only unique ids
	public Boolean isUniqueEmail(String email);
	// This method return type is boolean because we have to insert only unique email ids.
	
	
	public String generateTempPwd();
	// To generate temp password
	
	public Boolean saveUserAccount(UserAccount userAccount);
	// User account is saved or not we need to display so we took boolean data type
	// after clicking on submit button 
	
	public String getRegSuccMailBody(UserAccount userAccount);
	// From file we will get body and will pass to sendRegSuccEmail() method
	
	public Boolean sendRegSuccEmail(String to, String subject , String body);
	// we need to send some data to user with email for information like first name ,lastname so 
	// we took UserAccount object
	
	public Boolean isTempPwdValidForGivenEmail(String email,String tempPwd);
	//Is temp pwd is valid then unlockAccount method work
	
	
	// Unlock account screen
	public Boolean unlockAccount(String email,String pwd);
	//If account is updated with new pwd with valid tempwd then it will return true
	
	/**
	All above methods for registration functionality only
	 */
	
	public String recoverPwd(String email);
	
	public String getRecoverPwdEmailBody(UserAccount userAccount);
	
	public String sendPwdToEmail(String email, String subject,String body);
	
	/**
	 above the methods for forgot pwd functionality 
	 */
	
	
	
	
	
	
	
}
