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

import com.ajudaqui.meucomercio.dto.UsuarioDto;
import com.ajudaqui.meucomercio.modelo.Usuario;
import com.ajudaqui.meucomercio.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	public ResponseEntity<UsuarioDto> catadastrar(@RequestBody UsuarioDto usuarioDto,
			UriComponentsBuilder uriBuilder) {
		Usuario usuario = repository.save(usuarioDto.convert());
		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}

	@GetMapping
	public List<UsuarioDto> mostrarusuario() {
		List<UsuarioDto> usuariosDto = new ArrayList<>();
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = repository.findAll();
//		usuarios.stream().map(c -> usuariosDto.add(new usuarioDto(c)));
		usuarios.forEach(c->{
			UsuarioDto cDto= new UsuarioDto(c);
			usuariosDto.add(cDto);
		});

		return usuariosDto;
	}

	@GetMapping(value="/id/{id}")
	public ResponseEntity<UsuarioDto> mostrarusuarioId(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
		}

		return ResponseEntity.notFound().build();
	}
	@GetMapping(value="/nome/{nome}")
	public ResponseEntity<UsuarioDto> mostrarusuarioNome(@PathVariable("nome") String nome) {
		Usuario usuario = repository.findByNome(nome);
		
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
//		
	

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> atualizar(@RequestBody UsuarioDto usuarioDto, @PathVariable Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			usuario.get().setEmail(usuarioDto.getEmail());
			usuario.get().setPassword(usuarioDto.getPassword());
			usuario.get().setNome(usuarioDto.getNome());
			repository.save(usuario.get());

			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDto> remover(@PathVariable Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			repository.delete(usuario.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
