package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class InBoundBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "batch_id",nullable = false,updatable = false)
    private String batchId;

    @Column(name = "rejected",nullable = false)
    private int countOfRejectedUnits;

    @Column(name = "accepted",nullable = false)
    private int countOfAcceptedUnits;

    @OneToMany(mappedBy = "inBoundBatch")
    private List<ProductUnit> productUnits;




}
