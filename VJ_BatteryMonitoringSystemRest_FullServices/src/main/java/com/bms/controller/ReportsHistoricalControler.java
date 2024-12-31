package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bms.entity.DeviceData;
import com.bms.excel.HistoricalAlarmsDetailsReportDownloadXls;
import com.bms.excel.HistoricalCellDetailsReportDownloadXls;
import com.bms.excel.HistoricalStringDetailsReportDownloadXls;
import com.bms.miscellaneous.ContentPageMaker;
import com.bms.pojo.StringParamDTO;
import com.bms.services.GeneralDataService;
import com.bms.services.ReportsService;

@CrossOrigin
@RestController
public class ReportsHistoricalControler {
	/*** for fetching refer the siteIdListcontroller *******/

	@Autowired
	GeneralDataService generalDataService;

	@Autowired
	ReportsService reportsService;

	@CrossOrigin
	@GetMapping(value = "/getHistoricalStringDataBySiteidAndSerialNumberPg") // this is used for historical data.
	public ResponseEntity<ContentPageMaker> getHistoricalStringDataBySiteidAndSerialNumberPg(Pageable pageable,
			String serialNumber, String siteId) {
		ContentPageMaker rtnContentPageMaker = reportsService
				.getHistoricalStringDataBySerialNumberAndSiteIdWithPg(pageable, serialNumber, siteId);
		return ResponseEntity.ok(rtnContentPageMaker);
	}

	@CrossOrigin
	@GetMapping(value = "/getHistoricalCellDataBySiteidAndSerialNumberPg") // this is used for historical data.
	public ResponseEntity<ContentPageMaker> getHistoricalCellDataBySiteidAndSerialNumberPg(Pageable pageable,
			String serialNumber, String siteId) {
		ContentPageMaker rtnContentPageMaker = reportsService
				.getHistoricalCellDataBySerialNumberAndSiteIdWithPg(pageable, serialNumber, siteId);
		return ResponseEntity.ok(rtnContentPageMaker);
	}

	/****************** With Dates between ***********************************/

	@CrossOrigin
	@GetMapping(value = "/getHistoricalStringDataBySiteidAndSerialNumberBetweenDateswithPg")
	public ResponseEntity<ContentPageMaker> getHistoricalStringDataBySiteidAndSerialNumberBetweenDateswithPg(
			Pageable pageable, String serialNumber, String siteId, String strStartDate, String strEndDate) {
		ContentPageMaker rtnContentPageMaker = reportsService
				.getHistoricalStringDataBySerialNumberAndSiteIdBetWeenDatesWithPg(pageable, serialNumber, siteId,
						strStartDate, strEndDate);
		return ResponseEntity.ok(rtnContentPageMaker);
	}

	@CrossOrigin
	@GetMapping(value = "/getHistoricalCellDataBySiteidAndSerialNumberBetweenDateswithPg")
	public ResponseEntity<ContentPageMaker> getHistoricalCellDataBySiteidAndSerialNumberBetweenDateswithPg(
			Pageable pageable, String serialNumber, String siteId, String strStartDate, String strEndDate) {
		ContentPageMaker rtnContentPageMaker = reportsService
				.getHistoricalCellDataBySerialNumberAndSiteIdBetWeenDatesWithPg(pageable, serialNumber, siteId,
						strStartDate, strEndDate);
		return ResponseEntity.ok(rtnContentPageMaker);
	}

	@CrossOrigin
	@GetMapping(value = "/getHistoricalAlarmsDataBySiteidAndSerialNumberBetweenDateswithPg") // this is used for
																								// historical data.
	public ResponseEntity<ContentPageMaker> getHistoricalAlarmsDataBySiteidAndSerialNumberBetweenDateswithPg(
			Pageable pageable, String serialNumber, String siteId, String strStartDate, String strEndDate) {
		ContentPageMaker rtnContentPageMaker = reportsService
				.getHistoricalAlarmsDataBySerialNumberAndSiteIdBetweenDatesWithPg(pageable, serialNumber, siteId,
						strStartDate, strEndDate);
		return ResponseEntity.ok(rtnContentPageMaker);
	}

	@CrossOrigin
	@GetMapping(value = "/getHistoricalStringDataBySerialNumberAndSiteIdAndBetweenDates") // this is used for historical
																							// data.
	public ResponseEntity<List<StringParamDTO>> getHistoricalStringDataBySerialNumberAndSiteIdAndBetweenDates(
			String serialNumber, String siteId, String strFromDate, String strToDate) {
		List<StringParamDTO> lstStringParamDTO = reportsService
				.getHistoricalStringDataBySerialNumberAndSiteIdAndBetweenDates(serialNumber, siteId, strFromDate,
						strToDate);
		return ResponseEntity.ok(lstStringParamDTO);
	}
	

