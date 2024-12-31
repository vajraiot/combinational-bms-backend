package com.bms.entity;

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
@Entity
@Table(name = "manufacturer_details", uniqueConstraints = {
        @UniqueConstraint(columnNames = "serial_number_list_id"),//it is added for uniq key
})
public class ManufacturerDetails {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturer_details_id")
	private Long id;
	
	@Column(name="first_used_date",  nullable=true)
	private String firstUsedDate;

	@Column(name="battery_serial_number",  nullable=true)
	private String batterySerialNumber;
	
	@Column(name="battery_bank_type",  nullable=true)
	private String batteryBankType;
	
	@Column(name="ah_capacity",  nullable=true)
	private String ahCapacity;
	
	@Column(name="manifacture_name",  nullable=true)
	private String manifactureName;
	
	@Column(name="design_voltage",  nullable=true)
	private String designVoltage;
	
	@Column(name="individual_cell_voltage",  nullable=true)
	private String individualCellVoltage;
	
	@JsonIgnore()
	@OneToOne()
	@JoinColumn(name="serial_number_list_id")
	SerialNumberList serialNumberList;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;

	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }	
}
