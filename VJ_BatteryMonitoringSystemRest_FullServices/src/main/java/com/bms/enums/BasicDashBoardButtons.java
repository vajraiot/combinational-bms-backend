package com.bms.enums;


public enum BasicDashBoardButtons {
	STATIONERY("STATIONERY",1),
   MOVING("MOVING",2),
   OFF("OFF",3),
   TOTAL("TOTAL",4),
   UNKNOWN("UNKNOWN",5);
  // UNKNOWN("Unkwon packet","undefinied");
	
   private BasicDashBoardButtons(String _name, int _identifiers) {
	this.name=_name;
	this.identifiers=_identifiers;
	}
  
	public final String name;
	public final int identifiers;
	//public final String identifiers;
	

}
