package com.example.warehouse.dto.response;

import com.example.warehouse.enums.Status;

public record InboundShipmentResponse(
        String shipmentId,
        String sellerId,
        Status status,
        int quantity,
        long createdAt,
        ProductResponse productResponse
) {
}
