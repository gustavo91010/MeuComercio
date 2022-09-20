package com.ajudaqui.meucomercio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.ajudaqui.meucomercio.dto.EstoqueDto;
import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.repository.EstoqueRepository;

@RequestMapping("/estoque")
@RestController
public class EstoqueController {

	@Autowired
	private EstoqueRepository repository;

	@PostMapping
	public ResponseEntity<EstoqueDto> cadastrar(@RequestBody EstoqueDto estoqueDto,
			UriComponentsBuilder uriComponents) {
		Estoque estoque = repository.save(estoqueDto.converte());
		URI uri = uriComponents.path("/estoque/{id}").buildAndExpand(estoque.getId()).toUri();

		return ResponseEntity.created(uri).body(new EstoqueDto(estoque));
	}

	@GetMapping
	public List<EstoqueDto> mostrarEstoque() {
		List<Estoque> estoques = new ArrayList<>();
		List<EstoqueDto> estoquesDto = new ArrayList<>();
		estoques = repository.findAll();
		estoques.stream().map(c -> estoquesDto.add(new EstoqueDto(c)));
		return estoquesDto;

	}

	@GetMapping("/{id}")
	public ResponseEntity<EstoqueDto> getEstoque(@PathVariable Long id) {
		Optional<Estoque> estoque = repository.findById(id);
		if (estoque.isPresent()) {
			return ResponseEntity.ok(new EstoqueDto(estoque.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<EstoqueDto> atualizar(@PathVariable Long id,
			@RequestBody EstoqueDto estoqueDto) {
		Optional<Estoque> estoque = repository.findById(id);
		if (estoque.isPresent()) {
			Estoque EstoqueAtualizado = new Estoque();
			EstoqueAtualizado.setProduto(estoque.get().getProduto());
			EstoqueAtualizado.setQuantidade(estoque.get().getQuantidade());

			return ResponseEntity.ok(new EstoqueDto(EstoqueAtualizado));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EstoqueDto> remover(@PathVariable Long id) {
		Optional<Estoque> estoque = repository.findById(id);
		if (estoque.isPresent()) {

			repository.delete(estoque.get());

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
