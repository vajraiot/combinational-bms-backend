package com.bms.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.ChargerStatusData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SerialNumberList;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;
import com.bms.intefaces.GetInstantaneousIDatanterface;
import com.bms.miscellaneous.ApplicationConversions;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.miscellaneous.EntityToPojoConversion;
import com.bms.pojo.AllSitesAlarmsDTO;
import com.bms.pojo.ChargerDTO;
import com.bms.pojo.ChargerMonitoringDTO;
import com.bms.pojo.CommnStatus;
import com.bms.pojo.GeneralDataDTO;
import com.bms.pojo.InstDataFromDeviceDataDTO;
import com.bms.repositypck.GeneralDataRepos;
import com.bms.repositypck.RawdataRepos;
import com.bms.utilities.Commonutility;

@Component
public interface GeneralDataService {

	public GeneralData fetcthGeneralDataById(Long id);

	public GeneralData fetcthGeneralDataBySiteId(String siteId);
	public GeneralData fetcthGeneralDataBySiteIdAndSerialNumber(String siteId,String serialNumber);
	
	public List<InstDataFromDeviceDataDTO> fetchInstDataBySiteId(String siteId);
	
	public List<AllSitesAlarmsDTO> fetchAllSitesAlarms(List<SiteIdList> lstSiteIdList);
	
	
	public List<GeneralData> fetchEachGeneralDataForSiteidAndSerialNumber(List<SiteIdList> lstSiteIdList);
	
	public List<CommnStatus> getCommnStatus(List<SiteIdList> lstSiteIdList, int marginMinutes);
	@Service
	public class GeneralDataServiceClass implements GeneralDataService
	{   @Autowired
		GeneralDataRepos generalDataRepos;
	   
		@Transactional
		public GeneralData fetcthGeneralDataById(Long id)
		{
    	  
			Optional<GeneralData> optGeneralData= generalDataRepos.findById(id);
	
			if(!optGeneralData.isPresent())
			{
				return null;
			}
			//GeneralDataDTO instGeneralDataDTO=  EntityToPojoConversion.cnvrtGeneralDataDTO(optGeneralData.get());
			return optGeneralData.get();
		}	
		
	
		@Transactional
		public GeneralData fetcthGeneralDataBySiteId(String siteId)
		{
    		List<GeneralData> lstGeneralData= generalDataRepos.findTop1BySiteIdOrderByPacketDateTimeDesc(siteId); //changed server to packet
			if(lstGeneralData.isEmpty())
			{return null;}
			
		//	GeneralDataDTO instGeneralDataDTO=  EntityToPojoConversion.cnvrtGeneralDataDTO(lstGeneralData.get(0));
			return lstGeneralData.get(0);
		}
		
		
		@Transactional
		public GeneralData fetcthGeneralDataBySiteIdAndSerialNumber(String siteId,String serialNumber)
		{
			try {
    		List<GeneralData> lstGeneralData= generalDataRepos.findTop1BySiteIdAndDeviceDataSerialNumberOrderByPacketDateTimeDesc(siteId,serialNumber); //changed servet to packet time
			if(lstGeneralData.isEmpty())
			{return null;}
			
			//GeneralDataDTO instGeneralDataDTO=  EntityToPojoConversion.cnvrtGeneralDataDTO(lstGeneralData.get(0));
			return lstGeneralData.get(0);
			}
			catch(Exception ex)
			{
				System.out.println("exception occured in fetcthGeneralDataBySiteIdAndSerialNumber is:"+ex.toString());
				return null;
			}
		}
		
