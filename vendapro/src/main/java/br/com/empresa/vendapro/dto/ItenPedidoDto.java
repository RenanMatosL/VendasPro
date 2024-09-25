package br.com.empresa.vendapro.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import jakarta.validation.constraints.NotNull;

public class ItenPedidoDto implements Serializable {

	@NotNull(message = "{validacao.campo-obrigatorio.produto}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private ProdutoDto produto;

	@NotNull(message = "{validacao.campo-obrigatorio.quantidade}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;

	public ProdutoDto getProduto() {
		return produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}