package com.RapiSolver.Api.services;

import java.util.List;

import com.RapiSolver.Api.entities.Servicio;

public interface IServicioService extends CrudService<Servicio>{
	
	List<Servicio> findByserviceName(String serviceName) throws Exception;
	List<Servicio> findByserviceCategory(String serviceCategory) throws Exception;

}
