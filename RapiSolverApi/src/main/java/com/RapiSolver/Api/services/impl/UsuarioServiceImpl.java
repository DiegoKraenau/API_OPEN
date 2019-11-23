package com.RapiSolver.Api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RapiSolver.Api.controller.ModelView.UsuarioModelView;
import com.RapiSolver.Api.entities.Usuario;
import com.RapiSolver.Api.repository.IUsuarioRepository;
import com.RapiSolver.Api.services.IUsuarioService;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public Usuario save(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<Usuario> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		
		
		return usuarioRepository.findById(id);
	}

	@Override
	public List<Usuario> findAll() throws Exception {
		
		return usuarioRepository.findAll();
	}

	@Override
	public UsuarioModelView findByUserId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		 	UsuarioModelView u1=new UsuarioModelView();
			Usuario usuario=usuarioRepository.findById(id).get();
			u1.setId(usuario.getId());
			u1.setRolId(usuario.getRole().getId());
			u1.setCorreo(usuario.getUserName());
			u1.setUserPassword(usuario.getUserPassword());
			
			return u1;
	}

}
