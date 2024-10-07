package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.empresa.vendapro.enuns.StatusUsuarioAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	// Indica a injeção do EntityManager JPA pelo Spring
	@PersistenceContext
	EntityManager entityManager;

	// Indica que o método é implementação de uma interface
	@Override
	// Método que retorna a um List de TODOS Usuarios
	public List<Usuario> carregarTodosUsuarios() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT DISTINCT(u) ");
		jpql.append("FROM Usuario u ");
		jpql.append("ORDER BY u.nome ");

		// Criando a query, efetuando a busca e inserir os registros em uma lista
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql.toString(), Usuario.class);

		List<Usuario> listaUsuarios = typedQuery.getResultList();

		return listaUsuarios;
	}

	// Indica que o método é implementação de uma interface
	@Override
	// Método que retorna um Usuario por seu ID
	public Usuario consultarUsuarioPorId(Long idUsuario) {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT DISTINCT(u) ");
		jpql.append("FROM Usuario u ");
		jpql.append("WHERE u.idUsuario = :idUsuarioP ");

		// Criando a query, efetuando a busca e inserir os registros em uma lista
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(jpql.toString(), Usuario.class);

		// Parmâmetros da instrução SQL
		typedQuery.setParameter("idUsuarioP", idUsuario);

		// Tratar exceção de getSingleResult em cenário de registro não localizado
		try {
			Usuario usuario = typedQuery.getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			// Registro não encontrado
			return null;
		}
	}

	// Indica que o método é implementação de uma interface
	@Override
	// Método que cadastra um novo cliente
	public Usuario salvarUsuario(Usuario usuario) throws RegistroJaExisteException {
		entityManager.persist(usuario);
		return usuario;
	}

	// Indica que o método é implementação de uma interface
	@Override
	// Método que altera o status do cliente
	public void alterarStatusUsuario(Long idUsuario, StatusUsuarioAtivo statusUsuarioAtivo)
			throws RegistroNaoEncontradoException {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("UPDATE Usuario u ");
		jpql.append("SET u.statusUsuarioAtivo = :statusUsuarioAtivoP ");
		jpql.append("WHERE u.idUsuario = : idUsuarioP ");

		Query query = entityManager.createQuery(jpql.toString());

		// Parmâmetros da instrução SQL
		query.setParameter("statusUsuarioAtivoP", statusUsuarioAtivo);
		query.setParameter("idUsuarioP", idUsuario);

		// Executar o UPDATE e recuperar a quantidade de linhas alteradas (caso desejar
		// validar se alguma linha foi alterada)
		int quantidadeLinhasAfetadas = query.executeUpdate();

		// Verificar se o registro foi localizado
		if (quantidadeLinhasAfetadas == 0) {
			throw new RegistroNaoEncontradoException("Usuario de idUsuario " + idUsuario + " não localizado");
		}
	}

	@Override
	public UserDetails findUsuarioByLogin(String login) {
		return null;
	}

}