package com.bms.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;
import com.bms.enums.AlarmEnum;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.miscellaneous.EntityToPojoConversion;
import com.bms.pojo.AlarmsParamDTO;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.CellParamDTO;
import com.bms.pojo.CellVoltageTemperatureDataDTO;
import com.bms.pojo.ChargerDTO;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.StringParamDTO;
import com.bms.repositypck.ChargerMonitoringDataRepos;
import com.bms.repositypck.DeviceDataRepos;
import com.bms.repositypck.GeneralDataRepos;
import com.bms.repositypck.RawdataRepos;
import com.bms.repositypck.SiteIdListRepos;
import com.bms.repositypck.SiteLocationRepos;
import com.bms.utilities.ApplicationUtility;
import com.bms.utilities.Commonutility;

@Component
public interface ReportsService {

	public ContentPageMaker getHistoricalStringDataBySerialNumberAndSiteIdWithPg(Pageable pageable, String serialNumber,
			String siteId);

	public ContentPageMaker getHistoricalCellDataBySerialNumberAndSiteIdWithPg(Pageable pageable, String serialNumber,
			String siteId);

	public List<StringParamDTO> getHistoricalStringDataBySerialNumberAndSiteIdAndBetweenDates(String serialNumber,
			String siteId, String strStartDate, String strEndDate);

	public List<DeviceData> getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates(String serialNumber, String siteId,
			String strStartDate, String strEndDate);

	public ContentPageMaker getHistoricalAlarmsDataBySerialNumberAndSiteIdBetweenDatesWithPg(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate);
	

	public List<DeviceData> getLatestNRecords();
	
	
	
	public ContentPageMaker getHistoricalStringDataBySerialNumberAndSiteIdBetWeenDatesWithPg(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate);
	public ContentPageMaker getHistoricalCellDataBySerialNumberAndSiteIdBetWeenDatesWithPg(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate);

	@Service
	public class ReportsServiceClass implements ReportsService {
		@Autowired
		SiteIdListRepos siteIdListRepos;

		@Autowired
		SiteLocationRepos siteLocationRepos;

		@Autowired
		GeneralDataRepos generalDataRepos;

		@Autowired
		DeviceDataRepos deviceDataRepos;
		
		@Autowired
		ChargerMonitoringDataRepos chargerMonitoringDataRepos;

		public ContentPageMaker getHistoricalStringDataBySerialNumberAndSiteIdWithPg(Pageable pageable,
				String serialNumber, String siteId) {

			List<StringParamDTO> rtnLstStringParamDTO = new ArrayList<StringParamDTO>();

			// Page<DeviceData> pageDeviceData=
			// deviceDataRepos.findBySerialNumberAndGeneralDataSiteIdOrderByIdDesc(pageable,serialNumber,siteId);
			Page<DeviceData> pageDeviceData = deviceDataRepos
					.findBySerialNumberAndGeneralDataSiteIdOrderByGeneralDataPacketDateTimeDesc(pageable, serialNumber,
							siteId);
			List<DeviceData> lstDeviceData = pageDeviceData.getContent();

			rtnLstStringParamDTO = ApplicationUtility.convertStringParamDTO(lstDeviceData);

			ContentPageMaker contentPageMaker = new ContentPageMaker(rtnLstStringParamDTO, pageDeviceData);
			return contentPageMaker;
		}
		
//		public ContentPageMaker getHistoricalStringDataBySerialNumberAndSiteIdBetWeenDatesWithPg(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate) {
//
//			List<StringParamDTO> rtnLstStringParamDTO = new ArrayList<StringParamDTO>();
//			Page<DeviceData> pageDeviceData =getPgDeviceDataBySerialNumberAndSiteIdAndBetweenDates(pageable, serialNumber, siteId, strStartDate, strEndDate);
//			List<DeviceData> lstDeviceData = pageDeviceData.getContent();
//
//			rtnLstStringParamDTO = ApplicationUtility.convertStringParamDTO(lstDeviceData);
//
//			ContentPageMaker contentPageMaker = new ContentPageMaker(rtnLstStringParamDTO, pageDeviceData);
//			return contentPageMaker;
//		}
		
