package com.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.SiteIdList;
import com.bms.pojo.ApiResponSestatus;
import com.bms.services.SiteIdListService;

@CrossOrigin
@RestController
public class SiteLocationControler {
	/*** for fetching refer the siteIdListcontroller *******/

	@Autowired
	SiteIdListService siteIdListService;

	@PostMapping(value = "/postAddNewLocationToSiteId")
	public ResponseEntity<ApiResponSestatus> postAddNewLocationToSiteId(@RequestBody SiteIdList _siteIdList) {

		ApiResponSestatus rtnApiResponSestatus = siteIdListService.postAddNewLocationToSiteId(_siteIdList);
		return ResponseEntity.ok(rtnApiResponSestatus);
	}

	@PutMapping(value = "/updateSiteLocationToSiteId")
	public ResponseEntity<ApiResponSestatus> updateSiteLocationToSiteId(@RequestBody SiteIdList _siteIdList) {

		ApiResponSestatus rtnApiResponSestatus = siteIdListService.updateSiteLocationToSiteId(_siteIdList);
		return ResponseEntity.ok(rtnApiResponSestatus);
	}

	@DeleteMapping(value = "/deleteSiteLocationToSiteId")
	public ResponseEntity<ApiResponSestatus> deleteSiteLocationToSiteId(String siteId) {

		ApiResponSestatus rtnApiResponSestatus = siteIdListService.deleteSiteLocationToSiteId(siteId);
		return ResponseEntity.ok(rtnApiResponSestatus);
	}

}
