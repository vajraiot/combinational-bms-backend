package com.bms.utilities;

import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.DeviceData;
import com.bms.entity.ManufacturerDetails;
import com.bms.entity.SerialNumberList;
import com.bms.entity.SiteIdList;
import com.bms.enums.AlarmEnum;
import com.bms.miscellaneous.StartDateAndEndDate;
import com.bms.pojo.AlarmsParamDTO;
import com.bms.pojo.CellParamDTO;
import com.bms.pojo.CellVoltageTemperatureDataDTO;
import com.bms.pojo.ChargerDTO;
import com.bms.pojo.DayWiseDataDTO;
import com.bms.pojo.PlainManufacturerDetails;
import com.bms.pojo.SpecificCellDetailsDTO;
import com.bms.pojo.StringParamDTO;

public class ApplicationUtility {
	public static String  ConvertLHNtoString(int lhnValue)
	{
		String rtnString="";		
		
		if(lhnValue==AlarmEnum.LOW.identifiers)
		{
			rtnString=AlarmEnum.LOW.name;
		}
		else if(lhnValue==AlarmEnum.NORMAL.identifiers)
		{
			rtnString=AlarmEnum.NORMAL.name;
		}
		else if(lhnValue==AlarmEnum.HIGH.identifiers)
		{
			rtnString=AlarmEnum.HIGH.name;
		}
		return rtnString;
	}
	
