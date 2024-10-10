package com.carbigdata.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carbigdata.domain.model.exception.ClienteExistenteException;
import com.carbigdata.domain.model.exception.OcorrenciaFinalizadaException;
import com.carbigdata.domain.model.exception.OcorrenciaInexistenteException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
	= "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request, BindingResult bindingResult) {
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
	    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
	    
	    List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
	    		.map(objectError -> {
	    			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
	    			
	    			String name = objectError.getObjectName();
	    			
	    			if (objectError instanceof FieldError) {
	    				name = ((FieldError) objectError).getField();
	    			}
	    			
	    			return Problem.Object.builder()
	    				.name(name)
	    				.userMessage(message)
	    				.build();
	    		})
	    		.toList();
	    
	    Problem problem = createProblemBuilder(status, problemType, detail)
	        .userMessage(detail)
	        .objects(problemObjects)
	        .build();
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

		ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, 
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", 
				ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(OcorrenciaInexistenteException.class)
	public ResponseEntity<Object> handleOcorrenciaInexistenteException(OcorrenciaInexistenteException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ClienteExistenteException.class)
	public ResponseEntity<Object> handleClienteExistenteException(ClienteExistenteException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(OcorrenciaFinalizadaException.class)
	public ResponseEntity<Object> handleOcorrenciaFinalizadaException(OcorrenciaFinalizadaException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status,
			ProblemType problemType, String detail) {
		
		return Problem.builder()
			.timestamp(OffsetDateTime.now())
			.status(status.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail);
	}

}
