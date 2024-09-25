package br.com.empresa.vendapro.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.empresa.vendapro.dao.PedidoDao;
import br.com.empresa.vendapro.dto.ErroProcessamento;
import br.com.empresa.vendapro.dto.ErrosRequisicao;
import br.com.empresa.vendapro.dto.ItenPedidoDto;
import br.com.empresa.vendapro.dto.PedidoDto;
import br.com.empresa.vendapro.enuns.StatusPedido;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;
import br.com.empresa.vendapro.model.Pedido;
import br.com.empresa.vendapro.model.ProdutoPedido;
import br.com.empresa.vendapro.validacao.ValidacaoCadastroPedido;
import jakarta.validation.ConstraintViolation;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDao pedidoDao;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	@Qualifier("localValidatorFactoryBeanPadrao")
	private LocalValidatorFactoryBean localValidatorFactoryBean;

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pedido> carregarTodosPedidos() {
		return pedidoDao.carregarTodosPedidos();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Pedido consultarPedidoPorId(Long idPedido) {
		return pedidoDao.consultarPedidoPorId(idPedido);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido salvarPedido(Pedido pedido) {
		pedido.setDataPedido(new Date());
		pedido.setStatusPedido(StatusPedido.AGUARDANDO_PAGAMENTO);

		calcularValorPedido(pedido);

		return pedidoDao.salvarPedido(pedido);
	}

	public void calcularValorPedido(Pedido pedido) {
		BigDecimal valorPedido = new BigDecimal("0");
		for (ProdutoPedido produtoPedido : pedido.getListaProdutoPedido()) {
			produtoPedido.setValorUnitario(produtoPedido.getProduto().getPreco());

			valorPedido = valorPedido.add(produtoPedido.getValorUnitario().multiply(produtoPedido.getQuantidade()));
		}

		pedido.setValor(valorPedido);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void alterarPedido(Pedido pedido) {
		pedidoDao.alterarPedido(pedido);
	}

	@Override
	public List<PedidoDto> getListaPedidoDtoPorPedido(List<Pedido> listaPedido) {
		if (listaPedido == null || listaPedido.size() == 0) {
			return null;
		} else {
			List<PedidoDto> listaPedidoDto = new ArrayList();

			for (Pedido pedido : listaPedido) {
				PedidoDto pedidoDto = getPedidoDtoPorPedido(pedido);
				listaPedidoDto.add(pedidoDto);
			}

			return listaPedidoDto;
		}
	}

	@Override
	public PedidoDto getPedidoDtoPorPedido(Pedido pedido) {
		if (pedido != null) {
			PedidoDto pedidoDto = new PedidoDto();
			pedidoDto.setIdPedido(pedido.getIdPedido());
			pedidoDto.setIdCliente(pedido.getCliente().getIdCliente());
			pedidoDto.setDataPedido(pedido.getDataPedido());
			pedidoDto.setValor(pedido.getValor());
			pedidoDto.setStatusPedido(pedido.getStatusPedido());

			if (pedido.getListaProdutoPedido() != null && pedido.getListaProdutoPedido().size() > 0) {
				for (ProdutoPedido produtoPedido : pedido.getListaProdutoPedido()) {
					ItenPedidoDto itenPedidoDto = new ItenPedidoDto();
					itenPedidoDto.setProduto(produtoService.getProdutoDtoPorProduto(produtoPedido.getProduto()));
					itenPedidoDto.setQuantidade(produtoPedido.getQuantidade());
					itenPedidoDto.setValorUnitario(produtoPedido.getValorUnitario());

					pedidoDto.getListaItenPedido().add(itenPedidoDto);
				}
			}

			return pedidoDto;
		} else {
			return null;
		}
	}

	@Override
	public Pedido getPedidoPorPedidoDto(PedidoDto pedidoDto) {
		if (pedidoDto != null) {
			Pedido pedido = new Pedido();
			pedido.setIdPedido(pedidoDto.getIdPedido());
			pedido.setCliente(clienteService.consultarClientePorId(pedidoDto.getIdCliente(), false));
			pedido.setDataPedido(pedidoDto.getDataPedido());
			pedido.setStatusPedido(pedidoDto.getStatusPedido());
			pedido.setValor(pedidoDto.getValor());

			if (pedidoDto.getListaItenPedido() != null && pedidoDto.getListaItenPedido().size() > 0) {
				for (ItenPedidoDto itenPedidoDto : pedidoDto.getListaItenPedido()) {
					ProdutoPedido produtoPedido = new ProdutoPedido();
					produtoPedido.setPedido(pedido);
					produtoPedido.setProduto(
							produtoService.consultarProdutoPorId(itenPedidoDto.getProduto().getIdProduto()));
					produtoPedido.setQuantidade(itenPedidoDto.getQuantidade());
					produtoPedido.setValorUnitario(itenPedidoDto.getValorUnitario());

					pedido.getListaProdutoPedido().add(produtoPedido);
				}
			}

			return pedido;
		} else {
			return null;
		}
	}

	@Override
	public void validarPedidoDtoParaCadastro(PedidoDto pedidoDto) throws RequestInvalidoException {
		Set<ConstraintViolation<PedidoDto>> listaConstraintViolationErrosValidacao = localValidatorFactoryBean
				.validate(pedidoDto, ValidacaoCadastroPedido.class);

		if (listaConstraintViolationErrosValidacao != null && !listaConstraintViolationErrosValidacao.isEmpty()) {
			ErrosRequisicao errosRequisicao = new ErrosRequisicao();

			for (ConstraintViolation<PedidoDto> constraintViolationErroValidacao : listaConstraintViolationErrosValidacao) {
				errosRequisicao.getErros()
						.add(new ErroProcessamento(null, constraintViolationErroValidacao.getMessage()));
			}

			throw new RequestInvalidoException("Requisição inválida", errosRequisicao, null);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void alterarStatusPedido(Long idPedido, StatusPedido statusPedido) throws RegistroNaoEncontradoException {

		pedidoDao.alterarStatusPedido(idPedido, statusPedido);
	}
}
