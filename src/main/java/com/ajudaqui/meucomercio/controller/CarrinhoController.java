package com.ajudaqui.meucomercio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ajudaqui.meucomercio.dto.CarrinhoDto;
import com.ajudaqui.meucomercio.exception.CarrinhoInvalidoException;
import com.ajudaqui.meucomercio.exception.RecursoNaoEncontradoException;
import com.ajudaqui.meucomercio.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService service;

	@PostMapping(produces = "application/json")
	public ResponseEntity<CarrinhoDto> post(@RequestBody CarrinhoDto carrinhoDto,
			UriComponentsBuilder uriBuilder) throws CarrinhoInvalidoException {
		CarrinhoDto dto = service.save(carrinhoDto);
		URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping( produces = "application/json")
	public ResponseEntity<List<CarrinhoDto>> findAll() {
		List<CarrinhoDto> carrinhosDto = service.findAll();
		return ResponseEntity.ok(carrinhosDto);

	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<CarrinhoDto> findById(@PathVariable(value = "id") Long id) {
		CarrinhoDto carrinhoDto = service.findById(id);

		return ResponseEntity.ok(carrinhoDto);

	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<CarrinhoDto> update(Long id, @RequestBody CarrinhoDto carrinhoDto) throws RecursoNaoEncontradoException, CarrinhoInvalidoException {
		CarrinhoDto carrinho= service.update(id, carrinhoDto);
		
		return ResponseEntity.ok(carrinho);
		
//		Optional<Carrinho> carrinho = service.findById(id);
//		if (carrinho.isPresent()) {
//			carrinho.get().setCliente(carrinhoDto.getCliente());
//			carrinho.get().setEstoque(carrinhoDto.getEstoque());
//			carrinho.get().setId(carrinhoDto.getId());
//			carrinho.get().setProdutos(carrinhoDto.getProdutos());
//			carrinho.get().setQuantidade(carrinhoDto.getQuantidade());
//			carrinho.get().setValorTotal(carrinhoDto.getValorTotal());
//			carrinho.get().setVendedor(carrinhoDto.getVendedor());
//
//			return ResponseEntity.ok(new CarrinhoDto(carrinho.get()));
//		}
//		return ResponseEntity.notFound().build();

	}
	@DeleteMapping
	public ResponseEntity<CarrinhoDto> delete(Long id){
		
		service.deleteCarrinho(id);
		return ResponseEntity.ok().build();
		
	}
//		Optional<Carrinho> carrinho = service.findById(id);
//		if(carrinho.isPresent()) {
//			
//		service.delete(carrinho.get());
//		
//		return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.notFound().build();
//		
//	}

}
