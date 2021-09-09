package com.musala.gateways.dto;

import lombok.Data;

@Data
public class PaginationModel {
	protected int page = 0;
	protected int size = 10;

}
