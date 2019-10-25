package com.RapiSolver.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Location;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Integer>{

}
