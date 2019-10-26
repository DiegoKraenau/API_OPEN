package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Supplier;
import com.RapiSolver.Api.repository.ISupplierRepository;
import com.RapiSolver.Api.services.ISupplierService;

@Service
@Transactional(readOnly = true)
public class SupplierServiceImpl implements ISupplierService{

	@Autowired
	ISupplierRepository suppRepository;
	
	@Override
	@Transactional
	public Supplier save(Supplier t) throws Exception {
		// TODO Auto-generated method stub
		return suppRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		suppRepository.deleteById(id);
	}

	@Override
	public Optional<Supplier> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return suppRepository.findById(id);
	}

	@Override
	public List<Supplier> findAll() throws Exception {
		// TODO Auto-generated method stub
		return suppRepository.findAll();
	}

}
