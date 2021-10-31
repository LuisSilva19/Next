package com.br.desafio.next.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.next.model.Pedido;
import com.br.desafio.next.repository.PedidoRepository;

@RestController
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido> findAllPedido(){
		return pedidoRepository.findAll();
	}
	
	@PostMapping
	public Pedido savePedido(@RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	@GetMapping("{id}")
	public Pedido findPedidoPorId(@PathVariable("id") Integer id) {
		return pedidoRepository.getById(id);
	}
	
	@DeleteMapping("{id}")
	public void deletePedido(@PathVariable("id") Integer id ) {
		pedidoRepository.deleteById(id);
	}
	
	
}
