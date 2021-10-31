package com.br.desafio.next.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.br.desafio.nexti.dto.ClienteDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;	

@Entity
@Table(name="clientes")


@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
	  
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	
	@OneToMany(fetch = FetchType.LAZY) 
	private List<Pedido> pedidos;
	
	public Cliente() {
	}

	public Cliente(String nome, String cpf, LocalDate dataNascimento, List<Pedido> pedidos) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = LocalDate.now();
		this.pedidos = pedidos;
	}
	
	public Cliente(String nome, String cpf, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = LocalDate.now();
	}

	public Cliente(ClienteDto clienteDto) {
		this.nome = clienteDto.getNome();
		this.cpf = clienteDto.getCpf();
		this.dataNascimento = LocalDate.now();
	}

	public Integer getId() {
		return id;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
