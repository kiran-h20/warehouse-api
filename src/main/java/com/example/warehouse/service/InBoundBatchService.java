package com.example.warehouse.service;

import com.example.warehouse.dto.request.InBoundBatchRequset;
import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;
import com.example.warehouse.dto.response.InBoundBatchResponse;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.response.ProductUnitResponse;
import com.example.warehouse.entity.Block;

import java.util.List;

public interface InBoundBatchService {
    InBoundBatchResponse createBatch(InBoundBatchRequset request, String shipmentId);
    List<ProductUnitResponse> updateInventoryLocation(InventoryLocationUpdateRequest request);

    byte[] generateQRCodeImage(String rackId);
}
