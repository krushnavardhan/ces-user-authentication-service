package com.as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

	Roles findByRoleName(String roleName);

}
