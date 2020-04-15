package br.com.algaworks.produtos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.produtos.model.Produto;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> obterProduto(@PathVariable Long codigo) throws Exception {

		Produto produto = new Produto(codigo, "Ventilador", "Venta muito");
		
		Thread.sleep(3000);

		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@RequestBody Produto produto) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}
	
}
