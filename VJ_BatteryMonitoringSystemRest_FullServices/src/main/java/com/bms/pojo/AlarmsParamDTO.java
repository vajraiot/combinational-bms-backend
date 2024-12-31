package com.bms.pojo;

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
public class AlarmsParamDTO {

	private Long id; //device Id

	private String siteId;

	private String serialNumber;
	
	private String bankCycle;
	
	
	private String  ambientTemperature;

	private String soc;

	private String stringVoltage;

	private String  stringCurrent;

	private String  bmsSedCommunication;

	private String cellCommunication;


	private String  cellVoltage;


	private String cellTemperature;
	
	private String bMSAlarmsString;
	
	private String buzzer;
	
	private String ebStatus;
	
	
	private Date packetDateTime;
	private Date serverTime;
}
