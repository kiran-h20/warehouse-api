package com.example.warehouse.service;

import com.example.warehouse.dto.request.WareHouseRequest;
import com.example.warehouse.dto.response.WareHouseResponse;

public interface WareHouseService {


    WareHouseResponse createWarehouse(String userId, WareHouseRequest wareHouseRequest);
}
