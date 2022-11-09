package com.ajudaqui.meucomercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajudaqui.meucomercio.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
