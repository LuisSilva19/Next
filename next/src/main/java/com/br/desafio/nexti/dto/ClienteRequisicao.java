package com.br.desafio.nexti.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.br.desafio.next.model.Cliente;
import com.br.desafio.next.model.Pedido;
import com.br.desafio.next.repository.PedidoRepository;
import com.sun.istack.NotNull;

public class ClienteRequisicao {
	
	private Integer clienteId;
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String cpf;
	private LocalDate dataNascimento;

	public ClienteRequisicao() {
	} 
	
	public Integer getId() {
		return clienteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cliente converte(PedidoRepository pedidoRepository) {
		List<Pedido> listPedidos = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepository.findClienteNome(nome);
		
		pedidos.stream().forEach(p->{
			listPedidos.add(p);
		});
		
		return new Cliente(nome,cpf,dataNascimento, listPedidos);
	}
}
