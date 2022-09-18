package com.ajudaqui.meucomercio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estoque {
	
	// tentar fazer com que esta tabela seja simplesmente uma coluna na tabela produto.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Produto produto;
	private int quantodade;

	public Estoque(Produto produto, int quantodade) {
		super();
		this.produto = produto;
		this.quantodade = quantodade;
	}

	public Estoque() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantodade() {
		return quantodade;
	}
	

}
