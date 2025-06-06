package com.example.warehouse.dto.request;

public record InBoundBatchRequset(
        int countOfRejectedUnits,
        int countOfAcceptedUnits
) {
}
