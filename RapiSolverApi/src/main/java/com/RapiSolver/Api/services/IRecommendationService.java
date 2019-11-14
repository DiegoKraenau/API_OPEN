package com.RapiSolver.Api.services;

import java.util.List;

import com.RapiSolver.Api.controller.ModelView.DetailModelView;
import com.RapiSolver.Api.controller.ModelView.RecommendationModelView;
import com.RapiSolver.Api.entities.Recommendation;

public interface IRecommendationService extends CrudService<Recommendation>{
	
	List<RecommendationModelView> findRecommendationsByUserId(Integer id) throws Exception;
	
	Recommendation saveRecommendation(RecommendationModelView recommendationModelView) throws Exception;

}
