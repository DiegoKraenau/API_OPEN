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

import com.RapiSolver.Api.controller.ModelView.CustomerModelView;
import com.RapiSolver.Api.controller.ModelView.SupplierModelView;
import com.RapiSolver.Api.entities.Customer;
import com.RapiSolver.Api.entities.Usuario;
import com.RapiSolver.Api.services.ICustomerService;
import com.RapiSolver.Api.services.IRoleService;
import com.RapiSolver.Api.services.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/customers")
@Api(tags="Customer", value="Servicio Web RESTFull de customers")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Clientes", notes="Método para listar todos los clientes")
	@ApiResponses({
		@ApiResponse(code=201, message="Clientes encontrados"),
		@ApiResponse(code=404, message="Clientes no encontrados")
	})
	public ResponseEntity<List<Customer>>findAll(){
		try {
			List<Customer> customer = new ArrayList<>();
			customer = customerService.findAll();
			return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Cliente por Id", notes="Método para buscar cliente por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Clientes encontrados"),
		@ApiResponse(code=404, message="Clientes no encontrados")
	})
	public ResponseEntity<Customer> findById(@PathVariable("id") Integer id){
		try {
			Optional<Customer> customer = customerService.findById(id);
			if(!customer.isPresent()) {
				return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Cliente", notes="Método para crear clientes")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente creado correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody CustomerModelView customer){
		try {
			Customer customerNuevo = new Customer();
			Customer c1=new Customer();
			Usuario u1=new Usuario();
			
			u1.setUserName(customer.getEmail());
			u1.setUserPassword(customer.getUserPassword());
			u1.setRole(roleService.findById(1).get());
			
			u1=usuarioService.save(u1);
			
			c1.setEmail(customer.getEmail());
			c1.setName(customer.getName());
			c1.setLastName(customer.getLastName());
			c1.setUsuario(u1);
			//c1.setGender("No tiene sexo");
			
			
			
			customerNuevo = customerService.save(c1);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(customerNuevo.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Cliente", notes="Método para actualizar un cliente")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente actualizado correctamente"),
		@ApiResponse(code=404, message="Cliente no encontrado")
	})
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer){
		try {
			customerService.save(customer);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Cliente", notes="Método para eliminar una cliente")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente eliminado correctamente"),
		@ApiResponse(code=404, message="Cliente no encontrado")
	})
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer id){
		try {
			Optional<Customer> usuario = customerService.findById(id);
			if(!usuario.isPresent()) {
				return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			}
			customerService.deleteById(id);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/searchByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Cliente por codigo de Usuario", notes="Método para buscar cliente por codigo de Usuario")
	@ApiResponses({
		@ApiResponse(code=201, message="Cliente encontrados"),
		@ApiResponse(code=404, message="Cliente no encontrados")
	})
	public ResponseEntity<CustomerModelView> searchByUserId(@PathVariable("id") Integer id){
		try {
			CustomerModelView customer = customerService.findCustomerByUserId(id);
			
			return new ResponseEntity<CustomerModelView>(customer, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<CustomerModelView>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
