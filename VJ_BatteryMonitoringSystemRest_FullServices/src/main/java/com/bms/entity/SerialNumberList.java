package com.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@Table(name = "serial_number_list")
public class SerialNumberList {

	private static final long serialVersionUID = -1259518737699450692L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number_list_id")
	private Long id;

	@Column(name = "serial_number", nullable = true)
	private String serialNumber;

	@JsonIgnore()
	@ManyToOne()
	@JoinColumn(name = "siteid_list_id")
	SiteIdList siteIdList;

	@OneToOne(mappedBy = "serialNumberList")
	ManufacturerDetails ManufacturerDetails = new ManufacturerDetails();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;

	@PrePersist
	protected void onInsert() {
		serverTime = new Date();
	}

	// sample
	// packe:ACTVR,101010,OTSI,FV1.03,353635080699933,12,000000.0000,N,0000000.0000,E,0,000000000000,000.00,00.0,31,404,49,4F2F,1,0,0.100,019034,02
}
