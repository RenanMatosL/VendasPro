package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.enuns.StatusSubcategoriaAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Subcategoria;

import java.util.List;

public interface SubcategoriaDao{
	public List<Subcategoria> carregarTodasSubcategorias();
	public Subcategoria consultarSubcategoriaPorId(Long idSubcategoria) ;
	public Subcategoria salvarSubcategoria(Subcategoria subcategoria);
	public Subcategoria alterarSubcategoria(Subcategoria subcategoria) throws RegistroNaoEncontradoException;
}