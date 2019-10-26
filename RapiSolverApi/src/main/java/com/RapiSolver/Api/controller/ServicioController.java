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

import com.RapiSolver.Api.entities.Servicio;
import com.RapiSolver.Api.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/servicios")
@Api(tags="Servicios", value="Servicio Web RESTFull de Servicios")
public class ServicioController {
	
	@Autowired
	private IServicioService servicioService;
	
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Servicios", notes="Método para listar todos los servicios")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicios encontrados"),
		@ApiResponse(code=404, message="Servicios no encontrados")
	})
	public ResponseEntity<List<Servicio>>findAll(){
		try {
			List<Servicio> servicio = new ArrayList<>();
			servicio = servicioService.findAll();
			return new ResponseEntity<List<Servicio>>(servicio, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Servicio>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Servicio por Id", notes="Método para buscar servicio por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicios encontrados"),
		@ApiResponse(code=404, message="Servicios no encontrados")
	})
	public ResponseEntity<Servicio> findById(@PathVariable("id") Integer id){
		try {
			Optional<Servicio> servicio = servicioService.findById(id);
			if(!servicio.isPresent()) {
				return new ResponseEntity<Servicio>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Servicio>(servicio.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Servicio", notes="Método para crear servicios")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicio creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Servicio> insertServicio(@Valid @RequestBody Servicio servicio){
		try {
			Servicio servicioNuevo = new Servicio();
			servicioNuevo = servicioService.save(servicio);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(servicioNuevo.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Servicio", notes="Método para actualizar un servicio")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicio actualizado correctamente"),
		@ApiResponse(code=404, message="Servicio no encontrado")
	})
	public ResponseEntity<Servicio> updateServicio(@Valid @RequestBody Servicio servicio){
		try {
			servicioService.save(servicio);
			return new ResponseEntity<Servicio>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Servicio", notes="Método para eliminar una servicio")
	@ApiResponses({
		@ApiResponse(code=201, message="Servicio eliminado correctamente"),
		@ApiResponse(code=404, message="Servicio no encontrado")
	})
	public ResponseEntity<Servicio> deleteServicio(@PathVariable("id") Integer id){
		try {
			Optional<Servicio> servicio = servicioService.findById(id);
			if(!servicio.isPresent()) {
				return new ResponseEntity<Servicio>(HttpStatus.NOT_FOUND);
			}
			servicioService.deleteById(id);
			return new ResponseEntity<Servicio>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Servicio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
