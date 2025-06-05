package com.example.warehouse.controller;


import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.service.InboundShipmentService;
import com.example.warehouse.utility.ResponseStructure;
import com.example.warehouse.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class InboundShipmentController {


    private final InboundShipmentService inboundShipmentService;

    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/receive/shipment/{wareHouseId}")
    public ResponseEntity<ResponseStructure<InboundShipmentResponse>> receiveProductInWareHouse(@RequestBody InboundShipmentRequest request, @PathVariable String wareHouseId){
        System.out.println(request);
        InboundShipmentResponse inBoundShipmentResponse = inboundShipmentService.receiveProductInWareHouse(request,wareHouseId);
        ResponseStructure<InboundShipmentResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Product Added In The WareHouse",inBoundShipmentResponse);
        return new ResponseEntity<ResponseStructure<InboundShipmentResponse>>(responseStructure,HttpStatus.CREATED);
    }
    
}
