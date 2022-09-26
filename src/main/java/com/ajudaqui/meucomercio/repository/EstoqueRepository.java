package com.ajudaqui.meucomercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.meucomercio.modelo.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	String findByProduto_Nome(String nomeProduto);

}
