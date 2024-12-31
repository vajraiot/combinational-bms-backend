package com.bms.enums;


public enum DayWiseDataReport {
	
	SN("S.No","S.No ",0),
	DAY_WISE_DATE("dayWiseDate","Site Name",1),
	
	CHARGE_OR_DISCHARGE_CYCLE("chargeOrDischargeCycle","Charge Or Discharge Cycle",2),
	
	CUMULATIVE_AH_IN("cumulativeAHIn","Cumulative AH In",3),
	
	CUMULATIVE_AH_OUT("cumulativeAHOut","Cumulative AH Out",4),
	
	TOTAL_CHARGING_ENERGY("totalChargingEnergy","Total Charging Energy",5),
	
	TOTAL_DISCHARGING_ENERGY("totalDischargingEnergy","Total Discharging Energy",6),
	
	BATTERY_RUN_HOURS("batteryRunHours","Battery Run Hours",7);
	
	
   private DayWiseDataReport(String _entityFieldName, String _name, int _position) {
	this.name=_name;
	this.position=_position;
	this.entityFieldName=_entityFieldName;
	}
  
   public final String entityFieldName;
	public final String name;
	public final int position;
	//public final String identifiers;
	

}
