package com.bms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

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
@Entity
@Table(name = "charger_monitoring_data")  // Optional, specify if needed
public class ChargerMonitoringData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or another strategy, if applicable
    @Column(name = "id")
    private Long id;
	
	
	@Column(name="device_id", nullable=true)
	private float deviceId;
	
	@Column(name="ac_voltage", nullable=true)
	private float acVoltage;
	
	@Column(name="ac_current",  nullable=true)
	private float acCurrent;
	
	@Column(name="frequency",  nullable=true)
	private float frequency;
	
	@Column(name="energy",  nullable=true)
	private float energy;
  
	@Column(name="charger_status",  nullable=true)
	private String chargerStatus;
	
    @OneToOne(mappedBy = "chargerMonitoringData")
    private ChargerStatusData chargerStatusData=new ChargerStatusData();
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY,targetEntity=GeneralData.class)
	@JoinColumn(name="general_data_id" , nullable = false)
	private GeneralData generalData;
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }

}
