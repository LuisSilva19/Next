package com.br.desafio.next.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.desafio.next.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	@Query("Select p from Pedido p join p.cliente c where c.nome = :nome ")
	List<Pedido> findClienteNome(@Param("nome") String nome);

}
