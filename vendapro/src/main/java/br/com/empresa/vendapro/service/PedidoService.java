package br.com.empresa.vendapro.service;

import java.util.List;

import br.com.empresa.vendapro.dto.PedidoDto;
import br.com.empresa.vendapro.enuns.StatusPedido;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Pedido;

public interface PedidoService{
	public List<Pedido> carregarTodosPedidos();
	public Pedido consultarPedidoPorId(Long idPedido) ;
	public Pedido salvarPedido(Pedido pedido);
	public void alterarPedido(Pedido pedido);
	public List<PedidoDto> getListaPedidoDtoPorPedido(List<Pedido> listaPedido);
	public PedidoDto getPedidoDtoPorPedido(Pedido pedido);
	public Pedido getPedidoPorPedidoDto(PedidoDto pedidoDto);
	public void validarPedidoDtoParaCadastro(PedidoDto pedidoDto) throws RequestInvalidoException;
	public void alterarStatusPedido(Long idPedido, StatusPedido statusPedido) throws RegistroNaoEncontradoException;
}
