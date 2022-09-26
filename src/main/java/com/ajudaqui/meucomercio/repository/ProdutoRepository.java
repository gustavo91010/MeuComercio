package com.ajudaqui.meucomercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.meucomercio.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Produto findByNome(String produtoNome);


}
