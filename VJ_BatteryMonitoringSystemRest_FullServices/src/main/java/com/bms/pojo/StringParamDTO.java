package com.bms.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class StringParamDTO implements Serializable {
    
    private Long id;

    private String siteId;

    private String serialNumber;

    private int deviceId;

    private int bmsManufacturerID;

    private String installationDate;

    private String bMSAlarmsString;

    private int cellsConnectedCount;

    private int problemCells;

    private float stringVoltage;

    private float systemPeakCurrentInChargeOneCycle;

    private float averageDischargingCurrent;

    private float averageChargingCurrent;

    private float ahInForOneChargeCycle;

    private float ahOutForOneDischargeCycle;

    private float cumulativeAHIn;

    private float cumulativeAHOut;

    private int chargeTimeCycle;

    private int dischargeTimeCycle;

    private float totalChargingEnergy;

    private float totalDischargingEnergy;

    private float everyHourAvgTemp;

    private float cumulativeTotalAvgTempEveryHour;

    private int chargeOrDischargeCycle;

    private float socLatestValueForEveryCycle;

    private float dodLatestValueForEveryCycle;

    private float systemPeakCurrentInDischargeOneCycle;

    private float instantaneousCurrent;

    private float ambientTemperature;

    private int batteryRunHours;



    @Temporal(TemporalType.TIMESTAMP)
    private Date serverTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date packetDateTime;
}
