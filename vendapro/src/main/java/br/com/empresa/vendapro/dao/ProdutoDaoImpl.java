package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ProdutoDaoImpl implements ProdutoDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Produto> carregarTodosProdutos() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT p ");
		jpql.append("FROM Produto p ");
		jpql.append("ORDER BY p.nome ");

		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql.toString(), Produto.class);

		List<Produto> listaProdutos = typedQuery.getResultList();

		return listaProdutos;
	}

	@Override
	public Produto consultarProdutoPorId(Long idProduto) {
		return entityManager.find(Produto.class, idProduto);
	}

	@Override
	public Produto salvarProduto(Produto produto) {
		entityManager.persist(produto);
		return produto;
	}

	@Override
	public Produto alterarProduto(Produto produto) throws RegistroNaoEncontradoException {
		if (consultarProdutoPorId(produto.getIdProduto()) == null) {
			throw new RegistroNaoEncontradoException(
					"Produto de idProduto " + produto.getIdProduto() + " não localizado");
		}

		entityManager.merge(produto);

		entityManager.flush();

		return produto;
	}
}