package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.enuns.StatusPedido;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Pedido;

public interface PedidoDao{
	public List<Pedido> carregarTodosPedidos();
	public Pedido consultarPedidoPorId(Long idPedido) ;
	public Pedido salvarPedido(Pedido pedido);
	public void alterarPedido(Pedido pedido);
	public void alterarStatusPedido(Long idPedido, StatusPedido statusPedido) throws RegistroNaoEncontradoException;
}
