package com.usermanagementapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "statemaster")
public class StateMasterEntity {

    @Id
    @GeneratedValue
    @Column(name="state_id")
	private Integer stateId;
    @Column(name="state_name")
	private String stateName;
    @Column(name="country_id")
	private Integer countryId;
}
