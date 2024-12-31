package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.GeneralData;
import com.bms.entity.SiteIdList;
import com.bms.pojo.ChargerMonitoringDTO;
import com.bms.pojo.CommnStatus;
import com.bms.pojo.InstDataFromDeviceDataDTO;
import com.bms.services.ChargerMonitoringService;
import com.bms.services.GeneralDataService;
import com.bms.services.SiteIdListService;

@CrossOrigin
@RestController
public class GeneralDataControler {

	@Autowired
	GeneralDataService generalDataService;

	@Autowired
	SiteIdListService siteIdListService;
	
	@Autowired
	ChargerMonitoringService chargerMonitoringService;

	@GetMapping(value = "/getGeneralDataById")
	public ResponseEntity<GeneralData> getGeneralDataById(Long id) {
		GeneralData generalData = generalDataService.fetcthGeneralDataById(id);

		return ResponseEntity.ok(generalData);
	}

	@GetMapping(value = "/getGeneralDataBysiteId")
	public ResponseEntity<GeneralData> getGeneralDataById(String siteId) {
		GeneralData generalData = generalDataService.fetcthGeneralDataBySiteId(siteId);

		return ResponseEntity.ok(generalData);
	}

	@GetMapping(value = "/getInstDataWithTimeLapse")
	public ResponseEntity<List<InstDataFromDeviceDataDTO>> getInstDataWithTimeLapse(String siteId,
			int subtractingMinutes) {
		List<InstDataFromDeviceDataDTO> lstInstDataFromDeviceDataDTO = generalDataService.fetchInstDataBySiteId(siteId);

		return ResponseEntity.ok(lstInstDataFromDeviceDataDTO);
	}
	 @GetMapping("/getChargerMonitoringDataBySiteId")
	    public ResponseEntity<List<ChargerMonitoringDTO>> getChargerMonitoringDetailsBySiteId(@RequestParam String siteId) {
	        List<ChargerMonitoringDTO> rtnChargerMonitoringDetails = chargerMonitoringService.fetchChargerMonitoringDetailsBySiteId(siteId);
	        return ResponseEntity.ok(rtnChargerMonitoringDetails);
	    }
	 @GetMapping(value = "/getGeneralDataBySiteIdAndSerialNumber")
		public ResponseEntity<GeneralData> getGeneralDataBySiteIdAndSerialNumber(String siteId, String serialNumber) {
			GeneralData rtnGeneralData = generalDataService.fetcthGeneralDataBySiteIdAndSerialNumber(siteId, serialNumber);
			return ResponseEntity.ok(rtnGeneralData);
		}
	

	@GetMapping(value = "/getGeneralDataForEachSiteIdAndSerailNumber")
	public ResponseEntity<List<GeneralData>> getGeneralDataForEachSiteIdAndSerailNumber() {
		List<SiteIdList> lstSiteIdList = siteIdListService.fetchAllSiteIdWithSerialNumbers();
		List<GeneralData> lstGeneralData = generalDataService
				.fetchEachGeneralDataForSiteidAndSerialNumber(lstSiteIdList);
		return ResponseEntity.ok(lstGeneralData);
	}

	@GetMapping(value = "/getCommnStatus")
	public ResponseEntity<List<CommnStatus>> getCommnStatus(int marginMinutes) {
		List<SiteIdList> lstSiteIdList = siteIdListService.fetchAllSiteIdWithSerialNumbers();
		List<CommnStatus> rtnLstCommnStatus = generalDataService.getCommnStatus(lstSiteIdList, marginMinutes);
		return ResponseEntity.ok(rtnLstCommnStatus);
	}
	

}

