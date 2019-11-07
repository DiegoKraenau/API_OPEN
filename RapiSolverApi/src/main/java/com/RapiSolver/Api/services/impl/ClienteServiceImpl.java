package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Cliente;
import com.RapiSolver.Api.repository.IClienteRepository;
import com.RapiSolver.Api.services.IClienteService;


@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	@Transactional
	public Cliente save(Cliente t) throws Exception {
		return clienteRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		clienteRepository.deleteById(id);
	}

	@Override
	public Optional<Cliente> findById(int id) throws Exception {
		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> findAll() throws Exception {
		return clienteRepository.findAll();
	}

	@Override
	public List<Cliente> findByApellidos(String apellidos) throws Exception {
		return clienteRepository.findByApellidos(apellidos);
	}

	@Override
	public Cliente findByDni(String dni) throws Exception {
		return clienteRepository.findByDni(dni);
	}
	
	
}
