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

import com.bms.entity.DeviceData;
import com.bms.enums.HistoricaStringDetailsReportParams;
import com.bms.utilities.Commonutility;

public class HistoricalStringDetailsReportDownloadXls extends AbstractXlsView {

	private static SimpleDateFormat gDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		String xlFileName = "HistoricalStringDetailsReport" + ".xls";
		response.setHeader("Content-Disposition", "attachment;" + xlFileName);

		Sheet sheet = workbook.createSheet("HistoricalStringDetailsReport");

		List<DeviceData> lstDeviceData = new ArrayList<DeviceData>();
		try {

			lstDeviceData = (List<DeviceData>) model.get("HistoricalStringDetailsReport");
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

		Row rowhead = sheet.createRow((short) 0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.SN.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.SN.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.SITE_ID.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.SITE_ID.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.SERIAL_NUMBER.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.SERIAL_NUMBER.name);
		cell.setCellStyle(style0);

		/*
		 * cell =
		 * rowhead.createCell(HistoricaStringDetailsReportParams.INSTALLATION_DATE.
		 * position);
		 * cell.setCellValue(HistoricaStringDetailsReportParams.INSTALLATION_DATE.name);
		 * cell.setCellStyle(style0);
		 * 
		 * cell =
		 * rowhead.createCell(HistoricaStringDetailsReportParams.CELLS_CONNECTED_COUNT.
		 * position);
		 * cell.setCellValue(HistoricaStringDetailsReportParams.CELLS_CONNECTED_COUNT.
		 * name); cell.setCellStyle(style0);
		 * 
		 * cell =
		 * rowhead.createCell(HistoricaStringDetailsReportParams.PROBLEM_CELLS.position)
		 * ; cell.setCellValue(HistoricaStringDetailsReportParams.PROBLEM_CELLS.name);
		 * cell.setCellStyle(style0);
		 */

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.STRING_VOLTAGE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.STRING_VOLTAGE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.SYSTEM_PEAK_CURRENT_IN_CHARGE_ONE_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.SYSTEM_PEAK_CURRENT_IN_CHARGE_ONE_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.AVERAGE_DISCHARGING_CURRENT.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.AVERAGE_DISCHARGING_CURRENT.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.AVERAGE_CHARGING_CURRENT.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.AVERAGE_CHARGING_CURRENT.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.AH_IN_FOR_ONE_CHARGE_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.AH_IN_FOR_ONE_CHARGE_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.AH_OUT_FOR_ONE_DISCHARGE_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.AH_OUT_FOR_ONE_DISCHARGE_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.CUMULATIVE_AH_IN.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.CUMULATIVE_AH_IN.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.CUMULATIVE_AH_OUT.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.CUMULATIVE_AH_OUT.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.CHARGE_TIME_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.CHARGE_TIME_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.DISCHARGE_TIME_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.DISCHARGE_TIME_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.TOTAL_CHARGING_ENERGY.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.TOTAL_CHARGING_ENERGY.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.TOTAL_DISCHARGING_ENERGY.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.TOTAL_DISCHARGING_ENERGY.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.EVERY_HOUR_AVG_TEMP.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.EVERY_HOUR_AVG_TEMP.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.CUMULATIVE_TOTAL_AVG_TEMP_EVERY_HOUR.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.CUMULATIVE_TOTAL_AVG_TEMP_EVERY_HOUR.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.CHARGE_OR_DISHCARGE_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.CHARGE_OR_DISHCARGE_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.SOC_LATEST_VALUE_FOR_EVERY_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.SOC_LATEST_VALUE_FOR_EVERY_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.DOD_LATEST_VALUE_FOR_EVERY_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.DOD_LATEST_VALUE_FOR_EVERY_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead
				.createCell(HistoricaStringDetailsReportParams.SYSTEM_PEAK_CURRENT_IN_DISCHARGE_ONE_CYCLE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.SYSTEM_PEAK_CURRENT_IN_DISCHARGE_ONE_CYCLE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.INSTANTANEOUS_CURRENT.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.INSTANTANEOUS_CURRENT.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.AMBIENT_TEMPERATURE.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.AMBIENT_TEMPERATURE.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.BATTERY_RUN_HOURS.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.BATTERY_RUN_HOURS.name);
		cell.setCellStyle(style0);

		cell = rowhead.createCell(HistoricaStringDetailsReportParams.PACKET_TIME.position);
		cell.setCellValue(HistoricaStringDetailsReportParams.PACKET_TIME.name);
		cell.setCellStyle(style0);

		/*
		 * cell =
		 * rowhead.createCell(HistoricaStringDetailsReportParams.DEVICE_ID.position);
		 * cell.setCellValue(HistoricaStringDetailsReportParams.DEVICE_ID.name);
		 * cell.setCellStyle(style0);
		 * 
		 * cell =
		 * rowhead.createCell(HistoricaStringDetailsReportParams.BMS_MANUFACTURER_ID.
		 * position);
		 * cell.setCellValue(HistoricaStringDetailsReportParams.BMS_MANUFACTURER_ID.name
		 * ); cell.setCellStyle(style0);
		 */

		int rowNuber = 0;
		int serialNumber = 0;

		if (lstDeviceData != null)
			if (!lstDeviceData.isEmpty()) {

				for (DeviceData inst : lstDeviceData) {
					try {
						rowNuber++;
						serialNumber++;
						Row rows = sheet.createRow((short) rowNuber);
						/***************************************************************************/
						cell = rows.createCell(HistoricaStringDetailsReportParams.SN.position);
						cell.setCellValue(serialNumber);
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.SITE_ID.position);
						cell.setCellValue((inst.getGeneralData().getSiteId()));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.SERIAL_NUMBER.position);
						cell.setCellValue((inst.getSerialNumber()));
						cell.setCellStyle(style1);

						/*
						 * cell =
						 * rows.createCell(HistoricaStringDetailsReportParams.INSTALLATION_DATE.position
						 * ); cell.setCellValue(( inst.getInstallationDate()));
						 * cell.setCellStyle(style1);
						 * 
						 * cell =
						 * rows.createCell(HistoricaStringDetailsReportParams.CELLS_CONNECTED_COUNT.
						 * position); cell.setCellValue(( inst.getCellsConnectedCount()));
						 * cell.setCellStyle(style1);
						 * 
						 * cell =
						 * rows.createCell(HistoricaStringDetailsReportParams.PROBLEM_CELLS.position);
						 * cell.setCellValue(( inst.getProblemCells())); cell.setCellStyle(style1);
						 * 
						 * cell =
						 * rows.createCell(HistoricaStringDetailsReportParams.PROBLEM_CELLS.position);
						 * cell.setCellValue(( inst.getProblemCells())); cell.setCellStyle(style1);
						 */

						cell = rows.createCell(HistoricaStringDetailsReportParams.STRING_VOLTAGE.position);
						cell.setCellValue(floatPrecision(inst.getStringvoltage(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(
								HistoricaStringDetailsReportParams.SYSTEM_PEAK_CURRENT_IN_CHARGE_ONE_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getSystemPeakCurrentInChargeOneCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.AVERAGE_DISCHARGING_CURRENT.position);
						cell.setCellValue(floatPrecision(inst.getAverageDischargingCurrent(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.AVERAGE_CHARGING_CURRENT.position);
						cell.setCellValue(floatPrecision(inst.getAverageChargingCurrent(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.AH_IN_FOR_ONE_CHARGE_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getAhInForOneChargeCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows
								.createCell(HistoricaStringDetailsReportParams.AH_OUT_FOR_ONE_DISCHARGE_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getAhOutForOneDischargeCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.CUMULATIVE_AH_IN.position);
						cell.setCellValue(floatPrecision(inst.getCumulativeAHIn(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.CUMULATIVE_AH_OUT.position);
						cell.setCellValue(floatPrecision(inst.getCumulativeAHOut(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.CHARGE_TIME_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getChargeTimeCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.DISCHARGE_TIME_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getDischargeTimeCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.TOTAL_CHARGING_ENERGY.position);
						cell.setCellValue(floatPrecision(inst.getTotalChargingEnergy(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.TOTAL_DISCHARGING_ENERGY.position);
						cell.setCellValue(floatPrecision(inst.getTotalDischargingEnergy(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.EVERY_HOUR_AVG_TEMP.position);
						cell.setCellValue(floatPrecision(inst.getEveryHourAvgTemp(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(
								HistoricaStringDetailsReportParams.CUMULATIVE_TOTAL_AVG_TEMP_EVERY_HOUR.position);
						cell.setCellValue(floatPrecision(inst.getCumulativeTotalAvgTempEveryHour(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.CHARGE_OR_DISHCARGE_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getChargeOrDischargeCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(
								HistoricaStringDetailsReportParams.SOC_LATEST_VALUE_FOR_EVERY_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getSocLatestValueForEveryCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(
								HistoricaStringDetailsReportParams.DOD_LATEST_VALUE_FOR_EVERY_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getDodLatestValueForEveryCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(
								HistoricaStringDetailsReportParams.SYSTEM_PEAK_CURRENT_IN_DISCHARGE_ONE_CYCLE.position);
						cell.setCellValue(floatPrecision(inst.getSystemPeakCurrentInDischargeOneCycle(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.INSTANTANEOUS_CURRENT.position);
						cell.setCellValue(floatPrecision(inst.getInstantaneousCurrent(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.AMBIENT_TEMPERATURE.position);
						cell.setCellValue(floatPrecision(inst.getAmbientTemperature(), 3));
						cell.setCellStyle(style1);

						cell = rows.createCell(HistoricaStringDetailsReportParams.BATTERY_RUN_HOURS.position);
						cell.setCellValue(Commonutility.secondsTohhmmss(inst.getBatteryRunHours()));
						cell.setCellStyle(style1);

						// cell =
						// rows.createCell(HistoricaStringDetailsReportParams.SERVER_TIME.position);
						// cell.setCellValue(gDateFormate.format(inst.getServerTime()));

						cell = rows.createCell(HistoricaStringDetailsReportParams.PACKET_TIME.position);
						cell.setCellValue(gDateFormate.format(inst.getGeneralData().getPacketDateTime()));
						cell.setCellStyle(style1);

						/*
						 * cell =
						 * rows.createCell(HistoricaStringDetailsReportParams.DEVICE_ID.position);
						 * cell.setCellValue(( inst.getDeviceId())); cell.setCellStyle(style1);
						 * 
						 * cell =
						 * rows.createCell(HistoricaStringDetailsReportParams.BMS_MANUFACTURER_ID.
						 * position); cell.setCellValue(( inst.getBmsManufacturerID()));
						 * cell.setCellStyle(style1);
						 */

						/***************************************************************************/
					} catch (Exception ex) {
						System.out.println("Exceptin occured In Histrical string param:" + ex.toString());
						continue;
					}
				}

			}

		for (int columnIndex = 0; columnIndex < 30; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
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
