package com.ajudaqui.meucomercio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.ajudaqui.meucomercio.dto.VendedorDto;
import com.ajudaqui.meucomercio.modelo.Vendedor;
import com.ajudaqui.meucomercio.repository.VendedorRepository;

@RequestMapping("/vendedor")
@Controller
public class VendedorController {

	@Autowired
	private VendedorRepository repository;

	@PostMapping
	public ResponseEntity<VendedorDto> catadastrar(@RequestBody VendedorDto vendedorDto,
			UriComponentsBuilder uriBuilder) {
		Vendedor vendedor = repository.save(vendedorDto.convert());
		URI uri = uriBuilder.path("/vendedor/{id}").buildAndExpand(vendedor.getId()).toUri();

		return ResponseEntity.created(uri).body(new VendedorDto(vendedor));
	}

	@GetMapping
	public List<VendedorDto> mostrarVendedor() {
		List<VendedorDto> vendedoresDto = new ArrayList<>();
		List<Vendedor> vendedores = new ArrayList<>();
		vendedores = repository.findAll();
		vendedores.stream().map(v -> vendedoresDto.add(new VendedorDto(v)));

		return vendedoresDto;
	}

	@GetMapping("/{id}")
	public ResponseEntity<VendedorDto> mostrarVendedor(@RequestParam Long id) {
		Optional<Vendedor> vendedor = repository.findById(id);
		if (vendedor.isPresent()) {
			return ResponseEntity.ok(new VendedorDto(vendedor.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<VendedorDto> atualizar(@RequestBody VendedorDto vendedorDto, @RequestParam Long id) {
		Optional<Vendedor> vendedor = repository.findById(id);
		if (vendedor.isPresent()) {
			vendedor.get().setEmail(vendedorDto.getEmail());
			vendedor.get().setPassword(vendedorDto.getPassword());
			vendedor.get().setNome(vendedorDto.getNome());

			return ResponseEntity.ok(new VendedorDto(vendedor.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VendedorDto> remover(@RequestParam Long id) {
		Optional<Vendedor> vendedor = repository.findById(id);
		if (vendedor.isPresent()) {
			repository.delete(vendedor.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
