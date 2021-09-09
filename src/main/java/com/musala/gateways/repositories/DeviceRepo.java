package com.musala.gateways.repositories;

import com.musala.gateways.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepo extends JpaRepository<Device, Long> {

    List<Device> findByGatewaySerialNumber(String gatewaySerialNumber);

    boolean existsByUid(long uid);
}
