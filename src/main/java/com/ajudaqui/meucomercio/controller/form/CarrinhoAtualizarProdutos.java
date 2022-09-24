package com.ajudaqui.meucomercio.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.modelo.Produto;
import com.ajudaqui.meucomercio.repository.CarrinhoRepository;
import com.ajudaqui.meucomercio.repository.ProdutoRepository;

public class CarrinhoAtualizarProdutos {

	public static Carrinho atualizarProdutoCarrinho(Long id, 
			ProdutoRepository produtoRepository,
			CarrinhoRepository carinhoRepository,
			String... produtos) {
		List<Produto> produtosList = new ArrayList<>();
		for (int i = 0; i < produtos.length; i++) {

			String[] produtoNome = produtos;
			Produto produto = produtoRepository.findByNome(produtoNome[i]);
			produtosList.add(produto);

		}
		Carrinho carrinho= carinhoRepository.findById(id).orElseThrow();
		carrinho.setProdutos(produtosList);
		return carrinho;

	}

}
