package com.example.warehouse.utility;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseStructure<T>{
    int status;
    String message;
    T data;
}
