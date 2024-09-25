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

import br.com.empresa.vendapro.dto.ProdutoDto;
import br.com.empresa.vendapro.excecoes.ErroGenericoException;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Produto;
import br.com.empresa.vendapro.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private static final Log logger = LogFactory.getLog(ProdutoController.class);

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/todosProdutos", method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })
	public ResponseEntity<List<ProdutoDto>> recuperarTodosProdutos() throws ErroGenericoException {
		try {
			List<Produto> listaProdutos = produtoService.carregarTodosProdutos();

			if (listaProdutos != null && listaProdutos.size() > 0) {
				List<ProdutoDto> listaProdutoDto = produtoService.getListaProdutoDtoPorProduto(listaProdutos);

				return new ResponseEntity<List<ProdutoDto>>(listaProdutoDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<ProdutoDto>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Erro ao recuperar os produtos: " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public ResponseEntity<ProdutoDto> getProdutoPorID(@PathVariable("id") long idProduto) throws ErroGenericoException {
		try {
			Produto produto = produtoService.consultarProdutoPorId(idProduto);

			if (produto != null) {
				ProdutoDto produtoDto = produtoService.getProdutoDtoPorProduto(produto);

				return new ResponseEntity<ProdutoDto>(produtoDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<ProdutoDto>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Erro ao recuperar o produto por idProduto " + idProduto + ": " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(
			// URL
			value = "/"
			// Método HTTP
			, method = RequestMethod.POST, produces = { "application/json; charset=utf-8" }, consumes = {
					"application/json; charset=utf-8" })
	public ResponseEntity<Void> salvarProduto(@RequestBody ProdutoDto produtoDto,
			UriComponentsBuilder uriComponentsBuilder)
			throws RequestInvalidoException, ErroGenericoException, RegistroJaExisteException {
		try {
			produtoService.validarProdutoDtoParaCadastro(produtoDto);

			Produto produto = produtoService.getProdutoPorProdutoDto(produtoDto);

			produto = produtoService.salvarProduto(produto);

			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.setLocation(
					uriComponentsBuilder.path("/produtos/{id}").buildAndExpand(produto.getIdProduto()).toUri());

			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		} catch (RequestInvalidoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao salvar o produto (" + produtoDto.toString() + "): " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = {
			"application/json; charset=utf-8" }, consumes = { "application/json; charset=utf-8" })
	public ResponseEntity<Void> alterarProduto(@RequestBody ProdutoDto produtoDto) throws Exception {
		try {
			produtoService.validarProdutoDtoParaAlteracao(produtoDto);

			Produto produto = produtoService.getProdutoPorProdutoDto(produtoDto);

			produto = produtoService.alterarProduto(produto);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (RequestInvalidoException | RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao alterar o produto (" + produtoDto.toString() + "): " + e.getMessage(), e);

			throw new ErroGenericoException(e.getMessage(), null, e);
		}
	}
}