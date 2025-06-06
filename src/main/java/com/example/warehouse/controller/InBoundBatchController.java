package com.example.warehouse.controller;


import com.example.warehouse.dto.request.InBoundBatchRequset;
import com.example.warehouse.dto.response.InBoundBatchResponse;
import com.example.warehouse.service.InBoundBatchService;
import com.example.warehouse.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class InBoundBatchController {

    private final InBoundBatchService inBoundBatchService;

    @PostMapping("/batch/{inboundshipmentId}")
    public ResponseEntity<ResponseStructure<InBoundBatchResponse>>
    receiveProductInWareHouse(@RequestBody InBoundBatchRequset request, @PathVariable String inboundshipmentId){

        InBoundBatchResponse inBoundBatchResponse = inBoundBatchService.createBatch(request,inboundshipmentId);
        ResponseStructure<InBoundBatchResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Product Added In The WareHouse", inBoundBatchResponse);
        return new ResponseEntity<ResponseStructure<InBoundBatchResponse>>(responseStructure,HttpStatus.CREATED);
    }



    @GetMapping("product/qrcode/{unit}")
    public ResponseEntity<byte[]> generateProductQrCode(@PathVariable String unit) {
        byte[] barCode = inBoundBatchService.generateQRCodeImage(unit);
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(barCode);
    }





}
