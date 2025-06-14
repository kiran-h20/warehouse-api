package com.example.warehouse.repository;

import com.example.warehouse.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,String> {

    Optional<Client> findByApiKey(String apiKey);
}
