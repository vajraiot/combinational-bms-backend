package com.bms.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.bms.entity.DeviceData;
import com.bms.enums.HistoricaAlarmsDetailsReport;
import com.bms.enums.HistoricaCellDetailsReportParams;
import com.bms.pojo.AlarmsParamDTO;
import com.bms.utilities.ApplicationUtility;

public class HistoricalAlarmsDetailsReportDownloadXls extends AbstractXlsView {

	private static SimpleDateFormat gDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		String xlFileName = "HistoricalAlarmsgDetailsReport" + ".xlsx";
		response.setHeader("Content-Disposition", "attachment;" + xlFileName);

		Sheet sheet = workbook.createSheet("HistoricalAlarmsgDetailsReport");

		List<DeviceData> lstDeviceData = new ArrayList<DeviceData>();

		try {

			lstDeviceData = (List<DeviceData>) model.get("HistoricalAlarmsgDetailsReport");
		} catch (Exception ex) {
			System.out.println("Exception occured white model.get():" + ex.toString());
			return;
		}

		List<AlarmsParamDTO> lstAlarmsParamDTO = new ArrayList<AlarmsParamDTO>();
		try {
			lstAlarmsParamDTO = ApplicationUtility.convertAlarmsParamDTO(lstDeviceData);
		} catch (Exception ex) {
			System.out.println("Exception occured while converting lstDevicedata to lstAlarmsParamDTO.");
			return;
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

		/***************************** Header Row **********************************/
		HistoricaAlarmsDetailsReport hadr = HistoricaAlarmsDetailsReport.SN;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.SITE_ID;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.SERIAL_NUMBER;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.PACKET_TIME;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.BANK_CYCLE;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.SOC;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.STRING_VOLTAGE;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.STIRNG_CURRENT;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.BMS_SED_COMMUNICATION;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.CELL_COMMUNICATION;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.CELL_VOLTAGE;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		hadr = HistoricaAlarmsDetailsReport.CELL_TEMPERATURE;
		cell = fistRow.createCell(hadr.position);
		cell.setCellValue(hadr.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hadr.position, 10 * 256);

		/***************************************************************/

		int rowNuber = 0;
		int serialNumber = 0;

		if (lstAlarmsParamDTO != null)
			if (!lstAlarmsParamDTO.isEmpty()) {

				for (AlarmsParamDTO inst : lstAlarmsParamDTO) {
					rowNuber++;
					serialNumber++;
					Row rows = sheet.createRow((short) rowNuber);
					/***************************************************************************/
					cell = rows.createCell(HistoricaAlarmsDetailsReport.SN.position);
					cell.setCellValue(serialNumber);
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.SITE_ID.position);
					cell.setCellValue(inst.getSiteId());
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.SERIAL_NUMBER.position);
					cell.setCellValue(inst.getSerialNumber());
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaCellDetailsReportParams.PACKET_TIME.position);
					cell.setCellValue(gDateFormate.format(inst.getPacketDateTime()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.BANK_CYCLE.position);
					cell.setCellValue((inst.getBankCycle()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.SOC.position);
					cell.setCellValue((inst.getSoc()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.STRING_VOLTAGE.position);
					cell.setCellValue((inst.getStringVoltage()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.STIRNG_CURRENT.position);
					cell.setCellValue((inst.getStringCurrent()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.BMS_SED_COMMUNICATION.position);
					cell.setCellValue((inst.getBmsSedCommunication()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.CELL_COMMUNICATION.position);
					cell.setCellValue((inst.getCellCommunication()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.CELL_VOLTAGE.position);
					cell.setCellValue((inst.getCellVoltage()));
					cell.setCellStyle(style1);

					cell = rows.createCell(HistoricaAlarmsDetailsReport.CELL_TEMPERATURE.position);
					cell.setCellValue((inst.getCellTemperature()));
					cell.setCellStyle(style1);

					/***************************************************************************/
				}

			}

		for (int columnIndex = 0; columnIndex < (HistoricaAlarmsDetailsReport.values().length + 1); columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}

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
