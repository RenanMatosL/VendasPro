package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.empresa.vendapro.enuns.StatusSubcategoriaAtivo;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Subcategoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class SubcategoriaDaoImpl implements SubcategoriaDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Subcategoria> carregarTodasSubcategorias() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT c ");
		jpql.append("FROM Subcategoria c ");
		jpql.append("ORDER BY c.idSubcategoria DESC ");

		TypedQuery<Subcategoria> typedQuery = entityManager.createQuery(jpql.toString(), Subcategoria.class);
		List<Subcategoria> listaSubcategorias = typedQuery.getResultList();
		return listaSubcategorias;
	}

	@Override
	public Subcategoria consultarSubcategoriaPorId(Long idSubcategoria) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT s ");
		jpql.append("FROM Subcategoria s ");
		jpql.append("WHERE c.idSubcategoria = :idSubcategoria ");

		TypedQuery<Subcategoria> typedQuery = entityManager.createQuery(jpql.toString(), Subcategoria.class);
		typedQuery.setParameter("idSubcategoria", idSubcategoria);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	@Transactional
	@Override
	public Subcategoria salvarSubcategoria(Subcategoria subcategoria) {
		entityManager.persist(subcategoria);
		return subcategoria;
	}

	@Override
	public void alterarSubcategoria(Subcategoria subcategoria) {
		entityManager.merge(subcategoria);
		entityManager.flush();
	}

	@Override
	public void alterarStatusSubcategoria(Long idSubcategoria, StatusSubcategoriaAtivo statusSubcategoria) throws RegistroNaoEncontradoException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("UPDATE Subcategoria s ");
		jpql.append("SET c.statusSubcategoria = :statusSubcategoria ");
		jpql.append("WHERE c.idSubcategoria = :idSubcategoria ");

		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("statusSubcategoria", statusSubcategoria);
		query.setParameter("idSubcategoria", idSubcategoria);

		int quantidadeLinhasAfetadas = query.executeUpdate();

		if (quantidadeLinhasAfetadas == 0) {
			throw new RegistroNaoEncontradoException("Subcategoria de idSubcategoria " + idSubcategoria + " não localizado");
		}
	}
}