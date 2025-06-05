package com.example.warehouse.dto.request;

public record ProductRequest(
        String productId,
        String titel,
        double weight,
        double length,
        double width,
        double height,
        String type,
        String caseInst,
        int quatity,
        double price
) {
}
