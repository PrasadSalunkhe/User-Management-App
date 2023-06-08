package com.usermanagementapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "countrymaster")
public class CountryMasterEntity {
    @Id
    @GeneratedValue
    @Column(name="country_id")
	private Integer countryId;
    @Column(name="country_code")
	private String countryCode;
    @Column(name="country_name")
	private String countryName;
}
