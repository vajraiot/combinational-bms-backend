package com.bms.repositypck;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SerialNumberList;
import com.bms.entity.SiteIdList;
import java.lang.String;




@Repository
public interface SerialNumberListRepos extends JpaRepository<SerialNumberList,Long> {

	
	//List<SerialNumberList> findBySiteIdAndLstSerialNumberListSerialNumber(String siteId,String serialNumber);
	List<SerialNumberList> findBySiteIdListSiteIdAndSerialNumber(String siteId,String serialNumber);
	
}
