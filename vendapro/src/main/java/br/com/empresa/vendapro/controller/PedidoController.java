package br.com.empresa.vendapro.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.vendapro.dto.PedidoDto;
import br.com.empresa.vendapro.excecoes.ErroGenericoException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Pedido;
import br.com.empresa.vendapro.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

	private static final Log logger = LogFactory.getLog(PedidoController.class);

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/todosPedidos", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	public ResponseEntity<List<PedidoDto>> recuperarTodosPedidos() throws ErroGenericoException {
		try {
			List<Pedido> listaPedidos = pedidoService.carregarTodosPedidos();

			if (listaPedidos != null && listaPedidos.size() > 0) {
				List<PedidoDto> listaPedidoDto = pedidoService.getListaPedidoDtoPorPedido(listaPedidos);

				return new ResponseEntity<List<PedidoDto>>(listaPedidoDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<PedidoDto>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Erro ao recuperar os pedidos: " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public ResponseEntity<PedidoDto> getPedidoPorID(@PathVariable("id") long idPedido) throws ErroGenericoException {
		try {
			Pedido pedido = pedidoService.consultarPedidoPorId(idPedido);

			if (pedido != null) {
				PedidoDto pedidoDto = pedidoService.getPedidoDtoPorPedido(pedido);

				return new ResponseEntity<PedidoDto>(pedidoDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<PedidoDto>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Erro ao recuperar o pedido por idPedido " + idPedido + ": " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = {
			"application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
	public ResponseEntity<Void> salvarPedido(@RequestBody PedidoDto pedidoDto,
			UriComponentsBuilder uriComponentsBuilder) throws RequestInvalidoException, ErroGenericoException {
		try {
			pedidoService.validarPedidoDtoParaCadastro(pedidoDto);

			Pedido pedido = pedidoService.getPedidoPorPedidoDto(pedidoDto);

			pedido = pedidoService.salvarPedido(pedido);

			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.setLocation(
					uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getIdPedido()).toUri());

			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		} catch (RequestInvalidoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao salvar o pedido (" + pedidoDto.toString() + "): " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/alterarStatusPedido", method = RequestMethod.PUT, produces = {
			"application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
	public ResponseEntity<Void> alterarStatusPedido(@RequestBody PedidoDto pedidoDto) throws Exception {
		try {
			pedidoService.alterarStatusPedido(pedidoDto.getIdPedido(), pedidoDto.getStatusPedido());

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao alterar o pedido (" + pedidoDto.toString() + "): " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}
}