	/**********************************Alarms Convertion**************************/
	public static List<AlarmsParamDTO> convertAlarmsParamDTO(List<DeviceData>  _lstDeviceData)
	{
		List<AlarmsParamDTO> rtnLstAlarmsParamDTO=new ArrayList<AlarmsParamDTO>();
		for(DeviceData instDeviceData:_lstDeviceData)
		{
			AlarmsParamDTO alarmsParamDTO= convertAlarmsParamDTO(instDeviceData);
			rtnLstAlarmsParamDTO.add(alarmsParamDTO);
		}
		return rtnLstAlarmsParamDTO;
	}
	public static AlarmsParamDTO convertAlarmsParamDTO(DeviceData  _deviceData)
	{
		AlarmsParamDTO rtnAlarmsParamDTO=new AlarmsParamDTO();
           rtnAlarmsParamDTO=AlarmsParamDTO.builder()
				.id(_deviceData.getId())
        		.siteId(_deviceData.getGeneralData().getSiteId())
				.serialNumber(_deviceData.getSerialNumber())
				.packetDateTime(_deviceData.getGeneralData().getPacketDateTime())
				.serverTime(_deviceData.getServerTime())
							
				.bankCycle(_deviceData.getBMSAlarms().isBankCycleDC()?"DisCharging":"Charging")
				
				.ambientTemperature(_deviceData.getBMSAlarms().isAmbientTemperatureHN()?"High":"Normal")
				
				.soc(_deviceData.getBMSAlarms().isSocLN()?"Low":"Normal")
									
				.stringVoltage(ConvertLHNtoString(_deviceData.getBMSAlarms().getStringVoltageLHN()))
				
				.stringCurrent(_deviceData.getBMSAlarms().isStringCurrentHN()?"High":"Normal")
				
				.bmsSedCommunication(_deviceData.getBMSAlarms().isBmsSedCommunicationFD()?"Not Communicating":"Communicating")
				
				.cellCommunication(_deviceData.getBMSAlarms().isCellCommunicationFD()?"Not Communicating":"Communicating")
				
				.cellVoltage(ConvertLHNtoString(_deviceData.getBMSAlarms().getCellVoltageLHN()))
				
				.cellTemperature(_deviceData.getBMSAlarms().isCellTemperatureHN()?"High":"Normal")
				
				.buzzer(_deviceData.getBMSAlarms().isBuzzer()?"ON":"OFF")
				.bMSAlarmsString(_deviceData.getBMSAlarmsString())
				
				
				.build();
		
		return rtnAlarmsParamDTO;
		
	}
	
	
	/**********************************String Convertion**************************/
	public static List<StringParamDTO> convertStringParamDTO(List<DeviceData>  _lstdeviceData)
	{
		List<StringParamDTO> rtnLstStringParamDTO=new ArrayList<StringParamDTO>();
		for (DeviceData instDeviceData : _lstdeviceData) {
			StringParamDTO instStringParamDTO= convertStringParamDTO(instDeviceData);         	
			rtnLstStringParamDTO.add(instStringParamDTO);
		}
		return rtnLstStringParamDTO;
	}
	public static StringParamDTO convertStringParamDTO(DeviceData _deviceData)
	{
		StringParamDTO rtnStringParamDTO=new StringParamDTO();
		rtnStringParamDTO=StringParamDTO.builder()
				.siteId(_deviceData.getGeneralData().getSiteId())
				.serialNumber(_deviceData.getSerialNumber())
				.id(_deviceData.getId())
				.deviceId(_deviceData.getDeviceId())
				.bmsManufacturerID(_deviceData.getBmsManufacturerID())
				.installationDate(_deviceData.getInstallationDate())
				.bMSAlarmsString(_deviceData.getBMSAlarmsString())
				.cellsConnectedCount(_deviceData.getCellsConnectedCount())
				.problemCells(_deviceData.getProblemCells())
				.stringVoltage(_deviceData.getStringvoltage())
				.systemPeakCurrentInChargeOneCycle(_deviceData.getSystemPeakCurrentInChargeOneCycle())
				.averageDischargingCurrent(_deviceData.getAverageChargingCurrent())
				.averageChargingCurrent(_deviceData.getAverageChargingCurrent())
				.ahInForOneChargeCycle(_deviceData.getAhInForOneChargeCycle())
				.ahOutForOneDischargeCycle(_deviceData.getAhOutForOneDischargeCycle())
				.cumulativeAHIn(_deviceData.getCumulativeAHIn())
				.cumulativeAHOut(_deviceData.getCumulativeAHOut())
				.chargeTimeCycle(_deviceData.getChargeTimeCycle())
				.dischargeTimeCycle(_deviceData.getDischargeTimeCycle())
				.totalChargingEnergy(_deviceData.getTotalChargingEnergy())
				.totalDischargingEnergy(_deviceData.getTotalDischargingEnergy())
				.everyHourAvgTemp(_deviceData.getEveryHourAvgTemp())
				.cumulativeTotalAvgTempEveryHour(_deviceData.getCumulativeTotalAvgTempEveryHour())
				.chargeOrDischargeCycle(_deviceData.getChargeOrDischargeCycle())
				.socLatestValueForEveryCycle(_deviceData.getSocLatestValueForEveryCycle())
				.dodLatestValueForEveryCycle(_deviceData.getDodLatestValueForEveryCycle())
				.systemPeakCurrentInDischargeOneCycle(_deviceData.getSystemPeakCurrentInChargeOneCycle())
				.instantaneousCurrent(_deviceData.getInstantaneousCurrent())
				.ambientTemperature(_deviceData.getAmbientTemperature())
				.batteryRunHours(_deviceData.getBatteryRunHours())
				.serverTime(_deviceData.getServerTime())
				.packetDateTime(_deviceData.getGeneralData().getPacketDateTime())
				.build();
		
		return rtnStringParamDTO;
	}
	public static List<ChargerDTO> convertChargerDTO(List<ChargerMonitoringData> _lstChargerMonitoringData) {
	    List<ChargerDTO> rtnLstChargerDTO = new ArrayList<ChargerDTO>();
	    for (ChargerMonitoringData instChargerMonitoringData : _lstChargerMonitoringData) {
	        ChargerDTO instChargerDTO = convertChargerDTO(instChargerMonitoringData);          
	        rtnLstChargerDTO.add(instChargerDTO);
	    }
	    return rtnLstChargerDTO;
	}

