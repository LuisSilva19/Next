package com.br.desafio.next.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.next.config.security.TokenService;
import com.br.desafio.nexti.dto.LoginRequisicao;
import com.br.desafio.nexti.dto.TokenDto;

@RestController
@RequestMapping("/auth")
@Profile(value ={"prod", "test"})
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager auth; 
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginRequisicao login){
		UsernamePasswordAuthenticationToken dadosLogin = login.converter();
		 
		 try {
			 Authentication authenticate = auth.authenticate(dadosLogin);
			 String token = tokenService.gerarToken(authenticate);
			 return ResponseEntity.ok(new TokenDto(token, "Bearer"));	
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
