package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Role;
import com.RapiSolver.Api.repository.IRoleRepository;
import com.RapiSolver.Api.services.IRoleService;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	@Transactional
	public Role save(Role t) throws Exception {
		// TODO Auto-generated method stub
		return roleRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
	}

	@Override
	public Optional<Role> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

	@Override
	public List<Role> findAll() throws Exception {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}
