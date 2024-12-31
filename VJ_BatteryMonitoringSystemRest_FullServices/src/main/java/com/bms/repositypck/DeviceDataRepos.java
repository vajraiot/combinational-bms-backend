package com.bms.repositypck;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.DeviceData;

@Repository
public interface DeviceDataRepos extends JpaRepository<DeviceData, Long> {

	/********************* Reports *************************************/
	// Page<FTMSPacket>
	// findByVehicleRegAndPacketDateTimeBetweenOrderByPacketDateTimeDesc(Pageable
	// pageable,String vehicleRegNumber,Date startDate, Date endDate);
	// List<DeviceData> findBySiteIdAndDeviceDataSerialNumber(Pageable
	// pageable,String siteId,String serialNumber);
	// List<GeneralData>
	// findBySiteIdAndDeviceDataSerialNumberAndPacketDateTimeBetweenOrderByPacketDateTimeDesc(Pageable
	// pageable,String siteId,String serialNumber,Date fromDate,Date toDate);
	List<DeviceData> findBySerialNumberAndGeneralDataSiteIdOrderByIdDesc(String serialNumber, String siteId);
	// findByVehicleRegAndPacketDateTimeBetweenOrderByPacketDateTimeDesc(String
	// vehicleReg,Date dtmStartDate,Date dtmEndDate);

	// Page<DeviceData> findBySerialNumberAndGeneralDataSiteIdOrderByIdDesc
	// (Pageable pageable,String serialNumber,String siteId);
	// Page<DeviceData>
	// findBySerialNumberAndGeneralDataSiteIdOrderByGeneralDataPacketDateTimeDesc
	// (Pageable pageable,String serialNumber,String siteId);

	List<DeviceData> findBySerialNumberAndGeneralDataSiteIdOrderByGeneralDataPacketDateTimeDesc(String serialNumber,
			String siteId);

	Page<DeviceData> findBySerialNumberAndGeneralDataSiteIdOrderByGeneralDataPacketDateTimeDesc(Pageable pageable,
			String serialNumber, String siteId);

	List<DeviceData> findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdDesc(
			String serialNumber, String siteId, Date dtFromDate, Date dtToDate);

	Optional<DeviceData> findTop1BySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdDesc(
			String serialNumber, String siteId, Date dtFromDate, Date dtToDate);

	Optional<DeviceData> findTop1BySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdAsc(
			String serialNumber, String siteId, Date dtFromDate, Date dtToDate); // this is for assend order

	List<DeviceData> findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(
			String serialNumber, String siteId, Date dtFromDate, Date dtToDate);

	Page<DeviceData> findBySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(
			Pageable pageable, String serialNumber, String siteId, Date dtFromDate, Date dtToDate);
	
	
	List<DeviceData> findTop10ByOrderByIdDesc();// this for is testing.....

	// List<DeviceData> findBySerialNumberAndGeneralDataSiteId(Pageable
	// pageable,String serialNumber,String siteId);
	/********************************************************************/
}
