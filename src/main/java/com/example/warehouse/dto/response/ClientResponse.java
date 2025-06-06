package com.example.warehouse.dto.response;

public record ClientResponse(
        String clientId,
        String platformName,
        String email,
        String apiKey,
        String secreteKey,
        long registeredAt
) {
}
