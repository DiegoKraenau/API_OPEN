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

import com.RapiSolver.Api.entities.Role;
import com.RapiSolver.Api.services.IRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/roles")
@Api(tags="Role", value="Servicio Web RESTFull de Roles")
public class RolController {

	@Autowired
	private IRoleService roleService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Roles", notes="Método para listar todos los Roles")
	@ApiResponses({
		@ApiResponse(code=201, message="Roles encontrados"),
		@ApiResponse(code=404, message="Roles no encontrados")
	})
	public ResponseEntity<List<Role>>findAll(){
		try {
			List<Role> roles = new ArrayList<>();
			roles = roleService.findAll();
			return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Role>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Rol por Id", notes="Método para buscar Rol por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Rol encontrado"),
		@ApiResponse(code=404, message="Rol no encontrado")
	})
	public ResponseEntity<Role> findById(@PathVariable("id") Integer id){
		try {
			Optional<Role> role = roleService.findById(id);
			if(!role.isPresent()) {
				return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Role>(role.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Roles", notes="Método para crear Roles")
	@ApiResponses({
		@ApiResponse(code=201, message="Rol creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Role> insertRol(@Valid @RequestBody Role role){
		try {
			Role roleNew = new Role();
			roleNew = roleService.save(role);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(roleNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Rol", notes="Método para actualizar un Rol")
	@ApiResponses({
		@ApiResponse(code=201, message="Rol actualizado correctamente"),
		@ApiResponse(code=404, message="Rol no encontrado")
	})
	public ResponseEntity<Role> updateRol(@Valid @RequestBody Role role){
		try {
			roleService.save(role);
			return new ResponseEntity<Role>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Rol", notes="Método para eliminar un Rol")
	@ApiResponses({
		@ApiResponse(code=201, message="Rol eliminado correctamente"),
		@ApiResponse(code=404, message="Rol no encontrado")
	})
	public ResponseEntity<Role> deleteRole(@PathVariable("id") Integer id){
		try {
			Optional<Role> role = roleService.findById(id);
			if(!role.isPresent()) {
				return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
			}
			roleService.deleteById(id);
			return new ResponseEntity<Role>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
