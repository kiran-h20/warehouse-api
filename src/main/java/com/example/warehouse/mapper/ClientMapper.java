package com.example.warehouse.mapper;

import com.example.warehouse.dto.request.BlockRequest;
import com.example.warehouse.dto.request.ClientRequest;
import com.example.warehouse.dto.response.BlockResponse;
import com.example.warehouse.dto.response.ClientResponse;
import com.example.warehouse.entity.Block;
import com.example.warehouse.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequest source, Client target){
        if (source == null) {
            return target;
        }
        if (target == null) {
            target = new Client();
        }
        target.setPlatformName(source.platformName());
        target.setEmail(source.email());
        return target;
    }

    public ClientResponse toResponse(Client client,String rawSecreteKey){
        if (client == null) {
            return null;
        }
        return new ClientResponse(
                client.getClientId(),
                client.getPlatformName(),
                client.getEmail(),
                client.getApiKey(),
                rawSecreteKey,
                client.getRegisteredAt().toEpochMilli()
        );
    }

}
