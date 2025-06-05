package com.example.warehouse.mapper;

import com.example.warehouse.dto.request.InBoundBatchRequset;
import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InBoundBatchResponse;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.entity.InBoundBatch;
import com.example.warehouse.entity.InboundShipment;
import org.springframework.stereotype.Component;

@Component
public class InBoundBatchMapper {


    public InBoundBatch toEntity(InBoundBatchRequset source, InBoundBatch target){
        if (source == null) return null;
        target.setCountOfRejectedUnits(source.countOfRejectedUnits());
        target.setCountOfAcceptedUnits(source.countOfAcceptedUnits());
        return target;
    }

    public InBoundBatchResponse toResponse(InBoundBatch batch){
        if (batch == null) return null;
        return new InBoundBatchResponse(
                batch.getBatchId(),
                batch.getCountOfRejectedUnits(),
                batch.getCountOfAcceptedUnits()
        );
    }
//


}
