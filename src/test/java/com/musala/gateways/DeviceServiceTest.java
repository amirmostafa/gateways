package com.musala.gateways;

import com.musala.gateways.dto.DeviceDto;
import com.musala.gateways.entities.Device;
import com.musala.gateways.enums.DeviceStatus;
import com.musala.gateways.exceptions.*;
import com.musala.gateways.services.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql({"classpath:Data.sql"})
public class DeviceServiceTest {

    @Autowired
    DeviceService deviceService;

    //list
    @Test
    public void testListDevices() {
        List<DeviceDto> devices = deviceService.listDevices("g_test_2");
        assertFalse(devices.isEmpty());
    }

    //create
    @Test
    public void testCreateDevice_throwsNonUniqueException() {
        assertThrows(DeviceUidNonUniqueException.class, () -> deviceService.createDevice(initDeviceDto(-1000, "g_test_1")));
    }

    @Test
    public void testCreateDevice_throwsFullCapacityException() {
        assertThrows(GatewayFullCapacityException.class, () -> deviceService.createDevice(initDeviceDto(-2000, "g_test_2")));
    }

    @Test
    public void testUpdateDevice_throwsGatewayNotFoundException() {
        assertThrows(GatewayNotFoundException.class, () -> deviceService.createDevice(initDeviceDto(-11000, "gateway_not_found")));
    }

    @Test
    public void testCreateDevice() {
        deviceService.createDevice(initDeviceDto(-2000, "g_test_1"));
        assertNotNull(deviceService.getDeviceByUid(-2000));
    }

    // update
    @Test
    public void testUpdateDevice_throwsDeviceNotFoundException() {
        assertThrows(DeviceNotFoundException.class, () -> deviceService.updateDevice(initDeviceDto(-1100, "g_test_2")));
    }

    @Test
    public void testUpdateDevice() {
        deviceService.updateDevice(initDeviceDto(-1000, "g_test_2"));
        Device updatedDevice = deviceService.getDeviceByUid(-1000);
        assertNotNull(updatedDevice);
        assertEquals(updatedDevice.getVendor(), "test_vendor");
    }

    //update status
    @Test
    public void testUpdateStatus_throwsDeviceNotFoundException() {
        assertThrows(DeviceNotFoundException.class, () -> deviceService.updateDeviceStatus(-1100, DeviceStatus.ONLINE));
    }
    @Test
    public void testUpdateStatus_throwsInvalidStatusException() {
        assertThrows(InvalidDeviceStatusException.class, () -> deviceService.updateDeviceStatus(-1100, null));
    }

    @Test
    public void testUpdateStatus() {
        deviceService.updateDeviceStatus(-1000, DeviceStatus.ONLINE);

        Device updatedDevice = deviceService.getDeviceByUid(-1000);
        assertEquals(updatedDevice.getStatus(),  DeviceStatus.ONLINE);
    }

    //delete
    @Test
    public void deleteDevice_throwsDeviceNotFoundException() {
        assertThrows(DeviceNotFoundException.class, () -> deviceService.deleteDevice(-1100));
    }

    @Test
    public void deleteDevice() {
        deviceService.deleteDevice(-1011);
        assertThrows(DeviceNotFoundException.class, () -> deviceService.deleteDevice(-1011));
    }

    private DeviceDto initDeviceDto(long uid, String serialNo) {
        return new DeviceDto(uid, "test_vendor", null, DeviceStatus.OFFLINE, serialNo);
    }
}
