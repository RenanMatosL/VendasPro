package br.com.empresa.vendapro.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusSubcategoriaAtivo {

	ATIVO(1, "Subcategoria ativa"), INATIVA(0, "Subcategoria inativa");

	private int value;
	private String descricao;

	StatusSubcategoriaAtivo(int value, String descricao) {
		this.value = value;
		this.descricao = descricao;

	}

	// Caso esse enum seja utilizado pare receber ou retornar dados JSON de
	// requisições, essa anotação indica que deverá ser considerado o valor NUMÉRICO
	// desse atributo
	@JsonValue
	public Integer toInt() {
		return value;
	}
}