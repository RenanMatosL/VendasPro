package br.com.empresa.vendapro.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.empresa.vendapro.enuns.StatusPedido;
import br.com.empresa.vendapro.validacao.NotEmptyList;
import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastroPedido;
import jakarta.validation.constraints.NotNull;

public class PedidoDto implements Serializable{

	@NotNull(
		message="{validacao.campo-obrigatorio.idPedido}",
		groups= {ValidacaoAlteracao.class}
	)
	private Long idPedido;

	private Date dataPedido;
	private StatusPedido statusPedido;
	private BigDecimal valor;

	@NotNull(
		message="{validacao.campo-obrigatorio.idCliente}",
		groups= {ValidacaoCadastroPedido.class}
	)
	private Long idCliente;

	@NotEmptyList(
		message="{validacao.campo-obrigatorio.listaItenPedido}",
		groups= {ValidacaoCadastroPedido.class}
	)	
	private List <ItenPedidoDto> listaItenPedido = new ArrayList();

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

	public Long getIdCliente() {
		return idCliente;
	}

	public List<ItenPedidoDto> getListaItenPedido() {
		return listaItenPedido;
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

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public void setListaItenPedido(List<ItenPedidoDto> listaItenPedido) {
		this.listaItenPedido = listaItenPedido;
	}
	
	

}