		public ContentPageMaker getHistoricalStringDataBySerialNumberAndSiteIdBetWeenDatesWithPg(
		        Pageable pageable, String serialNumber, String siteId, String strStartDate, String strEndDate) {

		    // Fetch DeviceData based on criteria
		    Page<DeviceData> pageDeviceData = getPgDeviceDataBySerialNumberAndSiteIdAndBetweenDates(
		            pageable, serialNumber, siteId, strStartDate, strEndDate);
		    List<DeviceData> lstDeviceData = pageDeviceData.getContent();

		    // Convert DeviceData to StringParamDTO
		    List<StringParamDTO> rtnLstStringParamDTO = ApplicationUtility.convertStringParamDTO(lstDeviceData);

		    // Fetch ChargerMonitoringData based on criteria
		    Page<ChargerMonitoringData> pageChargerMonitoringData = getPgChargerMonitoringDataBySiteIdAndBetweenDates(
		            pageable, siteId, strStartDate, strEndDate);
		    List<ChargerMonitoringData> lstChargerMonitoringData = pageChargerMonitoringData.getContent();

		    // Convert ChargerMonitoringData to ChargerDTO
		    List<ChargerDTO> rtnLstChargerDTO = ApplicationUtility.convertChargerDTO(lstChargerMonitoringData);

		    // Combine StringParamDTO and ChargerDTO into ContentPageMaker
		    List<Object> combinedList = new ArrayList<>();
		    combinedList.addAll(rtnLstStringParamDTO);
		    combinedList.addAll(rtnLstChargerDTO);

		    // Create the ContentPageMaker with both lists and the page
		    ContentPageMaker contentPageMaker = new ContentPageMaker(combinedList, pageDeviceData);

		    return contentPageMaker;
		}



		

		public ContentPageMaker getHistoricalCellDataBySerialNumberAndSiteIdWithPg(Pageable pageable,
				String serialNumber, String siteId) {
			Page<DeviceData> pageDeviceData = deviceDataRepos.findBySerialNumberAndGeneralDataSiteIdOrderByGeneralDataPacketDateTimeDesc(pageable, serialNumber,
							siteId);
			List<DeviceData> lstDeviceData = pageDeviceData.getContent();

			List<CellParamDTO> lstCellParamDTO = ApplicationUtility.convertCellParamDTO(lstDeviceData);
			ContentPageMaker contentPageMaker = new ContentPageMaker(lstCellParamDTO, pageDeviceData);
			return contentPageMaker;
		}
		
		public ContentPageMaker getHistoricalCellDataBySerialNumberAndSiteIdBetWeenDatesWithPg(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate) {

			
			Page<DeviceData> pageDeviceData =getPgDeviceDataBySerialNumberAndSiteIdAndBetweenDates(pageable, serialNumber, siteId, strStartDate, strEndDate);
			List<DeviceData> lstDeviceData = pageDeviceData.getContent();

			List<CellParamDTO> lstCellParamDTO = ApplicationUtility.convertCellParamDTO(lstDeviceData);
			ContentPageMaker contentPageMaker = new ContentPageMaker(lstCellParamDTO, pageDeviceData);
			return contentPageMaker;
		}

		public ContentPageMaker getHistoricalAlarmsDataBySerialNumberAndSiteIdBetweenDatesWithPg(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate) {
			Date dtmStartDate = Commonutility.convertStringDateToDateFormate(strStartDate);
			Date dtmEndDate = Commonutility.convertStringDateToDateFormate(strEndDate);
			
			Page<DeviceData> pageDeviceData = deviceDataRepos.findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(pageable,serialNumber, siteId, dtmStartDate, dtmEndDate);
			List<DeviceData> lstDeviceData = pageDeviceData.getContent();

			List<AlarmsParamDTO> lstAlarmsParamDTO = ApplicationUtility.convertAlarmsParamDTO(lstDeviceData);
			
			 Page<ChargerMonitoringData> pageChargerMonitoringData =chargerMonitoringDataRepos
			            .findByGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(
			                    pageable, siteId, dtmStartDate, dtmEndDate);
			    List<ChargerMonitoringData> lstChargerMonitoringData = pageChargerMonitoringData.getContent();
			    
			    List<ChargerDTO> lstLstChargerDTO = ApplicationUtility.convertChargerDTO(lstChargerMonitoringData);
			    
			    List<Object> combinedList = new ArrayList<>();
			    combinedList.addAll(lstAlarmsParamDTO);
			    combinedList.addAll(lstLstChargerDTO);	    

			ContentPageMaker contentPageMaker = new ContentPageMaker(combinedList, pageDeviceData);
			return contentPageMaker;
		}

