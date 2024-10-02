package br.com.empresa.vendapro.dao;

import java.util.List;

import br.com.empresa.vendapro.enuns.StatusSubcategoriaAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Subcategoria;

public interface SubcategoriaDao {

	public List<Subcategoria> carregarTodasSubcategorias();

	public Subcategoria consultarSubcategoriaPorId(Long idSubcategoria);

	public Subcategoria salvarSubcategoria(Subcategoria subcategoria) throws RegistroJaExisteException;

	public void alterarSubcategoria(Subcategoria Subcategoria)
			throws RegistroNaoEncontradoException, RegistroJaExisteException;

	public void alterarStatusSubcategoria(Long idCategoria, StatusSubcategoriaAtivo statusSubcategoriaAtivo)
			throws RegistroNaoEncontradoException;

}
