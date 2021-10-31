package com.br.desafio.nexti.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequisicao {
	
	private String email;
	private String senha;
	
	public LoginRequisicao() {
	}
	
	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email,senha);
	}

	
	
}
