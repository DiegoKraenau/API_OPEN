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

import com.RapiSolver.Api.entities.DetalleServiceSupplier;
import com.RapiSolver.Api.entities.Role;
import com.RapiSolver.Api.services.IDetalleServiceSupplierService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/detalles")
@Api(tags="DetalleServiceSupplier", value="Servicio Web RESTFull de Detalle")
public class DetalleServiceSupplierController {
	
	@Autowired
	private IDetalleServiceSupplierService detalleService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Detalles", notes="Método para listar todos los Detalles")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalles encontrados"),
		@ApiResponse(code=404, message="Detalles no encontrados")
	})
	public ResponseEntity<List<DetalleServiceSupplier>>findAll(){
		try {
			List<DetalleServiceSupplier> detalles = new ArrayList<>();
			detalles = detalleService.findAll();
			return new ResponseEntity<List<DetalleServiceSupplier>>(detalles, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<DetalleServiceSupplier>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Detalle por Id", notes="Método para buscar Detalle por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle encontrado"),
		@ApiResponse(code=404, message="Detalle no encontrado")
	})
	public ResponseEntity<DetalleServiceSupplier> findById(@PathVariable("id") Integer id){
		try {
			Optional<DetalleServiceSupplier> detalle = detalleService.findById(id);
			if(!detalle.isPresent()) {
				return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<DetalleServiceSupplier>(detalle.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Detalle", notes="Método para crear Detalle")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Role> insertDetalle(@Valid @RequestBody DetalleServiceSupplier detalle){
		try {
			DetalleServiceSupplier detalleNew = new DetalleServiceSupplier();
			detalleNew = detalleService.save(detalle);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(detalleNew.getDetailId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Detalle", notes="Método para actualizar un Detalle")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle actualizado correctamente"),
		@ApiResponse(code=404, message="Detalle no encontrado")
	})
	public ResponseEntity<DetalleServiceSupplier> updateDetalle(@Valid @RequestBody DetalleServiceSupplier detalle){
		try {
			detalleService.save(detalle);
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Detalle", notes="Método para eliminar un Detalle")
	@ApiResponses({
		@ApiResponse(code=201, message="Detalle eliminado correctamente"),
		@ApiResponse(code=404, message="Detalle no encontrado")
	})
	public ResponseEntity<DetalleServiceSupplier> deleteDetalle(@PathVariable("id") Integer id){
		try {
			Optional<DetalleServiceSupplier> detalle = detalleService.findById(id);
			if(!detalle.isPresent()) {
				return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.NOT_FOUND);
			}
			detalleService.deleteById(id);
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<DetalleServiceSupplier>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	

}
