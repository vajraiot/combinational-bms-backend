package com.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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

//@Table(name="bms_alarm_status")
public class BMSAlarmStatus {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bms_alarm_status_id")
	private Long id;
	
	
	@Column(name="bank_discharge_cycle",  nullable=true)
	private boolean bankDischargeCycle;
	
	
	@Column(name="ambient_temperature_hn",  nullable=true)
	private boolean AmbientTemperatureHN;
	
	@Column(name="soc_ln",  nullable=true)
	private boolean socLN;

	@Column(name="string_voltage_ln",  nullable=true)
	private boolean stringVoltageLN;

	@Column(name="string_hn",  nullable=true)
	private boolean stringVoltageHN;

	@Column(name="string_current_hn",  nullable=true)
	private boolean stringCurrentHN;

	@Column(name="bms_sed_communication",  nullable=true)
	private boolean bmsSedCommunication;

	@Column(name="cell_communication",  nullable=true)
	private boolean cellCommunication;


	@Column(name="cell_voltage_ln",  nullable=true)
	private boolean cellVoltageLN;

	@Column(name="cell_voltage_hn",  nullable=true)
	private boolean cellVoltageHN;

	@Column(name="cell_temperature_hn",  nullable=true) 
	private boolean cellTemperatureHN;
	
	@Column(name="eb_status",  nullable=true)   //edited
	private boolean ebStatus;
	
	@Column(name="dg_status",  nullable=true)   //edited
	private boolean dgStatus;

	
	
	/*@Column(name="batteryMonitoring_data_id",  nullable=true)
	private Long batteryMonitoringDataId;*/
	
	@OneToOne(optional = false)
	@JoinColumn(name="device_data_id")
	private DeviceData deviceData;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }


	@Override
	public String toString() {
		return "BMSAlarmStatus [id=" + id + ", bankDischargeCycle=" + bankDischargeCycle + ", AmbientTemperatureHN="
				+ AmbientTemperatureHN + ", socLN=" + socLN + ", stringVoltageLN=" + stringVoltageLN
				+ ", stringVoltageHN=" + stringVoltageHN + ", stringCurrentHN=" + stringCurrentHN
				+ ", bmsSedCommunication=" + bmsSedCommunication + ", cellCommunication=" + cellCommunication
				+ ", cellVoltageLN=" + cellVoltageLN + ", cellVoltageHN=" + cellVoltageHN + ", cellTemperatureHN="
				+ cellTemperatureHN + ", deviceData=" + deviceData + ", serverTime=" + serverTime + "]";
	}

	
	
}
