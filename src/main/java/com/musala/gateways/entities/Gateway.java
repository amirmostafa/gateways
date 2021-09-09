package com.musala.gateways.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GATEWAY")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Gateway {

    @Id
    @NonNull
    @Column(name="SERIAL_NUMBER")
    private String serialNumber;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String ipv4;

    @OneToMany(mappedBy = "gateway", cascade = CascadeType.ALL)
    private List<Device> devices = new ArrayList<>();

}

