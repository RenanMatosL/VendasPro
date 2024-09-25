package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.empresa.vendapro.enuns.StatusClienteAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class ClienteDaoImpl implements ClienteDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Cliente> carregarTodosClientes(boolean recuperarPedidos) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT DISTINCT(c) ");
		jpql.append("FROM Cliente c ");

		if (recuperarPedidos) {
			jpql.append("	LEFT JOIN FETCH c.listaPedidos ");
		}

		jpql.append("ORDER BY c.nome ");

		TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql.toString(), Cliente.class);
		List<Cliente> listaClientes = typedQuery.getResultList();

		return listaClientes;
	}

	@Override
	public Cliente consultarClientePorId(Long idCliente, boolean recuperarPedidos) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT DISTINCT(c) ");
		jpql.append("FROM Cliente c ");

		if (recuperarPedidos) {
			jpql.append("	LEFT JOIN FETCH c.listaPedidos ");
		}

		jpql.append("WHERE c.idCliente = :idClienteP ");

		TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql.toString(), Cliente.class);
		typedQuery.setParameter("idClienteP", idCliente);

		try {
			Cliente cliente = typedQuery.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Cliente salvarCliente(Cliente cliente) throws RegistroJaExisteException {
		validarCamposUnicos(cliente);
		entityManager.persist(cliente);
		return cliente;
	}

	@Override
	public Cliente alterarCliente(Cliente cliente) throws RegistroNaoEncontradoException, RegistroJaExisteException {
		if (consultarClientePorId(cliente.getIdCliente(), false) == null) {
			throw new RegistroNaoEncontradoException(
					"Cliente de idCliente " + cliente.getIdCliente() + " não localizado");
		}

		validarCamposUnicos(cliente);
		entityManager.merge(cliente);
		entityManager.flush();
		return cliente;
	}

	@Override
	public void alterarStatusCliente(Long idCliente, StatusClienteAtivo statusClienteAtivo)
			throws RegistroNaoEncontradoException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("UPDATE Cliente c ");
		jpql.append("SET c.statusClienteAtivo = :statusClienteAtivoP ");
		jpql.append("WHERE c.idCliente = :idClienteP ");

		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("statusClienteAtivoP", statusClienteAtivo);
		query.setParameter("idClienteP", idCliente);

		int quantidadeLinhasAfetadas = query.executeUpdate();
		if (quantidadeLinhasAfetadas == 0) {
			throw new RegistroNaoEncontradoException("Cliente de idCliente " + idCliente + " não localizado");
		}
	}

	@Override
	public void validarCamposUnicos(Cliente cliente) throws RegistroJaExisteException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT COUNT(c) ");
		jpql.append("FROM Cliente c ");
		jpql.append("WHERE c.cpf = :cpfP ");

		if (cliente.getIdCliente() != null) {
			jpql.append("	AND c.idCliente <> :idClienteP ");
		}

		TypedQuery<Long> typedQuery = entityManager.createQuery(jpql.toString(), Long.class);
		typedQuery.setParameter("cpfP", cliente.getCpf());

		if (cliente.getIdCliente() != null) {
			typedQuery.setParameter("idClienteP", cliente.getIdCliente());
		}

		Long quantidadeRegistrosLocalizados = typedQuery.getSingleResult();

		if (quantidadeRegistrosLocalizados > 0) {
			throw new RegistroJaExisteException("Já existe um cliente com esses valores");
		}
	}

}