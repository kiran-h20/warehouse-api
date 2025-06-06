package com.example.warehouse.dto.request;

import com.example.warehouse.entity.Admin;
import com.example.warehouse.entity.User;

import java.util.List;

public record WareHouseRequest(
        String warehousename,
        String city,
        String landmark,
        String address

) {
}
