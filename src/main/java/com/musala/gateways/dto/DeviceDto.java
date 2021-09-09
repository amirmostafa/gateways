package com.musala.gateways.dto;

import com.musala.gateways.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    private long uid;
    private String vendor;
    private LocalDateTime creationDate;
    private DeviceStatus status;
    private String gatewaySerialNumber;
}
