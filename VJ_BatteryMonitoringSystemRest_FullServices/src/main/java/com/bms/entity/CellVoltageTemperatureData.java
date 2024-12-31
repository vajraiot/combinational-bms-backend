package com.bms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="cell_voltage_temperature_data")
//@Table(name="cell_voltage_data")
public class CellVoltageTemperatureData {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cell_voltage_temperature_data_id")
	private Long id;
	
	
	
	@Column(name="cell_number",  nullable=true)
	private int cellNumber;
	
	
	@Column(name="cell_voltage",  nullable=true)
	private float cellVoltage;
	
	@Column(name="cell_temperature",  nullable=true)
	private float cellTemperature;
	
	
	/*@Column(name="batteryMonitoring_data_id",  nullable=true)
	private Long batteryMonitoringDataId;*/
	
	@JsonIgnore()
	@ManyToOne()
	@JoinColumn(name="device_data_id", nullable=false)
	private DeviceData deviceData;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Long getId() {
		return id;
	}


	public int getCellNumber() {
		return cellNumber;
	}


	public float getCellVoltage() {
		return cellVoltage;
	}


	public float getCellTemperature() {
		return cellTemperature;
	}


	
	public DeviceData getDeviceData() {
		return deviceData;
	}


	public Date getServerTime() {
		return serverTime;
	}

}
