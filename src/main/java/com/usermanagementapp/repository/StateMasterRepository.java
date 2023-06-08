package com.usermanagementapp.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entity.StateMasterEntity;

public interface StateMasterRepository extends JpaRepository<StateMasterEntity, Serializable> {

	public List<StateMasterEntity> findByCountryId(Integer countryId);
}
