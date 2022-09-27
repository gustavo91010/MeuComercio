package com.ajudaqui.meucomercio.dto;

import java.math.BigDecimal;
/**
 * Essa classe tem a responsabiliidade de agrupar todos os produtos que foram escolhidos por um cliente
 * tendo ele a intenção de efetuar o pedido ou nao.
 * Todos os produtos estarão dentro do carrinho que estara acoplado a um cliente e possivelmente a 
 * um vendedor.
 */
import java.util.List;

import com.ajudaqui.meucomercio.modelo.Carrinho;
import com.ajudaqui.meucomercio.modelo.Estoque;
import com.ajudaqui.meucomercio.modelo.Usuario;

/**
 * Essa classe vai tratar dos itens que forem sendo escolhido pelo cliente para
 * possivel compra Os itens devem ser conferido no estoque pra ver a
 * disponibilidade, se o produto escolhigo tiver menos de 5 itens, o cliente
 * deve ser avosado; Ela mostrará o valor total, atua dos itens
 * 
 * 
 * 
 * @author gusta
 *
 */
public class CarrinhoDto {

	private Long id;
	private Usuario usuario;
//	private List<Produto> produtos;

	private List<Estoque> estoque;
//	private int quantidade;

	private BigDecimal valorTotal = BigDecimal.ZERO;

	public CarrinhoDto() {
	}

	public CarrinhoDto(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public CarrinhoDto(Carrinho carrinho) {
		super();
		this.id= carrinho.getId();
		this.usuario = carrinho.getUsuario();
//		this.produtos = carrinho.getProdutos();
//		this.quantidade = carrinho.getQuantidade();
		this.estoque= carrinho.getEstoque();
		this.valorTotal = carrinho.getValorTotal();
				}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	public List<Produto> getProdutos() {
//		return produtos;
//	}
//
//	public void setProdutos(List<Produto> produtos) {
//		this.produtos = produtos;
//	}
//	public int getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(int quantidade) {
//		this.quantidade = quantidade;
//	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}

	

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Carrinho convert() {
		Carrinho carrinho = new Carrinho();
		carrinho.setUsuario(this.usuario);
		carrinho.setEstoque(this.estoque);
		carrinho.setId(this.id);
//		carrinho.setProdutos(this.produtos);
//		carrinho.setQuantidade(this.quantidade);
		carrinho.setValorTotal(this.valorTotal);
		return carrinho;
	}

}
