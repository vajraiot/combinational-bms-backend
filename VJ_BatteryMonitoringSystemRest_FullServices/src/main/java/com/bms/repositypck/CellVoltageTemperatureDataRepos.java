package com.bms.repositypck;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.GeneralData;
import com.bms.entity.ManufacturerDetails;
import com.bms.entity.Rawdata;
import com.bms.entity.SiteIdList;
import java.lang.String;




@Repository
public interface CellVoltageTemperatureDataRepos extends JpaRepository<CellVoltageTemperatureData,Long> {

	//List<ManufacturerDetails> findBySerialNumberListSiteIdListSiteId(String siteid);
	//List<ManufacturerDetails> findBySerialNumberListSerialNumber(String serialNumber);
	
	//List<ManufacturerDetails> findBySerialNumberListSiteIdListSiteIdAndSerialNumberListSerialNumber(String siteid,String serialNumber);
	List<CellVoltageTemperatureData> findByDeviceDataGeneralDataSiteIdAndDeviceDataSerialNumberAndCellNumber(String siteId,String serialNumber,int cellNumber);
	//OrderByPacketDateTimeDesc
	List<CellVoltageTemperatureData> findByDeviceDataGeneralDataSiteIdAndDeviceDataSerialNumberAndCellNumberOrderByDeviceDataGeneralDataPacketDateTimeDesc(String siteId,String serialNumber,int cellNumber,Pageable pageable);
	
	List<CellVoltageTemperatureData> findByDeviceDataGeneralDataSiteIdAndDeviceDataSerialNumberAndCellNumberAndDeviceDataGeneralDataPacketDateTimeBetweenOrderByDeviceDataGeneralDataPacketDateTimeDesc(String siteId,String serialNumber,int cellNumber,Date dtmStartDate,Date dtmEndDate);
}
