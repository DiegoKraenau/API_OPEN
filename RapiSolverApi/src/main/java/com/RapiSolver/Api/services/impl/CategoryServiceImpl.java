package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.entities.Category;
import com.RapiSolver.Api.repository.ICategoryRepository;
import com.RapiSolver.Api.services.ICategoryService;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	ICategoryRepository categoryRepository;
	
	@Override
	@Transactional
	public Category save(Category t) throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

	@Override
	public Optional<Category> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
