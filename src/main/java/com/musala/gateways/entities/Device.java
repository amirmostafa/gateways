package com.musala.gateways.entities;

import com.musala.gateways.enums.DeviceStatus;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DEVICE")
@Data
public class Device {

    @Id
    private long uid;

    @NotNull
    @Column(nullable = false)
    private String vendor;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private DeviceStatus status;

    @ManyToOne
    @JoinColumn(name = "GATEWAY_ID", nullable = false)
    @NotNull
    private Gateway gateway;

}
