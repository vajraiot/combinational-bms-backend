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
@Table(name="login_users")
public class LoginRoles {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_users_id")
	private Long id;
	
	
	
	@Column(name="role",nullable=false,unique=true)
	private String role;
	
	@OneToMany(fetch=FetchType.LAZY, targetEntity=LoginCredentials.class,cascade= CascadeType.ALL)
	@JoinColumn(name = "login_users_id", referencedColumnName="login_users_id")
	private List<LoginCredentials> lstLoginCredentials;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }	
}
