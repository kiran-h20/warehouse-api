package com.example.warehouse.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "client_id",nullable = false,updatable = false)
    private String clientId;

    @Column(name = "platform_name",nullable = false)
    private String platformName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "api_key",nullable = false,unique = true)
    private String apiKey;

    @Column(name = "secrete_key",nullable = false)
    private String secreteKey;

    @CreatedDate
    @Column(name = "registered_at",nullable = false)
    private Instant registeredAt;

}