		@Transactional
		public List<InstDataFromDeviceDataDTO> fetchInstDataBySiteId(String siteId)
		{
    		List<GeneralData> lstGeneralData= generalDataRepos.findBySiteIdOrderByPacketDateTimeDesc(siteId);
			
    		List<InstDataFromDeviceDataDTO> lstInstDataFromDeviceDataDTO=new ArrayList<InstDataFromDeviceDataDTO>();
    		
    		for(GeneralData inst:lstGeneralData)
    		{
    			DeviceData  deviceData= inst.getDeviceData().get(0);
    			InstDataFromDeviceDataDTO instDataFromDeviceDataDTO= new InstDataFromDeviceDataDTO();
        		
    			instDataFromDeviceDataDTO.setPacketDateTime(inst.getPacketDateTime());
    			instDataFromDeviceDataDTO.setServerTime(inst.getServerTime());
    			
    			instDataFromDeviceDataDTO.setStringvoltage(deviceData.getStringvoltage());	
        		instDataFromDeviceDataDTO.setInstantaneousCurrent(deviceData.getInstantaneousCurrent());
        		instDataFromDeviceDataDTO.setAmbientTemperature(deviceData.getAmbientTemperature());
        		
        		instDataFromDeviceDataDTO.setSocLatestValueForEveryCycle(deviceData.getSocLatestValueForEveryCycle());
        		
        		instDataFromDeviceDataDTO.setDodLatestValueForEveryCycle(deviceData.getDodLatestValueForEveryCycle());
        		
        		lstInstDataFromDeviceDataDTO.add(instDataFromDeviceDataDTO);
    		}
    		//GeneralDataDTO instGeneralDataDTO=  EntityToPojoConversion.cnvrtGeneralDataDTO(lstGeneralData.get(0));
			return lstInstDataFromDeviceDataDTO;
		}
		
	
		public List<GeneralData> fetchEachGeneralDataForSiteidAndSerialNumber(List<SiteIdList> lstSiteIdList)
		{
			List<GeneralData> rtnLstGeneralData=new ArrayList<GeneralData>();
			for(SiteIdList insSiteIdList :lstSiteIdList)
			{
				try {
					if(insSiteIdList==null)
					{
						continue;
					}
					
					List<SerialNumberList> lstSerialNumberList=insSiteIdList.getLstSerialNumberList();
					for(SerialNumberList instSerialNumberList:lstSerialNumberList)
					{
						String strSiteId=insSiteIdList.getSiteId();
						String strSerialNumber=instSerialNumberList.getSerialNumber();
						List<GeneralData> lstGeneralData=generalDataRepos.findTop1BySiteIdAndDeviceDataSerialNumberOrderByPacketDateTimeDesc(strSiteId,strSerialNumber);
						if(lstGeneralData.isEmpty())
						{
							continue;
						}
						rtnLstGeneralData.add(lstGeneralData.get(0));
					}
				}
				catch(Exception ex)
				{
					System.out.println("exception occured in siteidListLoop:"+ex.toString());
					continue;
				}
			}
			return rtnLstGeneralData;
		}
		
		@Transactional
		public List<AllSitesAlarmsDTO> fetchAllSitesAlarms(List<SiteIdList> lstSiteIdList)
		{
			List<AllSitesAlarmsDTO> lstAllSitesAlarmsDTO=new ArrayList<AllSitesAlarmsDTO>();
		
			List<GeneralData> lstGeneralData= fetchEachGeneralDataForSiteidAndSerialNumber(lstSiteIdList);
			for(GeneralData inst:lstGeneralData)
			{

				AllSitesAlarmsDTO rcvdAllSitesAlarmsDTO=new ApplicationConversions().cnvrtGeneralDataToAllSitesAlarmDTO(inst);
									if(rcvdAllSitesAlarmsDTO!=null)
									lstAllSitesAlarmsDTO.add(rcvdAllSitesAlarmsDTO);
			}
			return lstAllSitesAlarmsDTO;
		}
		
		public List<GeneralData> getLatestGeneralDataForEachSiteId(List<SiteIdList> lstSiteIdList)
		{
			List<GeneralData> rtnLstGeneralData=new ArrayList<GeneralData>();
			for(SiteIdList instSiteIdList:lstSiteIdList)
			{
				if(instSiteIdList==null)
				{
					continue;
				}
				List<GeneralData> rcvdGeneralData= generalDataRepos.findTop1BySiteIdOrderByPacketDateTimeDesc(instSiteIdList.getSiteId());
				if(rcvdGeneralData.isEmpty())
				{
					continue;
				}
				
				rtnLstGeneralData.add(rcvdGeneralData.get(0));
			}
			
			return rtnLstGeneralData;
		}
		
//		CommnStatusDTO
		
		public List<CommnStatus> getCommnStatus(List<SiteIdList> lstSiteIdList, int marginMinutes)
		{
			List<CommnStatus> rtnLstCommnStatus=new ArrayList<CommnStatus>();
			
			Map<String, SiteLocation> mpSiteLocationObj=getMapofSiteLocation(lstSiteIdList);
			List<GeneralData> rcvdGeneralData= getLatestGeneralDataForEachSiteId(lstSiteIdList);
			
			for(GeneralData inst:rcvdGeneralData)
			{
				CommnStatus instCommnStatus=new CommnStatus();
				if(inst==null)
				{
					continue;
				}
				int statusType=Commonutility.CheckIfDateIsLatestOne(inst.getPacketDateTime(), marginMinutes) ? 1 : 0;
				SiteLocation siteLocation=mpSiteLocationObj.get(inst.getSiteId());
				instCommnStatus=CommnStatus.builder()
						.siteId(inst.getSiteId())
					    .statusType(statusType)
					    .generalData(inst)
					    .siteLocation(siteLocation)
						.build();
				
				rtnLstCommnStatus.add(instCommnStatus);
				
			}
			
			return rtnLstCommnStatus;
			
		}
		
		public Map<String, SiteLocation> getMapofSiteLocation(List<SiteIdList> lstSiteIdList)
		{
			Map<String, SiteLocation> mpSiteLocationObj= new HashMap<String, SiteLocation>(); 
			
			for(SiteIdList inst:lstSiteIdList)
			{
				try {
				mpSiteLocationObj.put(inst.getSiteId(), inst.getSiteLocation());
				}
				catch(Exception ex)
				{
					System.out.println("Exception occured while addin the map ");
					continue;
				}
			}
			
			return mpSiteLocationObj;
		}
		
	}
}
