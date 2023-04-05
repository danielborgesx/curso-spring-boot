package io.github.danielborgesx.serivce;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro) {
        this.errors = Collections.singletonList(mensagemErro);
    }
}
