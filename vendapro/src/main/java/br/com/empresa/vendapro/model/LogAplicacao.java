package br.com.empresa.vendapro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "LOG_APLICACAO", schema = "dbo")
public class LogAplicacao implements Serializable { //  Serializable: converter um objeto em um fluxo de bytes,
													// permitindo  armazenamento  ou transmissão por rede.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LOG_APLICACAO")
	private Long idLogAplicacao;

	@Column(name = "DESCRICAO", length = 250, nullable = false)
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)

	@Column(name = "DATA_LOG", nullable = false)
	private Date dataLog;

	public LogAplicacao() {
	}

	public LogAplicacao(String descricao, Date dataLog) {
		this.descricao = descricao;
		this.dataLog = dataLog;
	}

	public Long getIdLogAplicacao() {
		return idLogAplicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Date getDataLog() {
		return dataLog;
	}

	public void setIdLogAplicacao(Long idLogAplicacao) {
		this.idLogAplicacao = idLogAplicacao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataLog(Date dataLog) {
		this.dataLog = dataLog;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataLog, descricao, idLogAplicacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogAplicacao other = (LogAplicacao) obj;
		return Objects.equals(dataLog, other.dataLog) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(idLogAplicacao, other.idLogAplicacao);
	}

}
