package com.example.warehouse.controller;


import com.example.warehouse.dto.request.BlockRequest;
import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.BlockResponse;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.service.RackService;
import com.example.warehouse.utility.ResponseStructure;
import com.example.warehouse.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class RackController {

    private final RackService rackService;


    @PostMapping("rack/{userId}")
    public ResponseEntity<ResponseStructure<RackResponse>> addRoom(@RequestBody RackRequest rackRequest,@PathVariable String userId){
        RackResponse rackResponse=rackService.createRoom(rackRequest,userId);
        return RestResponseBuilder.created("Rack Created Successfully",rackResponse, HttpStatus.CREATED);
    }

    @GetMapping("rack/barcode/{rackId}")
    public ResponseEntity<byte[]> generateRackBarCode(@PathVariable String rackId) {
        byte[] barCode = rackService.generateQRCodeImage(rackId);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(barCode);
    }

}
