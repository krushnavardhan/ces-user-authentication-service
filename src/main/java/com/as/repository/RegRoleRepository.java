package com.as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entity.RegUserRoleMap;

public interface RegRoleRepository extends JpaRepository<RegUserRoleMap, Integer> {

}
