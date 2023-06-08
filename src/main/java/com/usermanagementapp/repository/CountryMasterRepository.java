package com.usermanagementapp.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entity.CountryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CountryMasterEntity, Serializable> {

}
