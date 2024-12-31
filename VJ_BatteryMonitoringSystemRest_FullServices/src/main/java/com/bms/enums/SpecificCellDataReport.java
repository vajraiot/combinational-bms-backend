package com.bms.enums;


public enum SpecificCellDataReport {
	
	SN("S.No","S.No ",0),
	SITE_ID("siteId","Site Name",1),
	SERIAL_NUMBER("serialNumber","Serial Number",2),
	PACKET_TIME("Packet Time","Packet Time",3),
	
	CELL_NUMBER("cellNumber","Cell Number",4),
	CELL_VOLTAGE("cellVoltage","Cell Voltage",5),
	CELL_TEMPERATURE("cellTemperature","Cell Temp",6);
	
   private SpecificCellDataReport(String _entityFieldName, String _name, int _position) {
	this.name=_name;
	this.position=_position;
	this.entityFieldName=_entityFieldName;
	}
  
   public final String entityFieldName;
	public final String name;
	public final int position;
	//public final String identifiers;
	

}
