package com.bms.pojo;

import java.util.Date;

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
public class AllSitesAlarmsDTO {

	private String siteId;
	private String serialNumber;

	private boolean bankCycleDC;

	private boolean ambientTemperatureHN;

	private boolean socLN;

	private int stringVoltageLHN;

	private boolean stringCurrentHN;

	private boolean bmsSedCommunicationFD;

	private boolean cellCommunicationFD;

	private int cellVoltageLHN;

	private boolean cellTemperatureHN;

	private boolean buzzer;

	private Date serverTime;
}
