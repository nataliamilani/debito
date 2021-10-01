package com.impacta.microservices.debito.demo.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaIdNotFoundException extends RuntimeException {

    public ContaIdNotFoundException(String msg) {
        super(msg);
    }
}
