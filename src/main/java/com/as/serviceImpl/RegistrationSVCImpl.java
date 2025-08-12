package com.as.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.as.beans.RegistrationBean;
import com.as.entity.RegUserRoleMap;
import com.as.entity.Registration;
import com.as.entity.Roles;
import com.as.repository.RegRoleRepository;
import com.as.repository.RegistrationRepository;
import com.as.repository.RolesRepository;
import com.as.service.RegistrationSVC;

@Service
public class RegistrationSVCImpl implements RegistrationSVC {

	@Autowired
	private RegistrationRepository registrationRepo;

	@Autowired
	private RolesRepository rolesRepo;

	@Autowired
	private RegRoleRepository regRoleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveOrUpdateUser(RegistrationBean registrationBean) {

		Registration registration = new Registration();
		BeanUtils.copyProperties(registrationBean, registration);
		registration.setPassword(passwordEncoder.encode(registrationBean.getPassword()));
		registration.setActiveFlg(true);
		registrationRepo.save(registration);

		Roles roles = rolesRepo.findByRoleName("USER");

		RegUserRoleMap regRoleUserMap = new RegUserRoleMap();
		regRoleUserMap.setRegistration(registration);
		regRoleUserMap.setRoles(roles);

		regRoleRepo.save(regRoleUserMap);
	}

}
