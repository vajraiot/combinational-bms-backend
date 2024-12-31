package com.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.miscellaneous.ContentPageMaker;
import com.bms.services.RawdataService;

@CrossOrigin
@RestController
public class RawDataControler {

	@Autowired
	RawdataService rawdataService;

	@CrossOrigin
	@GetMapping(value = "/GetAllRawDataByServerTimePg")
	public ContentPageMaker getAllVehicles(Pageable pageable) {
		return rawdataService.fetcthAllRawdataByServerTimePg(pageable);
	}

	@CrossOrigin
	@GetMapping(value = "/GetRawdataWithSearchingKeywordOrderByServerTimePg")
	public ContentPageMaker getRawdataWithSearchingKeywordOrderByServerTimePg(String searchingKeyword,
			Pageable pageable) {
		if (searchingKeyword == null) {
			return rawdataService.fetcthAllRawdataByServerTimePg(pageable);
		} else if ((searchingKeyword.equals("")) || (searchingKeyword.toLowerCase().contains("null"))) {
			return rawdataService.fetcthAllRawdataByServerTimePg(pageable);
		} else {
			return rawdataService.fetchAllDataBySearchingKeyword(searchingKeyword, pageable);
		}
	}

}
