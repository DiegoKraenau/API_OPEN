package com.RapiSolver.Api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Cliente;



@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
	public List<Cliente> findByApellidos(String apellidos) throws Exception;
	public Cliente findByDni(String dni) throws Exception;
}