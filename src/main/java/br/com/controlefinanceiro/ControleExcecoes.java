package br.com.controlefinanceiro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class ControleExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ObjetoErro> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError("Erro de validação: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        objetoErro.setCode(HttpStatus.BAD_REQUEST.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ObjetoErro> handleEntityNotFoundException(EntityNotFoundException ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError("Entidade não encontrada: " + ex.getMessage());
        objetoErro.setCode(HttpStatus.NOT_FOUND.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, JpaSystemException.class})
    public ResponseEntity<ObjetoErro> handleDataIntegrityAndJpaExceptions(Exception ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError(MensagemException.tratamentoViolacaoIntegridadeDados(ex));
        objetoErro.setCode(HttpStatus.CONFLICT.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ObjetoErro> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError("Nenhum resultado encontrado: " + ex.getMessage());
        objetoErro.setCode(HttpStatus.NOT_FOUND.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ObjetoErro> handleTransactionSystemException(TransactionSystemException ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError(MensagemException.tratamentoTransacaoSistema(ex));
        objetoErro.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ObjetoErro> handleDeserializationException(HttpMessageNotReadableException ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError(MensagemException.tratamentoMensagemHTTPIlegivel(ex));
        objetoErro.setCode(HttpStatus.BAD_REQUEST.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ObjetoErro> handleGenericException(Exception ex) {
        ObjetoErro objetoErro = new ObjetoErro();
        objetoErro.setError("Erro inesperado: " + ex.getMessage());
        objetoErro.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

		System.out.println("Tipo da exceção: " + ex.getClass().getName());


        return new ResponseEntity<>(objetoErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}