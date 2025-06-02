package com.example.warehouse.controller;

import com.example.warehouse.dto.request.WareHouseRequest;
import com.example.warehouse.dto.response.WareHouseResponse;
import com.example.warehouse.service.WareHouseService;
import com.example.warehouse.utility.ResponseStructure;
import com.example.warehouse.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
public class WareHouseController {

    private final WareHouseService wareHouseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/warehouses/{userId}")
    public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(
            @RequestBody WareHouseRequest wareHouseRequest,
            @PathVariable String userId){
        WareHouseResponse createWareHouse = wareHouseService.createWarehouse(userId,wareHouseRequest);
        return RestResponseBuilder.created("WareHouse Created Successfully",createWareHouse,HttpStatus.CREATED);
    }

}
