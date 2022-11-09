package com.ajudaqui.meucomercio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public ProdutoNaoEncontradoIdException() {
		super();
	}

}
