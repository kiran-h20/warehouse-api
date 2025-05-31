package com.example.warehouse.dto.response;

public record WareHouseResponse(
        String warehouseid,
        String warehousename,
        String city,
        String landmark,
        String address
) {
}
