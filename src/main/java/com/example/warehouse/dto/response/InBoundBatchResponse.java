package com.example.warehouse.dto.response;

public record InBoundBatchResponse(
        String batchId,
        int countOfRejectedUnits,
        int countOfAcceptedUnits
) {
}
