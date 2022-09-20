package com.ajudaqui.meucomercio.dto;

import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.modelo.Produto;

public class EstoqueDto {

	private Long id;

	private Produto produto;
	private int quantidade;

	public EstoqueDto() {
		// TODO Auto-generated constructor stub
	}

	public EstoqueDto(Estoque estoque) {

		this.produto = estoque.getProduto();
		this.quantidade = estoque.getQuantidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Estoque converte() {
		Estoque estoque= new Estoque(this.produto, this.quantidade);
		return estoque;
	}

}
