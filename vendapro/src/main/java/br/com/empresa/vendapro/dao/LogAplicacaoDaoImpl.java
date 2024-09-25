package br.com.empresa.vendapro.dao;

import java.util.Date;
import org.springframework.stereotype.Repository;
import br.com.empresa.vendapro.model.LogAplicacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class LogAplicacaoDaoImpl implements LogAplicacaoDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void salvarLogAplicacao(String descricao) {
		LogAplicacao logAplicacao = new LogAplicacao(descricao, new Date());
		entityManager.persist(logAplicacao);
	}
}