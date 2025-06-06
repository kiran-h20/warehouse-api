package com.example.warehouse.mapper;

import com.example.warehouse.dto.request.InboundShipmentRequest;
import com.example.warehouse.dto.request.ProductRequest;
import com.example.warehouse.dto.response.InboundShipmentResponse;
import com.example.warehouse.dto.response.ProductResponse;
import com.example.warehouse.entity.InboundShipment;
import com.example.warehouse.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class InboundShipmentMapper {

    public InboundShipment toEntity(InboundShipmentRequest source, InboundShipment target){
        if (source == null) {
            return target;
        }
        if (target == null) {
            target = new InboundShipment();
        }

        target.setSellerId(source.sellerId());
        target.setProduct(productToEntity(source.productRequest()));
        target.setQuantity(source.quantity());
        target.setStatus(source.status());
        return target;
    }

    public Product productToEntity(ProductRequest productRequest) {

        Product product = new Product();
        product.setProductId(productRequest.productId());
        product.setTitel(productRequest.titel());
        product.setWeight(productRequest.weight());
        product.setLength(productRequest.length());
        product.setHeight(productRequest.height());
        product.setWidth(productRequest.width());
        product.setMeterialType(productRequest.type());
        product.setCareInst(productRequest.caseInst());
        product.setPrice(productRequest.price());

        return product;
    }



    public InboundShipmentResponse toResponse(InboundShipment shipment){

            return new InboundShipmentResponse(
                    shipment.getShipmentId(),
                    shipment.getSellerId(),
                    shipment.getStatus(),
                    shipment.getQuantity(),
                    shipment.getCreatedAt().toEpochMilli(),
                    productToResponse(shipment.getProduct())
            );
        }

    private ProductResponse productToResponse(Product product) {

        return new ProductResponse(
                product.getProductId(),
                product.getTitel(),
                product.getWeight(),
                product.getLength(),
                product.getHeight(),
                product.getWidth(),
                product.getMeterialType(),
                product.getCareInst(),
                product.getPrice()
        );
    }
}
