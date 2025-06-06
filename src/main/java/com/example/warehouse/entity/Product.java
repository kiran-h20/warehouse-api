package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id",nullable = false,updatable = false)
    private String productId;

    @Column(name = "titel",nullable = false)
    private String titel;

    @Column(name = "price",nullable = false,updatable = false)
    private double price;

    @Column(name = "meterial_type",nullable = false)
    private String meterialType;

    @Column(name = "care_inst",nullable = false,length = 2000)
    private String careInst;

    @Column(name = "length",nullable = false)
    private double length;

    @Column(name = "breadth",nullable = false)
    private double width;

    @Column(name = "height",nullable = false)
    private double height;

    @Column(name = "weight",nullable = false)
    private double weight;

    @OneToMany
    private List<InboundShipment> shipment;

    @OneToMany(mappedBy = "product")
    private List<ProductUnit> productUnits;

}
