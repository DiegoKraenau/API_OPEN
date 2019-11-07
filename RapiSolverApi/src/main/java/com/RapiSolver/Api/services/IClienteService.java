package com.RapiSolver.Api.services;

import java.util.List;

import com.RapiSolver.Api.entities.Cliente;



public interface IClienteService extends CrudService<Cliente> {
	public List<Cliente> findByApellidos(String apellidos) throws Exception;
	public Cliente findByDni(String dni) throws Exception;
}
