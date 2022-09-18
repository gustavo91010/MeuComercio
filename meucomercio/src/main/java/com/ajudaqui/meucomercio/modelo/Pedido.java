package com.ajudaqui.meucomercio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A classe pedido vai ficar com a função de pagamento,
 * logo apois o cliente na classe Carrinho confirmar os produtos e confirma e separa os itens no estoque,
 *  manda a lista do carrinho para pagamento,(metodo pagamento() )
 *   vai fazer a confirmação do pedido
 * com o cartos de credito , pix ou boleto junto as entidades ant-fraude e só depois, se tudo for confirmado
 * ela ira dispara um emal para o cliente confirmando o pagamento.
 * 
 * @author gusta
 *
 */
@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	private Carrinho carrinho;
	
	@OneToOne
	private Estoque estoque;
	private String mensagem;
	
	
	
	

}
