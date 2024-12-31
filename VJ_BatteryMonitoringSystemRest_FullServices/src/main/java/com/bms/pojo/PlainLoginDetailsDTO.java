package com.bms.pojo;

import java.util.Date;

import javax.persistence.Column;
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
@NoArgsConstructor
@AllArgsConstructor
public class PlainLoginDetailsDTO {

	private Long loginUsersId;

	private String role;

	
	private Long loginCredentialsId;
	
	private String userName;

	private String password;
	
	private Boolean dashBoard;

	private Boolean reportsHistorical;
	
}
