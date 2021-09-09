package com.musala.gateways.mappers;

import com.musala.gateways.dto.GatewayDto;
import com.musala.gateways.entities.Gateway;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DeviceMapper.class)
public interface GatewayMapper {

    GatewayMapper Mapper = Mappers.getMapper(GatewayMapper.class);

    GatewayDto toDTO(Gateway gateway);

    Gateway toEntity(GatewayDto gatewayDto);

}
