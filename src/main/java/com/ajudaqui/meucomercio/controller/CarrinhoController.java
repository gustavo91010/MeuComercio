package com.ajudaqui.meucomercio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.ajudaqui.meucomercio.controller.form.AtualizarCarrinhoForm;
import com.ajudaqui.meucomercio.controller.form.CarrinhoAtualizarProdutos;
import com.ajudaqui.meucomercio.controller.form.CarrinhoCadastroForm;
import com.ajudaqui.meucomercio.dto.CarrinhoDto;
import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.repository.CarrinhoRepository;
import com.ajudaqui.meucomercio.repository.EstoqueRepository;
import com.ajudaqui.meucomercio.repository.ProdutoRepository;
import com.ajudaqui.meucomercio.repository.UsuarioRepository;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstoqueRepository estoqueRepository;

	@PostMapping
	public ResponseEntity<CarrinhoDto> novoCarrinho(@RequestBody CarrinhoCadastroForm carrinhoCadastroForm,
			UriComponentsBuilder uriBuilder) {
		Carrinho carrinho = carrinhoCadastroForm.cadastrar(carrinhoRepository, usuarioRepository);

		URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(carrinho.getId()).toUri();

		return ResponseEntity.created(uri).body(new CarrinhoDto(carrinho));
	}

	@GetMapping
	public List<CarrinhoDto> mostrarCarrinho() {
		List<Carrinho> carrinhos = new ArrayList<>();
		List<CarrinhoDto> carrinhosDto = new ArrayList<>();
		carrinhos = carrinhoRepository.findAll();
		carrinhos.forEach(c -> {
			CarrinhoDto cdto = new CarrinhoDto(c);
			carrinhosDto.add(cdto);
		});
		return carrinhosDto;

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CarrinhoDto> mostrarCarrinho(@PathVariable Long id) {
		Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
		if (carrinho.isPresent()) {
			return ResponseEntity.ok(new CarrinhoDto(carrinho.get()));
		}
		return ResponseEntity.notFound().build();

	}

//	@PutMapping(value = "/{id}")
//	public ResponseEntity<CarrinhoDto> atualizar(@PathVariable("id") Long id,
//			@RequestBody @Valid AtualizarCarrinhoForm atualizarCarrinhoForm) {
//		Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
//		if (carrinho.isPresent()) {
//			Carrinho carrinhoAtualizado = atualizarCarrinhoForm.atualizar(id, carrinhoRepository);
//
//			return ResponseEntity.ok(new CarrinhoDto(carrinhoAtualizado));
//		}
//		return ResponseEntity.notFound().build();
//
//	}

	@PutMapping(value = "/produtos/{id}")
	public Carrinho atualizarProdutoCarrinho(@PathVariable("id") Long id,
			@RequestBody CarrinhoAtualizarProdutos carrinhoAtualizarProdutos) {
		Carrinho carrinho= carrinhoRepository.findById(id).orElseThrow();
		carrinho.setEstoque(carrinhoAtualizarProdutos.listaAtualizada(produtoRepository, estoqueRepository));
		carrinhoRepository.save(carrinho);
		return carrinho;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CarrinhoDto> remover(@PathVariable("id") Long id) {
		Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
		if (carrinho.isPresent()) {

			carrinhoRepository.delete(carrinho.get());

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

}
