package br.com.empresa.vendapro.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.vendapro.dto.ClienteDto;
import br.com.empresa.vendapro.enuns.StatusClienteAtivo;
import br.com.empresa.vendapro.excecoes.ErroGenericoException;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Cliente;
import br.com.empresa.vendapro.service.ClienteService;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private static final Log logger = LogFactory.getLog(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/todosClientes", method = RequestMethod.GET, produces = {"application/json; charset=utf-8" })
	public ResponseEntity<List<ClienteDto>> recuperarTodosClientes() throws ErroGenericoException {
		try {
			List<Cliente> listaClientes = clienteService.carregarTodosClientes(false);

			if (listaClientes != null && listaClientes.size() > 0) {
				List<ClienteDto> listaClienteDto = clienteService.getListaClienteDtoPorCliente(listaClientes);

				return new ResponseEntity<List<ClienteDto>>(listaClienteDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ClienteDto>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Erro ao recuperar os clientes: " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public ResponseEntity<ClienteDto> getClientePorID(@PathVariable("id") long idCliente) throws ErroGenericoException {
		try {
			Cliente cliente = clienteService.consultarClientePorId(idCliente, false);

			if (cliente != null) {
				ClienteDto clienteDto = clienteService.getClienteDtoPorCliente(cliente);

				return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<ClienteDto>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Erro ao recuperar o cliente por idCliente " + idCliente + ": " + e.getMessage(), e);
			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = {
			"application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
	public ResponseEntity<Void> salvarCliente(@RequestBody @Validated(ValidacaoCadastro.class) ClienteDto clienteDto,
			UriComponentsBuilder uriComponentsBuilder)
			throws RequestInvalidoException, ErroGenericoException, RegistroJaExisteException {
		try {

			Cliente cliente = clienteService.getClientePorClienteDto(clienteDto);

			cliente = clienteService.salvarCliente(cliente);

			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.setLocation(
					uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(cliente.getIdCliente()).toUri());

			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		} catch (RegistroJaExisteException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao salvar o cliente (" + clienteDto.toString() + "): " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = {
			"application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
	public ResponseEntity<Void> alterarCliente(@RequestBody ClienteDto clienteDto) throws Exception {
		try {
			clienteService.validarClienteDtoParaAlteracao(clienteDto);

			Cliente cliente = clienteService.getClientePorClienteDto(clienteDto);

			cliente = clienteService.alterarCliente(cliente);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (RequestInvalidoException | RegistroJaExisteException | RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao alterar o cliente (" + clienteDto.toString() + "): " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json; charset=utf-8" })
	public ResponseEntity<Void> deletarCliente(@PathVariable("id") long idCliente)
			throws ErroGenericoException, RegistroNaoEncontradoException {
		try {
			clienteService.alterarStatusCliente(idCliente, StatusClienteAtivo.INATIVO);

			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao deletar o cliente idCliente " + idCliente + ": " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}
}
