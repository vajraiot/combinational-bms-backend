package com.bms.miscellaneous;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StartDateAndEndDate {

	Date startdate;
	Date endDate;
	public StartDateAndEndDate(Date startdate, Date endDate) {
		super();
		this.startdate = startdate;
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "StartDateAndEndDate [startdate=" +new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format( startdate )+ ", endDate=" + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(endDate) + "]";
	}
	
	public void toPrint()
	{
		System.out.println( this.toString());
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
