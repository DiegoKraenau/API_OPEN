package com.RapiSolver.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
