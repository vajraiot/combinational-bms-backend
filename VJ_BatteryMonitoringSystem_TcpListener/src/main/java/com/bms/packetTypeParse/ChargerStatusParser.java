package com.bms.packetTypeParse;


import com.bms.entity.ChargerStatusData;
import com.bms.miscellaneous.AcvoltageEnum;
import com.bms.miscellaneous.AlarmEnum;
import com.bms.miscellaneous.ChargerEnum;
import com.bms.utilities.Commonutility;

public class ChargerStatusParser {
	public ChargerStatusData parse(String strChargerStatus)
	{
		
		
		//BMSAlarmStatus _bMSAlarmStatus=new BMSAlarmStatus();
		
		ChargerStatusData _chargerStatus=new ChargerStatusData(); 
				 
		_chargerStatus.setInputMains(Commonutility.bitposition(strChargerStatus, 0));
		 
		_chargerStatus.setInputFuse(Commonutility.bitposition(strChargerStatus, 1));
		 
		 
		_chargerStatus.setRectifierFuse(Commonutility.bitposition(strChargerStatus, 2));

		_chargerStatus.setFilterFuse(Commonutility.bitposition(strChargerStatus, 3));
		
		boolean dcon=Commonutility.bitposition(strChargerStatus, 4);
		boolean dcln=Commonutility.bitposition(strChargerStatus, 8);
		_chargerStatus.setDcVoltageOLN(findinLHN(dcon,dcln));
		
		_chargerStatus.setOutputFuse(Commonutility.bitposition(strChargerStatus, 9));
		
		boolean acun=Commonutility.bitposition(strChargerStatus, 10);
		boolean acln=Commonutility.bitposition(strChargerStatus, 15);
		_chargerStatus.setAcVoltageULN(findinLHN(acun,acln));
		
		
		_chargerStatus.setChargerLoad(Commonutility.bitposition(strChargerStatus, 11));
		
		_chargerStatus.setAlarmSupplyFuse(Commonutility.bitposition(strChargerStatus, 12));
		
		_chargerStatus.setChargerTrip(Commonutility.bitposition(strChargerStatus, 13));
		
		_chargerStatus.setOutputMccb(Commonutility.bitposition(strChargerStatus, 14));
		
		
		
		_chargerStatus.setBatteryCondition(Commonutility.bitposition(strChargerStatus, 16));
		
		_chargerStatus.setTestPushButton(Commonutility.bitposition(strChargerStatus, 17));
		
		_chargerStatus.setResetPushButton(Commonutility.bitposition(strChargerStatus, 18));
		
		
		
		
		return _chargerStatus;
	}
	private static int findinLHN(boolean on,boolean ln ) //this method decide the parameter value low or normal or high
	{
		if(ln==true)
		{
			return ChargerEnum.LOW.identifiers; 
		}
		else if(on==true)
		{
			return ChargerEnum.OVER.identifiers;
		}
		else
		{
			return ChargerEnum.NORMAL.identifiers;
		}
		
	}
	private static int findinUHN(boolean on,boolean un ) //this method decide the parameter value low or normal or high
	{
		if(un==true)
		{
			return AcvoltageEnum.LOW.identifiers; 
		}
		else if(on==true)
		{
			return AcvoltageEnum.UNDER.identifiers;
		}
		else
		{
			return AcvoltageEnum.NORMAL.identifiers;
		}
		
	}


}