		public List<DeviceData> getLatestNRecords() {
			List<DeviceData> lstDeviceData = deviceDataRepos.findTop10ByOrderByIdDesc();
			return lstDeviceData;
		}

		/*************************** Beteen Dates *****************************/
		public List<DeviceData> getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates(String serialNumber,
				String siteId, String strStartDate, String strEndDate) {
			Date dtmStartDate = Commonutility.convertStringDateToDateFormate(strStartDate);
			Date dtmEndDate = Commonutility.convertStringDateToDateFormate(strEndDate);

			System.out.println(
					"getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates inputs are serialNumber:" + serialNumber
							+ " siteId:" + siteId + " dtmStartDate:" + dtmStartDate + " dtmEndDate:" + dtmEndDate);

			// List<DeviceData> lstDeviceData=
			// deviceDataRepos.findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdDesc(serialNumber,siteId,dtmStartDate,dtmEndDate);
			List<DeviceData> lstDeviceData = deviceDataRepos
					.findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(
							serialNumber, siteId, dtmStartDate, dtmEndDate);

			return lstDeviceData;
		}

		/*
		 * public List<DeviceData>
		 * getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates(String
		 * serialNumber,String siteId) {
		 * 
		 * List<DeviceData> lstDeviceData= deviceDataRepos.
		 * findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc
		 * (serialNumber,siteId);
		 * 
		 * return lstDeviceData; }
		 */

		public List<StringParamDTO> getHistoricalStringDataBySerialNumberAndSiteIdAndBetweenDates(String serialNumber,
				String siteId, String strStartDate, String strEndDate) {

			List<StringParamDTO> rtnLstStringParamDTO = new ArrayList<StringParamDTO>();

			Date dtFromDate = Commonutility.convertStringDateToDateFormate(strStartDate);
			Date dtToDate = Commonutility.convertStringDateToDateFormate(strEndDate);

			List<DeviceData> lstDeviceData = deviceDataRepos
					.findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdDesc(
							serialNumber, siteId, dtFromDate, dtToDate);

			List<StringParamDTO> lstStringParamDTO = ApplicationUtility.convertStringParamDTO(lstDeviceData);

			return lstStringParamDTO;
		}

		/**********************************************************************/

		/********************************************************************************/
		
		/*******************************Common Methods**************************************/
		private Page<DeviceData> getPgDeviceDataBySerialNumberAndSiteIdAndBetweenDates(Pageable pageable,String serialNumber, String siteId,String strStartDate,String strEndDate)
		{
			
			
			Date dtmStartDate = Commonutility.convertStringDateToDateFormate(strStartDate);
			Date dtmEndDate = Commonutility.convertStringDateToDateFormate(strEndDate);
			Page<DeviceData> pageDeviceData = deviceDataRepos.findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(pageable,serialNumber, siteId, dtmStartDate, dtmEndDate);
			return pageDeviceData;
		}
		private Page<ChargerMonitoringData> getPgChargerMonitoringDataBySiteIdAndBetweenDates(
		        Pageable pageable, String siteId, String strStartDate, String strEndDate) {

		    // Convert the string start and end dates to Date format
		    Date dtmStartDate = Commonutility.convertStringDateToDateFormate(strStartDate);
		    Date dtmEndDate = Commonutility.convertStringDateToDateFormate(strEndDate);
		    
		    // Fetch ChargerMonitoringData from repository based on siteId from GeneralData and the date range
		    Page<ChargerMonitoringData> pageChargerMonitoringData = chargerMonitoringDataRepos
		            .findByGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(
		                    pageable, siteId, dtmStartDate, dtmEndDate);
		    
		    return pageChargerMonitoringData;
		}

		
		
		/*********************************************************************************/
	}

}
