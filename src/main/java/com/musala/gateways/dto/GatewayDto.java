package com.musala.gateways.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayDto {
        private String serialNumber;
        private String name;
        private String ipv4;
        private List<DeviceDto> devices = new ArrayList<>();
}
