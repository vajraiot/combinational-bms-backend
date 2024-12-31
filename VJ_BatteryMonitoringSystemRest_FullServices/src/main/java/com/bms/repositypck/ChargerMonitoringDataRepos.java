package com.bms.repositypck;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.DeviceData;

import java.util.Date;

@Repository
public interface ChargerMonitoringDataRepos extends JpaRepository<ChargerMonitoringData, Long> {

    // Query to fetch ChargerMonitoringData based on siteId (from GeneralData) and packetDateTime range (from GeneralData)
    Page<ChargerMonitoringData> findByGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByGeneralDataPacketDateTimeDesc(
            org.springframework.data.domain.Pageable pageable, String siteId, Date dtFromDate, Date dtToDate);
    
   
}
