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
@Table(name = "site_location", uniqueConstraints = {
        @UniqueConstraint(columnNames = "siteid_list_id"),//it is added for uniq key
})
public class SiteLocation {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "site_location_id")
	private Long id;
	

	@Column(name="location_name" , nullable=true)
	private String locationName;
	
	@Column(name="latitude" , nullable=true)
	private float latitude;
	
	
	@Column(name="longitude" , nullable=true)
	private float longitude;
	
	
	@Column(name="vendor_name" , nullable=true)
	private String vendorName;
	
	
	@Column(name="battery_ah_capacity" , nullable=true)
	private String batteryAHCapacity;
	
	
	@JsonIgnore()
	@OneToOne()
	@JoinColumn(name="siteid_list_id")
	SiteIdList siteIdList;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	

	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }	
}
