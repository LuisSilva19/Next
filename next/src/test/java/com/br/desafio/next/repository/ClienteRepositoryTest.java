package com.br.desafio.next.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.desafio.next.model.Cliente;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	public void deveriaCarregarUmClienteAoBuscarPeloNome() {
		String nomeCLiente = "Sarah";
		Cliente cliente = clienteRepository.findByNome(nomeCLiente);
		Assertions.assertNotNull(cliente);
		Assertions.assertEquals(nomeCLiente, cliente.getNome());
	}	

}