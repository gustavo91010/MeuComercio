package com.ajudaqui.meucomercio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estoque {

	// tentar fazer com que esta tabela seja simplesmente uma coluna na tabela
	// produto.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Produto produto;
	private Integer quantidade;

	
	public Estoque(Produto produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Estoque() {
		// TODO Auto-generated constructor stub
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	

	

}
