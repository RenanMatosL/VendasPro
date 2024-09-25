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
	private static final long serialVersionUID = 1L;

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

	@Email(message = "email deve ser válido")
	@NotBlank(message = "email deve ser obrigatório") //MOVER ANOTAÇÕES BEAN VALIDATIO PARA OS DTOS
	@Column(name = "EMAIL", nullable = false)
	private String email;

	public Long getIdCliente() {
		return idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
