package com.usermanagementapp.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagementapp.entity.CityMasterEntity;


public interface CityMasterRepository extends JpaRepository<CityMasterEntity, Serializable> {
 public List<CityMasterEntity> findByStateId(Integer stateId);
}
