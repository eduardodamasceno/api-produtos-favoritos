package com.magalu.desafio.api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto {
	
	@Id
	private String id;
	
	@NotBlank
	@Column(name = "titulo")
	private String title;
	
	@NotBlank
	@Column(name = "imagem")
	private String image;
	
	@NotNull
	@Column(name = "preco")
	private Float price;
	
	@Column(name = "nota_review")
	private Float reviewScore;

}
