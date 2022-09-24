package com.ajudaqui.meucomercio.dto;

import java.util.List;

import com.ajudaqui.meucomercio.modelo.Usuario;
import com.ajudaqui.meucomercio.modelo.Endereco;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String password;
	private String email;
	private List<Endereco> endereco;

	public UsuarioDto(Usuario usuario) {
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
		this.nome = usuario.getNome();
		this.id = usuario.getId();
		this.endereco = usuario.getEndereco();

	}
	public UsuarioDto() {
		// TODO Auto-generated constructor stub
	}
	

	public UsuarioDto(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario convert() {
		Usuario usuario = new Usuario();
		usuario.setEmail(this.email);
		usuario.setId(this.id);
		usuario.setNome(this.nome);
		usuario.setPassword(this.password);
		usuario.setEndereco(this.endereco);

		return usuario;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

}
