create table produto (
	id varchar(50) not null primary key,
	titulo varchar(80) not null,
	imagem varchar(110) not null,
	preco decimal(8,2) not null,
	nota_review float
);