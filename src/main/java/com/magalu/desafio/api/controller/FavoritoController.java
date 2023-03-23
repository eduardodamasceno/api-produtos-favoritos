package com.magalu.desafio.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.magalu.desafio.api.domain.model.Favoritos;
import com.magalu.desafio.api.domain.model.Produto;
import com.magalu.desafio.api.domain.repository.FavoritosRepository;
import com.magalu.desafio.api.domain.repository.ProdutoRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RequestMapping("/clientes/{clienteId}/favoritos")
@RestController
@AllArgsConstructor
public class FavoritoController {
    
    @Autowired
    private FavoritosRepository favoritosRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping
    public ResponseEntity<List<Produto>> listar(@PathVariable Long clienteId) {
    List<Favoritos> favoritos = favoritosRepository.findByClienteid(clienteId);

    if (favoritos.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    List<Produto> produtosFavoritos = new ArrayList<>();
    for (Favoritos favorito : favoritos) {
        Produto produto = produtoRepository.findById(favorito.getProdutoid()).orElse(null);
            produtosFavoritos.add(produto);
    }

    return ResponseEntity.ok(produtosFavoritos);
    }
    
    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar(@PathVariable Long clienteId, @PathVariable String produtoId) {
    
    Favoritos favoritos = favoritosRepository.findByClienteidAndProdutoid(clienteId, produtoId);
    if (favoritos == null) {
        return ResponseEntity.notFound().build();
    }
    
    return produtoRepository.findById(produtoId)
    		.map(ResponseEntity::ok)
    		.orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> adicionar(@PathVariable Long clienteId, @RequestBody Produto produtoId) {
        Produto produto = produtoRepository.findById(produtoId.getId()).orElse(null);
    
    	
        if (produto == null) {
            Mono<Produto> produtoApi = WebClient.create().get()
                    .uri("http://challenge-api.luizalabs.com/api/product/{id}/", produtoId.getId())
                    .retrieve()
                    .bodyToMono(Produto.class);
            
            produto = produtoApi.block();
            if (produto == null) {
                return ResponseEntity.notFound().build();
            }
            
            produtoRepository.save(produto);
        }
        
        List<Favoritos> favoritos = favoritosRepository.findByClienteid(clienteId);
        boolean produtoJaExiste = favoritos.stream().anyMatch(f -> f.getProdutoid().equals(produtoId.getId()));
        if (produtoJaExiste) {
            return ResponseEntity.badRequest().body("O produto informado já está na lista de produtos favoritos do cliente.");
        }
        
        Favoritos favorito = new Favoritos();
        favorito.setClienteid(clienteId);
        favorito.setProdutoid(produto.getId());
        favorito = favoritosRepository.save(favorito);
  
      
        return ResponseEntity.status(HttpStatus.CREATED).body("O produto " + produto.getTitle() + " foi adicionado à lista de produtos favoritos do cliente com sucesso.");
    }
    
    
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> remover(@PathVariable("clienteId") Long clienteId, @PathVariable("produtoId") String produtoId) {
        Favoritos favoritos = favoritosRepository.findByClienteidAndProdutoid(clienteId, produtoId);
        if (favoritos == null) {
            return ResponseEntity.notFound().build();
        }
        favoritosRepository.delete(favoritos);
        return ResponseEntity.noContent().build();
    }

}