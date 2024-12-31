package com.bms.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.DeviceData;
import com.bms.enums.HistoricaAlarmsDetailsReport;
import com.bms.enums.HistoricaCellDetailsReportParams;

public class HistoricalCellDetailsReportDownloadXls extends AbstractXlsView {

	private static SimpleDateFormat gDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		String xlFileName = "HistoricalCellgDetailsReport" + ".xlsx";
		response.setHeader("Content-Disposition", "attachment;" + xlFileName);

		Sheet sheet = workbook.createSheet("HistoricalCellgDetailsReport");

		List<DeviceData> lstDeviceData = new ArrayList<DeviceData>();
		try {

			lstDeviceData = (List<DeviceData>) model.get("HistoricalCellgDetailsReport");
		} catch (Exception ex) {

		}

		sheet.setDefaultRowHeight((short) 450);
		CellStyle style = null;
		CellStyle style0 = null;
		CellStyle style1 = null;
		CellStyle style2 = null;
		CellStyle style3 = null;
		CellStyle style4 = null;
		DataFormat format = null;
		Cell cell;
		Font font;

		format = workbook.createDataFormat();
		style = workbook.createCellStyle();
		style0 = workbook.createCellStyle();
		style1 = workbook.createCellStyle();
		style2 = workbook.createCellStyle();
		style3 = workbook.createCellStyle();
		style4 = workbook.createCellStyle();

		style0.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style0.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style0.setAlignment(HorizontalAlignment.CENTER);
		style0.setVerticalAlignment(VerticalAlignment.CENTER);

		style0.setBorderBottom(BorderStyle.THIN);
		style0.setBorderTop(BorderStyle.THIN);
		style0.setBorderRight(BorderStyle.THIN);
		style0.setBorderLeft(BorderStyle.THIN);

		font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		style0.setFont(font);

		style1.setAlignment(HorizontalAlignment.CENTER);
		style1.setVerticalAlignment(VerticalAlignment.CENTER);
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderTop(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);

		Row fistRow = sheet.createRow((short) 0);
		Row secondRow = sheet.createRow((short) 1);
		int cellsCount = lstDeviceData.get(0).getCellVoltageTemperatureData().size();
		int pos = 0;
		System.out.println("cellsCount is:" + cellsCount);

		HistoricaCellDetailsReportParams hcdp = HistoricaCellDetailsReportParams.SN;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		fistRow.getSheet().addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.setColumnWidth(hcdp.position, 10 * 256);

		hcdp = HistoricaCellDetailsReportParams.SITE_ID;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		fistRow.getSheet().addMergedRegion(new CellRangeAddress(0, 1, hcdp.position, hcdp.position));
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = HistoricaCellDetailsReportParams.SERIAL_NUMBER;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		fistRow.getSheet().addMergedRegion(new CellRangeAddress(0, 1, hcdp.position, hcdp.position));
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = HistoricaCellDetailsReportParams.PACKET_TIME;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		fistRow.getSheet().addMergedRegion(new CellRangeAddress(0, 1, hcdp.position, hcdp.position));
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		int cellPosition = HistoricaCellDetailsReportParams.values().length;
		List<ExcelCellPosition> lstExcelCellPosition = new ArrayList<ExcelCellPosition>();
		Map<Integer, ExcelCellPosition> mpExcelCellPosition = new HashMap<Integer, ExcelCellPosition>();

		for (int i = 0; i < cellsCount; i++) {
			ExcelCellPosition lclExcelCellPosition = new ExcelCellPosition();
			int cellSerialNumber = (i + 1);
			int startCellPosition = cellPosition;
			cellPosition += 1;
			int endCellPosition = cellPosition;

			cell = fistRow.createCell(startCellPosition);
			cell.setCellValue("Cell " + cellSerialNumber);
			cell.setCellStyle(style0);
			fistRow.getSheet().addMergedRegion(new CellRangeAddress(0, 0, startCellPosition, endCellPosition));

			cell = secondRow.createCell(startCellPosition);
			cell.setCellValue("Voltage");
			cell.setCellStyle(style0);

			cell = secondRow.createCell(endCellPosition);
			cell.setCellValue("Temperature");
			cell.setCellStyle(style0);

			lclExcelCellPosition.setSerialNumber(cellSerialNumber);
			lclExcelCellPosition.setVoltage(startCellPosition);
			lclExcelCellPosition.setTemperature(endCellPosition);

			mpExcelCellPosition.put(cellSerialNumber, lclExcelCellPosition);
			cellPosition += 1;

			/********* this is for set widht for coloumn ************/
			sheet.setColumnWidth(startCellPosition, 15 * 256);
			sheet.setColumnWidth(endCellPosition, 15 * 256);

		}

