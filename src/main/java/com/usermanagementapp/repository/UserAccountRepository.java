package com.usermanagementapp.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entity.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {

	//As there is no any predefined method for email id validation so we write
	// our custom method started with "findBy" , so we no need to write query it
	// will auto generate query
	//select * from User_account where userEmail=?
	public UserAccountEntity  findByUserEmail(String Email);
	public UserAccountEntity findByUserEmailAndUserPazzword(String email,String pwd);
}
