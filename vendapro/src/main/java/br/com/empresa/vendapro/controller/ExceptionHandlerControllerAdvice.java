package br.com.empresa.vendapro.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.empresa.vendapro.dto.ErroProcessamento;
import br.com.empresa.vendapro.dto.ErrosRequisicao;
import br.com.empresa.vendapro.excecoes.ErroGenericoException;
import br.com.empresa.vendapro.excecoes.RegistroJaExisteException;
import br.com.empresa.vendapro.excecoes.RegistroNaoEncontradoException;
import br.com.empresa.vendapro.excecoes.RequestInvalidoException;

@ControllerAdvice(basePackages = { "br.com.empresa.vendaspro.controller" })
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ RegistroNaoEncontradoException.class })
	protected ResponseEntity<Object> handlerRegistroNaoEncontradoException(Exception exception, WebRequest webRequest) {

		RegistroNaoEncontradoException registroNaoEncontradoException = (RegistroNaoEncontradoException) exception;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json; charset=utf-8");

		return handleExceptionInternal(exception, registroNaoEncontradoException.getMessage(), httpHeaders,
				HttpStatus.NOT_FOUND, webRequest);
	}

	@ExceptionHandler({ RequestInvalidoException.class })
	protected ResponseEntity<Object> handlerRequestInvalidoException(Exception exception, WebRequest webRequest) {

		RequestInvalidoException requestInvalidoException = (RequestInvalidoException) exception;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json; charset=utf-8");

		return handleExceptionInternal(exception, requestInvalidoException.getErrosRequisicao(), httpHeaders,
				HttpStatus.BAD_REQUEST, webRequest);
	}

	@ExceptionHandler({ RegistroJaExisteException.class })
	protected ResponseEntity<Object> handlerRegistroJaExisteException(Exception exception, WebRequest webRequest) {

		RegistroJaExisteException registroJaExisteException = (RegistroJaExisteException) exception;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json; charset=utf-8");

		return handleExceptionInternal(exception, registroJaExisteException.getMessage(), httpHeaders,
				HttpStatus.CONFLICT, webRequest);
	}

	@ExceptionHandler({ ErroGenericoException.class })
	protected ResponseEntity<Object> handlerGenericExceptionException(Exception exception, WebRequest webRequest) {

		ErroGenericoException erroGenericoException = (ErroGenericoException) exception;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json; charset=utf-8");

		return handleExceptionInternal(exception, "Ocorreu um erro", httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR,
				webRequest);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders httpHeaders,
			HttpStatusCode httpStatusCode, WebRequest webRequest) {
		List<FieldError> listaFieldError = methodArgumentNotValidException.getBindingResult().getFieldErrors();

		ErrosRequisicao errosRequisicao = new ErrosRequisicao();

		for (FieldError fieldError : listaFieldError) {
			errosRequisicao.getErros().add(new ErroProcessamento(null, fieldError.getDefaultMessage()));
		}

		HttpHeaders httpHeadersRetorno = new HttpHeaders();
		httpHeadersRetorno.set("Content-Type", "application/json; charset=utf-8");

		return handleExceptionInternal(methodArgumentNotValidException, errosRequisicao, httpHeadersRetorno,
				HttpStatus.BAD_REQUEST, webRequest);
	}
}