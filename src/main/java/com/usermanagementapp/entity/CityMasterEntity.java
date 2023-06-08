package com.usermanagementapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CityMaster")
public class CityMasterEntity {

	    @Id
	    @GeneratedValue
	    @Column(name="city_id")
		private Integer cityId;
	    @Column(name="city_name")
		private String cityName;
	    @Column(name="state_id")
		private Integer stateId;
	    
	    
	    // hibernate:
        //ddl-auto: update
	    //(4, "Mysore",2),( 5, "Maywood",3),(6, "Westwood",3),( 7 , "Ockwood",4),(8, "Coyahoga county",4);
}    
