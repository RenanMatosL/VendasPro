package br.com.empresa.vendapro.excecoes;

public class RegistroJaExisteException extends Exception {
	public RegistroJaExisteException(String mensagem) {
		super(mensagem);
	}
}