		int rowNuber = 1;
		int serialNumber = 0;

		if (lstDeviceData != null)
			if (!lstDeviceData.isEmpty()) {

				for (DeviceData inst : lstDeviceData) {
					rowNuber++;
					serialNumber++;
					Row rows = sheet.createRow((short) rowNuber);
					/***************************************************************************/
					cell = rows.createCell(HistoricaAlarmsDetailsReport.SN.position);
					cell.setCellValue(serialNumber);
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaCellDetailsReportParams.SITE_ID.position);
					cell.setCellValue(inst.getGeneralData().getSiteId());
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaCellDetailsReportParams.SERIAL_NUMBER.position);
					cell.setCellValue(inst.getSerialNumber());
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaCellDetailsReportParams.PACKET_TIME.position);
					cell.setCellValue(gDateFormate.format(inst.getGeneralData().getPacketDateTime()));
					cell.setCellStyle(style1);

					List<CellVoltageTemperatureData> lstCellVoltageTemperatureData = inst
							.getCellVoltageTemperatureData();

					for (int i = 0; i < lstCellVoltageTemperatureData.size(); i++) {
						try {
							CellVoltageTemperatureData _cellVoltageTemperatureData = lstCellVoltageTemperatureData
									.get(i);
							int lclCellSerialNumber = i + 1;

							ExcelCellPosition _excelCellPosition = mpExcelCellPosition
									.get(_cellVoltageTemperatureData.getCellNumber());

							/********************** Voltage **************************/
							cell = rows.createCell(_excelCellPosition.getVoltage());
							cell.setCellValue(floatPrecision(_cellVoltageTemperatureData.getCellVoltage(), 3));
							cell.setCellStyle(style1);

							/********************** Temperature **************************/
							cell = rows.createCell(_excelCellPosition.getTemperature());
							cell.setCellValue(floatPrecision(_cellVoltageTemperatureData.getCellTemperature(), 3));
							cell.setCellStyle(style1);
						} catch (Exception ex) {
							System.out.println("Exception occured:" + ex.toString());
							continue;
						}
					}

					/***************************************************************************/
				}

			}

		/*
		 * for(int columnIndex = 0; columnIndex < 30; columnIndex++) {
		 * sheet.autoSizeColumn(columnIndex); }
		 */

		setBordersToMergedCells(sheet); // this is for adding bordey for merged boxes.

	}

	private void setBordersToMergedCells(Sheet sheet) {
		int numMerged = sheet.getNumMergedRegions();
		for (int i = 0; i < numMerged; i++) {
			CellRangeAddress mergedRegions = sheet.getMergedRegion(i);
			RegionUtil.setBorderLeft(BorderStyle.THIN, mergedRegions, sheet);
			RegionUtil.setBorderRight(BorderStyle.THIN, mergedRegions, sheet);
			RegionUtil.setBorderTop(BorderStyle.THIN, mergedRegions, sheet);
			RegionUtil.setBorderBottom(BorderStyle.THIN, mergedRegions, sheet);

		}
	}

	private String floatPrecision(float fltValue, int floatLimit) {
		// String.format("%.2f", inst.getDissolvedOxygen());
		String strFloatLimit = "%." + floatLimit + "f";

		return String.format(strFloatLimit, fltValue);

	}

	public String findWaterLevel(Boolean blnLowWaterLevel, Boolean blnHighWaterLevel) {
		if (blnHighWaterLevel == true) {
			return "High";
		} else if (blnLowWaterLevel == true) {
			return "Low";
		} else {
			return "Normal";
		}
	}

	public String booleonToYN(Boolean bln) {
		if (bln == true) {
			return "Yes";
		} else if (bln == false) {
			return "No";
		} else {
			return "";
		}
	}

	public class ExcelCellPosition {
		private int serialNumber;
		private int voltage;
		private int temperature;

		public int getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(int serialNumber) {
			this.serialNumber = serialNumber;
		}

		public int getVoltage() {
			return voltage;
		}

		public void setVoltage(int voltage) {
			this.voltage = voltage;
		}

		public int getTemperature() {
			return temperature;
		}

		public void setTemperature(int temperature) {
			this.temperature = temperature;
		}

		public ExcelCellPosition(int serialNumber, int voltage, int temperature) {
			super();
			this.serialNumber = serialNumber;
			this.voltage = voltage;
			this.temperature = temperature;
		}

		public ExcelCellPosition() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

}