	public static ChargerDTO convertChargerDTO(ChargerMonitoringData _chargerMonitoringData) {
	    ChargerDTO rtnChargerDTO = new ChargerDTO();
	    rtnChargerDTO = ChargerDTO.builder()
	            .id(_chargerMonitoringData.getChargerStatusData().getId())
	            .inputMains(_chargerMonitoringData.getChargerStatusData().isInputMains())
	            .inputFuse(_chargerMonitoringData.getChargerStatusData().isInputFuse())
	            .rectifierFuse(_chargerMonitoringData.getChargerStatusData().isRectifierFuse())
	            .filterFuse(_chargerMonitoringData.getChargerStatusData().isFilterFuse())
	            .dcVoltageOLN(_chargerMonitoringData.getChargerStatusData().getDcVoltageOLN())
	            .outputFuse(_chargerMonitoringData.getChargerStatusData().isOutputFuse())
	            .acVoltageULN(_chargerMonitoringData.getChargerStatusData().getAcVoltageULN())
	            .chargerLoad(_chargerMonitoringData.getChargerStatusData().isChargerLoad())
	            .alarmSupplyFuse(_chargerMonitoringData.getChargerStatusData().isAlarmSupplyFuse())
	            .chargerTrip(_chargerMonitoringData.getChargerStatusData().isChargerTrip())
	            .outputMccb(_chargerMonitoringData.getChargerStatusData().isOutputMccb())
	            .batteryCondition(_chargerMonitoringData.getChargerStatusData().isBatteryCondition())
	            .testPushButton(_chargerMonitoringData.getChargerStatusData().isTestPushButton())
	            .resetPushButton(_chargerMonitoringData.getChargerStatusData().isResetPushButton())
	            .build();

	    return rtnChargerDTO;
	}

	
	/**********************************Cell Convertion**************************/
	public static List<CellParamDTO> convertCellParamDTO(List<DeviceData>  _lstDeviceData)
	{
		List<CellParamDTO> rtnLstCellParamDTO=new ArrayList<CellParamDTO>();
		for(DeviceData instDeviceData:_lstDeviceData)
		{
			CellParamDTO instCellParamDTO= convertCellParamDTO(instDeviceData);
			rtnLstCellParamDTO.add(instCellParamDTO);
		}
		return rtnLstCellParamDTO;
	}
	public static CellParamDTO convertCellParamDTO(DeviceData  _deviceData)
	{
		try {
		CellParamDTO rtnCellParamDTO=new CellParamDTO();
		List<CellVoltageTemperatureDataDTO> instLstCellVoltageTemperatureDataDTO=new ArrayList<CellVoltageTemperatureDataDTO>();
		if(_deviceData==null)
		{
			return null;
		}
		if(_deviceData.getCellVoltageTemperatureData()==null)
		{
			return null;
		}
		
		
		for(CellVoltageTemperatureData instCellVoltageTemperatureData :_deviceData.getCellVoltageTemperatureData())
		{
			
			CellVoltageTemperatureDataDTO instCellParamDTO=new CellVoltageTemperatureDataDTO();
			instCellParamDTO=CellVoltageTemperatureDataDTO.builder()
					.cellNumber(instCellVoltageTemperatureData.getCellNumber())
					.cellVoltage(instCellVoltageTemperatureData.getCellVoltage())
					.cellTemperature(instCellVoltageTemperatureData.getCellTemperature())
					.build();
			instLstCellVoltageTemperatureDataDTO.add(instCellParamDTO);
		}

		rtnCellParamDTO=CellParamDTO.builder()
				.id(_deviceData.getId())
				.siteId(_deviceData.getGeneralData().getSiteId())
				.serialNumber(_deviceData.getSerialNumber())
				.packetDateTime(_deviceData.getGeneralData().getPacketDateTime())
				.serverTime(_deviceData.getGeneralData().getServerTime())
				.lstCellVoltageTemperatureData(instLstCellVoltageTemperatureDataDTO)
				.build();
		return rtnCellParamDTO;
		}
		catch(Exception ex)
		{
			System.out.println("Exception occured in convertCellParamDTO is:"+ex.toString());
			return null;
		}
	}
	
