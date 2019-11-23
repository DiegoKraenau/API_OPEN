package com.RapiSolver.Api.services;

import com.RapiSolver.Api.controller.ModelView.CustomerModelView;
import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.entities.Customer;

public interface ICustomerService extends CrudService<Customer>{

	
	CustomerModelView findCustomerByUserId(Integer id) throws Exception;
}
