package com.bms.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlainManufacturerDetails {


	private Long id;
	
	private String siteId;
	
	private String serialNumber;	
	
	
	private String firstUsedDate;

	private String batterySerialNumber;
	
	private String batteryBankType;
	
	private String ahCapacity;
	
	private String manifactureName;
	
	private String designVoltage;
	
	private String individualCellVoltage;
	
	private Date serverTime;

	@Override
	public String toString() {
		return "PlainManufacturerDetails [id=" + id + ", siteId=" + siteId + ", serialNumber=" + serialNumber
				+ ", firstUsedDate=" + firstUsedDate + ", batterySerialNumber=" + batterySerialNumber
				+ ", batteryBankType=" + batteryBankType + ", ahCapacity=" + ahCapacity + ", manifactureName="
				+ manifactureName + ", designVoltage=" + designVoltage + ", individualCellVoltage="
				+ individualCellVoltage + ", serverTime=" + serverTime + "]";
	}
	
	public void toPrint() 
	{
		System.out.println(toString());
	}
	
	
}
