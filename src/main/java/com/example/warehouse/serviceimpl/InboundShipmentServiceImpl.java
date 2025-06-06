package com.example.warehouse.serviceimpl;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.WareHouse;
import com.example.warehouse.exception.WareHouseNotFindByIdException;
import com.example.warehouse.mapper.InboundShipmentMapper;
import com.example.warehouse.repository.InboundShipmentRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.WareHouseRepository;
import com.example.warehouse.service.InboundShipmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InboundShipmentServiceImpl implements InboundShipmentService {



    private final InboundShipmentRepository inBoundShipmentRepository;
    private final InboundShipmentMapper inBoundShipmentMapper;
    private final WareHouseRepository wareHouseRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public InboundShipmentResponse receiveProductInWareHouse(InboundShipmentRequest request, String wareHouseId) {
        Product product = productRepository.findById(request.productRequest().productId())
                .orElse(null);

        if (product == null) {
            product = inBoundShipmentMapper.productToEntity(request.productRequest());
            product = productRepository.save(product);
        }

        WareHouse warehouse = wareHouseRepository.findById(wareHouseId)
                .orElseThrow(() -> new WareHouseNotFindByIdException("Warehouse not found!"));

        InboundShipment inBoundShipment = inBoundShipmentMapper.toEntity(request, new InboundShipment());
        inBoundShipment.setProduct(product);
        inBoundShipment.setWareHouse(warehouse);
        inBoundShipmentRepository.save(inBoundShipment);
        return inBoundShipmentMapper.toResponse(inBoundShipment);
    }
}
