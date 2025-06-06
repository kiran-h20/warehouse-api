package com.example.warehouse.controller;
import com.example.warehouse.dto.request.BlockRequest;
import com.example.warehouse.dto.request.RoomRequest;
import com.example.warehouse.dto.response.BlockResponse;
import com.example.warehouse.dto.response.RoomResponse;

import com.example.warehouse.service.BlockService;

import com.example.warehouse.utility.ResponseStructure;
import com.example.warehouse.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BlockController {


    private final BlockService blockService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("blocks/{room}")
    public ResponseEntity<ResponseStructure<BlockResponse>> addRoom(@RequestBody BlockRequest blockRequest, @PathVariable String room){
        BlockResponse blockResponse=blockService.creatBlock(blockRequest,room);
        return RestResponseBuilder.created("Block Created Successfully",blockResponse, HttpStatus.CREATED);
    }
}