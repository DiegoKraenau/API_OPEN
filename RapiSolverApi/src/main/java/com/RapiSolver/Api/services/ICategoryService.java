package com.RapiSolver.Api.services;

import com.RapiSolver.Api.entities.Category;

public interface ICategoryService extends CrudService<Category>{

	Category findByCategoryName(String categoryName) throws Exception;
}
