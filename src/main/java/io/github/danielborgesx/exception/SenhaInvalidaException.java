package io.github.danielborgesx.exception;

public class SenhaInvalidaException extends RuntimeException {
  public SenhaInvalidaException() {
      super("Senha inválida");
  }
}
