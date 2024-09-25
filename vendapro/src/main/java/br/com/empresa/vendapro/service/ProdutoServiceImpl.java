package br.com.empresa.vendapro.service;

import java.util.ArrayList;
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

import br.com.empresa.vendapro.dao.ProdutoDao;
import br.com.empresa.vendapro.dto.ErroProcessamento;
import br.com.empresa.vendapro.dto.ErrosRequisicao;
import br.com.empresa.vendapro.dto.ProdutoDto;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Produto;
import br.com.empresa.vendapro.validacao.ValidacaoAlteracao;
import br.com.empresa.vendapro.validacao.ValidacaoCadastro;
import jakarta.validation.ConstraintViolation;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private static final Log logger = LogFactory.getLog(ProdutoServiceImpl.class);

	@Autowired
	private ProdutoDao produtoDao;

	@Autowired
	@Qualifier("localValidatorFactoryBeanPadrao")
	private LocalValidatorFactoryBean localValidatorFactoryBean;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Produto> carregarTodosProdutos() {
		try {
			return produtoDao.carregarTodosProdutos();
		} catch (Exception e) {
			logger.error("Erro ao consultar todos os produtos", e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Produto consultarProdutoPorId(Long idProduto) {
		try {
			return produtoDao.consultarProdutoPorId(idProduto);
		} catch (Exception e) {
			logger.error("Erro ao consultar o produto por ID. ID_PRODUTO " + idProduto, e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Produto salvarProduto(Produto produto) {
		try {
			produtoDao.salvarProduto(produto);
			return produto;
		} catch (Exception e) {
			logger.error("Erro ao cadastrar o produto. NOME: " + produto.getNome(), e);

			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Produto alterarProduto(Produto produto) throws RegistroNaoEncontradoException {
		try {
			produtoDao.alterarProduto(produto);
			return produto;
		} catch (RegistroNaoEncontradoException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Erro ao alterar o produto. ID_PRODUTO " + produto.getIdProduto(), e);

			throw e;
		}
	}

	@Override
	public List<ProdutoDto> getListaProdutoDtoPorProduto(List<Produto> listaProduto) {
		if (listaProduto == null || listaProduto.size() == 0) {
			return null;
		} else {
			List<ProdutoDto> listaProdutoDto = new ArrayList();

			for (Produto produto : listaProduto) {
				ProdutoDto produtoDto = getProdutoDtoPorProduto(produto);
				listaProdutoDto.add(produtoDto);
			}

			return listaProdutoDto;
		}
	}

	@Override
	public ProdutoDto getProdutoDtoPorProduto(Produto produto) {
		if (produto != null) {
			ProdutoDto produtoDto = new ProdutoDto();
			produtoDto.setIdProduto(produto.getIdProduto());
			produtoDto.setNome(produto.getNome());
			produtoDto.setPreco(produto.getPreco());

			return produtoDto;
		} else {
			return null;
		}
	}

	@Override
	public Produto getProdutoPorProdutoDto(ProdutoDto produtoDto) {
		if (produtoDto != null) {
			Produto produto = new Produto();
			produto.setIdProduto(produtoDto.getIdProduto());
			produto.setNome(produtoDto.getNome());
			produto.setPreco(produtoDto.getPreco());

			return produto;
		} else {
			return null;
		}
	}

	@Override
	public void validarProdutoDtoParaCadastro(ProdutoDto produtoDto) throws RequestInvalidoException {
		Set<ConstraintViolation<ProdutoDto>> listaConstraintViolationErrosValidacao = localValidatorFactoryBean
				.validate(produtoDto, ValidacaoCadastro.class);

		if (listaConstraintViolationErrosValidacao != null && !listaConstraintViolationErrosValidacao.isEmpty()) {
			ErrosRequisicao errosRequisicao = new ErrosRequisicao();

			for (ConstraintViolation<ProdutoDto> constraintViolationErroValidacao : listaConstraintViolationErrosValidacao) {
				errosRequisicao.getErros()
						.add(new ErroProcessamento(null, constraintViolationErroValidacao.getMessage()));
			}

			throw new RequestInvalidoException("Requisição inválida", errosRequisicao, null);
		}
	}

	@Override
	public void validarProdutoDtoParaAlteracao(ProdutoDto produtoDto) throws RequestInvalidoException {
		Set<ConstraintViolation<ProdutoDto>> listaConstraintViolationErrosValidacao = localValidatorFactoryBean
				.validate(produtoDto, ValidacaoAlteracao.class);

		if (listaConstraintViolationErrosValidacao != null && !listaConstraintViolationErrosValidacao.isEmpty()) {
			ErrosRequisicao errosRequisicao = new ErrosRequisicao();

			for (ConstraintViolation<ProdutoDto> constraintViolationErroValidacao : listaConstraintViolationErrosValidacao) {
				errosRequisicao.getErros()
						.add(new ErroProcessamento(null, constraintViolationErroValidacao.getMessage()));
			}

			throw new RequestInvalidoException("Requisição inválida", errosRequisicao, null);
		}
	}
}
