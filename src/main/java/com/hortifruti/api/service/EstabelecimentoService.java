package com.hortifruti.api.service;

import java.util.List;

import com.hortifruti.api.entity.Estabelecimento;

public interface EstabelecimentoService {

	Estabelecimento save(Estabelecimento entity);
	
	Estabelecimento findById(String id);
	
	List<Estabelecimento> findAll();
	
	boolean existsById(String id);
	
	boolean existsByNome(String nome);
}
