package br.com.empresa.vendapro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.empresa.vendapro.enuns.StatusVendedorAtivo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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

@Entity
@Table(name = "VENDEDOR", schema = "dbo")
public class Vendedor extends Usuario implements Serializable {

	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VENDEDOR")
	private Long idVendedor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = false)
	private Date dataCadastro;

	@Embedded
	private DadosCadastro dadosCadastro;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "VENDEDOR_ATIVO", nullable = false)
	private StatusVendedorAtivo statusVendedorAtivo;

	@ManyToMany(
			/*
			 * Indica se esse relacionamento deverá ser recuperado nas consultas: EAGER
			 * recupera o relacionamento, LAZY não
			 */
			fetch = FetchType.LAZY)
	/*
	 * Em cenário em que na consulta, dentre todas as associações de entidades,
	 * houver mais de um relacionamento EAGER ManyToOne ou OneToMany, deverá
	 * utilizar coleções do tipo SET ou indicar que ao invés de JOIN, as associações
	 * deverão ser obtidas via sub consultas via FetchMode.SUBSELECT. Isso evita
	 * exceções referentes a múltiplos fetch e também duplicidades nos resultados
	 */
	@Fetch(value = FetchMode.SUBSELECT)

	/* Configurações da tabela auxiliar do relacionamento */
	@JoinTable(

			/*
			 * Nome da tabela auxiliar que representa o relacionamento (possui o ID das duas
			 * tabelas envolvidas)
			 */
			name = "VENDEDOR_PRODUTO"

			/* Nome da coluna referente ao id dessa entidade presente na tabela auxiliar */
			, joinColumns = @JoinColumn(name = "ID_VENDEDOR")

			/*
			 * Nome da coluna referente ao id da tabela referenciada presente na tabela
			 * auxiliar
			 */
			, inverseJoinColumns = @JoinColumn(name = "ID_PRODUTO"))
	private List<Produto> listaProdutos = new ArrayList();

	public Vendedor() {
		super();
	}

	public Vendedor(Long idVendedor, Date dataCadastro, DadosCadastro dadosCadastro,
			StatusVendedorAtivo statusVendedorAtivo, List<Produto> listaProdutos) {
		super();
		this.idVendedor = idVendedor;
		this.dataCadastro = dataCadastro;
		this.dadosCadastro = dadosCadastro;
		this.statusVendedorAtivo = statusVendedorAtivo;
		this.listaProdutos = listaProdutos;
	}

	public Long getIdVendedor() {
		return idVendedor;
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

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
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

	public DadosCadastro getDadosCadastro() {
		return dadosCadastro;
	}

	public void setDadosCadastro(DadosCadastro dadosCadastro) {
		this.dadosCadastro = dadosCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idVendedor);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		return Objects.equals(idVendedor, other.idVendedor);
	}

}
