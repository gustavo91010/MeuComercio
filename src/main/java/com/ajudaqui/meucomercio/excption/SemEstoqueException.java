package com.ajudaqui.meucomercio.excption;

public class SemEstoqueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SemEstoqueException(String mensagem) {
		super(mensagem);
	}

}
