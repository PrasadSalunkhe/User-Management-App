package com.usermanagementapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "UserAccount")
public class UserAccountEntity {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer userId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="user_lastname")
	private String userLastName;
	@Column(name="user_email")
	private String userEmail;
	@Column(name="user_pazzword")
	private String userPazzword;
	@Column(name="user_mobno")
	private String userMobileNumber;
	@Column(name="DOB")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@Column(name="gender")
	private Character gender;
	//@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;
	//@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="updated_date")
	private Date updatedDate;
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="state_id")
	private Integer stateId;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="account_status")
	private String accountStatus;
	//insert into UserAccount (user_id,first_name,user_lastname,user_email,user_pazzword,user_mobno,DOB,gender,created_date,updated_date,country_id,state_id,city_id,account_status)
	
}
