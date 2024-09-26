package br.com.empresa.vendapro.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.empresa.vendapro.enuns.StatusClienteAtivo;
import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ClienteDto implements Serializable {

	@NotNull(message = "{validacao.campo-obrigatorio.idCliente}", groups = { ValidacaoAlteracao.class })
	private Long idCliente;

	@NotEmpty(message = "{validacao.campo-obrigatorio.nome}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private String nome;

	@NotEmpty(message = "{validacao.campo-obrigatorio.cpf}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private String cpf;

	@NotNull(message = "{validacao.campo-obrigatorio.dataNascimento}", groups = { ValidacaoCadastro.class,
			ValidacaoAlteracao.class })
	private Date dataNascimento;

	private Date dataCadastro;

	private StatusClienteAtivo statusClienteAtivo;
	
	@Email(message = "email deve ser válido")
	@NotBlank(message = "email deve ser obrigatório")  
	private String email;


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

}