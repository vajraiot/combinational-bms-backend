package com.bms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity
@Table(name="device_data" )
public class DeviceData {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "device_data_id")
	private Long id;
	
	
	@Column(name="device_id", nullable=true)
	private int deviceId;
	
	@Column(name="bms_manufacturer_id", nullable=true)
	private int bmsManufacturerID;
	
	@Column(name="serial_number",  nullable=true)
	private String serialNumber;
	
	@Column(name="installaion_date",  nullable=true)
	private String installationDate;
	
	/*@Column(name="bank_alarm_id",  nullable=true)
	private Long bankAlarmId;*/
	
	
//	 @OneToOne(mappedBy = "deviceData",cascade=CascadeType.ALL ,fetch = FetchType.LAZY)
//     private BMSAlarmStatus bMSAlarmStatus;

	@Column(name="bMS_alarms_string",  nullable=true)
	private String bMSAlarmsString;
	
  @OneToOne(mappedBy = "deviceData")
  private BMSAlarms bMSAlarms=new BMSAlarms();
	
	
	@Column(name="cells_connected_count",  nullable=true)
	private int cellsConnectedCount;
	
	@Column(name="problem_cells",  nullable=true)
	private int problemCells;
	
	/*@Column(name="cell_voltage_id",  nullable=true)
	private Long cellVoltageId;
	
	@Column(name="cell_temperature_id",  nullable=true)
	private Long cellTemperatureId;*/
	
	 
	 @OneToMany(mappedBy = "deviceData")
	private List<CellVoltageTemperatureData> cellVoltageTemperatureData=new ArrayList<CellVoltageTemperatureData>();
	
	@Column(name="string_voltage",  nullable=true)
	private float stringvoltage;
	
	
	@Column(name="system_peack_current_in_charge_one_cycle",  nullable=true)
	private float systemPeakCurrentInChargeOneCycle;
	
	
	@Column(name="average_Discharging_current",  nullable=true)
	private float averageDischargingCurrent;
	
	@Column(name="average_charging_current",  nullable=true)
	private float averageChargingCurrent;
	
	
	
	@Column(name="ah_in_for_one_charge_cycle",  nullable=true)
	private float ahInForOneChargeCycle;
	
	
	@Column(name="ah_out_for_one_discharge_cycle",  nullable=true)
	private float ahOutForOneDischargeCycle;	
	
	@Column(name="cumulative_ah_in",  nullable=true)
	private float cumulativeAHIn;
	
	@Column(name="cumulative_ah_out",  nullable=true)
	private float cumulativeAHOut;
	
	@Column(name="charge_time_cycle",  nullable=true)
	private int chargeTimeCycle;
		
	@Column(name="discharge_time_cycle",  nullable=true)
	private int dischargeTimeCycle;

	@Column(name="total_charging_energy",  nullable=true)
	private float totalChargingEnergy;

	
	@Column(name="total_discharging_energy",  nullable=true)
	private float totalDischargingEnergy;
	
	
	@Column(name="every_hour_avg_temp",  nullable=true)
	private float everyHourAvgTemp;

	@Column(name="cumulative_total_avg_temp_every_hour",  nullable=true)
	private float cumulativeTotalAvgTempEveryHour;

	@Column(name="charge_or_Discharge_cycle",  nullable=true)
	private int chargeOrDischargeCycle;
	
	
	@Column(name="soc_latest_value_for_every_cycle",  nullable=true)
	private float socLatestValueForEveryCycle;
		
	@Column(name="dod_latest_value_for_every_cycle",  nullable=true)
	private float dodLatestValueForEveryCycle;
	
	
	
	@Column(name="system_peak_current_in_discharge_one_cycle",  nullable=true)
	private float systemPeakCurrentInDischargeOneCycle;
	
	
	
	@Column(name="instantaneous_current",  nullable=true)
	private float instantaneousCurrent;
	
	
	
	
	@Column(name="ambient_temperature",  nullable=true)
	private float ambientTemperature;
	
	@Column(name="battery_run_hours",  nullable=true)
	private int batteryRunHours;
	
		
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	@JsonIgnore()
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="general_data_id")
	private GeneralData generalData;
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }


	@Override
	public String toString() {
		return "DeviceData [id=" + id + ", deviceId=" + deviceId + ", bmsManufacturerID=" + bmsManufacturerID
				+ ", serialNumber=" + serialNumber
				+ ", siteId=" + generalData.getSiteId()
				+", installationDate=" + installationDate + ", bMSAlarmsString="
				+ bMSAlarmsString + ", bMSAlarms=" + bMSAlarms + ", cellsConnectedCount=" + cellsConnectedCount
				+ ", problemCells=" + problemCells + ", cellVoltageTemperatureData=" + cellVoltageTemperatureData
				+ ", stringvoltage=" + stringvoltage + ", systemPeakCurrentInChargeOneCycle="
				+ systemPeakCurrentInChargeOneCycle + ", averageDischargingCurrent=" + averageDischargingCurrent
				+ ", averageChargingCurrent=" + averageChargingCurrent + ", ahInForOneChargeCycle="
				+ ahInForOneChargeCycle + ", ahOutForOneDischargeCycle=" + ahOutForOneDischargeCycle
				+ ", cumulativeAHIn=" + cumulativeAHIn + ", cumulativeAHOut=" + cumulativeAHOut + ", chargeTimeCycle="
				+ chargeTimeCycle + ", dischargeTimeCycle=" + dischargeTimeCycle + ", totalChargingEnergy="
				+ totalChargingEnergy + ", totalDischargingEnergy=" + totalDischargingEnergy + ", everyHourAvgTemp="
				+ everyHourAvgTemp + ", cumulativeTotalAvgTempEveryHour=" + cumulativeTotalAvgTempEveryHour
				+ ", chargeOrDischargeCycle=" + chargeOrDischargeCycle + ", socLatestValueForEveryCycle="
				+ socLatestValueForEveryCycle + ", dodLatestValueForEveryCycle=" + dodLatestValueForEveryCycle
				+ ", systemPeakCurrentInDischargeOneCycle=" + systemPeakCurrentInDischargeOneCycle
				+ ", instantaneousCurrent=" + instantaneousCurrent + ", ambientTemperature=" + ambientTemperature
				+ ", batteryRunHours=" + batteryRunHours + ", serverTime=" + serverTime + "]";
	}

	
	public void toPrint()
	{
		System.out.println(toString());
	}
	
	}
