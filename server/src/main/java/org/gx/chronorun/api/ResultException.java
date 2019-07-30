package org.gx.chronorun.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResultException extends RuntimeException {

    public ResultException(String message) {
        super(message);
    }
}
