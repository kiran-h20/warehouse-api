package com.example.warehouse.utility;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T>{
    int status;
    String message;
    T data;
}
