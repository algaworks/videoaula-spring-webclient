package br.com.algaworks.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.algaworks.consumer.model.ProdutoComPreco;
import reactor.core.publisher.Mono;

@Service
public class PrecoProdutoService {

	@Autowired
	private WebClient webClientProdutos;
	
	@Autowired
	private WebClient webClientPrecos;
	
	public ProdutoComPreco obterPorCodigoParalelo(Long codigoProduto) {

		Mono<ProdutoComPreco> monoProduto = this.webClientProdutos
			.method(HttpMethod.GET)
			.uri("/produtos/{codigo}", codigoProduto)
			.retrieve()
			.bodyToMono(ProdutoComPreco.class);
	
		Mono<ProdutoComPreco> monoPreco = this.webClientPrecos
				.method(HttpMethod.GET)
				.uri("/precos/{codigo}", codigoProduto)
				.retrieve()
				.bodyToMono(ProdutoComPreco.class);

		ProdutoComPreco produtoComPreco = Mono.zip(monoProduto, monoPreco).map(tuple -> {
			tuple.getT1().setPreco(tuple.getT2().getPreco());
			return tuple.getT1();
		}).block();

		return produtoComPreco;
	}
	
	public ProdutoComPreco obterPorCodigoSincrono(Long codigoProduto) {

		Mono<ProdutoComPreco> monoProduto = this.webClientProdutos
			.method(HttpMethod.GET)
			.uri("/produtos/{codigo}", codigoProduto)
			.retrieve()
			.bodyToMono(ProdutoComPreco.class);
	
		Mono<ProdutoComPreco> monoPreco = this.webClientPrecos
				.method(HttpMethod.GET)
				.uri("/precos/{codigo}", codigoProduto)
				.retrieve()
				.bodyToMono(ProdutoComPreco.class);
		
		ProdutoComPreco produto = monoProduto.block();
		ProdutoComPreco preco = monoPreco.block();

		produto.setPreco(preco.getPreco());

		return produto;
	}
	
	public ProdutoComPreco criar(ProdutoComPreco produtoComPreco) {

		Mono<ProdutoComPreco> monoProduto = 
				this.webClientProdutos
					.post()
					.uri("/produtos")
					.body(BodyInserters.fromValue(produtoComPreco))
					.retrieve()
					.bodyToMono(ProdutoComPreco.class);

		return monoProduto.block();
	}
}
