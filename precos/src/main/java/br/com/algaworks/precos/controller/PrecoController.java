package br.com.algaworks.precos.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algaworks.precos.model.Preco;

@RestController
@RequestMapping("/precos")
public class PrecoController {	

	@GetMapping("/{codigoProduto}")
	public ResponseEntity<Preco> obterPreco(@PathVariable Long codigoProduto) throws Exception {
		
		Preco preco = new Preco(codigoProduto, new BigDecimal(100));
		
		Thread.sleep(3000);

		return ResponseEntity.ok(preco);
	}
}
