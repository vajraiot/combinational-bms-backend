package com.bms;

import java.util.Date;

import com.bms.entity.BMSAlarms;
import com.bms.entity.DeviceData;

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
public class t1 {

	private int id;
	
	private String name;
	 
	private int age;
	
	private int t1_id;

	@Override
	public String toString() {
		return "t1 [id=" + id + ", name=" + name + ", age=" + age 
				//+ ", t1_id=" + t1_id + "]"
				;
	}
	
	
}
