package com.bms.pojo;

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

public class DeviceDataDTO {

	private static final long serialVersionUID = -1259518737699450692L;

	private int deviceId;

	private int bmsManufacturerID;

	private String serialNumber;

	private String installationDate;

	private int cellsConnectedCount;

	private int problemCells;

	private List<CellParamDTO> cellParamDTO;

	private float stringvoltage;

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

	
	//private BMSAlarmStatusDTO bMSAlarmStatusDTO;
	
	private AlarmsDTO alarmsDTO; 
}
