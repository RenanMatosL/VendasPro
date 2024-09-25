package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.enuns.StatusClienteAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Cliente;

public interface ClienteDao{
	public List<Cliente> carregarTodosClientes(boolean recuperarPedidos);
	public Cliente consultarClientePorId(Long idCliente, boolean recuperarPedidos) ;
	public Cliente salvarCliente(Cliente cliente) throws RegistroJaExisteException;
	public Cliente alterarCliente(Cliente cliente) throws RegistroNaoEncontradoException, RegistroJaExisteException;
	public void alterarStatusCliente(Long idCliente, StatusClienteAtivo statusClienteAtivo) throws RegistroNaoEncontradoException;
	public void validarCamposUnicos (Cliente cliente) throws RegistroJaExisteException;
}
