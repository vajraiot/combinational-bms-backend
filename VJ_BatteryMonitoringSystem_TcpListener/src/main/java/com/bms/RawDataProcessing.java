package com.bms;

import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.service.BMSAlarmsService;
import com.bms.hibernate.service.CellVoltageTemperatureDataService;
import com.bms.hibernate.service.DeviceDataService;
import com.bms.hibernate.service.GeneralDataService;
import com.bms.hibernate.service.RawdataService;
import com.bms.packetTypeParse.BatteryMonitoringDataParser;
import com.bms.packetTypeParse.GeneralDataParser;
import com.bms.pojo.DeviceDataWithLastIndex;
import com.bms.utilities.Commonutility;
import com.bms.utilities.Converters;

public class RawDataProcessing {

	public static void packetProcess(String packet) {

		packet = packet.toUpperCase();
		Converters cnv = new Converters();

		/******************** save rawData **************************************/
		Rawdata rawdata = null;
		try {
			rawdata = new RawdataService().processAndsave(packet);
		} catch (Exception ex) {
			System.out.println("exception while saving the Rawdata" + ex);
		}

		/**********************************************************/

		/*********************
		 * BMS Data processing
		 *************************************/

		// BMSAlarmStatus _BMSAlarmStatus=new BMSAlarmStatusParser().parse(packet);
		GeneralData _generalData = new GeneralDataParser().parse(packet);

		new GeneralDataService().save(_generalData);

		int bytecount = 36;
		int deviceId = Commonutility.hex2decimal(Commonutility.getSubstringbytes(34, 1, packet));
		// while(bytecount<(packet.length()/2))
		// {
		DeviceDataWithLastIndex _deviceDataWithLastIndex = new BatteryMonitoringDataParser().parse(deviceId, bytecount,
				packet);
		// bytecount

		// }

		saveCompleteBatterMonitoringData(_generalData, _deviceDataWithLastIndex.getDeviceData());

		/*****************************************************************************/

	}

	public static int saveCompleteBatterMonitoringData(GeneralData _generalData, DeviceData _deviceData) {

		new DeviceDataService().save(_generalData, _deviceData);

		new BMSAlarmsService().save(_deviceData);

		new CellVoltageTemperatureDataService().save(_deviceData);
		return 0;
	}

}
