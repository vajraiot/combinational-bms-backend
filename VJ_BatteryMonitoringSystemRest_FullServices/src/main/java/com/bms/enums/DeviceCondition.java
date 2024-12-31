package com.bms.enums;


public enum DeviceCondition {
	STATIONERY("STATIONERY",1),
   MOVING("MOVING",2),
   OFF("OFF",3),
   NOTCOMMUNICATION("NOTCOMMUNICATION",4),
   UNKNOWN("UNKNOWN",5);
  // UNKNOWN("Unkwon packet","undefinied");
	
   private DeviceCondition(String _name, int _identifiers) {
	this.name=_name;
	this.identifiers=_identifiers;
	}
  
	public final String name;
	public final int identifiers;
	//public final String identifiers;
	

}
