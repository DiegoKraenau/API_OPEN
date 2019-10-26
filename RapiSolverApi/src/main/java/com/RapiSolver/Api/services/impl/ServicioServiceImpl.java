package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Servicio;
import com.RapiSolver.Api.repository.IServiceRepository;
import com.RapiSolver.Api.services.IServicioService;

@Service
@Transactional(readOnly = true)
public class ServicioServiceImpl implements IServicioService{
	
	@Autowired
	private IServiceRepository serviceRepository;

	@Override
	public Servicio save(Servicio t) throws Exception {
		// TODO Auto-generated method stub
		return serviceRepository.save(t);
	}

	@Override
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		serviceRepository.deleteById(id);
	}

	@Override
	public Optional<Servicio> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return serviceRepository.findById(id);
	}

	@Override
	public List<Servicio> findAll() throws Exception {
		// TODO Auto-generated method stub
		return serviceRepository.findAll();
	}

	@Override
	public List<Servicio> findByserviceName(String serviceName) throws Exception {
		// TODO Auto-generated method stub
		return serviceRepository.findByserviceName(serviceName);
	}

	@Override
	public List<Servicio> findByserviceCategory(String serviceCategory) throws Exception {
		// TODO Auto-generated method stub
		return serviceRepository.findByserviceCategory(serviceCategory);
	}

}