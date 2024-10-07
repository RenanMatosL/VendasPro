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

import java.util.List;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class SubcategoriaDaoImpl implements SubcategoriaDao{

	//Indica a injeção do EntityManager JPA pelo Spring
	@PersistenceContext
	EntityManager entityManager;

	//Indica que o método é implementação de uma interface
	@Override
	//Método que retorna a um List de TODOS Subcategorias
	public List<Subcategoria> carregarTodasSubcategorias() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT s ");
		jpql.append("FROM Subcategoria s ");
		jpql.append("ORDER BY p.nome ");

		//Criando a query, efetuando a busca e inserir os registros em uma lista
		TypedQuery<Subcategoria> typedQuery = entityManager.createQuery(jpql.toString(), Subcategoria.class);

		List<Subcategoria> listaSubcategorias = typedQuery.getResultList();

		return listaSubcategorias;
	}

	//Indica que o método é implementação de uma interface
	@Override
	//Método que retorna uma Subcategoria por seu ID
	public Subcategoria consultarSubcategoriaPorId(Long idSubcategoria) {
		return entityManager.find(Subcategoria.class, idSubcategoria);
	}

	//Indica que o método é implementação de uma interface
	@Override
	//Método que cadastra uma nova subcategoria
	public Subcategoria salvarSubcategoria(Subcategoria subcategoria){
		entityManager.persist(subcategoria);
		return subcategoria;
	}

	//Indica que o método é implementação de uma interface
	@Override
	//Método que altera uma subcategoria
	public Subcategoria alterarSubcategoria (Subcategoria subcategoria) throws RegistroNaoEncontradoException {
		//Verificar se o registro existe
		if (consultarSubcategoriaPorId(subcategoria.getIdSubcategoria()) == null) {
			throw new RegistroNaoEncontradoException("Subcategoria de idSubcategoria " + subcategoria.getIdSubcategoria() + " não localizado");
		}

		entityManager.merge(subcategoria);

		//Indicar ao JPA que comandos pendentes de execução (UPDATE, DELETE, por exemplo) deverão ser executados nesse momento
		entityManager.flush();

		return subcategoria;
	}
}

