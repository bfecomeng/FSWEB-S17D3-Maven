package com.workintech.zoo.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ZooException extends RuntimeException {
    private HttpStatus httpStatus;

    public ZooException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ZooException(String message) {
        super(message);
    }
}