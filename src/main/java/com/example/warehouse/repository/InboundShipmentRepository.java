package com.example.warehouse.repository;

import com.example.warehouse.entity.InboundShipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundShipmentRepository extends JpaRepository<InboundShipment,String> {
}
