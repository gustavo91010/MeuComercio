package com.ajudaqui.meucomercio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ajudaqui.meucomercio.dto.CarrinhoDto;
import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.repository.CarrinhoRepository;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoRepository repository;

	@PostMapping
	public ResponseEntity<CarrinhoDto> novoCarrinho(@RequestBody CarrinhoDto carrinhoDto,
			UriComponentsBuilder uriBuilder) {
		Carrinho carrinho = repository.save(carrinhoDto.convert());
		URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(carrinho.getId()).toUri();

		return ResponseEntity.created(uri).body(new CarrinhoDto(carrinho));
	}

	@GetMapping
	public List<CarrinhoDto> getCarrinho() {
		List<Carrinho> carrinhos = new ArrayList<>();
		List<CarrinhoDto> carrinhosDto = new ArrayList<>();
		carrinhos = repository.findAll();
		carrinhos.stream().map(c -> carrinhosDto.add(new CarrinhoDto(c)));
		return carrinhosDto;

	}

	@GetMapping("/{id}")
	public ResponseEntity<CarrinhoDto> getCarrinho(Long id) {
		Optional<Carrinho> carrinho = repository.findById(id);
		if (carrinho.isPresent()) {
			return ResponseEntity.ok(new CarrinhoDto(carrinho.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<CarrinhoDto> atualizar(Long id, @RequestBody CarrinhoDto carrinhoDto) {
		Optional<Carrinho> carrinho = repository.findById(id);
		if (carrinho.isPresent()) {
			carrinho.get().setCliente(carrinhoDto.getCliente());
			carrinho.get().setEstoque(carrinhoDto.getEstoque());
			carrinho.get().setId(carrinhoDto.getId());
			carrinho.get().setProdutos(carrinhoDto.getProdutos());
			carrinho.get().setQuantidade(carrinhoDto.getQuantidade());
			carrinho.get().setValorTotal(carrinhoDto.getValorTotal());
			carrinho.get().setVendedor(carrinhoDto.getVendedor());

			return ResponseEntity.ok(new CarrinhoDto(carrinho.get()));
		}
		return ResponseEntity.notFound().build();

	}
	@DeleteMapping
	public ResponseEntity<CarrinhoDto> remover(Long id){
		Optional<Carrinho> carrinho = repository.findById(id);
		if(carrinho.isPresent()) {
			
		repository.delete(carrinho.get());
		
		return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}

}
