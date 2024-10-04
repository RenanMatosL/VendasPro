package br.com.empresa.vendapro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.empresa.vendapro.enuns.StatusClienteAtivo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CLIENTE", schema = "dbo")
public class Cliente extends Usuario implements Serializable {
	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long idCliente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = false)
	private Date dataCadastro;
	
	@Embedded
	private DadosCadastro dadosCadastro;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CLIENTE_ATIVO", nullable = false)
	private StatusClienteAtivo statusClienteAtivo;

	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "cliente", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Pedido> listaPedidos = new ArrayList();

	@OneToMany(mappedBy = "cliente")
	private List<Feedback> feedbacks;

	public Cliente() {
		super();
	}
	
		
	public Cliente(Long idCliente, Date dataCadastro, DadosCadastro dadosCadastro,
			StatusClienteAtivo statusClienteAtivo, List<Pedido> listaPedidos, List<Feedback> feedbacks) {
		super();
		this.idCliente = idCliente;
		this.dataCadastro = dataCadastro;
		this.dadosCadastro = dadosCadastro;
		this.statusClienteAtivo = statusClienteAtivo;
		this.listaPedidos = listaPedidos;
		this.feedbacks = feedbacks;
	}



	public Long getIdCliente() {
		return idCliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public StatusClienteAtivo getStatusClienteAtivo() {
		return statusClienteAtivo;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setStatusClienteAtivo(StatusClienteAtivo statusClienteAtivo) {
		this.statusClienteAtivo = statusClienteAtivo;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
	}

	public DadosCadastro getDadosCadastro() {
		return dadosCadastro;
	}

	public void setDadosCadastro(DadosCadastro dadosCadastro) {
		this.dadosCadastro = dadosCadastro;
	}

}
