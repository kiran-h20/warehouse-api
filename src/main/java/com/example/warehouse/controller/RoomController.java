package com.example.warehouse.controller;

import com.example.warehouse.dto.request.RoomRequest;
import com.example.warehouse.dto.response.RoomResponse;
import com.example.warehouse.service.RoomService;
import com.example.warehouse.utility.ResponseStructure;
import com.example.warehouse.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController

public class RoomController {

    private final RoomService roomService;


    @PostMapping("/rooms/{warehouseId}")
    public ResponseEntity<ResponseStructure<RoomResponse>> addRoom(@RequestBody RoomRequest roomRequest, @PathVariable String warehouseId){
        RoomResponse roomResponse=roomService.creatRoom(roomRequest,warehouseId);
        return RestResponseBuilder.created("Room Created Successfully",roomResponse, HttpStatus.CREATED);
    }



}
