package com.bms.packetTypeParse;

import com.bms.entity.BMSAlarmStatus;
import com.bms.utilities.Commonutility;


public class BMSAlarmStatusParser {

	
	//ex:0001050A
	
	public BMSAlarmStatus parse(String strRawData)
	{
		
		
		BMSAlarmStatus _bMSAlarmStatus=new BMSAlarmStatus();
		
				 
		 _bMSAlarmStatus.setBankDischargeCycle(Commonutility.bitposition(strRawData, 1));
		 
		 _bMSAlarmStatus.setAmbientTemperatureHN(Commonutility.bitposition(strRawData, 2));
		 
		 
		 _bMSAlarmStatus.setSocLN(Commonutility.bitposition(strRawData, 3));

		 _bMSAlarmStatus.setStringVoltageLN(Commonutility.bitposition(strRawData, 8));

		 _bMSAlarmStatus.setStringVoltageHN(Commonutility.bitposition(strRawData, 9));

		 _bMSAlarmStatus.setStringCurrentHN(Commonutility.bitposition(strRawData, 10));

		 _bMSAlarmStatus.setBmsSedCommunication(Commonutility.bitposition(strRawData, 11));

		 _bMSAlarmStatus.setCellCommunication(Commonutility.bitposition(strRawData, 16));

		 _bMSAlarmStatus.setCellVoltageLN(Commonutility.bitposition(strRawData, 17));

		 _bMSAlarmStatus.setCellVoltageHN(Commonutility.bitposition(strRawData, 18));
		 
		 
		 _bMSAlarmStatus.setCellTemperatureHN(Commonutility.bitposition(strRawData, 19));
		 
		 _bMSAlarmStatus.setEbStatus(Commonutility.bitposition(strRawData, 22)); //edited
		 
		 _bMSAlarmStatus.setDgStatus(Commonutility.bitposition(strRawData, 23)); //edited
		 
		
		return _bMSAlarmStatus;
	}
	
}
