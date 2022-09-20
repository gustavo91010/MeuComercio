package com.ajudaqui.meucomercio.dto;

import java.math.BigDecimal;

import com.ajudaqui.meucomercio.modelo.Categoria;
import com.ajudaqui.meucomercio.modelo.Produto;

public class ProdutoDto {

	private Long id;
	private String nome;
	private String descricao;
	private Categoria categoria;
	private BigDecimal valor = BigDecimal.ZERO;

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria();
		this.valor = produto.getValor();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Produto converte() {
		Produto produto = new Produto();
		produto.setCategoria(this.categoria);
		produto.setDescricao(this.descricao);
		produto.setId(this.id);
		produto.setNome(this.nome);
		produto.setValor(this.valor);

		return produto;
	}

}
