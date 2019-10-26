package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.DetalleServiceSupplier;
import com.RapiSolver.Api.repository.IDetalleServiceSupplierRepository;
import com.RapiSolver.Api.services.IDetalleServiceSupplierService;

@Service
@Transactional(readOnly = true)
public class DetalleServiceSupplierServiceImpl implements IDetalleServiceSupplierService {
	
	@Autowired
	private IDetalleServiceSupplierRepository detalleServiceSupplierRepository;

	@Override
	public DetalleServiceSupplier save(DetalleServiceSupplier t) throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.save(t);
	}

	@Override
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		detalleServiceSupplierRepository.deleteById(id);
	}

	@Override
	public Optional<DetalleServiceSupplier> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.findById(id);
	}

	@Override
	public List<DetalleServiceSupplier> findAll() throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.findAll();
	}

	@Override
	public Optional<DetalleServiceSupplier> findBydetailId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return detalleServiceSupplierRepository.findBydetailId(id);
	}

}