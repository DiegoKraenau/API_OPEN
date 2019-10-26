package com.RapiSolver.Api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Servicio;



@Repository
public interface IServiceRepository extends JpaRepository<Servicio, Integer>{

}
