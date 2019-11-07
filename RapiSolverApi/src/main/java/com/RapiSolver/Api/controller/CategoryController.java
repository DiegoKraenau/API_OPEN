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

import com.RapiSolver.Api.controller.ModelView.CategoryModelView;
import com.RapiSolver.Api.entities.Category;
import com.RapiSolver.Api.services.ICategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categories")
@Api(tags="Category", value="Servicio Web RESTFull de Categorias")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Categorias", notes="Método para listar todas las categorias")
	@ApiResponses({
		@ApiResponse(code=201, message="Categorias encontradas"),
		@ApiResponse(code=404, message="Categorias no encontradas")
	})
	public ResponseEntity<List<CategoryModelView>>findAll(){
		try {
			List<Category> category = new ArrayList<>();
			category = categoryService.findAll();
			List<CategoryModelView> categoryGroup=new ArrayList<>();
			for (Category categoria : category) {
				CategoryModelView c1=new CategoryModelView();
				c1.setId(categoria.getId());
				c1.setCategoryName(categoria.getCategoryName());
				c1.setCategoryDescription(categoria.getCategoryDescription());
				
				categoryGroup.add(c1);
			}
			
			return new ResponseEntity<List<CategoryModelView>>(categoryGroup, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<CategoryModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Categoria por Id", notes="Método para buscar Categoria por Id")
	@ApiResponses({
		@ApiResponse(code=201, message="Categorias encontradas"),
		@ApiResponse(code=404, message="Categorias no encontradas")
	})
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
		try {
			Optional<Category> categoria = categoryService.findById(id);
			if(!categoria.isPresent()) {
				return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Category>(categoria.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Categorias", notes="Método para crear Categorias")
	@ApiResponses({
		@ApiResponse(code=201, message="Categoria creada correctamente"),
		@ApiResponse(code=400, message="Solicitud de creación inválida")
	})
	public ResponseEntity<Category> insertCategory(@Valid @RequestBody Category categoria){
		try {
			Category categoriaNueva = new Category();
			categoriaNueva = categoryService.save(categoria);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(categoriaNueva.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Actualizar Categoria", notes="Método para actualizar una Categoria")
	@ApiResponses({
		@ApiResponse(code=201, message="Categoria actualizada correctamente"),
		@ApiResponse(code=404, message="Categoria no encontrada")
	})
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category categoria){
		try {
			categoryService.save(categoria);
			return new ResponseEntity<Category>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Categoria", notes="Método para eliminar una Categoria")
	@ApiResponses({
		@ApiResponse(code=201, message="Categoria eliminada correctamente"),
		@ApiResponse(code=404, message="Categoria no encontrada")
	})
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id){
		try {
			Optional<Category> category = categoryService.findById(id);
			if(!category.isPresent()) {
				return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			}
			categoryService.deleteById(id);
			return new ResponseEntity<Category>(HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
