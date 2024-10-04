package br.com.empresa.vendapro.dto;

import java.sql.Date;

import br.com.empresa.vendapro.enuns.StatusVendedorAtivo;
import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VendedorDto {

	//Validação de preenchimento obrigatório
	@NotNull(
		//Mensagem a ser exibida em caso de não conformidade no campo (recuperada de arquivo Properties)
		message="{validacao.campo-obrigatorio.idVendedor}",
		//Indicamos que essa validação deverá ser aplicada somente para os grupos indicados
		groups= {ValidacaoAlteracao.class}
	)
	private Long idVendedor;

	//Validação de preenchimento obrigatório (não nulo e não vazio)
	@NotEmpty(
		//Mensagem a ser exibida em caso de não conformidade no campo (recuperada de arquivo Properties)
		message="{validacao.campo-obrigatorio.nome}",
		//Indicamos que essa validação deverá ser aplicada somente para os grupos indicados
		groups= {ValidacaoCadastro.class, ValidacaoAlteracao.class}
	)
	private String nome;

	//Validação de preenchimento obrigatório (não nulo e não vazio)
	@NotEmpty(
		//Mensagem a ser exibida em caso de não conformidade no campo (recuperada de arquivo Properties)
		message="{validacao.campo-obrigatorio.cpf}",
		//Indicamos que essa validação deverá ser aplicada somente para os grupos indicados
		groups= {ValidacaoCadastro.class, ValidacaoAlteracao.class}
	)
	private String cpf;

	//Validação de preenchimento obrigatório
	@NotNull(
		//Mensagem a ser exibida em caso de não conformidade no campo (recuperada de arquivo Properties)
		message="{validacao.campo-obrigatorio.dataNascimento}",
		//Indicamos que essa validação deverá ser aplicada somente para os grupos indicados
		groups= {ValidacaoCadastro.class, ValidacaoAlteracao.class}
	)
	private Date dataNascimento;

	private Date dataCadastro;

	private StatusVendedorAtivo statusVendedorAtivo;

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
	
	
	

}