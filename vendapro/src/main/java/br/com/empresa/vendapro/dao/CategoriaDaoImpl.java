package br.com.empresa.vendapro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CategoriaDaoImpl implements CategoriaDao{

	//Indica a injeção do EntityManager JPA pelo Spring
	@PersistenceContext
	EntityManager entityManager;

	//Indica que o método é implementação de uma interface
	@Override
	//Método que retorna um List de TODAS Categorias
	public List<Categoria> carregarTodasCategorias() {
		StringBuilder jpql = new StringBuilder("");
		jpql.append("SELECT c ");
		jpql.append("FROM Categoria c ");
		jpql.append("ORDER BY c.nome ");

		//Criando a query, efetuando a busca e inserir os registros em uma lista
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql.toString(), Categoria.class);

		List<Categoria> listaCategorias = typedQuery.getResultList();

		return listaCategorias;
	}

	//Indica que o método é implementação de uma interface
	@Override
	//Método que retorna uma Categoria por seu ID
	public Categoria consultarCategoriaPorId(Long idCategoria) {
		return entityManager.find(Categoria.class, idCategoria);
	}

	//Indica que o método é implementação de uma interface
	@Override
	//Método que cadastra um novo categoria
	public Categoria salvarCategoria(Categoria categoria){
		entityManager.persist(categoria);
		return categoria;
	}

	//Indica que o método é implementação de uma interface
	@Override
	//Método que altera um categoria
	public Categoria alterarCategoria (Categoria categoria) throws RegistroNaoEncontradoException {
		//Verificar se o registro existe
		if (consultarCategoriaPorId(categoria.getIdCategoria()) == null) {
			throw new RegistroNaoEncontradoException("Categoria de idCategoria " + categoria.getIdCategoria() + " não localizado");
		}

		entityManager.merge(categoria);

		//Indicar ao JPA que comandos pendentes de execução (UPDATE, DELETE, por exemplo) deverão ser executados nesse momento
		entityManager.flush();

		return categoria;
	}
}



