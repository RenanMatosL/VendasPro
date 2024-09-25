package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.empresa.vendapro.enuns.StatusPedido;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class PedidoDaoImpl implements PedidoDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Pedido> carregarTodosPedidos() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT p ");
		jpql.append("FROM Pedido p ");
		jpql.append("ORDER BY p.idPedido DESC ");

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql.toString(), Pedido.class);
		List<Pedido> listaPedidos = typedQuery.getResultList();
		return listaPedidos;
	}

	@Override
	public Pedido consultarPedidoPorId(Long idPedido) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT p ");
		jpql.append("FROM Pedido p ");
		jpql.append("WHERE p.idPedido = :idPedidoP ");

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql.toString(), Pedido.class);
		typedQuery.setParameter("idPedidoP", idPedido);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Pedido salvarPedido(Pedido pedido) {
		entityManager.persist(pedido);
		return pedido;
	}

	@Override
	public void alterarPedido(Pedido pedido) {
		entityManager.merge(pedido);
		entityManager.flush();
	}

	@Override
	public void alterarStatusPedido(Long idPedido, StatusPedido statusPedido) throws RegistroNaoEncontradoException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("UPDATE Pedido p ");
		jpql.append("SET p.statusPedido = :statusPedidoP ");
		jpql.append("WHERE p.idPedido = :idPedidoP ");

		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("statusPedidoP", statusPedido);
		query.setParameter("idPedidoP", idPedido);

		int quantidadeLinhasAfetadas = query.executeUpdate();

		if (quantidadeLinhasAfetadas == 0) {
			throw new RegistroNaoEncontradoException("Pedido de idPedido " + idPedido + " não localizado");
		}
	}
}
