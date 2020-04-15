package br.com.algaworks.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.consumer.model.ProdutoComPreco;
import br.com.algaworks.consumer.service.PrecoProdutoService;

@RestController
public class PrecoProdutoController {

	@Autowired
	private PrecoProdutoService precoProdutoService;
	
	@GetMapping("/produto/{codigo}/preco")
	public ResponseEntity<ProdutoComPreco> obterProdutoComPreco(@PathVariable Long codigo) {
		
		ProdutoComPreco produtoComPreco = this.precoProdutoService.obterPorCodigoSincrono(codigo);

		return ResponseEntity.ok(produtoComPreco);
	}

	@GetMapping("/produto/{codigo}/preco/async")
	public ResponseEntity<ProdutoComPreco> obterProdutoComPrecoParalelo(@PathVariable Long codigo) {
		
		ProdutoComPreco produtoComPreco = this.precoProdutoService.obterPorCodigoParalelo(codigo);

		return ResponseEntity.ok(produtoComPreco);
	}

	@PostMapping("/produto")
	public ResponseEntity<ProdutoComPreco> criarProduto(@RequestBody ProdutoComPreco produto) {

		ProdutoComPreco produtoComPreco = this.precoProdutoService.criar(produto);

		return ResponseEntity.ok(produtoComPreco);
	}
}
