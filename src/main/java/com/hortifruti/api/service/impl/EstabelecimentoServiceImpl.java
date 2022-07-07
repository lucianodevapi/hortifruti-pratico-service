package com.hortifruti.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hortifruti.api.entity.Estabelecimento;
import com.hortifruti.api.repository.EstabelecimentoRepository;
import com.hortifruti.api.service.EstabelecimentoService;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {
	
	@Autowired
	private EstabelecimentoRepository repository;

	@Override
	public Estabelecimento save(Estabelecimento entity) {
		return repository.save(entity);
	}

	@Override
	public Estabelecimento findById(String id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Estabelecimento> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(String id) {
		return repository.existsById(id);
	}

	@Override
	public boolean existsByNome(String nome) {
		return repository.existsByNome(nome);
	}

}
