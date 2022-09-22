package com.ajudaqui.meucomercio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

import com.ajudaqui.meucomercio.dto.VendedorDto;
import com.ajudaqui.meucomercio.modelo.Vendedor;
import com.ajudaqui.meucomercio.repository.VendedorRepository;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private VendedorRepository repository;
			
//			@Autowired
//	private VendedorService service;

	@Transactional
	@PostMapping
//	public void catadastrar(@RequestBody VendedorDto vendedorDto)
//			 {
//		Vendedor vendedor =vendedorDto.convert();
////		repository.save(vendedor);
//		service.create(vendedor);
//	}
	public ResponseEntity<VendedorDto> catadastrar(@RequestBody VendedorDto vendedorDto,
			UriComponentsBuilder uriBuilder) {
		Vendedor vendedor =vendedorDto.convert();
		repository.save(vendedor);
		URI uri = uriBuilder.path("/vendedor/{id}").buildAndExpand(vendedor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VendedorDto(vendedor));
	}

	@GetMapping
	public List<VendedorDto> mostrarVendedor() {
		List<VendedorDto> vendedoresDto = new ArrayList<>();
		List<Vendedor> vendedores = new ArrayList<>();
		vendedores = repository.findAll();
//		vendedores.stream().map(v -> vendedoresDto.add(new VendedorDto(v)));
		vendedores.forEach(v->{
			VendedorDto vdto= new VendedorDto(v);
			vendedoresDto.add(vdto);
		});

		return vendedoresDto;
	}

	@GetMapping("/{id}")
	public ResponseEntity<VendedorDto> mostrarVendedor(@PathVariable Long id) {
		Optional<Vendedor> vendedor = repository.findById(id);
		if (vendedor.isPresent()) {
			return ResponseEntity.ok(new VendedorDto(vendedor.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<VendedorDto> atualizar(@RequestBody VendedorDto vendedorDto, @PathVariable Long id) {
		Optional<Vendedor> vendedor = repository.findById(id);
		if (vendedor.isPresent()) {
			vendedor.get().setEmail(vendedorDto.getEmail());
			vendedor.get().setPassword(vendedorDto.getPassword());
			vendedor.get().setNome(vendedorDto.getNome());
//			VendedorDto novoVendeddorDto=new VendedorDto(vendedor.get());
			repository.save(vendedor.get());
			return ResponseEntity.ok(new VendedorDto(vendedor.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VendedorDto> remover(@PathVariable Long id) {
		Optional<Vendedor> vendedor = repository.findById(id);
		if (vendedor.isPresent()) {
			repository.delete(vendedor.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
