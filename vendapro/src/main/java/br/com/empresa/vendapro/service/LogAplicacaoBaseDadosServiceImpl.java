package br.com.empresa.vendapro.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.empresa.vendapro.dao.LogAplicacaoDao;

@Service
public class LogAplicacaoBaseDadosServiceImpl implements LogAplicacaoService {

	private static final Log logger = LogFactory.getLog(LogAplicacaoBaseDadosServiceImpl.class);

	@Autowired
	private LogAplicacaoDao logAplicacaoDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void salvarLogAplicacao(String descricao) {
		try {
			logAplicacaoDao.salvarLogAplicacao(descricao);
		} catch (Exception e) {
			logger.error("Erro ao salvar o log", e);

			throw e;
		}
	}
}