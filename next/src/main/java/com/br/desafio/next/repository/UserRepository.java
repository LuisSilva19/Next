package com.br.desafio.next.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafio.next.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
}