 public static List<PlainManufacturerDetails> covertToPlainManufacturerDetails(List<SiteIdList> _lstsiteIdList)
	{
		try {
			List<PlainManufacturerDetails> rtnLstPlainManufacturerDetails=new ArrayList<PlainManufacturerDetails>();
			
		   for(SiteIdList instSiteIdList:_lstsiteIdList)
		   {
			   List<PlainManufacturerDetails> lclLstPlainManufacturerDetails=  covertToPlainManufacturerDetails(instSiteIdList);
			   if(lclLstPlainManufacturerDetails==null)
			   {
				   continue;
			   }
			   rtnLstPlainManufacturerDetails.addAll(lclLstPlainManufacturerDetails);
			   
		   }
			return rtnLstPlainManufacturerDetails;
		}
		catch(Exception ex)
		{
			System.out.println("Exception occured:"+ex.toString());
			return null;
		}
	}
	
  public static List<PlainManufacturerDetails> covertToPlainManufacturerDetails(SiteIdList _siteIdList)
	{
		try {
			List<PlainManufacturerDetails> rtnLstPlainManufacturerDetails=new ArrayList<PlainManufacturerDetails>();
			
		  for(SerialNumberList instSerialNumberList:_siteIdList.getLstSerialNumberList())
		  {
			  PlainManufacturerDetails plainManufacturerDetails=new PlainManufacturerDetails();
			  if(instSerialNumberList.getManufacturerDetails()==null)
			  {
				  continue;
			  }
			  ManufacturerDetails mds= instSerialNumberList.getManufacturerDetails();
			  plainManufacturerDetails=PlainManufacturerDetails.builder()
					  .id(mds.getId())
					  .siteId(_siteIdList.getSiteId())
					  .serialNumber(instSerialNumberList.getSerialNumber())
					  .firstUsedDate(mds.getFirstUsedDate())
					  .batterySerialNumber(mds.getBatterySerialNumber())
					  .batteryBankType(mds.getBatteryBankType())
					  .ahCapacity(mds.getAhCapacity())
					  .manifactureName(mds.getManifactureName())
					  .designVoltage(mds.getDesignVoltage())
					  .individualCellVoltage(mds.getIndividualCellVoltage())
					  .serverTime(mds.getServerTime())
					  .build();	 
			  rtnLstPlainManufacturerDetails.add(plainManufacturerDetails);
		  }
			
			return rtnLstPlainManufacturerDetails;
		}
		catch(Exception ex)
		{
			System.out.println("Exception occured:"+ex.toString());
			return null;
		}
	}
  
  public static ManufacturerDetails covertToManufacturerDetails(PlainManufacturerDetails _plainManufacturerDetails)
  {
	  ManufacturerDetails rtnManufacturerDetails=new ManufacturerDetails();
	  rtnManufacturerDetails=ManufacturerDetails.builder()
			  .id(_plainManufacturerDetails.getId())
			  
			  .firstUsedDate(_plainManufacturerDetails.getFirstUsedDate())
			  .batterySerialNumber(_plainManufacturerDetails.getBatterySerialNumber())
			  .batteryBankType(_plainManufacturerDetails.getBatteryBankType())
			  .ahCapacity(_plainManufacturerDetails.getAhCapacity())
			  .manifactureName(_plainManufacturerDetails.getManifactureName())
			  .designVoltage(_plainManufacturerDetails.getDesignVoltage())
			  .individualCellVoltage(_plainManufacturerDetails.getIndividualCellVoltage())
			  .serverTime(_plainManufacturerDetails.getServerTime())
			  .build();
	  return rtnManufacturerDetails;
  }
  
