package com.bms.pojo;


import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.DeviceData;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DeviceDataWithLastIndex {

	private DeviceData deviceData;
	private ChargerMonitoringData chargerMonitoringData;
	private int lastIndex;
	

}
