package com.br.desafio.next.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.desafio.next.model.Cliente;
import com.br.desafio.next.repository.ClienteRepository;
import com.br.desafio.next.repository.PedidoRepository;
import com.br.desafio.nexti.dto.AtualizaClienteRequisicao;
import com.br.desafio.nexti.dto.ClienteDto;
import com.br.desafio.nexti.dto.ClienteRequisicao;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	@Cacheable(value = "ListaDeClientes")
	public Page<ClienteDto> getCliente(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao){
		Page<Cliente> clientes = clienteRepository.findAll(paginacao);
		return ClienteDto.converte(clientes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> getClientePorID(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDto());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "ListaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> saveCliente(@RequestBody @Valid ClienteRequisicao clienteRequisicao, UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteRequisicao.converte(pedidoRepository);
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@PutMapping("{id}")
	@Transactional
	@CacheEvict(value = "ListaDeClientes", allEntries = true)
	public ResponseEntity<ClienteDto> updateCliente(@PathVariable Integer id, @RequestBody @Valid AtualizaClienteRequisicao requisicao) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		
		if(optional.isPresent()) {
			Cliente cliente = requisicao.atualiza(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	@Transactional
	@CacheEvict(value = "ListaDeClientes", allEntries = true)
	public ResponseEntity<?> deleteClientePorId(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(cliente.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/*
	@GetMapping
	public Page<ClienteDto> getCliente(@RequestParam int pagina, @RequestParam int qtd, @RequestParam String CampoOrdenacao){
		Pageable paginacao = PageRequest.of(pagina,qtd, Direction.ASC, CampoOrdenacao);
		
		
		 Page<Cliente> clientes = clienteRepository.findAll(paginacao); 
		 return ClienteDto.converte(clientes);
	}
	*/
}
