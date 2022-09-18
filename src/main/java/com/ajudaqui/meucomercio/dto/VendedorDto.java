package com.ajudaqui.meucomercio.dto;

import com.ajudaqui.meucomercio.modelo.Vendedor;

public class VendedorDto {
	
	private Long id;
	private String nome;
	private String password;
	private String email;
	
	public VendedorDto(Vendedor vendedor) {
		this.email= vendedor.getEmail();
		this.password= vendedor.getPassword();
		this.nome= vendedor.getNome();
		this.id= vendedor.getId();
		
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
	public Vendedor convert() {
		Vendedor vendedor= new Vendedor();
		vendedor.setEmail(this.email);
		vendedor.setId(this.id);
		vendedor.setNome(this.nome);
		vendedor.setPassword(this.password);

		return null;
	}
	
	

}
