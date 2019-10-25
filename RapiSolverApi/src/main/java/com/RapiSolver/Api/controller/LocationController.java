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

import com.RapiSolver.Api.entities.Location;
import com.RapiSolver.Api.services.ILocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/locations")
@Api(tags="Location", value="Servicio Web RESTFull de Locations")
public class LocationController {

	@Autowired
	private ILocationService locationService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Location", notes="Método para listar todos las Locations")
	@ApiResponses({
		@ApiResponse(code=201, message="Locations encontrados"),
		@ApiResponse(code=404, message="Locations no encontrados")
	})
	public ResponseEntity<List<Location>>findAll(){
		try {
			List<Location> locations = new ArrayList<>();
			locations = locationService.findAll();
			return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Location>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Location por Id", notes="Método para buscar Location por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Location encontrado"),
		@ApiResponse(code=404, message="Location no encontrado")
	})
	public ResponseEntity<Location> findById(@PathVariable("id") Integer id){
		try {
			Optional<Location> location = locationService.findById(id);
			if(!location.isPresent()) {
				return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Location>(location.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Location", notes="Método para crear Location")
	@ApiResponses({
		@ApiResponse(code=201, message="Location creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Location> insertLocation(@Valid @RequestBody Location locacion){
		try {
			Location locationNew = new Location();
			locationNew = locationService.save(locacion);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(locationNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Location", notes="Método para actualizar un Location")
	@ApiResponses({
		@ApiResponse(code=201, message="Location actualizado correctamente"),
		@ApiResponse(code=404, message="Location no encontrado")
	})
	public ResponseEntity<Location> updateLocation(@Valid @RequestBody Location location){
		try {
			locationService.save(location);
			return new ResponseEntity<Location>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Location", notes="Método para eliminar un Location")
	@ApiResponses({
		@ApiResponse(code=201, message="Location eliminado correctamente"),
		@ApiResponse(code=404, message="Location no encontrado")
	})
	public ResponseEntity<Location> deleteLocation(@PathVariable("id") Integer id){
		try {
			Optional<Location> location = locationService.findById(id);
			if(!location.isPresent()) {
				return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
			}
			locationService.deleteById(id);
			return new ResponseEntity<Location>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
