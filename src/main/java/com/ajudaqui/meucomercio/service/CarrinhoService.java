package com.ajudaqui.meucomercio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajudaqui.meucomercio.dto.CarrinhoDto;
import com.ajudaqui.meucomercio.exception.CarrinhoInvalidoException;
import com.ajudaqui.meucomercio.exception.ProdutoNaoEncontradoIdException;
import com.ajudaqui.meucomercio.exception.RecursoNaoEncontradoException;
import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;


	public CarrinhoDto save( CarrinhoDto carrinhoDto) throws CarrinhoInvalidoException {
		
		if(carrinhoDto.getCliente()== null || carrinhoDto.getEstoque().isEmpty()) {
			throw new CarrinhoInvalidoException("invalid format of product");
		}
		
		Carrinho carrinho = repository.save(carrinhoDto.convert());
		return new CarrinhoDto(carrinho);
		
//		URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(carrinho.getId()).toUri();

//		return ResponseEntity.created(uri).body(new CarrinhoDto(carrinho));
	}


	public List<CarrinhoDto> findAll() {
		List<Carrinho> carrinhos = new ArrayList<>();
		List<CarrinhoDto> carrinhosDto = new ArrayList<>();
		carrinhos = repository.findAll();
		carrinhos.stream().map(c -> carrinhosDto.add(new CarrinhoDto(c)));
		return carrinhosDto;

	}

	
	public CarrinhoDto findById(Long id)  {
		Carrinho carrinho = repository.findById(id)
				.orElseThrow(()-> new ProdutoNaoEncontradoIdException());
		return new CarrinhoDto(carrinho);
		
			
		

	}


	public CarrinhoDto update(Long id,  CarrinhoDto carrinhoDto) throws RecursoNaoEncontradoException, CarrinhoInvalidoException {
		
		if(!repository.existsById(id)) {
			throw new RecursoNaoEncontradoException("Carrinho nao encontrado.");
			
		}
		
		return save(carrinhoDto);
//		Optional<Carrinho> carrinho = repository.findById(id);
//		
//			carrinho.get().setCliente(carrinhoDto.getCliente());
//			carrinho.get().setEstoque(carrinhoDto.getEstoque());
//			carrinho.get().setId(carrinhoDto.getId());
//			carrinho.get().setProdutos(carrinhoDto.getProdutos());
//			carrinho.get().setQuantidade(carrinhoDto.getQuantidade());
//			carrinho.get().setValorTotal(carrinhoDto.getValorTotal());
//			carrinho.get().setVendedor(carrinhoDto.getVendedor());
//
//		return new CarrinhoDto(carrinho.get());	
		

	}
	
	public void deleteCarrinho(Long id){
		
		 Carrinho carrinho= findById(id).convert();
		repository.delete(carrinho);
		
		
//		Optional<Carrinho> carrinho = repository.findById(id);
//		
//			
//		repository.delete(carrinho.get());
		
		
		
	}

}
