package br.com.empresa.vendapro.dao;


import java.util.List;

import br.com.empresa.vendapro.enuns.StatusVendedorAtivo; // criar  enumeração para status  
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Vendedor;  

public interface VendedorDao {  
    // Método para carregar todos os vendedores, opcionalmente recuperando pedidos  
    List<Vendedor> carregarTodosVendedores(boolean recuperarPedidos);  

    Vendedor consultarVendedorPorId(Long idVendedor, boolean recuperarPedidos);  

    Vendedor salvarVendedor(Vendedor vendedor) throws RegistroJaExisteException;  

    Vendedor alterarVendedor(Vendedor vendedor) throws RegistroNaoEncontradoException, RegistroJaExisteException;  

    void alterarStatusVendedor(Long idVendedor, StatusVendedorAtivo statusVendedorAtivo) throws RegistroNaoEncontradoException;  

    void validarCamposUnicos(Vendedor vendedor) throws RegistroJaExisteException;

	Vendedor buscarPorEmail(String email);  
} 