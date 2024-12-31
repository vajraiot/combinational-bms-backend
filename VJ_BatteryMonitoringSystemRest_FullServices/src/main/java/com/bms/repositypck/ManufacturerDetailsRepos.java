package com.bms.repositypck;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.GeneralData;
import com.bms.entity.ManufacturerDetails;
import com.bms.entity.Rawdata;
import com.bms.entity.SiteIdList;
import com.bms.entity.SiteLocation;

import java.lang.String;




@Repository
public interface ManufacturerDetailsRepos extends JpaRepository<ManufacturerDetails,Long> {

	
	
	List<ManufacturerDetails> findBySerialNumberListSiteIdListSiteId(String siteid);
	List<ManufacturerDetails> findBySerialNumberListSerialNumber(String serialNumber);
	
	List<ManufacturerDetails> findBySerialNumberListSiteIdListSiteIdAndSerialNumberListSerialNumber(String siteid,String serialNumber);
	
}
