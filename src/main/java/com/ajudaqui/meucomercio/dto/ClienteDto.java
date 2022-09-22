package com.ajudaqui.meucomercio.dto;

import java.util.List;

import com.ajudaqui.meucomercio.modelo.Cliente;
import com.ajudaqui.meucomercio.modelo.Endereco;

public class ClienteDto {

	private Long id;
	private String nome;
	private String password;
	private String email;
	private List<Endereco> endereco;

	public ClienteDto(Cliente Cliente) {
		this.email = Cliente.getEmail();
		this.password = Cliente.getPassword();
		this.nome = Cliente.getNome();
		this.id = Cliente.getId();
		this.endereco = Cliente.getEndereco();

	}
	public ClienteDto() {
		// TODO Auto-generated constructor stub
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

	public Cliente convert() {
		Cliente cliente = new Cliente();
		cliente.setEmail(this.email);
		cliente.setId(this.id);
		cliente.setNome(this.nome);
		cliente.setPassword(this.password);
		cliente.setEndereco(this.endereco);

		return cliente;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

}
