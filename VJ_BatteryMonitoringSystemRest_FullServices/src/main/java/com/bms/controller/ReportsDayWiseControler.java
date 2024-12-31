package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bms.excel.DayWiseDataReportDownloadXls;
import com.bms.pojo.DayWiseDataDTO;
import com.bms.pojo.MonthWiseDataDTO;
import com.bms.services.DayWiseDataService;
import com.bms.services.GeneralDataService;
import com.bms.services.MonthWiseDataService;
import com.bms.services.ReportsService;

@CrossOrigin
@RestController
public class ReportsDayWiseControler {

	@Autowired
	GeneralDataService generalDataService;

	@Autowired
	ReportsService reportsService;

	
	@Autowired
	MonthWiseDataService monthWiseDataService;
	

	@Autowired
	DayWiseDataService dayWiseDataService;


	@CrossOrigin
	@GetMapping(value = "/getDaywiseReports")
	public ResponseEntity<List<DayWiseDataDTO>> getDaywiseReports(String siteId, String serialNumber,
			String strStartDate, String strEndDate) {
		List<DayWiseDataDTO> lstDayWiseDataDTO = dayWiseDataService.getDayWiseReport(siteId, serialNumber, strStartDate,
				strEndDate);
		return ResponseEntity.ok(lstDayWiseDataDTO);
	}
	
	@CrossOrigin
	@GetMapping(value = "/getMonthlyReports")
	public ResponseEntity <List<MonthWiseDataDTO>> getMonthlyReports(
	        @RequestParam String siteId,
	        @RequestParam String serialNumber,
	        @RequestParam String year,
	        @RequestParam String month) {
	    try {
	        // Call the service to generate the monthly report
	        List<MonthWiseDataDTO> monthWiseDataDTO = monthWiseDataService.generateMonthlyReport(siteId, serialNumber, year,month);

	        if (monthWiseDataDTO == null) {
	            return ResponseEntity.noContent().build(); // HTTP 204 No Content
	        }

	        return ResponseEntity.ok(monthWiseDataDTO); // HTTP 200 OK with data
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500 Internal Server Error
	    }
	}


	

	/**********************************************
	 * DownLoad Excel
	 ***********************************/
	@CrossOrigin
	@GetMapping(value = "/downloadDaywiseReports")
	public ModelAndView downloadDaywiseReports(String siteId, String serialNumber, String strStartDate,
			String strEndDate) {
		try {
			List<DayWiseDataDTO> lstDayWiseDataDTO = dayWiseDataService.getDayWiseReport(siteId, serialNumber,
					strStartDate, strEndDate);
			return new ModelAndView(new DayWiseDataReportDownloadXls(), "DayWiseDataDetailsReport", lstDayWiseDataDTO);
		} catch (Exception ex) {
			System.out.println("Exception occured in downloadDaywiseReports." + ex.toString());
			return null;
		}
	}

	/**********************************************************************************************************/

}
