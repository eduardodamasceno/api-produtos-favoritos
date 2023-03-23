package com.magalu.desafio.api.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.magalu.desafio.api.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}