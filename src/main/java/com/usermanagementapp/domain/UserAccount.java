package com.usermanagementapp.domain;

import java.util.Date;

import lombok.Data;

@Data

public class UserAccount {

	private Integer userId;
	private String firstName;
	private String userLastName;
	private String userEmail;
	private String userPazzword;
	private String userMobileNumber;
	private Date dateOfBirth;
	private Character gender;
	private Date createdDate;
	private Date updatedDate;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	private String accountStatus;

}
