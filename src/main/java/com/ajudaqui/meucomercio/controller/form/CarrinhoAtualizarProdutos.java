package com.ajudaqui.meucomercio.controller.form;

import java.util.List;

import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.modelo.Produto;
import com.ajudaqui.meucomercio.repository.EstoqueRepository;
import com.ajudaqui.meucomercio.repository.ProdutoRepository;

public class CarrinhoAtualizarProdutos {

	private String produtoNome;
	private Integer quantidade;

	public String getProdutoNome() {
		return produtoNome;
	}

	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<Estoque> listaAtualizada(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {

		Produto produto = produtoRepository.findByNome(produtoNome);

		Estoque novoestoque = new Estoque(produto, quantidade);
		List<Estoque> listaEstoque = estoqueRepository.findAll();
		estoqueRepository.save(novoestoque);
		listaEstoque.add(novoestoque);
		return listaEstoque;

	}

}
