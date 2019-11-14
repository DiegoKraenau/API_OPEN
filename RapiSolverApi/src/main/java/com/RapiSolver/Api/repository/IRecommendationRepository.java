package com.RapiSolver.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RapiSolver.Api.entities.Recommendation;

@Repository
public interface IRecommendationRepository extends JpaRepository<Recommendation, Integer> {

}
