package com.example.warehouse.entity;

import com.example.warehouse.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "InboundShipment")
public class InboundShipment {


    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse wareHouse;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "shipment_id",nullable = false,updatable = false)
    private String shipmentId;

    @Column(name = "seller_id",nullable = false)
    private String sellerId;

    @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private Instant createdAt;

    @Column(name = "quantity",nullable = false,updatable = false)
    private int quantity;

    @Column(name = "status",nullable = false)
    private Status status;

//    @OneToMany(mappedBy = "inboundShipment")
//    private List<ProductUnit> productUnits;


}
