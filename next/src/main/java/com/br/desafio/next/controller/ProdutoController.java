package com.br.desafio.next.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.next.model.Produto;
import com.br.desafio.next.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> findAllProdutos(){
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public Produto saveProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PutMapping()
	public Produto updateProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@GetMapping("{id}")
	public Produto findProdutoPorId(@PathVariable("id") Integer id) {
		return produtoRepository.getById(id);
	}
	
	@DeleteMapping("{id}")
	public void deleteProduto(@PathVariable("id") Integer id) {
		produtoRepository.deleteById(id);
	}
	
	
	
}
