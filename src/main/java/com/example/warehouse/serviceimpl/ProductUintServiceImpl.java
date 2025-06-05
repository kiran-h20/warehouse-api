package com.example.warehouse.serviceimpl;

import com.example.warehouse.dto.request.InventoryLocationUpdateRequest;
import com.example.warehouse.dto.response.ProductUnitResponse;
import com.example.warehouse.entity.ProductUnit;
import com.example.warehouse.mapper.ProductUnitMapper;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.ProductUnitRepository;
import com.example.warehouse.service.ProductUintService;
import lombok.AllArgsConstructor;
import org.springframework.expression.spel.ast.StringLiteral;

import java.util.List;

@AllArgsConstructor
public class ProductUintServiceImpl implements ProductUintService {

    private final ProductUnitRepository productUnitRepository;
    private final ProductUnitMapper productUnitMapper;
    private final BlockRepository blockRepository;

    @Override
    public ProductUnitResponse updateLocation(InventoryLocationUpdateRequest updateRequest) {

        List<ProductUnit> productUnits=productUnitRepository.findAllById(updateRequest.unitId());

        return null;
    }
}
