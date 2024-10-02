package br.com.empresa.vendapro.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusCategoriaAtivo {
	

	ATIVO(1, "Categoria ativo"), INATIVO(0, "Categoria inativo");

	private int value;
	private String descricao;

	StatusCategoriaAtivo(int value, String descricao) {
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