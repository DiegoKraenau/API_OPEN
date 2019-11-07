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

import com.RapiSolver.Api.entities.Cliente;
import com.RapiSolver.Api.services.IClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/prueba")
@Api(tags="Cliente", value="Servicio Web RESTFull de Clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Clientes", notes="Método para listar todos los clientes")
	@ApiResponses({
		@ApiResponse(code=201, message="Clientes encontrados"),
		@ApiResponse(code=404, message="Clientes no encontrados")
	})
	public ResponseEntity<List<Cliente>>findAll(){
		try {
			List<Cliente> clientes = new ArrayList<>();
			clientes = clienteService.findAll();
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Cliente por Id", notes="Método para buscar cliente por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente encontrado"),
		@ApiResponse(code=404, message="Cliente no encontrado")
	})
	public ResponseEntity<Cliente> findById(@PathVariable("id") Integer id){
		try {
			Optional<Cliente> cliente = clienteService.findById(id);
			if(!cliente.isPresent()) {
				return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/searchByDni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Cliente por Dni", notes="Método para buscar cliente por Dni")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente encontrado"),
		@ApiResponse(code=404, message="Cliente no encontrado")
	})
	public ResponseEntity<Cliente> findByDni(@PathVariable("dni") String dni){
		try {
			Cliente cliente = clienteService.findByDni(dni);
			if(cliente==null) {
				return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/searchByApellidos/{apellidos}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Clientes por Apellidos", notes="Método para buscar clientes por Apellidos")
	@ApiResponses({
		@ApiResponse(code=201, message="Clientes encontrados"),
		@ApiResponse(code=404, message="Clientes no encontrados")
	})
	public ResponseEntity<List<Cliente>> findByApellidos(@PathVariable("apellidos") String apellidos){
		try {
			List<Cliente> clientes = new ArrayList<>(); 
			clientes = clienteService.findByApellidos(apellidos);
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Clientes", notes="Método para crear clientes")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Cliente> insertCliente(@Valid @RequestBody Cliente cliente){
		try {
			Cliente clienteNew = new Cliente();
			clienteNew = clienteService.save(cliente);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(clienteNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Cliente", notes="Método para actualizar un cliente")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente actualizado correctamente"),
		@ApiResponse(code=404, message="Cliente no encontrado")
	})
	public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente cliente){
		try {
			clienteService.save(cliente);
			return new ResponseEntity<Cliente>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Cliente", notes="Método para eliminar un cliente")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente eliminado correctamente"),
		@ApiResponse(code=404, message="Cliente no encontrado")
	})
	public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") Integer id){
		try {
			Optional<Cliente> cliente = clienteService.findById(id);
			if(!cliente.isPresent()) {
				return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
			}
			clienteService.deleteById(id);
			return new ResponseEntity<Cliente>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Cliente>( HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
