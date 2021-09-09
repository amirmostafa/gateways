package com.musala.gateways.controllers;

import com.musala.gateways.dto.GatewayDto;
import com.musala.gateways.dto.PaginationModel;
import com.musala.gateways.services.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateways")
public class GatewayController {

	@Autowired
	GatewayService gatewayService;


	@GetMapping()
	public ResponseEntity<?> listGateways(PaginationModel paginationModel) {
		return ResponseEntity.ok(gatewayService.listGateways(paginationModel));
	}

	@GetMapping("{serialNumber}")
	public ResponseEntity<?> getGatewayDetails(@PathVariable String serialNumber) {
		return ResponseEntity.ok(gatewayService.getGatewayDetails(serialNumber));
	}


	@PostMapping()
	public ResponseEntity<?> create(@RequestBody GatewayDto gatewayDto) {
		gatewayService.createGateway(gatewayDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody GatewayDto gatewayDto) {
		gatewayService.updateGateway(gatewayDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{serialNumber}")
	public ResponseEntity<?> deleteGateway(@PathVariable String serialNumber) {
		gatewayService.deleteGateway(serialNumber);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
