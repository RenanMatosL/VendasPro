package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.empresa.vendapro.enuns.StatusVendedorAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class VendedorDaoImpl implements VendedorDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Vendedor> carregarTodosVendedores(boolean recuperarProdutos) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT DISTINCT(v) ");
		jpql.append("FROM Vendedor v ");

		if (recuperarProdutos) {
			jpql.append("	LEFT JOIN FETCH v.listaProdutos ");
		}

		jpql.append("ORDER BY c.nome ");

		TypedQuery<Vendedor> typedQuery = entityManager.createQuery(jpql.toString(), Vendedor.class);
		List<Vendedor> listaVendedores = typedQuery.getResultList();

		return listaVendedores;
	}

	@Override
	public Vendedor consultarVendedorPorId(Long idVendedor, boolean recuperarProdutos) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT DISTINCT(v) ");
		jpql.append("FROM Vendedor v ");

		if (recuperarProdutos) {
			jpql.append("	LEFT JOIN FETCH c.listaProdutos ");
		}

		jpql.append("WHERE v.idVendedor = :idVendedorP ");

		TypedQuery<Vendedor> typedQuery = entityManager.createQuery(jpql.toString(), Vendedor.class);
		typedQuery.setParameter("idVendedorP", idVendedor);

		try {
			Vendedor vendedor = typedQuery.getSingleResult();
			return vendedor;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Vendedor salvarVendedor(Vendedor vendedor) throws RegistroJaExisteException {
		validarCamposUnicos(vendedor);
		entityManager.persist(vendedor);
		return vendedor;
	}

	@Override
	public Vendedor alterarVendedor(Vendedor vendedor)
			throws RegistroNaoEncontradoException, RegistroJaExisteException {
		if (consultarVendedorPorId(vendedor.getIdVendedor(), false) == null) {
			throw new RegistroNaoEncontradoException(
					"Vendedor de idVendedor " + vendedor.getIdVendedor() + " não localizado");
		}

		validarCamposUnicos(vendedor);
		entityManager.merge(vendedor);
		entityManager.flush();
		return vendedor;
	}

	@Override
	public void alterarStatusVendedor(Long idVendedor, StatusVendedorAtivo statusVendedorAtivo)
			throws RegistroNaoEncontradoException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("UPDATE Vendedor c ");
		jpql.append("SET c.statusVendedorAtivo = :statusVendedorAtivoP ");
		jpql.append("WHERE c.idVendedor = :idVendedorP ");

		Query query = entityManager.createQuery(jpql.toString());
		query.setParameter("statusVendedorAtivoP", statusVendedorAtivo);
		query.setParameter("idVendedorP", idVendedor);

		int quantidadeLinhasAfetadas = query.executeUpdate();
		if (quantidadeLinhasAfetadas == 0) {
			throw new RegistroNaoEncontradoException("Vendedor de idVendedor " + idVendedor + " não localizado");
		}
	}

	@Override
	public void validarCamposUnicos(Vendedor vendedor) throws RegistroJaExisteException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT COUNT(c) ");
		jpql.append("FROM Vendedor c ");
		jpql.append("WHERE v.cpf = :cpfP ");

		if (vendedor.getIdVendedor() != null) {
			jpql.append("	AND c.idVendedor <> :idVendedorP ");
		}

		TypedQuery<Long> typedQuery = entityManager.createQuery(jpql.toString(), Long.class);
		typedQuery.setParameter("cpfP", vendedor.getCpf());

		if (vendedor.getIdVendedor() != null) {
			typedQuery.setParameter("idVendedorP", vendedor.getIdVendedor());
		}

		Long quantidadeRegistrosLocalizados = typedQuery.getSingleResult();

		if (quantidadeRegistrosLocalizados > 0) {
			throw new RegistroJaExisteException("Já existe um vendedor com esses valores");
		}
	}

	@Override
	public Vendedor buscarPorEmail(String email) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT v");
		jpql.append("FROM Vendedor v");
		jpql.append("WHERE v.email = :email");

		try {
			return entityManager.createQuery(jpql.toString()
					, Vendedor.class).setParameter(
							"email", email).getSingleResult();
		} catch (Exception e) {
			return null; // "Alterar" este código após testar criptografia de senha.
			// Retorna null se o vendedor não for encontrado  

		}

	}

}