package br.com.empresa.vendapro.model;

import java.sql.Date;

import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//Anotando os atributos comuns para não precisar de duplicar o Mapeamento, evitando criar tabelas, tornando-as integradas às subclasses que à implementam   
@MappedSuperclass
//@Embeddable
public abstract class DadosCadastro {

	private static long serialVersionUID = 1L; // Adicione um ID de serialização

	@Column(name = "cpf", length = 11, nullable = false, unique = true)
	@Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos numéricos.")
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private java.util.@NotNull(message = "{validacao.campo-obrigatorio.dataNascimento}", groups = {
			ValidacaoCadastro.class, ValidacaoAlteracao.class }) Date dataNascimento;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "CEP", nullable = false)
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP deve seguir o formato XXXXX-XXX")
	private String cep; // CEP do cliente

	@Column(name = "RUA", length = 250, nullable = false)
	private String rua;

	@Column(name = "NUMERO", length = 6, nullable = false)
	private String numero;

	public DadosCadastro() {
		super();
	}

	public DadosCadastro(String nome,
			@Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos numéricos.") String cpf,
			Date dataNascimento, String telefone,
			@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP deve seguir o formato XXXXX-XXX") String cep,
			String rua, String numero) {

		super();
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
	}

	public String getCpf() {
		return cpf;
	}

	public java.util.Date getDataNascimento() {
		return dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(java.util.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	
}