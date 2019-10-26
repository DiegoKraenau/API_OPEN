package com.RapiSolver.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Servicio;



@Repository
public interface IServiceRepository extends JpaRepository<Servicio, Integer>{
	public List<Servicio> findByserviceName(String serviceName);
	public List<Servicio> findByserviceCategory(String serviceCategory);

}
