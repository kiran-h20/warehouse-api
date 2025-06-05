package com.example.warehouse.entity;

import com.example.warehouse.enums.BlockType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@Table(name = "block")
public class Block {

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany
    @JoinColumn(name = "block_id")
    private List<Rack> rack;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "block_id",nullable = false,updatable = false)
    private String blockId;

    @Column(name = "height",nullable = false)
    private double height;

    @Column(name = "length",nullable = false)
    private double length;

    @Column(name = "breadth",nullable = false)
    private double breadth;

    @Column(name = "type",nullable = false)
    private BlockType type;
}