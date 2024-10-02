package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.enuns.StatusCategoriaAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Categoria;

public interface CategoriaDao {

	public List<Categoria> carregarTodosCategorias();

	public Categoria consultarCategoriaPorId(Long idCategoria);

	public Categoria salvarCategoria(Categoria categoria) throws RegistroJaExisteException;

	public void alterarCategoria(Categoria categoria)
			throws RegistroNaoEncontradoException, RegistroJaExisteException;

	public void alterarStatusCategoria(Long idCategoria, StatusCategoriaAtivo statusCategoriaAtivo)
			throws RegistroNaoEncontradoException;

}
