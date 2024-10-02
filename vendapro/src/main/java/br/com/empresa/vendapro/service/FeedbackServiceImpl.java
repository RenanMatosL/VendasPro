/*
 * package br.com.empresa.vendapro.service;
 * 
 * import java.util.ArrayList; import java.util.List; import java.util.Set;
 * 
 * import org.apache.commons.logging.Log; import
 * org.apache.commons.logging.LogFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.transaction.annotation.Propagation; import
 * org.springframework.transaction.annotation.Transactional; import
 * org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
 * 
 * import br.com.empresa.vendapro.dao.FeedbackDao; import
 * br.com.empresa.vendapro.dto.ErroProcessamento; import
 * br.com.empresa.vendapro.dto.ErrosRequisicao; import
 * br.com.empresa.vendapro.dto.FeedbackDto; import
 * br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException; import
 * br.com.empresa.vendapro.excecoes.RequestInvalidoException; import
 * br.com.empresa.vendapro.model.Feedback; import
 * br.com.empresa.vendapro.validacao.ValidacaoAlteracao; import
 * br.com.empresa.vendapro.validacao.ValidacaoCadastro; import
 * jakarta.validation.ConstraintViolation;
 * 
 * public class FeedbackServiceImpl {
 * 
 * 
 * private static final Log logger =
 * LogFactory.getLog(FeedbackServiceImpl.class);
 * 
 * @Autowired private FeedbackDao feedbackDao;
 * 
 * @Autowired
 * 
 * @Qualifier("localValidatorFactoryBeanPadrao") private
 * LocalValidatorFactoryBean localValidatorFactoryBean;
 * 
 * @Transactional(propagation = Propagation.SUPPORTS)
 * 
 * @Override public List<Feedback> carregarTodosFeedbacks() { try { return
 * feedbackDao.carregarTodosFeedbacks(); } catch (Exception e) {
 * logger.error("Erro ao consultar todos os feedbacks", e);
 * 
 * throw e; } }
 * 
 * @Transactional(propagation = Propagation.SUPPORTS)
 * 
 * @Override public Feedback consultarFeedbackPorId(Long idFeedback) { try {
 * return feedbackDao.consultarFeedbackPorId(idFeedback); } catch (Exception e)
 * { logger.error("Erro ao consultar o feedback por ID. ID_PRODUTO " +
 * idFeedback, e);
 * 
 * throw e; } }
 * 
 * @Transactional(propagation = Propagation.REQUIRED)
 * 
 * @Override public Feedback salvarFeedback(Feedback feedback) { try {
 * feedbackDao.salvarFeedback(feedback); return feedback; } catch (Exception e)
 * { logger.error("Erro ao cadastrar o feedback. NOME: " + feedback.getNome(),
 * e);
 * 
 * throw e; } }
 * 
 * @Transactional(propagation = Propagation.REQUIRED)
 * 
 * @Override public Feedback alterarFeedback(Feedback feedback) throws
 * RegistroNaoEncontradoException { try { feedbackDao.alterarFeedback(feedback);
 * return feedback; } catch (RegistroNaoEncontradoException e) { throw e; }
 * catch (Exception e) { logger.error("Erro ao alterar o feedback. ID_PRODUTO "
 * + feedback.getIdFeedback(), e);
 * 
 * throw e; } }
 * 
 * @Override public List<FeedbackDto>
 * getListaFeedbackDtoPorFeedback(List<Feedback> listaFeedback) { if
 * (listaFeedback == null || listaFeedback.size() == 0) { return null; } else {
 * List<FeedbackDto> listaFeedbackDto = new ArrayList();
 * 
 * for (Feedback feedback : listaFeedback) { FeedbackDto feedbackDto =
 * getFeedbackDtoPorFeedback(feedback); listaFeedbackDto.add(feedbackDto); }
 * 
 * return listaFeedbackDto; } }
 * 
 * @Override public FeedbackDto getFeedbackDtoPorFeedback(Feedback feedback) {
 * if (feedback != null) { FeedbackDto feedbackDto = new FeedbackDto();
 * feedbackDto.setIdFeedback(feedback.getIdFeedback());
 * feedbackDto.setNome(feedback.getNome());
 * feedbackDto.setPreco(feedback.getPreco());
 * 
 * return feedbackDto; } else { return null; } }
 * 
 * @Override public Feedback getFeedbackPorFeedbackDto(FeedbackDto feedbackDto)
 * { if (feedbackDto != null) { Feedback feedback = new Feedback();
 * feedback.setIdFeedback(feedbackDto.getIdFeedback());
 * feedback.setNome(feedbackDto.getNome());
 * feedback.setPreco(feedbackDto.getPreco());
 * 
 * return feedback; } else { return null; } }
 * 
 * @Override public void validarFeedbackDtoParaCadastro(FeedbackDto feedbackDto)
 * throws RequestInvalidoException { Set<ConstraintViolation<FeedbackDto>>
 * listaConstraintViolationErrosValidacao = localValidatorFactoryBean
 * .validate(feedbackDto, ValidacaoCadastro.class);
 * 
 * if (listaConstraintViolationErrosValidacao != null &&
 * !listaConstraintViolationErrosValidacao.isEmpty()) { ErrosRequisicao
 * errosRequisicao = new ErrosRequisicao();
 * 
 * for (ConstraintViolation<FeedbackDto> constraintViolationErroValidacao :
 * listaConstraintViolationErrosValidacao) { errosRequisicao.getErros() .add(new
 * ErroProcessamento(null, constraintViolationErroValidacao.getMessage())); }
 * 
 * throw new RequestInvalidoException("Requisição inválida", errosRequisicao,
 * null); } }
 * 
 * @Override public void validarFeedbackDtoParaAlteracao(FeedbackDto
 * feedbackDto) throws RequestInvalidoException {
 * Set<ConstraintViolation<FeedbackDto>> listaConstraintViolationErrosValidacao
 * = localValidatorFactoryBean .validate(feedbackDto, ValidacaoAlteracao.class);
 * 
 * if (listaConstraintViolationErrosValidacao != null &&
 * !listaConstraintViolationErrosValidacao.isEmpty()) { ErrosRequisicao
 * errosRequisicao = new ErrosRequisicao();
 * 
 * for (ConstraintViolation<FeedbackDto> constraintViolationErroValidacao :
 * listaConstraintViolationErrosValidacao) { errosRequisicao.getErros() .add(new
 * ErroProcessamento(null, constraintViolationErroValidacao.getMessage())); }
 * 
 * throw new RequestInvalidoException("Requisição inválida", errosRequisicao,
 * null); } } }
 */