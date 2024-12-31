package com.bms.enums;


public enum WeeklyReportParams {
	SN("S.No",0),
	WEEK("week",1),
	IMEINUMBER("ImeiNumber",2),
	TOTALCONSUMPTIONINLTRS("TotalConsumptionInLtrs",3),
	TOTALFUELFILLEDINLTRS("TotalFuelFilledInLtrs",4),
	TOTALFILLINGS("TotalFillings",5),
	TOTALFUELSTOLENINLTRS("TotalFuelstolenInLtrs",6),
	TOTALTHEFT("TotalTheft",7);
   
   
  
 
  // UNKNOWN("Unkwon packet","undefinied");
	
   private WeeklyReportParams(String _name, int _identifiers) {
	this.name=_name;
	this.identifiers=_identifiers;
	}
  
	public final String name;
	public final int identifiers;
	//public final String identifiers;
	

}
