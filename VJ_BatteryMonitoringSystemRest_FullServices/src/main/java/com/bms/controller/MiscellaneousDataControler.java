package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.SiteIdList;
import com.bms.pojo.AllSitesAlarmsDTO;
import com.bms.services.GeneralDataService;
import com.bms.services.SiteIdListService;

@CrossOrigin
@RestController
public class MiscellaneousDataControler {

	@Autowired
	GeneralDataService generalDataService;

	@Autowired
	SiteIdListService siteIdListService;

	@GetMapping(value = "/getAllSitesAlarmsData")
	public ResponseEntity<List<AllSitesAlarmsDTO>> getAllSitesAlarmsData() {
		List<SiteIdList> lstSiteIdList = siteIdListService.fetchAllSiteIdWithSerialNumbers();
		List<AllSitesAlarmsDTO> lstAllSitesAlarmsDTO = generalDataService.fetchAllSitesAlarms(lstSiteIdList);

		return ResponseEntity.ok(lstAllSitesAlarmsDTO);
	}

}
