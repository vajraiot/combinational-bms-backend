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
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.bms.enums.SpecificCellDataReport;
import com.bms.pojo.SpecificCellDetailsDTO;
import com.bms.utilities.Commonutility;

public class SpecificCellDataReportDownloadXls extends AbstractXlsView {

	private static SimpleDateFormat gDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		String xlFileName = "SpecificCellDataReport" + ".xls";
		response.setHeader("Content-Disposition", "attachment;" + xlFileName);

		Sheet sheet = workbook.createSheet("SpecificCellDataReport");

		List<SpecificCellDetailsDTO> lstCellVTD = new ArrayList<SpecificCellDetailsDTO>();
		try {

			lstCellVTD = (List<SpecificCellDetailsDTO>) model.get("SpecificCellDataReport");
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

		style1.setAlignment(HorizontalAlignment.CENTER);
		style1.setVerticalAlignment(VerticalAlignment.CENTER);
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderTop(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);

		style0.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style0.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style0.setAlignment(HorizontalAlignment.CENTER);
		style0.setVerticalAlignment(VerticalAlignment.CENTER);
//		    style0.setWrapText(true);
		style0.setBorderBottom(BorderStyle.THIN);
		style0.setBorderTop(BorderStyle.THIN);
		style0.setBorderRight(BorderStyle.THIN);
		style0.setBorderLeft(BorderStyle.THIN);

		font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		style0.setFont(font);

		Row fistRow = sheet.createRow((short) 0);

		SpecificCellDataReport hcdp = SpecificCellDataReport.SN;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 10 * 256);

		hcdp = SpecificCellDataReport.SITE_ID;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = SpecificCellDataReport.SERIAL_NUMBER;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = SpecificCellDataReport.PACKET_TIME;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = SpecificCellDataReport.CELL_NUMBER;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = SpecificCellDataReport.CELL_VOLTAGE;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		hcdp = SpecificCellDataReport.CELL_TEMPERATURE;
		cell = fistRow.createCell(hcdp.position);
		cell.setCellValue(hcdp.name);
		cell.setCellStyle(style0);
		sheet.setColumnWidth(hcdp.position, 25 * 256);

		int rowNuber = 0;
		int serialNumber = 0;

		if (lstCellVTD != null)
			if (!lstCellVTD.isEmpty()) {

				for (SpecificCellDetailsDTO inst : lstCellVTD) {

					rowNuber++;
					serialNumber++;
					Row rows = sheet.createRow((short) rowNuber);
					/***************************************************************************/
					cell = rows.createCell(SpecificCellDataReport.SN.position);
					cell.setCellValue(serialNumber);
					cell.setCellStyle(style1);

					cell = rows.createCell(SpecificCellDataReport.SITE_ID.position);
					cell.setCellValue(inst.getSiteId());
					cell.setCellStyle(style1);

					cell = rows.createCell(SpecificCellDataReport.SERIAL_NUMBER.position);
					cell.setCellValue(inst.getSerialNumber());
					cell.setCellStyle(style1);

					cell = rows.createCell(SpecificCellDataReport.PACKET_TIME.position);
					cell.setCellValue(gDateFormate.format(inst.getPacketDateTime()));
					cell.setCellStyle(style1);

					cell = rows.createCell(SpecificCellDataReport.CELL_NUMBER.position);
					cell.setCellValue((inst.getCellNumber()));
					cell.setCellStyle(style1);

					cell = rows.createCell(SpecificCellDataReport.CELL_VOLTAGE.position);
					cell.setCellValue(Commonutility.floatPrecision(inst.getCellVoltage(), 3));
					cell.setCellStyle(style1);

					cell = rows.createCell(SpecificCellDataReport.CELL_TEMPERATURE.position);
					cell.setCellValue(Commonutility.floatPrecision(inst.getCellTemperature(), 3));
					cell.setCellStyle(style1);

					/***************************************************************************/

				}

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

}
