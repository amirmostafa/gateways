package com.musala.gateways.services;

import com.musala.gateways.dto.GatewayDto;
import com.musala.gateways.dto.PaginationModel;
import com.musala.gateways.dto.ResultSet;
import com.musala.gateways.entities.Gateway;
import com.musala.gateways.exceptions.GatewayNotFoundException;
import com.musala.gateways.exceptions.GatewaySerialNonUniqueException;
import com.musala.gateways.exceptions.Ipv4NotValidException;
import com.musala.gateways.mappers.GatewayMapper;
import com.musala.gateways.repositories.GatewayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepo gatewayRepo;

    public ResultSet<GatewayDto> listGateways(PaginationModel pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());

        Page<Gateway> gatewayPage = gatewayRepo.findAll(pageable);
        List<GatewayDto> gatewayDtoList = new ArrayList<>();
        for (Gateway gateway : gatewayPage.getContent()) {
            gatewayDtoList.add(GatewayMapper.Mapper.toDTO(gateway));
        }
        ResultSet<GatewayDto> resultSet = new ResultSet<>();
        resultSet.setData(gatewayDtoList);
        resultSet.setTotal(gatewayPage.getTotalElements());
        return resultSet;
    }

    public GatewayDto getGatewayDetails(String serialNumber) {
        Gateway gateway = getGatewayBySerialnumber(serialNumber);
        return GatewayMapper.Mapper.toDTO(gateway);
    }

    @Transactional
    public void createGateway(GatewayDto gatewayDto) {
        validate(gatewayDto, true);
        Gateway gateway = GatewayMapper.Mapper.toEntity(gatewayDto);
        gatewayRepo.save(gateway);
    }

    @Transactional
    public void updateGateway(GatewayDto gatewayDto) {
        validate(gatewayDto, false);
        Gateway gateway = getGatewayBySerialnumber(gatewayDto.getSerialNumber());
        gateway.setIpv4(gatewayDto.getIpv4());
        gateway.setName(gatewayDto.getName());
        gatewayRepo.save(gateway);
    }

    @Transactional
    public void deleteGateway(String serialNumber) {
        Gateway gateway = getGatewayBySerialnumber(serialNumber);
        gatewayRepo.delete(gateway);
    }

    public Gateway getGatewayBySerialnumber(String serialNumber) {
        return gatewayRepo.findById(serialNumber).orElseThrow(() -> new GatewayNotFoundException(serialNumber));
    }

    private void validate(GatewayDto gatewayDto, boolean isNew) {
        if (!gatewayDto.getIpv4().matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")) {
            throw new Ipv4NotValidException();
        }
        if (isNew) {
            if (gatewayRepo.existsBySerialNumber(gatewayDto.getSerialNumber())) {
                throw new GatewaySerialNonUniqueException(gatewayDto.getSerialNumber());
            }
        }
    }
}
