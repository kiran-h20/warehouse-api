package com.example.warehouse.dto.request;

import com.example.warehouse.entity.Product;
import com.example.warehouse.enums.Status;

import java.time.Instant;

public record InboundShipmentRequest(
        String sellerId,
       ProductRequest productRequest,
       int quantity,
        Status status
) {
}
