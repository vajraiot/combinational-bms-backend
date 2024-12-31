package com.bms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

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
@Table(name = "siteid_list", uniqueConstraints = {
        @UniqueConstraint(columnNames = "site_id"),//it is added for uniq key
})
public class SiteIdList {

	private static final long serialVersionUID = -1259518737699450692L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "siteid_list_id")
	private Long id;
	
	@Column(name="site_id",  nullable=true)
	private String siteId;
	
    @OneToMany(mappedBy="siteIdList")
   List<SerialNumberList> lstSerialNumberList=new ArrayList<SerialNumberList>();
    
	
   
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="server_time", nullable = true, insertable = true, updatable = false)
	private Date serverTime;
	
	
	@PrePersist
	  protected void onInsert() {
		serverTime = new Date();
	  }


	@Override
	public String toString() {
		return "SiteIdList [id=" + id + ", siteId=" + siteId + ", serverTime=" + serverTime + "]";
	}

	public void toPrint()
	{
		System.out.println(this.toString()); 
	}
	//sample packe:ACTVR,101010,OTSI,FV1.03,353635080699933,12,000000.0000,N,0000000.0000,E,0,000000000000,000.00,00.0,31,404,49,4F2F,1,0,0.100,019034,02
}
