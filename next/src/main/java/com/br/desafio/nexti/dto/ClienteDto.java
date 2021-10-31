package com.br.desafio.nexti.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.br.desafio.next.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class ClienteDto {

	private Integer id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;

	public ClienteDto() {
	}
	
	public ClienteDto(Cliente cliente){
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Integer getId() {
		return id;
	}
	
	public static Page<ClienteDto> converte(Page<Cliente> clientes) {
		return clientes.map(ClienteDto::new);
	}

}
