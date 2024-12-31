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
public class BMSAlarmStatusDTO {

	private boolean bankDischargeCycle;

	private boolean AmbientTemperatureHN;

	private boolean socLN;

	private boolean stringVoltageLN;

	private boolean stringVoltageHN;

	private boolean stringCurrentHN;

	private boolean bmsSedCommunication;

	private boolean cellCommunication;

	private boolean cellVoltageLN;

	private boolean cellVoltageHN;

	private boolean cellTemperatureHN;
}
