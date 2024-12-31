package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bms.excel.SpecificCellDataReportDownloadXls;
import com.bms.pojo.SpecificCellDetailsDTO;
import com.bms.services.CellVoltageTemperatureDataService;
import com.bms.services.SiteIdListService;

@CrossOrigin
@RestController
public class CellVoltageTemperatureDataControler {

	@Autowired
	SiteIdListService siteIdListService;

	@Autowired
	CellVoltageTemperatureDataService cellVoltageTemperatureDataService;

	@GetMapping(value = "/getSpecificCellDataBySiteIdAndSerialNumber")
	public ResponseEntity<List<SpecificCellDetailsDTO>> getSpecificCellDataBySiteIdAndSerialNumber(String siteId,
			String serialNumber, int cellNumber, int numberOfRecords) {
		List<SpecificCellDetailsDTO> lstCellVoltageTemperatureData = cellVoltageTemperatureDataService
				.getTopNCellDataBySiteAndSerialNumberForSpecificCell(siteId, serialNumber, cellNumber, numberOfRecords);

		return ResponseEntity.ok(lstCellVoltageTemperatureData);
	}

	@GetMapping(value = "/getSpecificCellDataBySiteIdAndSerialNumberBetweenDates")
	public ResponseEntity<List<SpecificCellDetailsDTO>> getSpecificCellDataBySiteIdAndSerialNumberBetweenDates(
			String siteId, String serialNumber, int cellNumber, String strStartDate, String strEndDate) {
		List<SpecificCellDetailsDTO> lstCellVoltageTemperatureData = cellVoltageTemperatureDataService
				.getTopNCellDataBySiteAndSerialNumberForSpecificCellBetweenDates(siteId, serialNumber, cellNumber,
						strStartDate, strEndDate);

		return ResponseEntity.ok(lstCellVoltageTemperatureData);
	}

	/*************************** Reports **********************************/
	@CrossOrigin
	@GetMapping(value = "/downloadCellDataReport")
	public ModelAndView downloadSpecificCellDataReport(String siteId, String serialNumber, int cellNumber,
			String strStartDate, String strEndDate) {
		try {
			List<SpecificCellDetailsDTO> _lstCellVoltageTemperatureData = cellVoltageTemperatureDataService
					.getTopNCellDataBySiteAndSerialNumberForSpecificCellBetweenDates(siteId, serialNumber, cellNumber,
							strStartDate, strEndDate);

			return new ModelAndView(new SpecificCellDataReportDownloadXls(), "SpecificCellDataReport",
					_lstCellVoltageTemperatureData);
		} catch (Exception ex) {
			System.out.println("Exception occured in downloadCellDataReport." + ex.toString());
			return null;
		}
	}

}
