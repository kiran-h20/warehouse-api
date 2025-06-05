package com.example.warehouse.exception.handler;

import com.example.warehouse.exception.InvalidUserRole;
import com.example.warehouse.exception.UserAlreadyExistException;
import com.example.warehouse.exception.UserNotFoundException;
import com.example.warehouse.exception.WareHouseNotFindByIdException;
import com.example.warehouse.utility.ErrorStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleWareHouseNotFindById(WareHouseNotFindByIdException e){
        ErrorStructure errorResponse = new ErrorStructure(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return new ResponseEntity<ErrorStructure>(errorResponse,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now().toString());
        error.put("error", ex.getClass().getSimpleName());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorStructure> handleUserAlreadyExist(UserAlreadyExistException ex) {
        ErrorStructure errorResponse = new ErrorStructure(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<ErrorStructure>(errorResponse,HttpStatus.NOT_FOUND);
    }


}
