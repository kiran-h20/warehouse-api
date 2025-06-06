package com.example.warehouse.serviceimpl;

import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.entity.Client;
import com.example.warehouse.mapper.ClientMapper;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {


    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse registerClient(ClientRequest clientRequest){
       String apiId = UUID.randomUUID().toString();
       String secretKey = Base64.getEncoder().encodeToString(new SecureRandom().generateSeed(32));
       String encodedsecretKey = passwordEncoder.encode(secretKey);

        Client client=new Client();
        client.setPlatformName(clientRequest.platformName());
        client.setEmail(clientRequest.email());
        client.setApiKey(apiId);
        client.setSecreteKey(encodedsecretKey);

        clientRepository.save(client);
        return clientMapper.toResponse(client,secretKey);

    }

}
