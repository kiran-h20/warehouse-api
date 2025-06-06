package com.example.warehouse.service;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.response.RackResponse;

public interface InboundShipmentService {

    InboundShipmentResponse receiveProductInWareHouse(InboundShipmentRequest request, String wareHouseId);

}
