package io.github.danielborgesx.rest.controller;

import io.github.danielborgesx.exception.PedidoNaoEncontradoException;
import io.github.danielborgesx.exception.RegraNegocioException;
import io.github.danielborgesx.service.ApiErrors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException exception) {
        String mensagemErro = exception.getMessage();
        return new ApiErrors(mensagemErro);
    }
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundExcpection(PedidoNaoEncontradoException exception) {
        return new ApiErrors(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidExpection(MethodArgumentNotValidException exception) {
        List<String> erros = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(erro ->
                        erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(erros);
    }
}
