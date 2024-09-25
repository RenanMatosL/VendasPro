package br.com.empresa.vendapro.excecoes;

import br.com.empresa.vendapro.dto.ErroProcessamento;

public class ErroGenericoException extends Exception {

	private ErroProcessamento erroProcessamento;

	public ErroGenericoException(String mensagemErro, ErroProcessamento erroProcessamento, final Throwable throwable) {
		super(mensagemErro, throwable);
		this.erroProcessamento = erroProcessamento;
	}

	public ErroProcessamento getErroProcessamento() {
		return erroProcessamento;
	}

	public void setErroProcessamento(ErroProcessamento erroProcessamento) {
		this.erroProcessamento = erroProcessamento;
	}

}
