package com.bms.miscellaneous;

public enum ChargerEnum {
	
	LOW("Low",0),
	NORMAL("Normal",1),
	OVER("Over",2);
	//
	//CHARGE_CYCLE("Charge Cycle",0),
	//DISCHARGE_CYCLE("Discharge Cycle",1),

		  
				
	   
	  
	 
	  // UNKNOWN("Unkwon packet","undefinied");
		
	   private ChargerEnum(String _name, int _identifiers) {
		this.name=_name;
		this.identifiers=_identifiers;
		}
	  
		public final String name;
		public final int identifiers;

}
