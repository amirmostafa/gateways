package com.musala.gateways.repositories;

import com.musala.gateways.entities.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayRepo extends JpaRepository<Gateway, String> {
    boolean existsBySerialNumber(String serialNumber);
}
