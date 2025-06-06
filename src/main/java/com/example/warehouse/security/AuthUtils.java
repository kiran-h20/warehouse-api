package com.example.warehouse.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuthUtils {

    private AuthUtils(){
        //it is utility cons
    }

    public static Optional<Authentication> getAuthentication(){
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }


    public static Optional<String> getCurrentUserName(){
       return getAuthentication().map(
               auth->auth.getName()
       );
    }

    public static void setAuthentication(Authentication auth){
        Objects.requireNonNull(auth,"Authentication can not be null!!!");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
