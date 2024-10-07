package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.enuns.StatusCategoriaAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Categoria;

import java.util.List;

public interface CategoriaDao{
	public List<Categoria> carregarTodasCategorias();
	public Categoria consultarCategoriaPorId(Long idCategoria) ;
	public Categoria salvarCategoria(Categoria categoria);
	public Categoria alterarCategoria(Categoria categoria) throws RegistroNaoEncontradoException;
}