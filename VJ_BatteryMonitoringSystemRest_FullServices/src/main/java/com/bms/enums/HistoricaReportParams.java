package com.bms.enums;


public enum HistoricaReportParams {
	SN("S.No",0),
	IMEINUMBER("ImeiNumber",1),
	LATITUDE("Latitude",2),
	LONGNITUDE("Longnitude",3),
	PACKET_DATE_TIME("PacketDateTime",4),
	VEHICLEREG("VehicleReg",5),
	FLOW_METER_1("Inlet",6),
	FLOW_METER_2("Outlet",7),
	
	WATERLEVEL("WaterLevel(MM)",8),
	PH_SENSOR_DATA("PhSensorData",9),
	CONDUCTIVITY_DATA("ConductivityData",10),
	DISSOLVED_OXYGEN("DissolvedOxygen",11),	
	TEMPERATURE("temperature",12),	
	LEAKAGE_DETECTION("LeakageDetection",13),
	VALVE_STATUS("ValveStatus",14),
	LOW_WATER_LEVEL("LowWaterLevel",15),
	HIGT_WATER_LEVEL("HighWaterLevel",16),
	SERVER_TIME("Servert Time",17);  
  
 
  // UNKNOWN("Unkwon packet","undefinied");
	
   private HistoricaReportParams(String _name, int _position) {
	this.name=_name;
	this.position=_position;
	}
  
	public final String name;
	public final int position;
	//public final String identifiers;
	

}
