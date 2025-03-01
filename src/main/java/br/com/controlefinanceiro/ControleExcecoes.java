package br.com.controlefinanceiro;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes  extends ResponseEntityExceptionHandler
{
	
	//interceptar erros comuns do projeto
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
	
		String msg = "";
		
		if(ex instanceof MethodArgumentNotValidException)
		{
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for(ObjectError objectError : list)
			{
				msg += objectError.getDefaultMessage();
			}
			
		}
		else
		{
			msg = ex.getMessage();
		}
		
		ObjetoErro objetoErro = new ObjetoErro();
		objetoErro.setError(msg);
		
		
		return new ResponseEntity<>(objetoErro, headers, statusCode);
	}
	
	//interceptar erros de banco de dados
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExcpetionDataIntegry(Exception ex)
	{
	
		String msg = "";
		
		if(ex instanceof DataIntegrityViolationException)
		{
			msg = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();		
		}
		else if (ex instanceof ConstraintViolationException)
		{
			msg = ((ConstraintViolationException) ex).getCause().getCause().getMessage();	
		}
		else if (ex instanceof SQLException)
		{
			msg = ((SQLException) ex).getCause().getCause().getMessage();	
		}	
		else
		{
			msg = ex.getMessage();
		}
		
		
		ObjetoErro objetoErro = new ObjetoErro();
		objetoErro.setError(msg);
		objetoErro.setCode(HttpStatus.INTERNAL_SERVER_ERROR + "==>" + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		
		return new ResponseEntity<>(objetoErro,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	  @ExceptionHandler(MensagemException.class)
	    protected ResponseEntity<Object> handleContaNaoEncontradaException(MensagemException ex) {
	        String msg = ex.getMessage();

	        ObjetoErro objetoErro = new ObjetoErro();
	        objetoErro.setError(msg);
	        objetoErro.setCode(HttpStatus.NOT_FOUND + " ==> " + HttpStatus.NOT_FOUND.getReasonPhrase());

	        return new ResponseEntity<>(objetoErro, HttpStatus.NOT_FOUND);
	    }
	
}
