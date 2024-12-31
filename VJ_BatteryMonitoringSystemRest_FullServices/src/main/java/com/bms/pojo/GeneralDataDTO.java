package com.bms.pojo;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor

//@Table(name="general_data")
public class GeneralDataDTO {

	private static final long serialVersionUID = -1259518737699450692L;

	private Long id;

	private String startPacket;

	private String protocalVersion;

	private String dataIdentifier;

	private String siteId;

	private String time;

	private String date;

	private Date packetDateTime;

   private List<DeviceDataDTO> deviceDataDTO;

	private Date serverTime;

}
