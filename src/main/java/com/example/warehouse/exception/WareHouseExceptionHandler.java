package com.example.warehouse.exception;

import com.example.warehouse.utility.ErrorStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WareHouseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleIllegalOperation(InvalidUserRole e){
        ErrorStructure errorResponse = new ErrorStructure(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return new ResponseEntity<ErrorStructure>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleWareHouseNotFindById(UserNotFoundException e){
        ErrorStructure errorResponse = new ErrorStructure(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return new ResponseEntity<ErrorStructure>(errorResponse,HttpStatus.NOT_FOUND);
    }

}
