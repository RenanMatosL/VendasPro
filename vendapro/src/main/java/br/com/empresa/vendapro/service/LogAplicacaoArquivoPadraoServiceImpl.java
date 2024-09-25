package br.com.empresa.vendapro.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class LogAplicacaoArquivoPadraoServiceImpl implements LogAplicacaoService {

	private static final Log logger = LogFactory.getLog(LogAplicacaoArquivoPadraoServiceImpl.class);

	@Override
	public void salvarLogAplicacao(String descricao) {
		logger.info(descricao);
	}
}