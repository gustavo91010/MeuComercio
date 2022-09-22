package com.ajudaqui.meucomercio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.meucomercio.modelo.Vendedor;
import com.ajudaqui.meucomercio.repository.VendedorRepository;

@Service
public class VendedorService {
	
	@Autowired
	VendedorRepository repository;
	
	public void create(Vendedor vendedor) {
		repository.save(vendedor);
		
	}

}
