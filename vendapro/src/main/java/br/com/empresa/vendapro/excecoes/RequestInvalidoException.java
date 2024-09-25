package br.com.empresa.vendapro.excecoes;

import br.com.empresa.vendapro.dto.ErrosRequisicao;

public class RequestInvalidoException extends Exception{
	
	private ErrosRequisicao errosRequisicao;
	
	public RequestInvalidoException (String mensagemErro, ErrosRequisicao errosRequisicao, final Throwable throwable) {
		super(mensagemErro, throwable);
		this.errosRequisicao = errosRequisicao;
    	}

	public ErrosRequisicao getErrosRequisicao() {
		return errosRequisicao;
	}

	public void setErrosRequisicao(ErrosRequisicao errosRequisicao) {
		this.errosRequisicao = errosRequisicao;
	}
	
	
	
	

}