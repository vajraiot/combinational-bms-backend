package com.bms.packetTypeParse;

import java.util.List;

import com.bms.packetTypeParse.ChargerStatusParser;
import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.ChargerStatusData;
import com.bms.pojo.DeviceDataWithLastIndex;
import com.bms.utilities.Commonutility;

public class ChargerMonitoringParser {
	public DeviceDataWithLastIndex parse(int deviceId,int bytecount,String strRawData)
	{
		try {
		//DeviceDataWithLastIndex rtnBatteryMonitoringDataWithLastIndex=new DeviceDataWithLastIndex();
		
		ChargerMonitoringData _chargerMonitoringdata =new  ChargerMonitoringData() ;
		_chargerMonitoringdata.setDeviceId(deviceId);
	
		_chargerMonitoringdata.setAcVoltage(Commonutility.hex2ieee754tofloat(Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=4),strRawData)));
	
		_chargerMonitoringdata.setAcCurrent(Commonutility.hex2ieee754tofloat(Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=4),strRawData)));
	
		_chargerMonitoringdata.setFrequency(Commonutility.hex2ieee754tofloat(Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=4),strRawData)));
	
		_chargerMonitoringdata.setEnergy(Commonutility.hex2ieee754tofloat(Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=4),strRawData)));
	

		String strChargerStatus=Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=4),strRawData);
		_chargerMonitoringdata.setChargerStatus(strChargerStatus);
		
		System.out.println("strChargerStatus is:"+strChargerStatus);
		
		ChargerStatusData _chargrStatusData=new ChargerStatusParser().parse(strChargerStatus);
		_chargerMonitoringdata.setChargerStatusData(_chargrStatusData);
		
		
		
		
		
	DeviceDataWithLastIndex _rtnchargerMonitoringDataWithLastIndex=DeviceDataWithLastIndex.builder()
			.chargerMonitoringData(_chargerMonitoringdata)
			.lastIndex(bytecount)
			.build();
			
	return _rtnchargerMonitoringDataWithLastIndex;
	    }
	catch(Exception ex)
		{
		 return null;
		}
 }
}
