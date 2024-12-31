package com.bms.enums;


public enum HistoricaCellDetailsReportParams {
	
	SN("S.No","S.No ",0),
	SITE_ID("siteId","Site Name",1),
	SERIAL_NUMBER("serialNumber","Serial Number",2),
	PACKET_TIME("Packet Time","Packet Time",3);
	
   private HistoricaCellDetailsReportParams(String _entityFieldName, String _name, int _position) {
	this.name=_name;
	this.position=_position;
	this.entityFieldName=_entityFieldName;
	}
  
   public final String entityFieldName;
	public final String name;
	public final int position;
	//public final String identifiers;
	

}
