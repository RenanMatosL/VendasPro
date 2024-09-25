package br.com.empresa.vendapro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.empresa.vendapro.enuns.StatusVendedorAtivo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "VENDEDOR", schema = "dbo")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VENDEDOR")
	private Long idVendedor;

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
	@Column(name = "VENDEDOR_ATIVO", nullable = false)
	private StatusVendedorAtivo statusVendedorAtivo;
	
	@ManyToMany (
		/*Indica se esse relacionamento deverá ser recuperado nas consultas: EAGER recupera o relacionamento, LAZY não*/
		fetch = FetchType.LAZY
	) 
	/*Em cenário em que na consulta, dentre todas as associações de entidades, houver mais de um relacionamento EAGER ManyToOne ou OneToMany, deverá utilizar coleções do tipo SET 
	ou indicar que ao invés de JOIN, as associações deverão ser obtidas via sub consultas via FetchMode.SUBSELECT. Isso evita exceções referentes a múltiplos fetch e também duplicidades 
	nos resultados*/
    @Fetch(value = FetchMode.SUBSELECT)
	/*Configurações da tabela auxiliar do relacionamento*/
	@JoinTable(
		/*Nome da tabela auxiliar que representa o relacionamento (possui o ID das duas tabelas envolvidas)*/
		name="VENDEDOR_PRODUTO"
		/*Nome da coluna referente ao id dessa entidade presente na tabela auxiliar*/
		,joinColumns = @JoinColumn(name = "ID_VENDEDOR")
		/*Nome da coluna referente ao id da tabela referenciada presente na tabela auxiliar*/
		,inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO")
	)
	private List<Produto> listaProdutos = new ArrayList();
	
	@Column(name = "EMAIL", nullable = false, length = 100, unique = true)
	private String email;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	public Long getIdVendedor() {
		return idVendedor;
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

	public StatusVendedorAtivo getStatusVendedorAtivo() {
		return statusVendedorAtivo;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
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

	public void setStatusVendedorAtivo(StatusVendedorAtivo statusVendedorAtivo) {
		this.statusVendedorAtivo = statusVendedorAtivo;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Este método é chamado para garantir que a senha seja criptografada antes de
	// ser salva
	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVendedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		return Objects.equals(idVendedor, other.idVendedor);
	}
}
