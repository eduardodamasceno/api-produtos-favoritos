create table favoritos (
	id bigint not null auto_increment primary key,
	cliente_id bigint not null,
	produto_id varchar(50) not null,
	constraint fk_favoritos_cliente foreign key (cliente_id) references cliente(id),
	constraint fk_favoritos_produto foreign key (produto_id) references produto(id),
	constraint unq_favorito unique (cliente_id, produto_id)
);