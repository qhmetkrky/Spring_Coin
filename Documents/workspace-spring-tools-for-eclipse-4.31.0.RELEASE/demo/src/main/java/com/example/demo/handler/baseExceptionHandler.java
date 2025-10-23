package com.example.demo.handler;


import com.example.demo.dto.dtoerror;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class baseExceptionHandler {

    private final dtoerror dtoerror;

    public baseExceptionHandler(dtoerror dtoerror) {
        this.dtoerror = dtoerror;
    }

    private List<String> getlist(List<String> e,String new_error){
        e.add(new_error);
        return e;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<dtoerror> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();

        for(ObjectError objectError:ex.getBindingResult().getAllErrors()){

            errors.add(objectError.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new dtoerror(errors));
    }

    private <T> errormethod<T> errormethod(T errors){
        errormethod<T> errormethod = new errormethod();

        errormethod.setMessage(errors);
        return errormethod;
    }
}
