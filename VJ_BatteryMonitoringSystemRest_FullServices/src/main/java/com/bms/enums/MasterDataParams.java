package com.bms.enums;


public enum MasterDataParams {
	SN("S.No",0),
   TYPE_OF_TRACKER_ID("Type of Tracker Id",1),
   VEHICLE_ID_FORKLIFTER_ID("Vehicle/Forklifter Id",2),
   FUEL_SENSOR_ID("fuel Sensor Id",3),
   TANK_TYPE_STORAGE_MOVING("Tank Type (Storage/Moving)",4),
   TANK_SHAPE_RECT_CYL("Tank Shape (Rectangular/Cylindrical)",5),
   TANK_DIMENSIONS_IN_MM("Tank Dimensions(mm)",6),
   FUEL_CAPACITY_IN_LTRS("Fuel Capacity(Ltrs)",7),
   LATTITUDE("Lattitude",8),
   LONGNITUDE("Longitude ",9),
   GEO_FENCING_LIMITS("Geo Fencing Limits",10),
	X("X",11),
	Y("Y",12),
	Z("Z",13);
   
   
  
 
  // UNKNOWN("Unkwon packet","undefinied");
	
   private MasterDataParams(String _name, int _identifiers) {
	this.name=_name;
	this.identifiers=_identifiers;
	}
  
	public final String name;
	public final int identifiers;
	//public final String identifiers;
	

}
