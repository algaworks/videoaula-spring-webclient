package br.com.algaworks.consumer.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoComPreco {

	private Long codigo;
	private String nome;
	private BigDecimal preco;
	
}
