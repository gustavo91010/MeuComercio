package com.ajudaqui.meucomercio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.meucomercio.modelo.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	List<Estoque> findByProduto_Nome(String nomeProduto);

}
