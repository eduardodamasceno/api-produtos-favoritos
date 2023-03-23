package com.magalu.desafio.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magalu.desafio.api.domain.model.Favoritos;


public interface FavoritosRepository extends JpaRepository<Favoritos, Long>{
	List<Favoritos> findByClienteid(Long cliente_id);
	List<Favoritos> findByProdutoid(String produtoid);
	Favoritos findByClienteidAndProdutoid(Long cliente_id, String produtoid);
}
