package br.com.empresa.vendapro.model;

import java.util.Objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUTO_PEDIDO", schema = "dbo")
@IdClass(ProdutoPedidoId.class)
public class ProdutoPedido implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO")
	private Pedido pedido;

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private Produto produto;

	@Column(name = "QUANTIDADE", precision = 9, scale = 3, nullable = false)
	private BigDecimal quantidade;

	@Column(name = "VALOR_UNITARIO", precision = 10, scale = 2, nullable = false)
	private BigDecimal valorUnitario;

	public Pedido getPedido() {
		return pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedido, produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoPedido other = (ProdutoPedido) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
	}

}
