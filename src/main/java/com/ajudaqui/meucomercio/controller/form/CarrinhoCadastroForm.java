package com.ajudaqui.meucomercio.controller.form;

import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.modelo.Usuario;
import com.ajudaqui.meucomercio.repository.CarrinhoRepository;
import com.ajudaqui.meucomercio.repository.UsuarioRepository;

public class CarrinhoCadastroForm {
	public CarrinhoCadastroForm() {
		// TODO Auto-generated constructor stub
	}

	public CarrinhoCadastroForm(String nome) {
		this.nome = nome;
	}

	private String nome;

	public Carrinho cadastrar(CarrinhoRepository repository, UsuarioRepository usuarioRepository) {

		Usuario usuario1 = usuarioRepository.findByNome(nome);
		Carrinho carrinho = new Carrinho();
		if (usuario1 == null) {

			Usuario usuario = new Usuario(this.nome);
			carrinho.setUsuario(usuario);
		} else {

			carrinho = new Carrinho(usuario1);
			carrinho.setUsuario(usuario1);
		}

		repository.save(carrinho);
		return carrinho;
	}

	public String getNome() {
		return nome;
	}

}
