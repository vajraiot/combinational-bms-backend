package com.bms.repositypck;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.intefaces.GetInstantaneousIDatanterface;
import java.lang.String;




@Repository
public interface GeneralDataRepos extends JpaRepository<GeneralData,Long> {
 
	List<GeneralData> findTop1BySiteIdOrderByServerTimeDesc(String siteId);
	List<GeneralData> findTop1BySiteIdOrderByPacketDateTimeDesc(String siteId);
	
	List<GeneralData> findTop1BySiteIdAndDeviceDataSerialNumberOrderByServerTimeDesc(String siteId,String serialNumber);
	List<GeneralData> findTop1BySiteIdAndDeviceDataSerialNumberOrderByPacketDateTimeDesc(String siteId,String serialNumber);
	
	List<GeneralData> findBySiteIdOrderByPacketDateTimeDesc(String siteId);
	
//	@Query("SELECT gd FROM GeneralData gd " +
//	           "JOIN FETCH gd.chargerMonitoringData cmd " +
//	           "LEFT JOIN FETCH cmd.chargerStatusData csd " +
//	           "WHERE gd.siteId = :siteId " +
//	           "ORDER BY gd.serverTime DESC")
//	    List<GeneralData> findTop1BySiteIdOrderByServerTimeDesc(@Param("siteId") String siteId);
	
	
	
	
	
	
	//List<GeneralData> findBySiteId(String siteid);
	//List<GetInstantaneousIDatanterface> findBySiteIdOrderByPacketDateTimeDesc(String siteId);

//	Page<Rawdata> findAllByOrderByServerTimeDesc(Pageable pageable);
//	Page<Rawdata>  findAllByOrderByServerTimeDesc(Pageable  pageable);
	
	//Page<Rawdata> findByRawdataIsLike(String searchingKeyword,Pageable  pageable);
	//Page<Rawdata> findByRawdataContaining(String searchingKeyword,Pageable  pageable);
	
	
	/*********************Reports*************************************/
	//Page<FTMSPacket> findByVehicleRegAndPacketDateTimeBetweenOrderByPacketDateTimeDesc(Pageable pageable,String vehicleRegNumber,Date startDate, Date endDate);
//	List<GeneralData>  findBySiteIdAndDeviceDataSerialNumber(Pageable pageable,String siteId,String serialNumber);
	//List<GeneralData>  findBySiteIdAndDeviceDataSerialNumberAndPacketDateTimeBetweenOrderByPacketDateTimeDesc(Pageable pageable,String siteId,String serialNumber,Date fromDate,Date toDate);
	/********************************************************************/
	

}
