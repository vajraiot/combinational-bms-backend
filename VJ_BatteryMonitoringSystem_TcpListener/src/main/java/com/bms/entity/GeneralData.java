package com.bms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="general_data")

public class GeneralData {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "general_data_id")
	private Long id;
	
	@Column(name="start_packet", nullable=true)
	private String startPacket;
	
	@Column(name="protocal_version",  nullable=true)
	private String protocalVersion;
	
	@Column(name="data_identifier",  nullable=true)
	private String dataIdentifier;
	
	@Column(name="site_id",  nullable=true)
	private String siteId;
	
	@Column(name="time",  nullable=true)
	private String time;
	
	@Column(name="date",  nullable=true)
	private String date;
	
	@Column(name="packet_date_time",  nullable=true)
	private Date packetDateTime;
	
	
//	@OneToMany(fetch=FetchType.LAZY, targetEntity=BatteryMonitoringData.class, cascade=CascadeType.ALL)
//	@JoinColumn(name = "general_data_id", referencedColumnName="general_data_id")
//	private List<BatteryMonitoringData> batteryMonitoringData;

	@OneToMany(fetch=FetchType.LAZY, targetEntity=DeviceData.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "general_data_id", referencedColumnName="general_data_id")
	private List<DeviceData> deviceData;
	
	@OneToMany(fetch=FetchType.LAZY, targetEntity=ChargerMonitoringData.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "general_data_id", referencedColumnName="general_data_id")
	private List<ChargerMonitoringData> chargerMonitoringData;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }



	@Override
	public String toString() {
		return "GeneralData [id=" + id + ", startPacket=" + startPacket + ", protocalVersion=" + protocalVersion
				+ ", dataIdentifier=" + dataIdentifier + ", siteId=" + siteId + ", time=" + time + ", date=" + date
				+ ", packetDateTime=" + packetDateTime + ", serverTime=" + serverTime + "]";
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Long getId() {
		return id;
	}



	public String getStartPacket() {
		return startPacket;
	}



	public String getProtocalVersion() {
		return protocalVersion;
	}



	public String getDataIdentifier() {
		return dataIdentifier;
	}



	public String getSiteId() {
		return siteId;
	}



	public String getTime() {
		return time;
	}



	public String getDate() {
		return date;
	}



	public Date getPacketDateTime() {
		return packetDateTime;
	}



	public List<DeviceData> getDeviceData() {
		return deviceData;
	}



	public Date getServerTime() {
		return serverTime;
	}




	public List<ChargerMonitoringData> getChargerMonitoringData() {
		return chargerMonitoringData;
	}


	
	//sample packe:ACTVR,101010,OTSI,FV1.03,353635080699933,12,000000.0000,N,0000000.0000,E,0,000000000000,000.00,00.0,31,404,49,4F2F,1,0,0.100,019034,02
}
