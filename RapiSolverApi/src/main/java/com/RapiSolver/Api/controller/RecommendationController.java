package com.RapiSolver.Api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RapiSolver.Api.controller.ModelView.RecommendationModelView;
import com.RapiSolver.Api.entities.Location;
import com.RapiSolver.Api.entities.Recommendation;
import com.RapiSolver.Api.services.ILocationService;
import com.RapiSolver.Api.services.IRecommendationService;
import com.RapiSolver.Api.services.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/recommendations")
@Api(tags="Recommendations", value="Servicio Web RESTFull de Recommendations")
public class RecommendationController {
	
	@Autowired
	private IRecommendationService recommendationService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value="/recommendationsByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Recomendaciones por codigo de Usuario", notes="Método para buscar recomendaciones por el codigo de Usuario")
	@ApiResponses({
		@ApiResponse(code=201, message="Recomendaciones  encontradas"),
		@ApiResponse(code=404, message="Recomendaciones no encontradas")
	})
	public ResponseEntity<List<RecommendationModelView>> recomendationsByUserId(@PathVariable("id") Integer id){
		try {
			List<RecommendationModelView> grupoRecomendaciones=recommendationService.findRecommendationsByUserId(id);
			return new ResponseEntity<List<RecommendationModelView>>(grupoRecomendaciones, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<RecommendationModelView>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Recomendacion", notes="Método para crear Recomendacion")
	@ApiResponses({
		@ApiResponse(code=201, message="Recomendacion creada correctamente"),
		@ApiResponse(code=400, message="Solicitud de Recomendacion inválida")
	})
	public ResponseEntity<Recommendation> insertRecommendation(@Valid @RequestBody RecommendationModelView recommendationModelView){
		try {
			
			Recommendation recommendationNew = new Recommendation();
			recommendationNew = recommendationService.saveRecommendation(recommendationModelView);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(recommendationNew.getRecommendationId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e){
			return new ResponseEntity<Recommendation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
