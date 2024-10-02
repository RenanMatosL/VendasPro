package br.com.empresa.vendapro.dao;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.empresa.vendapro.enuns.StatusCategoriaAtivo;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class CategoriaDaoImpl implements CategoriaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Categoria> carregarTodosCategorias() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT c ");
		jpql.append("FROM Categoria c ");
		jpql.append("ORDER BY c.idCategoria DESC ");

		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql.toString(), Categoria.class);
		List<Categoria> listaCategorias = typedQuery.getResultList();
		return listaCategorias;
	}

	@Override
	public Categoria consultarCategoriaPorId(Long idCategoria) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT c ");
		jpql.append("FROM Categoria c ");
		jpql.append("WHERE c.idCategoria = :idCategoria ");

		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql.toString(), Categoria.class);
		typedQuery.setParameter("idCategoria", idCategoria);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	@Transactional
	@Override
	public Categoria salvarCategoria(Categoria categoria) {
		entityManager.persist(categoria);
		return categoria;
	}

	@Override
	public void alterarCategoria(Categoria categoria) {
		entityManager.merge(categoria);
		entityManager.flush();
	}

	@Override
	public void alterarStatusCategoria(Long idCategoria, StatusCategoriaAtivo statusCategoria) throws RegistroNaoEncontradoException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("UPDATE Categoria c ");
		jpql.append("SET c.statusCategoria = :statusCategoria ");
		jpql.append("WHERE c.idCategoria = :idCategoria ");

		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("statusCategoria", statusCategoria);
		query.setParameter("idCategoria", idCategoria);

		int quantidadeLinhasAfetadas = query.executeUpdate();

		if (quantidadeLinhasAfetadas == 0) {
			throw new RegistroNaoEncontradoException("Categoria de idCategoria " + idCategoria + " não localizado");
		}
	}
}









































/*
 * import java.util.List;
 * 
 * import org.springframework.stereotype.Repository;
 * 
 * import br.com.empresa.vendapro.enuns.StatusCategoriaAtivo; import
 * br.com.empresa.vendapro.excecoes.RegistroJaExisteException; import
 * br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException; import
 * br.com.empresa.vendapro.model.Categoria; import
 * jakarta.persistence.EntityManager; import
 * jakarta.persistence.NoResultException; import
 * jakarta.persistence.PersistenceContext; import jakarta.persistence.Query;
 * import jakarta.persistence.TypedQuery;
 * 
 * @Repository public class CategoriaDaoImpl implements CategoriaDao {
 * 
 * @PersistenceContext EntityManager entityManager;
 * 
 * @Override public List<Categoria> carregarTodosCategorias() { StringBuilder
 * jpql = new StringBuilder(""); jpql.append("SELECT DISTINCT(c) ");
 * jpql.append("FROM Categoria c ");
 * 
 * jpql.append("ORDER BY c.nome ");
 * 
 * TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql.toString(),
 * Categoria.class); List<Categoria> listaCategorias =
 * typedQuery.getResultList();
 * 
 * return listaCategorias; }
 * 
 * @Override public Categoria consultarCategoriaPorId(Long idCategoria) {
 * StringBuilder jpql = new StringBuilder("");
 * jpql.append("SELECT DISTINCT(c) "); jpql.append("FROM Categoria c ");
 * 
 * TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql.toString(),
 * Categoria.class); typedQuery.setParameter("idCategoriaP", idCategoria);
 * 
 * try { Categoria categoria = typedQuery.getSingleResult(); return categoria; }
 * catch (NoResultException e) { return null; } }
 * 
 * @Override public Categoria salvarCategoria(Categoria categoria) throws
 * RegistroJaExisteException { entityManager.persist(categoria); return
 * categoria; }
 * 
 * @Override public Categoria alterarCategoria(Categoria categoria) throws
 * RegistroNaoEncontradoException, RegistroJaExisteException { if
 * (consultarCategoriaPorId(categoria.getIdCategoria()) == null) { throw new
 * RegistroNaoEncontradoException( "Categoria de idCategoria " +
 * categoria.getIdCategoria() + " não localizado"); }
 * 
 * entityManager.merge(categoria); entityManager.flush(); return categoria; }
 * 
 * @Override public void alterarStatusCategoria(Long idCategoria,
 * StatusCategoriaAtivo statusCategoriaAtivo) throws
 * RegistroNaoEncontradoException { StringBuilder jpql = new StringBuilder("");
 * jpql.append("UPDATE Categoria c ");
 * jpql.append("SET c.statusCategoriaAtivo = :statusCategoriaAtivoP ");
 * jpql.append("WHERE c.idCategoria = :idCategoriaP ");
 * 
 * Query query = entityManager.createQuery(jpql.toString());
 * query.setParameter("statusCategoriaAtivoP", statusCategoriaAtivo);
 * query.setParameter("idCategoriaP", idCategoria);
 * 
 * int quantidadeLinhasAfetadas = query.executeUpdate(); if
 * (quantidadeLinhasAfetadas == 0) { throw new
 * RegistroNaoEncontradoException("Categoria de idCategoria " + idCategoria +
 * " não localizado"); } }
 * 
 * }
 */