package com.ajudaqui.meucomercio.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.modelo.Produto;
import com.ajudaqui.meucomercio.repository.CarrinhoRepository;

public class AtualizarCarrinhoForm {

	@NotNull
	@NotEmpty
	private List<Estoque> estoque;
	@NotNull
	@NotEmpty
	private List<Produto> produto;
	@NotNull
	@NotEmpty
	private int quantodade;

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public int getQuantodade() {
		return quantodade;
	}

	public Carrinho atualizar(Long id, CarrinhoRepository repository) {
		Carrinho carrinho = repository.findById(id).orElseThrow();
		carrinho.setEstoque(this.estoque);
		carrinho.setProdutos(this.produto);
		carrinho.setQuantidade(this.quantodade);
		repository.save(carrinho);
		return carrinho;
	}

}
