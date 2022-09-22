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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ajudaqui.meucomercio.dto.ClienteDto;
import com.ajudaqui.meucomercio.modelo.Cliente;
import com.ajudaqui.meucomercio.repository.ClienteRepository;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@PostMapping
	public ResponseEntity<ClienteDto> catadastrar(@RequestBody ClienteDto clienteDto,
			UriComponentsBuilder uriBuilder) {
		Cliente cliente = repository.save(clienteDto.convert());
		URI uri = uriBuilder.path("/Cliente/{id}").buildAndExpand(cliente.getId()).toUri();

		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}

	@GetMapping
	public List<ClienteDto> mostrarCliente() {
		List<ClienteDto> clientesDto = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();
		clientes = repository.findAll();
//		clientes.stream().map(c -> clientesDto.add(new ClienteDto(c)));
		clientes.forEach(c->{
			ClienteDto cDto= new ClienteDto(c);
			clientesDto.add(cDto);
		});

		return clientesDto;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> mostrarCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(cliente.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> atualizar(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			cliente.get().setEmail(clienteDto.getEmail());
			cliente.get().setPassword(clienteDto.getPassword());
			cliente.get().setNome(clienteDto.getNome());
			repository.save(cliente.get());

			return ResponseEntity.ok(new ClienteDto(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteDto> remover(@PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			repository.delete(cliente.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
