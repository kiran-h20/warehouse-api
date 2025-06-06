package com.example.warehouse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product_unit")
public class ProductUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "unit_id",nullable = false,updatable = false)
    private String unitId;

    @ManyToOne
    @JoinColumn(name = "shipment_id",nullable = false)
    private InboundShipment inboundShipment;

    @ManyToOne
    @JoinColumn(name = "inbound_batch_id")
    private InBoundBatch inBoundBatch;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
}
