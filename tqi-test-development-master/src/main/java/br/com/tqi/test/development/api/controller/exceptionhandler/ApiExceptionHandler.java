package br.com.tqi.test.development.api.controller.exceptionhandler;

import br.com.tqi.test.development.domain.exception.EntidadeNaoEncontradaException;
import br.com.tqi.test.development.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var problems = new Problems();
        problems.setStatus(status.value());
        problems.setTitulo(ex.getMessage());
        problems.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problems, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var problema = new Problems();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        var fields = new ArrayList<Problems.Fields>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new Problems.Fields(nome, mensagem));
        }

        var problems = new Problems();
        problems.setStatus(status.value());
        problems.setTitulo("Um ou mais campos estão inválidos. "
                + "Faça o preenchimento correto e tente novamente");
        problems.setDataHora(OffsetDateTime.now());
        problems.setFields(fields);

        return super.handleExceptionInternal(ex, problems, headers, status, request);
    }
}
