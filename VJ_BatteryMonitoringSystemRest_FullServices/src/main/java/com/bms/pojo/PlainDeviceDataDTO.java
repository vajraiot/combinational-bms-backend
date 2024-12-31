package com.bms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bms.entity.DeviceData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlainDeviceDataDTO {  //no use presently

	private String siteId;
	private String serialNumber;
	
	private DeviceData deviceData;
	
}
