package com.bms.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class MonthWiseDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; // Device ID
    private String month; // Month (e.g., "January")
    private List<DayWiseDataDTO> dayWiseReports = new ArrayList<>(); // List to hold day-wise reports

    private int chargeOrDischargeCycle = 0; // Total cycle count for the month
    private float cumulativeAHIn = 0; // Total ampere-hours in
    private float cumulativeAHOut = 0; // Total ampere-hours out
    private float totalChargingEnergy = 0; // Total energy during charging
    private float totalDischargingEnergy = 0; // Total energy during discharging
    private int batteryRunHours = 0; // Total battery run hours

    /**
     * Accumulate day-wise data into month-wise data and store day-wise data.
     *
     * @param dayWiseData the day-wise data to accumulate
     */
    public void accumulate(DayWiseDataDTO dayWiseData) {
        if (dayWiseData != null) {
            // Add day-wise data to the list
            this.dayWiseReports.add(dayWiseData);

            // Accumulate the data into the monthly totals
            this.cumulativeAHIn += dayWiseData.getCumulativeAHIn();
            this.cumulativeAHOut += dayWiseData.getCumulativeAHOut();
            this.totalChargingEnergy += dayWiseData.getTotalChargingEnergy();
            this.totalDischargingEnergy += dayWiseData.getTotalDischargingEnergy();
            this.batteryRunHours += dayWiseData.getBatteryRunHours();
            this.chargeOrDischargeCycle += dayWiseData.getChargeOrDischargeCycle();
        }
    }

    /**
     * Reset the cumulative data for a new month.
     */
    public void resetCumulativeData() {
        this.cumulativeAHIn = 0;
        this.cumulativeAHOut = 0;
        this.totalChargingEnergy = 0;
        this.totalDischargingEnergy = 0;
        this.batteryRunHours = 0;
        this.chargeOrDischargeCycle = 0;
        this.dayWiseReports.clear(); // Clear day-wise reports for a new month
    }
}
