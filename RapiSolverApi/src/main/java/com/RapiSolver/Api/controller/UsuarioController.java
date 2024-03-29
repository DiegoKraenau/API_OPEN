package com.RapiSolver.Api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RapiSolver.Api.controller.ModelView.UsuarioModelView;

import com.RapiSolver.Api.entities.Role;
import com.RapiSolver.Api.entities.Usuario;

import com.RapiSolver.Api.services.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/usuarios")
@Api(tags="Usuario", value="Servicio Web RESTFull de Usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;


	

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Clientes", notes="Método para listar todos los clientes")
	@ApiResponses({
		@ApiResponse(code=201, message="Clientes encontrados"),
		@ApiResponse(code=404, message="Clientes no encontrados")
	})
	public ResponseEntity<List<UsuarioModelView>>findAll(){
		try {
			List<UsuarioModelView> usuariosGroup=usuarioService.findUsers();
			
			return new ResponseEntity<List<UsuarioModelView>>(usuariosGroup, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<UsuarioModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Usuario por Id", notes="Método para buscar Usuario por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Usuario encontrado"),
		@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<UsuarioModelView> findById(@PathVariable("id") Integer id){
		try {
			 UsuarioModelView usuario=usuarioService.findByUserId(id);
			return new ResponseEntity<UsuarioModelView>(usuario, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<UsuarioModelView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Usuarios", notes="Método para crear Usuarios")
	@ApiResponses({
		@ApiResponse(code=201, message="Usuario creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Role> insertUsuario(@Valid @RequestBody Usuario usuario){
		try {
			Usuario usuarioNew = new Usuario();
			usuarioNew = usuarioService.save(usuario);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(usuarioNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Usuario", notes="Método para actualizar un Usuario")
	@ApiResponses({
		@ApiResponse(code=201, message="Usuario actualizado correctamente"),
		@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario){
		try {
			usuarioService.save(usuario);
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Usuario", notes="Método para eliminar un Usuario")
	@ApiResponses({
		@ApiResponse(code=201, message="Usuario eliminado correctamente"),
		@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") Integer id){
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if(!usuario.isPresent()) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
			usuarioService.deleteById(id);
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
