package com.musala.gateways.controllers;

import com.musala.gateways.dto.DeviceDto;
import com.musala.gateways.dto.PaginationModel;
import com.musala.gateways.enums.DeviceStatus;
import com.musala.gateways.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("devices")
public class DeviceController {

	@Autowired
	DeviceService deviceService;


	@GetMapping()
	public ResponseEntity<?> listDevices(@RequestParam String gatewaySerialNumber) {
		return ResponseEntity.ok(deviceService.listDevices(gatewaySerialNumber));
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody DeviceDto deviceDto) {
		deviceService.createDevice(deviceDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody DeviceDto deviceDto) {
		deviceService.updateDevice(deviceDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("{uuid}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable long uuid, @PathVariable DeviceStatus status) {
		deviceService.updateDeviceStatus(uuid, status);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{uuid}")
	public ResponseEntity<?> deleteDevice(@PathVariable long uuid) {
		deviceService.deleteDevice(uuid);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
