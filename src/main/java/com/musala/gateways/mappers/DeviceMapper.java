package com.musala.gateways.mappers;

import com.musala.gateways.dto.DeviceDto;
import com.musala.gateways.entities.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapper {

    DeviceMapper Mapper = Mappers.getMapper(DeviceMapper.class);

    @Mapping(target = "gatewaySerialNumber", source = "gateway.serialNumber")
    DeviceDto toDTO(Device device);

    Device toEntity(DeviceDto deviceDto);

}
