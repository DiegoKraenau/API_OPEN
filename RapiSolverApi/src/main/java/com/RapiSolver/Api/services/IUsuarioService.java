package com.RapiSolver.Api.services;

import java.util.List;

import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.controller.ModelView.UsuarioModelView;
import com.RapiSolver.Api.entities.Usuario;

public interface IUsuarioService extends CrudService<Usuario>{

	/*
	 UsuarioModelView u1=new UsuarioModelView();
		Usuario usuario=usuarioRepository.findById(id).get();
		u1.setId(usuario.getId());
		u1.setRolId(usuario.getRole().getId());
		u1.setUserName(usuario.getUserName());
		u1.setUserPassword(usuario.getUserPassword());
		
		return u1;
	 */
	
	UsuarioModelView findByUserId(Integer id) throws Exception;
	
	List<UsuarioModelView> findUsers()throws Exception;
}
