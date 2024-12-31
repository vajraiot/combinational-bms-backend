package com.bms.enums;


public enum HistoricaStringDetailsReportParams {
	
	SN("S.No","S.No ",0),
	SITE_ID("siteId","Site Name",1),
	SERIAL_NUMBER("serialNumber","Serial Number",2),
	
	//INSTALLATION_DATE("installationDate","Installation Data",3),
	//CELLS_CONNECTED_COUNT("cellsConnectedCount","Cells Connected Count",4),
	//PROBLEM_CELLS("problemCells","Problem Cells",5),
	STRING_VOLTAGE("stringvoltage","String Voltage",3),
	SYSTEM_PEAK_CURRENT_IN_CHARGE_ONE_CYCLE("systemPeakCurrentInChargeOneCycle","Peak Current In Charge Cycle",4),
	AVERAGE_DISCHARGING_CURRENT("averageDischargingCurrent","Average Discharging Current",5),
	AVERAGE_CHARGING_CURRENT("averageChargingCurrent","Avarage Charging Current",6),
	AH_IN_FOR_ONE_CHARGE_CYCLE("ahInForOneChargeCycle","AH Out For Charge Cycle",7),
	AH_OUT_FOR_ONE_DISCHARGE_CYCLE("ahOutForOneDischargeCycle","AH Out For Discharge Cycle",8),
	CUMULATIVE_AH_IN("cumulativeAHIn","Cumulative Ah In",9),
	CUMULATIVE_AH_OUT("cumulativeAHOut","Cumulative Ah Out",10),
	CHARGE_TIME_CYCLE("chargeTimeCycle","Charge Time Cycle",11),
	
	DISCHARGE_TIME_CYCLE("dischargeTimeCycle","Discharge Time Cycle",12),
	TOTAL_CHARGING_ENERGY("totalChargingEnergy","Total Charging Energy",13),
	TOTAL_DISCHARGING_ENERGY("totalDischargingEnergy","Total Dischaarging Energy",14),
	
	EVERY_HOUR_AVG_TEMP("everyHourAvgTemp","Every Hour Average Temperature",15),
	CUMULATIVE_TOTAL_AVG_TEMP_EVERY_HOUR("cumulativeTotalAvgTempEveryHour","Cumulative Total Average Temperature Every Hour",16),
	CHARGE_OR_DISHCARGE_CYCLE("chargeOrDischargeCycle","Charge Or Discharge Cycle",17),
	SOC_LATEST_VALUE_FOR_EVERY_CYCLE("socLatestValueForEveryCycle","State of Charge",18),
	DOD_LATEST_VALUE_FOR_EVERY_CYCLE("dodLatestValueForEveryCycle","Depth of Discharge",19),
	SYSTEM_PEAK_CURRENT_IN_DISCHARGE_ONE_CYCLE("systemPeakCurrentInDischargeOneCycle","Peak Current In Discharge Cycle",20),
	INSTANTANEOUS_CURRENT("instantaneousCurrent","Instantaneous Current",21),
	AMBIENT_TEMPERATURE("ambientTemperature","Ambient Temperature",22),
	
	BATTERY_RUN_HOURS("batteryRunHours","Battery Run Hours",23),
	//SERVER_TIME("serverTime","Server Time",28),
	PACKET_TIME("Packet Time","Packet Time",24);
	
	//DEVICE_ID("deviceId","Device Id",3),
	//BMS_MANUFACTURER_ID("bmsManufacturerID","BMS Manufacturer Id",4),
	
   private HistoricaStringDetailsReportParams(String _entityFieldName, String _name, int _position) {
	this.name=_name;
	this.position=_position;
	this.entityFieldName=_entityFieldName;
	}
  
   public final String entityFieldName;
	public final String name;
	public final int position;
	//public final String identifiers;
	

}
