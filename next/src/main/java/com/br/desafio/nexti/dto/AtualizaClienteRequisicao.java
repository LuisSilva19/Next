package com.br.desafio.nexti.dto;

import javax.validation.constraints.NotEmpty;

import com.br.desafio.next.model.Cliente;
import com.br.desafio.next.repository.ClienteRepository;
import com.sun.istack.NotNull;

public class AtualizaClienteRequisicao {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String cpf;
 
	public AtualizaClienteRequisicao(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
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

	public Cliente atualiza(Integer id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getById(id);
		cliente.setNome(this.nome);
		cliente.setCpf(this.cpf);
		
		return cliente;
	}
	
	
	
	
}
