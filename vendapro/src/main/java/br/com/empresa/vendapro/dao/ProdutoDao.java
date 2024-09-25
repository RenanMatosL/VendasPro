package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Produto;

public interface ProdutoDao{
	public List<Produto> carregarTodosProdutos();
	public Produto consultarProdutoPorId(Long idProduto) ;
	public Produto salvarProduto(Produto produto);
	public Produto alterarProduto(Produto produto) throws RegistroNaoEncontradoException;
}
