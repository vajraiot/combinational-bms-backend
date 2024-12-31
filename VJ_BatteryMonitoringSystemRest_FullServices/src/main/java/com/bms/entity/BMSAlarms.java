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
@Table(name="bms_alarms")
public class BMSAlarms {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bms_alarm_status_id")
	private Long id;
	
	@Column(name="bank_cycle_dc",  nullable=true)
	private boolean bankCycleDC;
	
	
	@Column(name="ambient_temperature_hn",  nullable=true)
	private boolean ambientTemperatureHN;
	
	
	@Column(name="soc_ln",  nullable=true)
	private boolean socLN;

	@Column(name="string_voltage_lhn",  nullable=true)
	private int stringVoltageLHN;

	@Column(name="string_current_hn",  nullable=true)
	private boolean stringCurrentHN;

	@Column(name="bms_sed_communication_fd",  nullable=true)
	private boolean bmsSedCommunicationFD;

	@Column(name="cell_communication_fd",  nullable=true)
	private boolean cellCommunicationFD;


	@Column(name="cell_voltage_lhn",  nullable=true)
	private int cellVoltageLHN;


	@Column(name="cell_temperature_hn",  nullable=true)
	private boolean cellTemperatureHN;

	@JsonIgnore()
  	@OneToOne()
	@JoinColumn(name="device_data_id")
	private DeviceData deviceData;
	
	@Column(name="buzzer",  nullable=true)
	private boolean buzzer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }




	
	
	
}
