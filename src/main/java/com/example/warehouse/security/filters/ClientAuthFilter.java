package com.example.warehouse.security.filters;

import com.example.warehouse.entity.Client;
import com.example.warehouse.enums.UserRole;
import com.example.warehouse.exception.ClientNotFoundByApi;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.security.AuthUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;




@Component
@AllArgsConstructor
public class ClientAuthFilter extends OncePerRequestFilter {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String X_API_KEY ="X-Api-Key";
    private static final String X_SECRET_KEY="X-Secret-Key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String apiKey=request.getHeader(X_API_KEY);
        String secretKey=request.getHeader(X_SECRET_KEY);

        if(isValid(apiKey) &&isValid(secretKey)){
            Client client = clientRepository.findByApiKey(apiKey)
                            .orElseThrow(()->new ClientNotFoundByApi("Client Not Found by api key"));
            var doseMatch = passwordEncoder.matches(secretKey,client.getSecreteKey());

            if(doseMatch && AuthUtils.getAuthentication().isEmpty()){
                var token= new UsernamePasswordAuthenticationToken(client.getPlatformName(),
                        null, List.of(new SimpleGrantedAuthority(UserRole.CLIENT.name())));
                AuthUtils.setAuthentication(token);
            }

        }
        filterChain.doFilter(request,response);

    }
    private boolean isValid(String value) {
        return value != null && !value.isBlank();
    }
}