	/**********************************************
	 * DownLoad Excel
	 ***********************************/
	@CrossOrigin
	@GetMapping(value = "/downloadHistoricalStringReport")
	public ModelAndView downloadHistoricalStringReport(String siteId, String serialNumber, String strStartDate,
			String strEndDate) {
		try {
			System.out.println("Request for downloadHistoricalStringReport siteId:" + siteId + " serialNumber:"
					+ serialNumber + " strStartDate:" + strStartDate + " strEndDate:" + strEndDate);
			List<DeviceData> _lstDeviceData = reportsService.getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates(
					serialNumber, siteId, strStartDate, strEndDate);
			return new ModelAndView(new HistoricalStringDetailsReportDownloadXls(), "HistoricalStringDetailsReport",
					_lstDeviceData);
		} catch (Exception ex) {
			System.out.println("Exception occured in DownloadHistoricalStringReport." + ex.toString());
			return null;
		}
	}

	@CrossOrigin
	@GetMapping(value = "/downloadHistoricalCellsReport")
	public ModelAndView downloadHistoricalCellsReport(String siteId, String serialNumber, String strStartDate,
			String strEndDate) {
		try {
			System.out.println("Request for downloadHistoricalCellsReport siteId:" + siteId + " serialNumber:"
					+ serialNumber + " strStartDate:" + strStartDate + " strEndDate:" + strEndDate);
			List<DeviceData> _lstDeviceData = reportsService.getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates(
					serialNumber, siteId, strStartDate, strEndDate);
			return new ModelAndView(new HistoricalCellDetailsReportDownloadXls(), "HistoricalCellgDetailsReport",
					_lstDeviceData);
		} catch (Exception ex) {
			System.out.println("Exception occured in DownloadHistoricalCellsReport." + ex.toString());
			return null;
		}
	}

	@CrossOrigin
	@GetMapping(value = "/downloadHistoricalAlarmsReport")
	public ModelAndView downloadHistoricalAlarmsReport(String siteId, String serialNumber, String strStartDate,
			String strEndDate) {
		try {
			System.out.println("Request for downloadHistoricalAlarmsReport siteId:" + siteId + " serialNumber:"
					+ serialNumber + " strStartDate:" + strStartDate + " strEndDate:" + strEndDate);
			List<DeviceData> _lstDeviceData = reportsService.getHistoricalDataBySerialNumberAndSiteIdWithBetweenDates(
					serialNumber, siteId, strStartDate, strEndDate);
			return new ModelAndView(new HistoricalAlarmsDetailsReportDownloadXls(), "HistoricalAlarmsgDetailsReport",
					_lstDeviceData);
		} catch (Exception ex) {
			System.out.println("Exception occured in DownloadHistoricalCellsReport." + ex.toString());
			return null;
		}
	}

	/****************** Testing Excell Download ************************/
	@CrossOrigin
	@GetMapping(value = "/TestExcel") // testing purpose
	public ModelAndView testExcel(String serialNumber, String siteId, String strStartDate, String strEndDate) {
		try {
			List<DeviceData> _lstDeviceData = reportsService.getLatestNRecords();
			return new ModelAndView(new HistoricalStringDetailsReportDownloadXls(), "HistoricalStringDetailsReport",
					_lstDeviceData);
		} catch (Exception ex) {
			System.out.println("Exception occured." + ex.toString());
			return null;
		}
	}

	@CrossOrigin
	@GetMapping(value = "/cdt") // testing purpose
	public ModelAndView cellDetailsTest() {
		try {
			List<DeviceData> _lstDeviceData = reportsService.getLatestNRecords();
			return new ModelAndView(new HistoricalCellDetailsReportDownloadXls(), "HistoricalCellgDetailsReport",
					_lstDeviceData);
		} catch (Exception ex) {
			System.out.println("Exception occured." + ex.toString());
			return null;
		}
	}

	/************************************************************************************************/

	/******************************************
	 * DayWise Report
	 ********************************************/

	/************************************************************************************************/

	/******************************************
	 * Monthly Report
	 ********************************************/

	/****************************************************************************************************/

	/************* Testing ********************/
	/*
	 * @CrossOrigin
	 * 
	 * @GetMapping(value="/TestgetHistoricalDataForStringParamBetweenDates") //this
	 * is used for historical data. // public ContentPageMaker
	 * getFtmsDataWithSelectedParametersByPacketTimePg(Pageable pageable,String
	 * strFromtDate,String strToDate) public ResponseEntity<List<StringParamDTO>>
	 * testGetFtmsDataWithSelectedParametersByPacketTimePg(String serialNumber,
	 * String siteId) { List<StringParamDTO> rtnLstStringParamDTO=
	 * reportsService.getHistoricalDataBetweenDatesForSerialNumberAndSiteId(
	 * serialNumber, siteId); return ResponseEntity.ok(rtnLstStringParamDTO); }
	 */
}
