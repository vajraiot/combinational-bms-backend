package com.bms.miscellaneous;

import java.util.ArrayList;
import java.util.List;


import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.enums.AlarmEnum;
import com.bms.pojo.AlarmsDTO;
import com.bms.pojo.BMSAlarmStatusDTO;
import com.bms.pojo.DeviceDataDTO;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.CellParamDTO;

public class EntityToPojoConversion {
//
//	public static GeneralDataDTO  cnvrtGeneralDataDTO(GeneralData instGeneralData)
//	{
//
//		List<DeviceDataDTO> lstDeviceDataDTO=new ArrayList<DeviceDataDTO>();
//		lstDeviceDataDTO.add(cnvrtDeviceDataDTO(instGeneralData.getDeviceData().get(0)));
//		
//		GeneralDataDTO _generalDataDTO=GeneralDataDTO.builder()
//				.id(instGeneralData.getId())
//				.startPacket(instGeneralData.getStartPacket())
//				.protocalVersion(instGeneralData.getProtocalVersion())
//				.dataIdentifier(instGeneralData.getDataIdentifier())
//				.date(instGeneralData.getDate())
//				.time(instGeneralData.getTime())
//				.packetDateTime((instGeneralData.getPacketDateTime()))
//				.serverTime((instGeneralData.getServerTime()))
//				.siteId(instGeneralData.getSiteId())
//				.deviceDataDTO(lstDeviceDataDTO)
//	
//				.build();
//		
//	
//	return _generalDataDTO;
//	}
//	
//
//	public static DeviceDataDTO cnvrtDeviceDataDTO(DeviceData deviceData )
//	{
//		DeviceDataDTO rtnBatteryMonitoringDataDTO = new DeviceDataDTO();
//		
//		List<CellVoltageTemperatureDataDTO> instListcellVoltageTemperatureDataDTO=cnvrtCellVoltageTemperatureData(deviceData.getCellVoltageTemperatureData());
//		
//		rtnBatteryMonitoringDataDTO=DeviceDataDTO.builder()
//				.deviceId(deviceData.getDeviceId())
//				.bmsManufacturerID(deviceData.getBmsManufacturerID())
//				.serialNumber(deviceData.getSerialNumber())
//				.installationDate(deviceData.getInstallationDate())
//				.cellsConnectedCount(deviceData.getCellsConnectedCount())
//				.problemCells(deviceData.getProblemCells())
//				
//				.cellVoltageTemperatureDataDTO(instListcellVoltageTemperatureDataDTO)
//				
//				
//				.stringvoltage(deviceData.getStringvoltage())
//				.systemPeakCurrentInChargeOneCycle(deviceData.getSystemPeakCurrentInChargeOneCycle())
//				
//				.averageDischargingCurrent(deviceData.getAverageDischargingCurrent())
//				.averageChargingCurrent(deviceData.getAverageChargingCurrent())
//				
//				.ahInForOneChargeCycle(deviceData.getAhInForOneChargeCycle())
//				.ahOutForOneDischargeCycle(deviceData.getAhOutForOneDischargeCycle())
//				
//				.cumulativeAHIn(deviceData.getCumulativeAHIn())
//				.cumulativeAHOut(deviceData.getCumulativeAHOut())
//				
//				.chargeTimeCycle(deviceData.getChargeTimeCycle())
//				.dischargeTimeCycle(deviceData.getDischargeTimeCycle())
//				
//				.totalChargingEnergy(deviceData.getTotalChargingEnergy())
//				
//				.totalDischargingEnergy(deviceData.getTotalDischargingEnergy())
//				.everyHourAvgTemp(deviceData.getEveryHourAvgTemp())
//				.cumulativeTotalAvgTempEveryHour(deviceData.getCumulativeTotalAvgTempEveryHour())
//				.chargeOrDischargeCycle(deviceData.getChargeOrDischargeCycle())
//				.socLatestValueForEveryCycle(deviceData.getSocLatestValueForEveryCycle())
//				.dodLatestValueForEveryCycle(deviceData.getDodLatestValueForEveryCycle())
//				.systemPeakCurrentInDischargeOneCycle(deviceData.getSystemPeakCurrentInDischargeOneCycle())
//				.instantaneousCurrent(deviceData.getInstantaneousCurrent())
//				.ambientTemperature(deviceData.getAmbientTemperature())
//				.batteryRunHours(deviceData.getBatteryRunHours())
//				
//				//.alarmsDTO(simplifyingAlarmStatus(deviceData.getBMSAlarmStatus()))
//				.build();
//		return rtnBatteryMonitoringDataDTO;
//	}
//	
//	
//	public static BMSAlarmStatusDTO getBMSAlarmStatus(BMSAlarmStatus bMSAlarmStatus)
//	{
//		BMSAlarmStatusDTO rtnBMSAlarmStatusDTO=new BMSAlarmStatusDTO();
//		rtnBMSAlarmStatusDTO=BMSAlarmStatusDTO.builder()
//				.bankDischargeCycle(bMSAlarmStatus.isBankDischargeCycle())
//				.AmbientTemperatureHN(bMSAlarmStatus.isAmbientTemperatureHN())
//				.socLN(bMSAlarmStatus.isSocLN())
//				
//				.stringVoltageLN(bMSAlarmStatus.isStringVoltageLN())
//				.stringVoltageHN(bMSAlarmStatus.isStringVoltageHN())
//				
//				.bmsSedCommunication(bMSAlarmStatus.isBmsSedCommunication())
//				.cellCommunication(bMSAlarmStatus.isCellCommunication())
//				
//				.cellVoltageLN(bMSAlarmStatus.isCellVoltageLN())
//				.cellVoltageHN(bMSAlarmStatus.isCellVoltageHN())
//				
//				
//				
//				
//				.build();
//		
//		return rtnBMSAlarmStatusDTO;
//	}
//	
//	
//	public static List<CellVoltageTemperatureDataDTO> cnvrtCellVoltageTemperatureData(List<CellVoltageTemperatureData> lstCellVoltageTemperatureData)
//	{
//		 List<CellVoltageTemperatureDataDTO> rtnCellTemperatureDataDTO=new ArrayList<CellVoltageTemperatureDataDTO>();
//		 
//		 
//		 for(CellVoltageTemperatureData inst:lstCellVoltageTemperatureData)
//		 {
//			 CellVoltageTemperatureDataDTO _cellTemperatureDataDTO=CellVoltageTemperatureDataDTO.builder()
//					 .cellNumber(inst.getCellNumber())
//					 .cellTemperature(inst.getCellTemperature())
//					 .cellVoltage(inst.getCellVoltage())
//					 .build();
//			 
//			 rtnCellTemperatureDataDTO.add(_cellTemperatureDataDTO);
//		 }
//		 return rtnCellTemperatureDataDTO;
//	}
//	
//	public static AlarmsDTO simplifyingAlarmStatus(BMSAlarmStatus _bMSAlarmStatus)
//	{
//         AlarmsDTO rtnAlarmsDTO=new AlarmsDTO();
//         rtnAlarmsDTO.setBankCycleDC(boolToInt(_bMSAlarmStatus.isBankDischargeCycle()));
//         
//         rtnAlarmsDTO.setAmbientTemperatureHN(boolToInt(_bMSAlarmStatus.isAmbientTemperatureHN()));
//         
//         rtnAlarmsDTO.setSocLN(boolToInt(_bMSAlarmStatus.isSocLN()));
//
//         rtnAlarmsDTO.setStringVoltageLHN(findinLHN(_bMSAlarmStatus.isStringVoltageLN(),_bMSAlarmStatus.isStringVoltageHN()));
//         
//         rtnAlarmsDTO.setBmsSedCommunicationFD(boolToInt(_bMSAlarmStatus.isBmsSedCommunication()));
//
//         rtnAlarmsDTO.setCellCommunicationFD(boolToInt(_bMSAlarmStatus.isCellCommunication()));
//
//         rtnAlarmsDTO.setCellVoltageLHN(findinLHN(_bMSAlarmStatus.isCellVoltageLN(),_bMSAlarmStatus.isCellVoltageHN()));
//         
//         rtnAlarmsDTO.setCellTemperatureHN(boolToInt(_bMSAlarmStatus.isCellTemperatureHN()));
//         
//         return rtnAlarmsDTO;
//	}
//	
//	public AlarmsDTO simplifyingAlarmStatus(BMSAlarmStatusDTO _bMSAlarmStatus )//overided method
//	{
//         AlarmsDTO rtnAlarmsDTO=new AlarmsDTO();
//         rtnAlarmsDTO.setBankCycleDC(boolToInt(_bMSAlarmStatus.isBankDischargeCycle()));
//         
//         rtnAlarmsDTO.setAmbientTemperatureHN(boolToInt(_bMSAlarmStatus.isAmbientTemperatureHN()));
//         
//         rtnAlarmsDTO.setSocLN(boolToInt(_bMSAlarmStatus.isSocLN()));
//
//         rtnAlarmsDTO.setStringVoltageLHN(findinLHN(_bMSAlarmStatus.isStringVoltageLN(),_bMSAlarmStatus.isStringVoltageHN()));
//         
//         rtnAlarmsDTO.setBmsSedCommunicationFD(boolToInt(_bMSAlarmStatus.isBmsSedCommunication()));
//
//         rtnAlarmsDTO.setCellCommunicationFD(boolToInt(_bMSAlarmStatus.isCellCommunication()));
//
//         rtnAlarmsDTO.setCellVoltageLHN(findinLHN(_bMSAlarmStatus.isCellVoltageLN(),_bMSAlarmStatus.isCellVoltageHN()));
//         
//         rtnAlarmsDTO.setCellTemperatureHN(boolToInt(_bMSAlarmStatus.isCellTemperatureHN()));
//         
//         return rtnAlarmsDTO;
//	}
//	private static int findinLHN(boolean ln,boolean hn) //this method decide the parameter value low or normal or high
//	{
//		if(ln==true)
//		{
//			return AlarmEnum.LOW.identifiers; 
//		}
//		else
//		{
//			if(hn==true)
//			{
//				return AlarmEnum.HIGH.identifiers;
//			}
//			else
//			{
//				return AlarmEnum.NORMAL.identifiers;
//			}
//		}
//	}
//	
	public static int boolToInt(boolean bln)
	{
		return bln?1:0;
	}
}
