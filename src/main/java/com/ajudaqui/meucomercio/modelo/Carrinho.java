package com.ajudaqui.meucomercio.modelo;

import java.math.BigDecimal;
/**
 * Essa classe tem a responsabiliidade de agrupar todos os produtos que foram escolhidos por um cliente
 * tendo ele a intenção de efetuar o pedido ou nao.
 * Todos os produtos estarão dentro do carrinho que estara acoplado a um cliente e possivelmente a 
 * um vendedor.
 */
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Essa classe vai tratar dos itens que forem sendo escolhido pelo cliente para possivel compra
 * Os itens devem ser conferido no estoque pra ver a disponibilidade,
 * se  o produto escolhigo tiver menos de 5 itens, o cliente deve ser avosado;
 * Ela mostrará o valor total, atua dos itens
 * 
 * 
 * @author gusta
 *
 */
@Entity
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Vendedor vendedor;
	@OneToOne
	private Cliente cliente;
	
	@OneToMany
	private List<Produto> produtos;
	
	@OneToMany
	 List<Estoque> estoque;;
	private int quantidade;

//	@OneToMany
//	private Produto produtos;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	public Carrinho() {
		// TODO Auto-generated constructor stub
	}
	

	public Carrinho(Vendedor vendedor, Cliente cliente, List<Produto> produtos, int quantidade) {
		super();
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.produtos = produtos;
		this.quantidade = quantidade;
		this.valorTotal = produtos.stream().map(p-> p.getValor()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	public Carrinho( Cliente cliente, List<Produto> produtos, int quantidade) {
		super();
		this.cliente = cliente;
		this.produtos = produtos;
		this.quantidade = quantidade;
		this.valorTotal = produtos.stream().map(p-> p.getValor()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	

	
	

}
