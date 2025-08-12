package com.as.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reg_user_role_map")
public class RegUserRoleMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reg_user_role_map_id")
	private Integer regUserRoleMapId;

	@ManyToOne
	@JoinColumn(name = "registration_id")
	private Registration registration;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles roles;

	public Integer getRegUserRoleMapId() {
		return regUserRoleMapId;
	}

	public void setRegUserRoleMapId(Integer regUserRoleMapId) {
		this.regUserRoleMapId = regUserRoleMapId;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
