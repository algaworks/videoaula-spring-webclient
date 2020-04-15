package br.com.algaworks.produtos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Produto {

	private Long codigo;
	private String nome;
	private String descricao;
}
