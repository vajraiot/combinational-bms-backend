package com.bms.enums;


public enum HistoricaAlarmsDetailsReport {
	
	SN("S.No","S.No ",0),
	SITE_ID("siteId","Site Name",1),
	SERIAL_NUMBER("serialNumber","Serial Number",2),
	PACKET_TIME("Packet Time","Packet Time",3),
	
	BANK_CYCLE("bankCycle","Bank Status",4),
	SOC("soc","State of Charge",5),
	STRING_VOLTAGE("stringVoltage","String Voltage",6),
	
	STIRNG_CURRENT("stringCurrent","String Current",7),
	BMS_SED_COMMUNICATION("bmsSedCommunication","BMS Sed Communication",8),
	CELL_COMMUNICATION("cellCommunication","Cell Communicaiton",9),
	CELL_VOLTAGE("cellVoltage","Cell Voltage",10),
	CELL_TEMPERATURE("cellTemperature","Cell Temperature",11);
	
	
   private HistoricaAlarmsDetailsReport(String _entityFieldName, String _name, int _position) {
	this.name=_name;
	this.position=_position;
	this.entityFieldName=_entityFieldName;
	}
  
   public final String entityFieldName;
	public final String name;
	public final int position;
	//public final String identifiers;
	

}
