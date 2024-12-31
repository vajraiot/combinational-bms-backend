package com.bms.miscellaneous;

public enum AlarmEnum {

LOW("Low",0),
NORMAL("Normal",1),
HIGH("High",2),
OVER("Over",3);
//
//CHARGE_CYCLE("Charge Cycle",0),
//DISCHARGE_CYCLE("Discharge Cycle",1),

	  
			
   
  
 
  // UNKNOWN("Unkwon packet","undefinied");
	
   private AlarmEnum(String _name, int _identifiers) {
	this.name=_name;
	this.identifiers=_identifiers;
	}
  
	public final String name;
	public final int identifiers;
	//public final String identifiers;
	

}
