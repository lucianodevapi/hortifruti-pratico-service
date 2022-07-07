package com.hortifruti.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hortifruti.api.entity.Estabelecimento;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento, String> {

	boolean existsById(String id);

	boolean existsByNome(String nome);
}
