package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.entity.ManufacturerDetails;
import com.bms.pojo.ApiResponSestatus;
import com.bms.pojo.PlainManufacturerDetails;
import com.bms.services.ManufacturerDetailsService;
import com.bms.services.SiteIdListService;

@CrossOrigin
@RestController
public class ManufacturerDetailsControler {
	/*** for fetching refer the siteIdListcontroller *******/

	@Autowired
	SiteIdListService siteIdListService;

	@Autowired
	ManufacturerDetailsService manufacturerDetailsService;

	@GetMapping(value = "/getManufacturerDetailsBySiteIdAndSerialNumber")
	public ResponseEntity<ManufacturerDetails> getManufacturerDetailsBySiteIdAndSerialNumber(String siteId,
			String serialNumber) {
		ManufacturerDetails manufacturerDetails = manufacturerDetailsService
				.fetchManufacturerDetailsBySiteidandSerialNumber(siteId, serialNumber);

		return ResponseEntity.ok(manufacturerDetails);
	}

	@GetMapping(value = "/getAllManufacturerDetails")
	public ResponseEntity<List<PlainManufacturerDetails>> getManufacturerDetails() {
		List<PlainManufacturerDetails> lstPlainManufacturerDetails = manufacturerDetailsService
				.fetchAllManufacturerDetails();

		return ResponseEntity.ok(lstPlainManufacturerDetails);
	}

	@PostMapping(value = "/postSaveManufacturerDetails")
	public ResponseEntity<ApiResponSestatus> postSaveManufacturerDetails(
			@RequestBody PlainManufacturerDetails _plainManufacturerDetails) {
		System.out.println("Save Request Recieved:" + _plainManufacturerDetails.toString());
		ApiResponSestatus apiResponSestatus = manufacturerDetailsService
				.saveManufacturerDetails(_plainManufacturerDetails);

		return ResponseEntity.ok(apiResponSestatus);

	}

	@PutMapping(value = "/putUpdateManufacturerDetails")
	public ResponseEntity<ApiResponSestatus> putUpdateManufacturerDetails(
			@RequestBody PlainManufacturerDetails _plainManufacturerDetails) {
		System.out.println("Update Request Recieved:" + _plainManufacturerDetails.toString());

		ApiResponSestatus apiResponSestatus = manufacturerDetailsService
				.updateManufacturerDetails(_plainManufacturerDetails);

		return ResponseEntity.ok(apiResponSestatus);

	}

	@DeleteMapping(value = "/deleteManufacturerDetailsBySiteIdAndSerialNumber")
	public ResponseEntity<ApiResponSestatus> deleteManufacturerDetailsBySiteIdAndSerialNumber(String siteId,
			String serialNumber) {
		System.out.println("Delete Request Recieved siteId:" + siteId + " SerialNumber:" + serialNumber);
		ApiResponSestatus apiResponSestatus = manufacturerDetailsService
				.deleteManufacturerDetailsBySiteIdAndSerialNumber(siteId, serialNumber);

		return ResponseEntity.ok(apiResponSestatus);

	}

}
