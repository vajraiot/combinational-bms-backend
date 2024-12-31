package com.bms.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.miscellaneous.EntityToPojoConversion;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.SpecificCellDetailsDTO;
import com.bms.repositypck.CellVoltageTemperatureDataRepos;
import com.bms.repositypck.GeneralDataRepos;
import com.bms.repositypck.RawdataRepos;
import com.bms.repositypck.SiteIdListRepos;
import com.bms.repositypck.SiteLocationRepos;
import com.bms.utilities.ApplicationUtility;
import com.bms.utilities.Commonutility;

@Component
public interface CellVoltageTemperatureDataService {

	public List<SpecificCellDetailsDTO> getTopNCellDataBySiteAndSerialNumberForSpecificCell(String siteId,String serialNumber,int cellNumber,int numberOfRecords );
	
	public List<SpecificCellDetailsDTO> getTopNCellDataBySiteAndSerialNumberForSpecificCellBetweenDates(String siteId,String serialNumber,int cellNumber,String strStartDate, String strEndDate );
	
	@Service
	public class SiteIdListServiceClass implements CellVoltageTemperatureDataService {
		@Autowired
		SiteIdListRepos siteIdListRepos;

		
		@Autowired
		CellVoltageTemperatureDataRepos cellVoltageTemperatureDataRepos;

		@Transactional
		public List<SpecificCellDetailsDTO> getTopNCellDataBySiteAndSerialNumberForSpecificCell(String siteId,String serialNumber,int cellNumber,int numberOfRecords ) {

			List<CellVoltageTemperatureData> lstCellVoltageTemperatureData= cellVoltageTemperatureDataRepos.findByDeviceDataGeneralDataSiteIdAndDeviceDataSerialNumberAndCellNumberOrderByDeviceDataGeneralDataPacketDateTimeDesc(siteId, serialNumber,cellNumber, PageRequest.of(0, numberOfRecords));
		
			List<SpecificCellDetailsDTO> lstSpecificCellDetailsDTO= ApplicationUtility.convertToSpecificCellDetailsDTO(lstCellVoltageTemperatureData);
			return lstSpecificCellDetailsDTO;
		}
		
		@Transactional
		public List<SpecificCellDetailsDTO> getTopNCellDataBySiteAndSerialNumberForSpecificCellBetweenDates(String siteId,String serialNumber,int cellNumber,String strStartDate, String strEndDate ) {

			Date dtmStartDate = Commonutility.convertStringDateToDateFormate(strStartDate);
			Date dtmEndDate = Commonutility.convertStringDateToDateFormate(strEndDate);

			
			List<CellVoltageTemperatureData> lstCellVoltageTemperatureData= cellVoltageTemperatureDataRepos.findByDeviceDataGeneralDataSiteIdAndDeviceDataSerialNumberAndCellNumberAndDeviceDataGeneralDataPacketDateTimeBetweenOrderByDeviceDataGeneralDataPacketDateTimeDesc(siteId, serialNumber, cellNumber, dtmStartDate, dtmEndDate);
		
			List<SpecificCellDetailsDTO> lstSpecificCellDetailsDTO= ApplicationUtility.convertToSpecificCellDetailsDTO(lstCellVoltageTemperatureData);
			return lstSpecificCellDetailsDTO;
		}
	}

}
