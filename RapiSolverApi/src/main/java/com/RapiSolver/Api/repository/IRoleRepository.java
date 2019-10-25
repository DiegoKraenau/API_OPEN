package com.RapiSolver.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

}
