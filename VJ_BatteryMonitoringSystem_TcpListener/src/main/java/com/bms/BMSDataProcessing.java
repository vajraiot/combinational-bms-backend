package com.bms;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.service.BMSAlarmsService;
import com.bms.hibernate.service.CellVoltageTemperatureDataService;
import com.bms.hibernate.service.ChargerMonitoringDataService;
import com.bms.hibernate.service.ChargerStatusService;
import com.bms.hibernate.service.DeviceDataService;
import com.bms.hibernate.service.GeneralDataService;
import com.bms.hibernate.service.RawdataService;
import com.bms.hibernate.service.SiteIdLIstService;
import com.bms.packetTypeParse.BatteryMonitoringDataParser;
import com.bms.packetTypeParse.ChargerMonitoringParser;
import com.bms.packetTypeParse.GeneralDataParser;
import com.bms.pojo.DeviceDataWithLastIndex;
import com.bms.utilities.Commonutility;
import com.bms.utilities.Converters;


public class BMSDataProcessing {

	static Logger logger = LoggerFactory.getLogger(BMSDataProcessing.class.getName());
	//private static Logger logger =   Logger.getLogger(BMSDataProcessing.class);
	public void packetProcess(String packet) {
		try {
		
	
		packet=packet.toUpperCase();
		Converters cnv = new Converters();		

		/********************save rawData**************************************/
		 Rawdata	rawdata=new Rawdata();
		try {
			rawdata = new RawdataService().processAndsave(packet); 
		}
		catch(Exception ex)
		{
		logger.error("ex while saving the rawdata..");	
		}
		
		
		/**********************************************************/
		
		
		/*********************BMS Data processing*************************************/
		
		logger.info("*******************************Parsing Started for rawData id is:"+rawdata.getId()+"*******************************");
		
		GeneralData _generalData=new GeneralDataParser().parse(packet);
		if(_generalData==null) {return;}
		
		 
		logger.info("generlData parsed.");
		try {
		    List<DeviceData> _lstBatteryMonitoringData = new ArrayList<>();
		    List<ChargerMonitoringData> _lstBatteryChargerMonitoringData = new ArrayList<>();
		   
		    int bytecount = 34;
		  
		   
		    int deviceId;
		    int deviceId1;

		    if (bytecount < (packet.length() / 2) - 10) {
		        bytecount++;

		        // Get the device ID at byte 36
		        deviceId = Commonutility.hex2decimal(Commonutility.getSubstringStartAndEndIndex(bytecount, (bytecount += 1), packet));

		        // Parse initial Battery Monitoring data
		        DeviceDataWithLastIndex batteryDataB = new BatteryMonitoringDataParser().parse(deviceId, bytecount, packet);
		        int numberOfCellCount = batteryDataB.getDeviceData().getCellsConnectedCount();
		        int bytecount2 = 65 + (numberOfCellCount * 4) + 74;

		        if (deviceId >= 48 && deviceId <= 51 && (packet.length() / 2) < bytecount2) {
		            logger.info("Battery Monitoring detected for Device ID: " + deviceId);

		            DeviceDataWithLastIndex batteryData = new BatteryMonitoringDataParser().parse(deviceId, bytecount, packet);

		            if (batteryData == null) {
		                logger.warn("Battery Monitoring parsing failed for Device ID: " + deviceId);
		                return;
		            }

		            logger.info("Battery Monitoring Data parsed successfully.");
		            bytecount = batteryData.getLastIndex() - 1;
		            _lstBatteryMonitoringData.add(batteryData.getDeviceData());
		        } else if (deviceId == 64 && (packet.length() / 2) < bytecount2) {
		            logger.info("Battery Charger Monitoring detected for Device ID: " + deviceId);

		            DeviceDataWithLastIndex chargerData = new ChargerMonitoringParser().parse(deviceId, bytecount, packet);

		            if (chargerData == null) {
		                logger.warn("Charger Monitoring parsing failed for Device ID: " + deviceId);
		                return;
		            }

		            logger.info("Battery Charger Monitoring Data parsed successfully.");
		            bytecount = chargerData.getLastIndex() - 1;
		            _lstBatteryChargerMonitoringData.add(chargerData.getChargerMonitoringData());
		        } else if (bytecount2 < (packet.length() / 2)) {
		            deviceId1 = Commonutility.hex2decimal(Commonutility.getSubstringStartAndEndIndex(bytecount2, (bytecount2 += 1), packet));

		            if (deviceId >= 48 && deviceId <= 51 && deviceId1 == 64) {
		                logger.info("Combination detected: Battery Monitoring and Battery Charger Monitoring");

		                if (batteryDataB == null) {
		                    logger.warn("Battery Monitoring parsing failed for Device ID: " + deviceId);
		                    return;
		                }

		                logger.info("Battery Monitoring Data parsed successfully.");
		                bytecount = batteryDataB.getLastIndex() - 1;
		                _lstBatteryMonitoringData.add(batteryDataB.getDeviceData());

		                DeviceDataWithLastIndex chargerData = new ChargerMonitoringParser().parse(deviceId1, bytecount2, packet);

		                if (chargerData == null) {
		                    logger.warn("Battery Charger Monitoring parsing failed for Device ID: " + deviceId1);
		                    return;
		                }

		                logger.info("Battery Charger Monitoring Data parsed successfully.");
		                bytecount2 = chargerData.getLastIndex() - 1;
		                _lstBatteryChargerMonitoringData.add(chargerData.getChargerMonitoringData());
		            }
		        } else {
		            logger.warn("Unknown or Reserved Device ID: " + deviceId);
		            // Stop processing for invalid or reserved device IDs
		        }
		    }


		
		logger.info("going to save to database ");
		/*-------------------Saving To database--------------------------------*/
		/******************Eliminate FFFF values from voltage and Temp****************/
		
		/****************************************************************************/
		new GeneralDataService().save(_generalData);
		logger.info("GeneralDataService saved in database  ");
		logger.info("number of BatteryMonitoringData is:"+_lstBatteryMonitoringData.size());
		logger.info("number of Battery & charger MonitoringData is:"+_lstBatteryChargerMonitoringData.size());
		for(DeviceData instBatteryMonitoringData:_lstBatteryMonitoringData)
		{
			saveCompleteBatterMonitoringData(_generalData,instBatteryMonitoringData);
		}
		for(ChargerMonitoringData instBatteryChargerMonitoringData:_lstBatteryChargerMonitoringData)
		{
			saveCompleteBatteryChargerMonitoringData(_generalData,instBatteryChargerMonitoringData);
		}
		/*----------------------------------------------------------------------*/
		/*************save SiteId and serial number with one to many relationship***********************/
		logger.info("_lstBatteryChargerMonitoringData"+_lstBatteryChargerMonitoringData);
		_generalData.setChargerMonitoringData(_lstBatteryChargerMonitoringData);
		_generalData.setDeviceData( _lstBatteryMonitoringData);
		
		new SiteIdLIstService().saveSiteIdAndDeviceId(_generalData);
		/*********************************************************************************************/
		
		logger.info("_lstBatteryMonitoringData data Saved in database");
		logger.info("_lstBatteryChargerMonitoringData data Saved in database");
		
		new RawdataService().rawDataparseStatus(_generalData.getId(),true, rawdata);
		logger.info("****************************************************************************************************");
		}
		catch(Exception ex)
		{
			logger.error("---Exception is:"+ex.toString());
			
			new RawdataService().rawDataparseStatus(null,false, rawdata);
	      
		}
			
	/*****************************************************************************/
		}
		catch(Exception ex)
		{
			logger.error("Exception Occured in packetProcess:"+ex.toString());
		}
		finally
		{
//			new MongodbRawDataSaving().mongodbRawDataInsertion(packet);//Raw Data will save in mongo DB also.If you dont want it please comment this line.
		}
	
		
	}
	
	
	public static int saveCompleteBatterMonitoringData(GeneralData _generalData,DeviceData _batteryMonitoringData)
	{
	
      try {		
    	DeviceData  _instbatteryMonitoringData	=new DeviceDataService().save(_generalData,_batteryMonitoringData);

		new BMSAlarmsService().save(_instbatteryMonitoringData);

		new CellVoltageTemperatureDataService().save(_instbatteryMonitoringData);
		return 1;
      }
      catch(Exception ex)
      {
    	  logger.info("Exception in saveCompleteBatterMonitoringData method :"+ex.toString());
     	  return 0;
      }
	}
	public static int saveCompleteBatteryChargerMonitoringData(GeneralData _generalData,ChargerMonitoringData _batteryChargerMonitoringData)
	{
	
      try {		
    	  ChargerMonitoringData  _instbatteryChargerMonitoringData	=new ChargerMonitoringDataService().save(_generalData,_batteryChargerMonitoringData);

		new ChargerStatusService().save(_instbatteryChargerMonitoringData);

		return 1;
      }
      catch(Exception ex)
      {
    	  logger.info("Exception in saveCompleteBatteryChargerMonitoringData method :"+ex.toString());
     	  return 0;
      }
	}
	
	public static GeneralData replaceFFFFValuesWithPreviousValues(GeneralData instGeneralData)
	{
		DeviceData instPresentBatteryMonitoringData=instGeneralData.getDeviceData().get(0);
		List<CellVoltageTemperatureData> lstPresentCellVoltageData= instPresentBatteryMonitoringData.getCellVoltageTemperatureData();
		
		boolean isContainsFFFF=false;
		for(int i=0;i<instPresentBatteryMonitoringData.getCellsConnectedCount();i++)
		{
			if(instPresentBatteryMonitoringData.getCellVoltageTemperatureData().get(i).getCellTemperature()>(65000f)||(instPresentBatteryMonitoringData.getCellVoltageTemperatureData().get(i).getCellVoltage()>(65f))) //here voltage is 65 because it is already devide by 1000 thats y.
			{
				isContainsFFFF=true;
				break;
			}
			else
			{
				isContainsFFFF=false;
			}
		}
		
		if(isContainsFFFF==false) //if dont have the ffff then no need to change values.
		{
			return instGeneralData;
		}
		
		
		
		return instGeneralData;
	}
}
