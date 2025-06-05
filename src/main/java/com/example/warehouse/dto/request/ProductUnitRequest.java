package com.example.warehouse.dto.request;

import com.example.warehouse.entity.InBoundBatch;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.Product;

public record ProductUnitRequest(
        String location,
        InBoundBatchRequset inBoundBatchRequest
) {
}
