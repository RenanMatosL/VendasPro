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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "CLIENTE", schema = "dbo")
public class Cliente implements Serializable {
	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long idCliente;

	@Column(name = "NOME", length = 250, nullable = false)
	private String nome;

	@Column(name = "CPF", length = 250, nullable = false, unique = true)
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private Date dataNascimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = false)
	private Date dataCadastro;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "CLIENTE_ATIVO", nullable = false)
	private StatusClienteAtivo statusClienteAtivo;

	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "cliente", fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Pedido> listaPedidos = new ArrayList();

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "CEP", nullable = false)
	private String cep; // CEP do cliente

	@OneToMany(mappedBy = "cliente")
	private List<Feedback> feedbacks;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
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

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	

}
