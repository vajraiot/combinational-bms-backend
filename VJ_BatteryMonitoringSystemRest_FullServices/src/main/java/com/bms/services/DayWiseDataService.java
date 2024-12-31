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
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.miscellaneous.EntityToPojoConversion;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.DayWiseDataDTO;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.SpecificCellDetailsDTO;
import com.bms.repositypck.CellVoltageTemperatureDataRepos;
import com.bms.repositypck.DeviceDataRepos;
import com.bms.repositypck.GeneralDataRepos;
import com.bms.repositypck.RawdataRepos;
import com.bms.repositypck.SiteIdListRepos;
import com.bms.repositypck.SiteLocationRepos;
import com.bms.utilities.ApplicationUtility;
import com.bms.utilities.Commonutility;
import com.bms.utilities.Commonutility.StartAndEndTimeInDate;

@Component
public interface DayWiseDataService {

	public List<DayWiseDataDTO> getDayWiseReport(String siteId,String serialNumber,String strStartDate, String strEndDate);
	@Service
	public class SiteIdListServiceClass implements DayWiseDataService {
		@Autowired
		SiteIdListRepos siteIdListRepos;

		@Autowired
		DeviceDataRepos deviceDataRepos;
		
		public List<DayWiseDataDTO> getDayWiseReport(String siteId,String serialNumber,String strStartDate, String strEndDate )
		{
			try {
			List<DayWiseDataDTO>  rtnLstDayWiseDataDTO=new ArrayList<DayWiseDataDTO>();
						
			Date dtmStartDate = Commonutility.convertStringDateToDateFormate(strStartDate);
			Date dtmEndDate = Commonutility.convertStringDateToDateFormate(strEndDate);

			 List<Date> lstDates= Commonutility.getDaysBetweenDates(dtmStartDate, dtmEndDate);
			 Long id=new Long(0);
			 for(Date instDate:lstDates)
			 {
				 DayWiseDataDTO lclDayWiseDataDTO=new DayWiseDataDTO();
				 lclDayWiseDataDTO=	 getDayWiseReport(siteId,serialNumber,instDate);
				 if(lclDayWiseDataDTO==null)
				 {
					 continue;
				 }
				 lclDayWiseDataDTO.setId(id++);
				 rtnLstDayWiseDataDTO.add(lclDayWiseDataDTO);
						 
			 }
			 
			 return rtnLstDayWiseDataDTO;
			}
			catch(Exception ex)
			{
				System.out.println("Exception occured while processing the All getDayWiseReport"+ex.toString());
				return null;
			}
			
		}
		
		public DayWiseDataDTO getDayWiseReport(String siteId,String serialNumber,Date dt )
		{
			try {
			DayWiseDataDTO rtnDayWiseDataDTO=new DayWiseDataDTO();
			StartAndEndTimeInDate startAndEndTimeInDate	=Commonutility.getStartAndEndDateTimeInDate(dt);
			
			Optional<DeviceData> fistDeviceDateOnDate = deviceDataRepos.findTop1BySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdAsc(serialNumber, siteId, startAndEndTimeInDate.getStartTimeInDate(),startAndEndTimeInDate.getEndTimeInDate());
			Optional<DeviceData> lastDeviceDateOnDate= deviceDataRepos.findTop1BySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdDesc(serialNumber, siteId, startAndEndTimeInDate.getStartTimeInDate(),startAndEndTimeInDate.getEndTimeInDate());
						
			if((!fistDeviceDateOnDate.isPresent())||(!lastDeviceDateOnDate.isPresent()))
			{
				return null;
			}
			rtnDayWiseDataDTO= ApplicationUtility.processingDayWiseData(fistDeviceDateOnDate.get(), lastDeviceDateOnDate.get());
			
			return rtnDayWiseDataDTO;
			}
			catch(Exception ex)
			{
				System.out.println("Exception occured while processing the single getDayWiseReport"+ex.toString());
				return null;
			}
		}    
	
	}

}
