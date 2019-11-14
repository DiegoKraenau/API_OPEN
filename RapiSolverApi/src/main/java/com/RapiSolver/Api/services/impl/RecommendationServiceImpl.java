package com.RapiSolver.Api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.controller.ModelView.RecommendationModelView;
import com.RapiSolver.Api.entities.Recommendation;
import com.RapiSolver.Api.entities.Supplier;
import com.RapiSolver.Api.repository.IRecommendationRepository;
import com.RapiSolver.Api.repository.ISupplierRepository;
import com.RapiSolver.Api.repository.IUsuarioRepository;
import com.RapiSolver.Api.services.IRecommendationService;

@Service
@Transactional(readOnly = true)
public class RecommendationServiceImpl implements IRecommendationService {

	@Autowired
	ISupplierRepository suppRepository;
	
	@Autowired
	IRecommendationRepository recommendationRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Override
	public Recommendation save(Recommendation t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Recommendation> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recommendation> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecommendationModelView> findRecommendationsByUserId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Supplier> suppliers=suppRepository.findAll();
		Supplier s1=new Supplier();
		
		for (Supplier supplier : suppliers) {
			if(supplier.getUsuario().getId()==id) {
				s1=supplier; 
			}
		}
		
		
		
		List<Recommendation> recommendations=s1.getListRecommendations();
		List<RecommendationModelView> r1=new ArrayList<>();
		
		
		for (Recommendation recommendation : recommendations) {
			RecommendationModelView rmw=new RecommendationModelView();
			rmw.setMark(recommendation.getMark());
			rmw.setNote(recommendation.getNote());
			rmw.setRecommendationId(recommendation.getRecommendationId());
			rmw.setEmail(recommendation.getUsuario().getUserName());
			rmw.setSupplierId(recommendation.getSupplier().getId());
			rmw.setUsuarioId(recommendation.getUsuario().getId());
			
			r1.add(rmw);
		}
				
				
		return r1;
	}

	@Override
	@Transactional
	public Recommendation saveRecommendation(RecommendationModelView recommendationModelView) throws Exception {
		// TODO Auto-generated method stub
		Recommendation r1=new Recommendation();
		
		List<Supplier> suppliers=suppRepository.findAll();
		Supplier s1=new Supplier();
		
		for (Supplier supplier : suppliers) {
			if(supplier.getUsuario().getId()==recommendationModelView.getSupplierId()) {
				s1=supplier; 
			}
		}
		
		r1.setMark(recommendationModelView.getMark());
		r1.setNote(recommendationModelView.getNote());
		r1.setUsuario(usuarioRepository.findById(recommendationModelView.getUsuarioId()).get());
		r1.setSupplier(s1);
		
		Recommendation r2=recommendationRepository.save(r1);
		
		return r2;
	}

}
