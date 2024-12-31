package com.bms.miscellaneous;

import java.util.ArrayList;
import java.util.List;

import com.bms.entity.BMSAlarms;
import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.enums.AlarmEnum;
import com.bms.pojo.AlarmsDTO;
import com.bms.pojo.AllSitesAlarmsDTO;
import com.bms.pojo.BMSAlarmStatusDTO;
import com.bms.pojo.DeviceDataDTO;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.CellParamDTO;

public class ApplicationConversions {
	public  AllSitesAlarmsDTO cnvrtGeneralDataToAllSitesAlarmDTO(GeneralData _generalData)
	{
		try {
		AllSitesAlarmsDTO rtnAllSitesAlarmsDTO=new AllSitesAlarmsDTO();
		BMSAlarms _bmsAlarms=new BMSAlarms();
		String strSiteId;
		String strSerailNumber;
		
	/*	if(_generalData==null)
		{
			return null;
		}
		
		if(_generalData.getDeviceData()==null)
		{
			return null;
		}
		if(_generalData.getDeviceData().size()<1)
		{
			return null;
		}
	    if(_generalData.getDeviceData().get(0)==null)
	    {
	    	return null;
	    }
	    
	    if(_generalData.getDeviceData().get(0).getBMSAlarms()==null)
	    {
	    	
	    }*/
	    
		try {
			strSiteId=_generalData.getSiteId();
			strSerailNumber=_generalData.getDeviceData().get(0).getSerialNumber();
			_bmsAlarms=_generalData.getDeviceData().get(0).getBMSAlarms();
		}
		catch(Exception ex)
		{
			System.out.println(" exception occured while getting the _bmsAlarms from general data is:"+ex.toString());
			return null;
		}
		if(_bmsAlarms==null)
		{
			return null;
		}
		
		rtnAllSitesAlarmsDTO=AllSitesAlarmsDTO.builder()
				.siteId(strSiteId)
				.serialNumber(strSerailNumber)
				.bankCycleDC(_bmsAlarms.isBankCycleDC())
				.ambientTemperatureHN(_bmsAlarms.isAmbientTemperatureHN())
				.socLN(_bmsAlarms.isSocLN())
				.stringVoltageLHN(_bmsAlarms.getStringVoltageLHN())
				.stringCurrentHN(_bmsAlarms.isStringCurrentHN())
				.bmsSedCommunicationFD(_bmsAlarms.isBmsSedCommunicationFD())
				.cellCommunicationFD(_bmsAlarms.isCellCommunicationFD())
				.cellVoltageLHN(_bmsAlarms.getCellVoltageLHN())
				.cellTemperatureHN(_bmsAlarms.isCellTemperatureHN())
				.buzzer(_bmsAlarms.isBuzzer())
				.serverTime(_bmsAlarms.getServerTime())
				.build();
				
		return rtnAllSitesAlarmsDTO;
		}
		catch(Exception ex)
		{
			System.out.println("Exception occured in cnvrtGeneralDataToAllSitesAlarmDTO is:"+ex.toString());
			return null;
		}
	}
}
