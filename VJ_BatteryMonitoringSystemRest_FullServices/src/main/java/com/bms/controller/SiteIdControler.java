package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.SiteIdList;
import com.bms.services.SiteIdListService;

@CrossOrigin
@RestController
public class SiteIdControler {

	@Autowired
	SiteIdListService siteIdListService;

	@GetMapping(value = "/getAllSiteIdLIst")
	public ResponseEntity<List<String>> getGeneralDataById() {
		List<String> lstSiteId = siteIdListService.fetchAllSiteIds();
		return ResponseEntity.ok(lstSiteId);
	}

	@GetMapping(value = "/getSiteIdWithSerialNumbers")
	public ResponseEntity<SiteIdList> getSiteIdWithSerialNumbers(String siteid) {
		SiteIdList rtnSiteIdList = siteIdListService.fetchSiteIdWithSerialNumbers(siteid);
		return ResponseEntity.ok(rtnSiteIdList);
	}

	@GetMapping(value = "/getAllSiteIdWithSerialNumbers")
	public ResponseEntity<List<SiteIdList>> getAllSiteIdWithSerialNumbers() {
		List<SiteIdList> rtnLstSiteIdList = siteIdListService.fetchAllSiteIdWithSerialNumbers();
		return ResponseEntity.ok(rtnLstSiteIdList);
	}
	
//	@GetMapping(value = "/getAllSiteIdWithSerialNumbers")
//	public ResponseEntity<List<SiteIdList>> getAllSiteIdWithSerialNumbers() {
//		List<SiteIdList> rtnLstSiteIdList = siteIdListService.fetchAllSiteIdWithSerialNumbers();
//		return ResponseEntity.ok(rtnLstSiteIdList);
//	}
//	@GetMapping(value = "/getAllSiteIdWithSerialNumbers")
//	public ResponseEntity<List<SiteIdList>> getAllSiteIdWithSerialNumbers() {
//		List<SiteIdList> rtnLstSiteIdList = siteIdListService.fetchAllSiteIdWithSerialNumbers();
//		return ResponseEntity.ok(rtnLstSiteIdList);
//	}

}
