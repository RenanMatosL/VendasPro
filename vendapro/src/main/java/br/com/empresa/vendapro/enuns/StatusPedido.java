package br.com.empresa.vendapro.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

//Enum presente na PRÓPRIA classe (pois é de uso exclusivo dela
public enum StatusPedido {

	EM_CRIACAO(1, "Pedido em criação"),
	AGUARDANDO_PAGAMENTO(2, "Pedido aguardando pagamento"),
	PAGO(3, "Pedido pago");

	private int value;
	private String descricao;
			
	StatusPedido (int value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}
			
	//Caso esse enum seja utilizado pare receber ou retornar dados JSON de requisições, essa anotação indica que deverá ser considerado o valor NUMÉRICO desse atributo
	@JsonValue
	public Integer toInt() {
		return value;
	}
}