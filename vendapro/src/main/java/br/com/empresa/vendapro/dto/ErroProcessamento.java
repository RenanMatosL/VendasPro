package br.com.empresa.vendapro.dto;

public class ErroProcessamento {

	private Integer codigoErro;
	private String descricaoErro;

	public ErroProcessamento() {
	}

	public ErroProcessamento(Integer codigoErro, String descricaoErro) {
		this.codigoErro = codigoErro;
		this.descricaoErro = descricaoErro;
	}

	public Integer getCodigoErro() {
		return codigoErro;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setCodigoErro(Integer codigoErro) {
		this.codigoErro = codigoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

}