package br.com.empresa.vendapro.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import br.com.empresa.vendapro.validacao.ValidacaoCadastroPedido;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProdutoDto implements Serializable {

	@NotNull(message = "{validacao.campo-obrigatorio.idProduto}", groups = { ValidacaoAlteracao.class,
			ValidacaoCadastroPedido.class })
	private Long idProduto;

	@NotEmpty(message = "{validacao.campo-obrigatorio.nome}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private String nome;

	@NotNull(message = "{validacao.campo-obrigatorio.preco}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private BigDecimal preco;

	public Long getIdProduto() {
		return idProduto;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}