package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "warehouse")
public class WareHouse {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "warehouse_id", nullable = false, updatable = false)
    private String warehouseId;

    @Column(name = "warehousename", nullable = false)
    private String warehousename;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "landmark",nullable = false)
    private String landMark;

    @Column(name = "address",nullable = false)
    private String address;

    @OneToMany(mappedBy = "warehouse")
    private List<Staff> staffs;

    @OneToMany(mappedBy = "warehouse")
    private List<Admin> admins;
}
