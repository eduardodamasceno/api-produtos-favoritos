package com.magalu.desafio.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magalu.desafio.api.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