  public static  List<SpecificCellDetailsDTO> convertToSpecificCellDetailsDTO(List<CellVoltageTemperatureData> lstCellVoltageTemperatureData)
  {
	  List<SpecificCellDetailsDTO> lstSpecificCellDetailsDTO=new ArrayList<SpecificCellDetailsDTO>();
	  for(CellVoltageTemperatureData instCellVoltageTemperatureData:lstCellVoltageTemperatureData)
	  {

		  SpecificCellDetailsDTO  lclSpecificCellDetailsDTO= convertToSpecificCellDetailsDTO(instCellVoltageTemperatureData);
		  if(lclSpecificCellDetailsDTO==null)
		  {
			  continue;
		  }
		  
		  lstSpecificCellDetailsDTO.add(lclSpecificCellDetailsDTO);
	  }
	  
	  return lstSpecificCellDetailsDTO;
	  
  }
  
  public   static SpecificCellDetailsDTO convertToSpecificCellDetailsDTO(CellVoltageTemperatureData _cellVoltageTemperatureData)
  {
	  try {
	  SpecificCellDetailsDTO rtnSpecificCellDetailsDTO=new SpecificCellDetailsDTO();
	  
	  rtnSpecificCellDetailsDTO=SpecificCellDetailsDTO.builder()
		
			  .id(_cellVoltageTemperatureData.getId())
			  .serialNumber(_cellVoltageTemperatureData.getDeviceData().getSerialNumber())
			  .siteId(_cellVoltageTemperatureData.getDeviceData().getGeneralData().getSiteId())
			  .cellNumber(_cellVoltageTemperatureData.getCellNumber())
			  .cellVoltage(_cellVoltageTemperatureData.getCellVoltage())
			  .cellTemperature(_cellVoltageTemperatureData.getCellTemperature())
			  .packetDateTime(_cellVoltageTemperatureData.getDeviceData().getGeneralData().getPacketDateTime())
			  .serverTime(_cellVoltageTemperatureData.getServerTime())			  
			  .build();
	  
	  return rtnSpecificCellDetailsDTO;
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception Occured while Converting To Specific Cell Details:"+ex.toString());
		  return null;
	  }
  }
  
  public static DayWiseDataDTO processingDayWiseData(DeviceData fistDeviceDateOnDate,DeviceData lastDeviceDateOnDate)
  {
	  DayWiseDataDTO rtnDayWiseDataDTO =new DayWiseDataDTO();
	  
	  rtnDayWiseDataDTO=DayWiseDataDTO.builder()
			  
			  .dayWiseDate(fistDeviceDateOnDate.getGeneralData().getPacketDateTime())
			  .chargeOrDischargeCycle(lastDeviceDateOnDate.getChargeOrDischargeCycle()-fistDeviceDateOnDate.getChargeOrDischargeCycle())
			
			  .cumulativeAHIn(lastDeviceDateOnDate.getCumulativeAHIn()-fistDeviceDateOnDate.getCumulativeAHIn())
			  
			  .chargeOrDischargeCycle(lastDeviceDateOnDate.getChargeOrDischargeCycle()-fistDeviceDateOnDate.getChargeOrDischargeCycle())
			  
			  .cumulativeAHOut(lastDeviceDateOnDate.getCumulativeAHOut()-fistDeviceDateOnDate.getCumulativeAHOut())
			  
			  .totalChargingEnergy(lastDeviceDateOnDate.getTotalChargingEnergy()-fistDeviceDateOnDate.getTotalChargingEnergy())
			  
			  .totalDischargingEnergy(lastDeviceDateOnDate.getTotalDischargingEnergy()-fistDeviceDateOnDate.getTotalDischargingEnergy())
			  
			  .batteryRunHours(lastDeviceDateOnDate.getBatteryRunHours())
			  
			  .build();
	  
	  
	  return rtnDayWiseDataDTO;
  }
	
	
}
