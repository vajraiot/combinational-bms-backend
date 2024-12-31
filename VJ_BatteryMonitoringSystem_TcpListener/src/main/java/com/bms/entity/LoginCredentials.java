package com.bms.entity;

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
@Table(name="login_credentials")
public class LoginCredentials {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_credentials_id")
	private Long id;
	
	
	
	@Column(name="user_name",nullable=false)
	private String userName;
	

	@Column(name="password",nullable=false)
	private String password;
	
	
	 @OneToOne(mappedBy="loginCredentials"  ,fetch=FetchType.LAZY,cascade=CascadeType.ALL )
	 private AccessPermissions accessPermissions;

	 
     @ManyToOne()
     @JoinColumn(name = "login_users_id" )
     private LoginRoles loginRoles;
		
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	

	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }	
}
