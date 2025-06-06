package com.example.warehouse.dto.response;

public record ProductUnitResponse(
        String unitId,
        String location,
        String InBoundBatchId,
        String InBoundShipmentId,
        String productId
) {
}