package com.bms.pojo;

import java.util.Date;

import com.bms.entity.ChargerMonitoringData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargerMonitoringDTO {

    private Long id; // Use Long for IDs to maintain consistency

    private float deviceId; // Changed to Double for better precision

    private float acVoltage; // Changed to Double for better precision

    private float acCurrent; // Changed to Double for better precision

    private float frequency; // Changed to Double for better precision

    private float energy; // Changed to Double for better precision

    private ChargerDTO chargerDTO; // Nested DTO, ensure proper handling

}
