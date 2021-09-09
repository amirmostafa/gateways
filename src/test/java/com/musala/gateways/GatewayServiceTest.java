package com.musala.gateways;

import com.musala.gateways.dto.GatewayDto;
import com.musala.gateways.dto.PaginationModel;
import com.musala.gateways.dto.ResultSet;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.exceptions.*;
import com.musala.gateways.services.GatewayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql({"classpath:Data.sql"})
public class GatewayServiceTest {

    @Autowired
    GatewayService gatewayService;

    final String EXISTING_SERIAL_NO = "g_test_1";
    final String NEW_SERIAL_NO = "g_test_4";
    final String NOT_FOUND_SERIAL_NO = "not found";


    //list
    @Test
    public void testListGateways() {
        ResultSet<GatewayDto> gateways = gatewayService.listGateways(new PaginationModel());
        assertNotNull(gateways);
        assertTrue(gateways.getTotal() > 0);
        assertFalse(gateways.getData().isEmpty());
    }


    //get gateway details
    @Test
    public void testGetDetails_throwsGatewayNotFoundException() {
        assertThrows(GatewayNotFoundException.class, () -> gatewayService.getGatewayDetails(NOT_FOUND_SERIAL_NO));
    }

    @Test
    public void testGetDetails() {
        GatewayDto gateway = gatewayService.getGatewayDetails(EXISTING_SERIAL_NO);
        assertNotNull(gateway);
    }

    //create
    @Test
    public void testCreateGateway_throwsNonUniqueException() {
        assertThrows(GatewaySerialNonUniqueException.class, () -> gatewayService.createGateway(initGatewayDto(EXISTING_SERIAL_NO, "22.22.22.22")));
    }

    @Test
    public void testCreateGateway_throwsInvalidIpv4Exception() {
        assertThrows(Ipv4NotValidException.class, () -> gatewayService.createGateway(initGatewayDto(NEW_SERIAL_NO, "22.22.22")));
    }

    @Test
    public void testCreateGateway() {
        gatewayService.createGateway(initGatewayDto(NEW_SERIAL_NO, "22.22.22.22"));
        assertNotNull(gatewayService.getGatewayBySerialnumber(NEW_SERIAL_NO));
    }

    // update
    @Test
    public void testUpdateGateway_throwsGatewayNotFoundException() {
        assertThrows(GatewayNotFoundException.class, () -> gatewayService.updateGateway(initGatewayDto(NOT_FOUND_SERIAL_NO, "22.22.22.22")));
    }

    @Test
    public void testUpdateGateway() {
        gatewayService.updateGateway(initGatewayDto(EXISTING_SERIAL_NO, "22.22.22.11"));
        Gateway updatedGateway = gatewayService.getGatewayBySerialnumber(EXISTING_SERIAL_NO);
        assertNotNull(updatedGateway);
        assertEquals(updatedGateway.getIpv4(), "22.22.22.11");
    }

    //delete
    @Test
    public void deleteGateway_throwsGatewayNotFoundException() {
        assertThrows(GatewayNotFoundException.class, () -> gatewayService.deleteGateway(NOT_FOUND_SERIAL_NO));
    }

    @Test
    public void deleteGateway() {
        gatewayService.deleteGateway("g_test_3");
        assertThrows(GatewayNotFoundException.class, () -> gatewayService.deleteGateway("g_test_3"));
    }

    private GatewayDto initGatewayDto(String serialNumber, String ipv4) {
        return new GatewayDto(serialNumber, "test_name", ipv4, null);
    }
}
