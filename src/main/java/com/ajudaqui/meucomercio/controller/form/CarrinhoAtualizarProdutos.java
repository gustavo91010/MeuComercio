package com.ajudaqui.meucomercio.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner.ListEditor;

import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.modelo.Produto;
import com.ajudaqui.meucomercio.repository.EstoqueRepository;
import com.ajudaqui.meucomercio.repository.ProdutoRepository;

public class CarrinhoAtualizarProdutos {

	private String produtoNome;
	private Integer quantidade;



	public List<Estoque> listaAtualizada(ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
		
		Produto produto= produtoRepository.findByNome(this.produtoNome);
		List<Estoque> listaEstoque= estoqueRepository.findAll();
		listaEstoque.add(new Estoque(produto, quantidade));

		
		return listaEstoque;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

////		Carrinho carrinho = carinhoRepository.findById(idCarrinho).orElseThrow();
//		System.out.println("Id carrinho: "+carrinho.getId());
//
////		Produto produto = produtoRepository.findByNome(this.produtoNome);
//		Produto produto = produtoRepository.findByNome(produtoNome);
//		
//		System.out.println("Nome produto: "+ estoqueRepository.findByProduto_Nome(produtoNome));
////		System.out.println("Nome produto: "+ produto.getNome());
//		System.out.println("Nome produto: "+ produto);
//
//
//		List<Estoque> listEstoque = estoqueRepository.findAll();
//		System.out.println("tamanho da lista estoque antes: "+listEstoque.size());
//		listEstoque.add(new Estoque(produto, this.quantidade));
//		System.out.println("tamanho da lista estoquedepois: "+listEstoque.size());
//		
////		listEstoque.stream()
////					.filter(e -> e.getProduto().equals(produto))
////					.map(listEstoque.add(new Estoque(produto, quantidade)));
//
//		return listEstoque;
//	}

//	Produto p1= produtoRepository.findByNome(this.produto);
//	 carrinho= carinhoRepository.findById(idCarrinho).orElseThrow();
////	 List<Produto> produtosEscolhidos = produtoRepository.findAll();
//	
//	
//	produtoList.addAll(this.estque.getProduto());
//	produtoList.add(p1);
//	Estoque e1= new Estoque(p1, this.quantidade);
//	System.out.println("tamanho da lista produto: "+produtoList.size());
//	estoqueList.add(e1);
////	System.out.println("produto novo: "+e1.getProduto().toString()+" quantidade: "+e1.getQuantidade());
//	carrinho.setEstoque(estoqueList);
//	carinhoRepository.save(carrinho);
//	
//	return carrinho;

//		List<Produto> produtosList = new ArrayList<>();
//		for (int i = 0; i < produtos.length; i++) {
//
//			String[] produtoNome = produtos;
//			Produto produto = produtoRepository.findByNome(produtoNome[i]);
//			produtosList.add(produto);
//
//		}
//		Carrinho carrinho= carinhoRepository.findById(id).orElseThrow();
//		carrinho.setProdutos(produtosList);
//		return carrinho;

}
