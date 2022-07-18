package com.hortifruti.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hortifruti.api.entity.Estabelecimento;
import com.hortifruti.api.request.EstabelecimentoAddRequest;
import com.hortifruti.api.request.EstabelecimentoUpdateRequest;
import com.hortifruti.api.response.EstabelecimentoResponse;
import com.hortifruti.api.response.message.MessageResponse;
import com.hortifruti.api.service.impl.EstabelecimentoServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/estabelecimento")
public class EstabeleciementoController {

	@Autowired
	private EstabelecimentoServiceImpl service;

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody EstabelecimentoAddRequest request) {

		try {
			if (service.existsByNome(request.getNome())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Name is already taken!"));
			}
			Estabelecimento estabelecimento = toEntity(request);
			Estabelecimento entity = service.save(estabelecimento);
			EstabelecimentoResponse response = toResponse(entity);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Estabelecimento não cadastrado"));
		}
	}

	@ApiOperation(value = "Update Country")
	@PutMapping("/update")
	public ResponseEntity<?> updat(@Valid @RequestBody EstabelecimentoUpdateRequest request) {

		try {
			Estabelecimento estabelecimento = service.findById(request.getId());

			if (estabelecimento != null && estabelecimento.getId().equals(request.getId())) {

				if (!request.getNome().isEmpty() && !request.getNome().equals(estabelecimento.getNome())) {
					estabelecimento.setNome(request.getNome());
				}
				if (!request.getLogo().isEmpty() && !request.getLogo().equals(estabelecimento.getLogo())) {
					estabelecimento.setLogo(request.getLogo());
				}

				if (request.isBloqueado() != estabelecimento.isBloqueado()) {
					estabelecimento.setBloqueado(request.isBloqueado());
				}
				
				if(request.getOnline() != estabelecimento.getOnline()) {
					estabelecimento.setOnline(request.getOnline());
				}
				estabelecimento.setUpdatedAt(new Date());

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Estabelecimento não encontraco"));
			}

			Estabelecimento entity = service.save(estabelecimento);
			EstabelecimentoResponse response = toResponse(entity);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Estabelecimento não encontrado "));
		}
	}

	@ApiOperation(value = "Buscar Todos")
	@GetMapping("/findAll")
	public ResponseEntity<?> all() {
		List<Estabelecimento> list = service.findAll();
		List<EstabelecimentoResponse> responseList = toResponseList(list);
		return ResponseEntity.ok().body(responseList);
	}

	@ApiOperation(value = "Buscar por Id")
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		try {
			Estabelecimento entity = service.findById(id);
			EstabelecimentoResponse response = toResponse(entity);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Estabelecimento não encontrado "));
		}
	}

	private Estabelecimento toEntity(EstabelecimentoAddRequest request) {
		Estabelecimento entity = new Estabelecimento();
		entity.setUserId(request.getUserId());
		entity.setNome(request.getNome());
		entity.setLogo(request.getLogo());
		entity.setBloqueado(request.isBloqueado());
		entity.setUpdatedAt(new Date());
		return entity;
	}

	private EstabelecimentoResponse toResponse(Estabelecimento entity) {
		EstabelecimentoResponse response = new EstabelecimentoResponse();
		response.setId(entity.getId());
		response.setUserId(entity.getUserId());
		response.setNome(entity.getNome());
		response.setLogo(entity.getLogo());
		response.setBloqueado(entity.isBloqueado());
		response.setUpdatedAt(entity.getUpdatedAt());
		return response;
	}

	List<EstabelecimentoResponse> toResponseList(List<Estabelecimento> entityList) {
		List<EstabelecimentoResponse> responseList = new ArrayList<EstabelecimentoResponse>();
		EstabelecimentoResponse response = new EstabelecimentoResponse();
		for (Estabelecimento entity : entityList) {
			response.setId(entity.getId());
			response.setUserId(entity.getUserId());
			response.setNome(entity.getNome());
			response.setLogo(entity.getLogo());
			response.setBloqueado(entity.isBloqueado());
			response.setUpdatedAt(entity.getUpdatedAt());
			responseList.add(response);
		}
		return responseList;
	}
}
