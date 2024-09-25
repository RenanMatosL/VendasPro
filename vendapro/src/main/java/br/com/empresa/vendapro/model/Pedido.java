package br.com.empresa.vendapro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.empresa.vendapro.enuns.StatusPedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PEDIDO", schema = "dbo")

public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEDIDO")
	private Long idPedido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_PEDIDO", nullable = false)
	private Date dataPedido;

	@Enumerated()
	@Column(name = "STATUS_PEDIDO", nullable = false)
	private StatusPedido statusPedido;

	@Column(name = "VALOR", precision = 9, scale = 2, nullable = false)
	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	private Cliente cliente;

	@OneToMany(

			mappedBy = "pedido", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProdutoPedido> listaProdutoPedido = new ArrayList();

	public Long getIdPedido() {
		return idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<ProdutoPedido> getListaProdutoPedido() {
		return listaProdutoPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setListaProdutoPedido(List<ProdutoPedido> listaProdutoPedido) {
		this.listaProdutoPedido = listaProdutoPedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(idPedido, other.idPedido);
	}

}
