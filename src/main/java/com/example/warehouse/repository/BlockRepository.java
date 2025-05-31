package com.example.warehouse.repository;

import com.example.warehouse.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,String> {
}
