package com.musala.gateways.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultSet<T> {
    private List<T> data;
    private long total;
}
