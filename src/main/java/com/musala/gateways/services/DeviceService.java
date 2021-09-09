package com.musala.gateways.services;

import com.musala.gateways.dto.DeviceDto;
import com.musala.gateways.entities.Device;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.enums.DeviceStatus;
import com.musala.gateways.exceptions.DeviceNotFoundException;
import com.musala.gateways.exceptions.DeviceUidNonUniqueException;
import com.musala.gateways.exceptions.GatewayFullCapacityException;
import com.musala.gateways.exceptions.InvalidDeviceStatusException;
import com.musala.gateways.mappers.DeviceMapper;
import com.musala.gateways.repositories.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private GatewayService gatewayService;

    public List<DeviceDto> listDevices(String gatewaySerialNumber) {
        List<Device> deviceList = deviceRepo.findByGatewaySerialNumber(gatewaySerialNumber);
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        for (Device device : deviceList) {
            deviceDtoList.add(DeviceMapper.Mapper.toDTO(device));
        }
        return deviceDtoList;
    }

    @Transactional
    public void createDevice(DeviceDto deviceDto) {
        validateNewDevice(deviceDto.getUid());
        Gateway gateway = gatewayService.getGatewayBySerialnumber(deviceDto.getGatewaySerialNumber());
        if (gateway.getDevices().size() > 9) {
            throw new GatewayFullCapacityException();
        }
        Device device = DeviceMapper.Mapper.toEntity(deviceDto);
        device.setCreationDate(LocalDateTime.now());
        device.setGateway(gateway);
        deviceRepo.save(device);
    }

    @Transactional
    public void updateDevice(DeviceDto deviceDto) {
        Device device = getDeviceByUid(deviceDto.getUid());
        device.setStatus(deviceDto.getStatus());
        device.setVendor(deviceDto.getVendor());
        deviceRepo.save(device);
    }

    public void updateDeviceStatus(long uuid, DeviceStatus deviceStatus) {
        if (deviceStatus == null) {
            throw new InvalidDeviceStatusException();
        }
        Device device = getDeviceByUid(uuid);
        device.setStatus(deviceStatus);
        deviceRepo.save(device);
    }

    @Transactional
    public void deleteDevice(long uuid) {
        Device device = getDeviceByUid(uuid);
        deviceRepo.delete(device);
    }

    public Device getDeviceByUid(long uuid) {
        return deviceRepo.findById(uuid).orElseThrow(() -> new DeviceNotFoundException(uuid));
    }

    private void validateNewDevice(long uid) {
        if (deviceRepo.existsByUid(uid)) {
            throw new DeviceUidNonUniqueException(uid);
        }
    }
}
