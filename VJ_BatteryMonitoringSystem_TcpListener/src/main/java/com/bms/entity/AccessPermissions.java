package com.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@NoArgsConstructor
@Entity
@Table(name="acess_permissions")
public class AccessPermissions {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acess_permissions_id")
	private Long id;
	
	


	@Column(name="dashboard",nullable=true ,columnDefinition = "bit default 1")
	private Boolean dashBoard;
	
	
	

	@Column(name="reports_historical",nullable=true ,columnDefinition = "bit default 1")
	private Boolean reportsHistorical;
	
	
	@OneToOne()
	@JoinColumn(name = "login_credentials_id")
	private LoginCredentials loginCredentials;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	

	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }	
}
