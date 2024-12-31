package com.bms.packetTypeParse;

import com.bms.entity.BMSAlarms;
import com.bms.miscellaneous.AlarmEnum;
import com.bms.utilities.Commonutility;


public class BMSAlarmsParser {

	
	//ex:0001050A
	
	public BMSAlarms parse(String strBmsAlertString)
	{
		
		
		//BMSAlarmStatus _bMSAlarmStatus=new BMSAlarmStatus();
		
		BMSAlarms _bMSAlarms=new BMSAlarms(); 
				 
		_bMSAlarms.setBankCycleDC(Commonutility.bitposition(strBmsAlertString, 1));
		 
		_bMSAlarms.setAmbientTemperatureHN(Commonutility.bitposition(strBmsAlertString, 2));
		 
		 
		_bMSAlarms.setSocLN(Commonutility.bitposition(strBmsAlertString, 3));

		boolean svln=Commonutility.bitposition(strBmsAlertString, 8);
		boolean svhn=Commonutility.bitposition(strBmsAlertString, 9);
		_bMSAlarms.setStringVoltageLHN(findinLHN(svln,svhn));

		_bMSAlarms.setStringCurrentHN(Commonutility.bitposition(strBmsAlertString, 10));

		_bMSAlarms.setBmsSedCommunicationFD(Commonutility.bitposition(strBmsAlertString, 11));

		_bMSAlarms.setCellCommunicationFD(Commonutility.bitposition(strBmsAlertString, 16));

		boolean cvln=Commonutility.bitposition(strBmsAlertString, 17);
		boolean cvhn=Commonutility.bitposition(strBmsAlertString, 18);
		
		_bMSAlarms.setCellVoltageLHN(findinLHN(cvln,cvhn));
		 
		_bMSAlarms.setCellTemperatureHN(Commonutility.bitposition(strBmsAlertString, 19));
		
		_bMSAlarms.setEbStatus(Commonutility.bitposition(strBmsAlertString, 22)); //edited
		
		_bMSAlarms.setDgStatus(Commonutility.bitposition(strBmsAlertString, 23)); //edited
		
		
		
		_bMSAlarms.setBuzzer(Commonutility.bitposition(strBmsAlertString,31));
		 
		return _bMSAlarms;
	}
	
	private static int findinLHN(boolean ln,boolean hn) //this method decide the parameter value low or normal or high
	{
		if(ln==true)
		{
			return AlarmEnum.LOW.identifiers; 
		}
		else if(hn==true)
		{
			return AlarmEnum.HIGH.identifiers;
		}
		else
		{
			return AlarmEnum.NORMAL.identifiers;
		}
		
	}
	
}
