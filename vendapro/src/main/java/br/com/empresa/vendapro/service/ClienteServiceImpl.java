package br.com.empresa.vendapro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.empresa.vendapro.dao.ClienteDao;
import br.com.empresa.vendapro.dto.ClienteDto;
import br.com.empresa.vendapro.dto.ErroProcessamento;
import br.com.empresa.vendapro.dto.ErrosRequisicao;
import br.com.empresa.vendapro.enuns.StatusClienteAtivo;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Cliente;
import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import jakarta.validation.ConstraintViolation;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Log logger = LogFactory.getLog(ClienteServiceImpl.class);

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	@Qualifier("logAplicacaoBaseDadosServiceImpl")
	private LogAplicacaoService logAplicacaoService;

	@Autowired
	@Qualifier("localValidatorFactoryBeanPadrao")
	private LocalValidatorFactoryBean localValidatorFactoryBean;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Cliente> carregarTodosClientes(boolean recuperarPedidos) {
		try {
			return clienteDao.carregarTodosClientes(recuperarPedidos);
		} catch (Exception e) {
			logger.error("Erro ao consultar todos os clientes", e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Cliente consultarClientePorId(Long idCliente, boolean recuperarPedidos) {
		try {
			return clienteDao.consultarClientePorId(idCliente, recuperarPedidos);
		} catch (Exception e) {
			logger.error("Erro ao consultar um cliente por ID. ID_CLIENTE " + idCliente, e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Cliente salvarCliente(Cliente cliente) throws RegistroJaExisteException {
		try {
			logAplicacaoService.salvarLogAplicacao("Tentativa de cadastro de cliente chamado " + cliente.getNome());

			cliente.setDataCadastro(new Date());
			cliente.setStatusClienteAtivo(StatusClienteAtivo.ATIVO);

			return clienteDao.salvarCliente(cliente);
		} catch (RegistroJaExisteException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao salvar o cliente. Nome cliente: " + cliente.getNome(), e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Cliente alterarCliente(Cliente cliente) throws RegistroNaoEncontradoException, RegistroJaExisteException {
		try {

			Cliente clienteBase = clienteDao.consultarClientePorId(cliente.getIdCliente(), false);

			if (clienteBase == null) {
				throw new RegistroNaoEncontradoException(
						"Cliente de idCliente " + cliente.getIdCliente() + " não localizado");
			}

			clienteBase.setNome(cliente.getNome());
			clienteBase.setCpf(cliente.getCpf());
			clienteBase.setDataNascimento(cliente.getDataNascimento());

			return clienteDao.alterarCliente(clienteBase);
		} catch (RegistroJaExisteException e) {
			throw e;
		} catch (RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao alterar o cliente. ID_CLIENTE " + cliente.getIdCliente(), e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void alterarStatusCliente(Long idCliente, StatusClienteAtivo statusClienteAtivo)
			throws RegistroNaoEncontradoException {
		try {
			clienteDao.alterarStatusCliente(idCliente, statusClienteAtivo);
		} catch (RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao alterar status do cliente. ID_CLIENTE " + idCliente, e);

			throw e;
		}
	}

	@Override
	public List<ClienteDto> getListaClienteDtoPorCliente(List<Cliente> listaCliente) {
		if (listaCliente == null || listaCliente.size() == 0) {
			return null;
		} else {
			List<ClienteDto> listaClienteDto = new ArrayList();

			for (Cliente cliente : listaCliente) {
				ClienteDto clienteDto = getClienteDtoPorCliente(cliente);
				listaClienteDto.add(clienteDto);
			}

			return listaClienteDto;
		}
	}

	@Override
	public ClienteDto getClienteDtoPorCliente(Cliente cliente) {
		if (cliente != null) {
			ClienteDto clienteDto = new ClienteDto();
			clienteDto.setIdCliente(cliente.getIdCliente());
			clienteDto.setNome(cliente.getNome());
			clienteDto.setCpf(cliente.getCpf());
			clienteDto.setDataCadastro(cliente.getDataCadastro());
			clienteDto.setDataNascimento(cliente.getDataNascimento());
			clienteDto.setStatusClienteAtivo(cliente.getStatusClienteAtivo());

			return clienteDto;
		} else {
			return null;
		}
	}

	@Override
	public Cliente getClientePorClienteDto(ClienteDto clienteDto) {
		if (clienteDto != null) {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(clienteDto.getIdCliente());
			cliente.setNome(clienteDto.getNome());
			cliente.setCpf(clienteDto.getCpf());
			cliente.setDataCadastro(clienteDto.getDataCadastro());
			cliente.setDataNascimento(clienteDto.getDataNascimento());
			cliente.setStatusClienteAtivo(clienteDto.getStatusClienteAtivo());

			return cliente;
		} else {
			return null;
		}
	}

	@Override
	public void validarClienteDtoParaCadastro(ClienteDto clienteDto) throws RequestInvalidoException {
		Set<ConstraintViolation<ClienteDto>> listaConstraintViolationErrosValidacao = localValidatorFactoryBean
				.validate(clienteDto, ValidacaoCadastro.class);

		if (listaConstraintViolationErrosValidacao != null && !listaConstraintViolationErrosValidacao.isEmpty()) {
			ErrosRequisicao errosRequisicao = new ErrosRequisicao();

			for (ConstraintViolation<ClienteDto> constraintViolationErroValidacao : listaConstraintViolationErrosValidacao) {
				errosRequisicao.getErros()
						.add(new ErroProcessamento(null, constraintViolationErroValidacao.getMessage()));
			}

			throw new RequestInvalidoException("Requisição inválida", errosRequisicao, null);
		}
	}

	@Override
	public void validarClienteDtoParaAlteracao(ClienteDto clienteDto) throws RequestInvalidoException {
		Set<ConstraintViolation<ClienteDto>> listaConstraintViolationErrosValidacao = localValidatorFactoryBean
				.validate(clienteDto, ValidacaoAlteracao.class);

		if (listaConstraintViolationErrosValidacao != null && !listaConstraintViolationErrosValidacao.isEmpty()) {
			ErrosRequisicao errosRequisicao = new ErrosRequisicao();

			for (ConstraintViolation<ClienteDto> constraintViolationErroValidacao : listaConstraintViolationErrosValidacao) {
				errosRequisicao.getErros()
						.add(new ErroProcessamento(null, constraintViolationErroValidacao.getMessage()));
			}

			throw new RequestInvalidoException("Requisição inválida", errosRequisicao, null);
		}
	}
}