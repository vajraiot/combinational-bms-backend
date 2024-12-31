package com.bms.pojo;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class ChargerDTO {
	 private Long id;

	   
	    private boolean inputMains;

	   
	    private boolean inputFuse;

	    
	    private boolean rectifierFuse;

	    
	    private boolean filterFuse;

	    
	    private int dcVoltageOLN;

	    
	    private boolean outputFuse;

	   
	    private int acVoltageULN;

	    
	    private boolean chargerLoad;

	   
	    private boolean alarmSupplyFuse;

	   
	    private boolean chargerTrip;

	    
	    private boolean outputMccb;

	   
	

	    
	    private boolean batteryCondition;

	    
	    private boolean testPushButton;

	   
	    private boolean resetPushButton;

}
