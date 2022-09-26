package com.ajudaqui.meucomercio.modelo;

import java.math.BigDecimal;
/**
 * Essa classe tem a responsabiliidade de agrupar todos os produtos que foram escolhidos por um usuario
 * tendo ele a intenção de efetuar o pedido ou nao.
 * Todos os produtos estarão dentro do carrinho que estara acoplado a um usuario e possivelmente a 
 * um vendedor.
 */
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Essa classe vai tratar dos itens que forem sendo escolhido pelo usuario para
 * possivel compra Os itens devem ser conferido no estoque pra ver a
 * disponibilidade, se o produto escolhigo tiver menos de 5 itens, o usuario
 * deve ser avosado; Ela mostrará o valor total, atua dos itens
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
	
	@OneToOne(cascade = CascadeType.PERSIST)// para fazer quando o usuario for saldo na classe carrinho, e o mesmo ainda nao esteja no bandco de dados, esse seja salvo la tambem
	private Usuario usuario;
	@OneToMany
	private List<Produto> produtos;
	@OneToMany(cascade = CascadeType.PERSIST)
//	@JoinColumn(name= "produto_quantidade")
	private List<Estoque> estoque;
	private int quantidade;
	private BigDecimal valorTotal = BigDecimal.ZERO;

	public Carrinho() {
		// TODO Auto-generated constructor stub
	}

	public Carrinho(Usuario usuario, List<Produto> produtos, int quantidade) {
		super();
		this.usuario = usuario;
		this.produtos = produtos;
		this.quantidade = quantidade;
		this.valorTotal = produtos.stream().map(p -> p.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Carrinho(Usuario usuario) {
		super();
		this.usuario = usuario;
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
