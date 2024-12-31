package com.bms;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.bms.utilities.Commonutility;


//@ComponentScan("com.vajra.services")
//@EntityScan(basePackages = "com.vajra.services")
@SpringBootApplication
public class VJBatteryMonitoringSystem {

	public static void main(String[] args) {
		SpringApplication.run(VJBatteryMonitoringSystem.class, args);
		//testing();
	}
	
	public static void testing()
	{
		try {
		String sDate1="2020-11-12 20:14:12";   //yyyy-MM-dd HH:mm:ss
	    Date dt1=Commonutility.getgDateFormate().parse(sDate1);
	    
	    String sDate2="2020-11-14 20:14:12";   //yyyy-MM-dd HH:mm:ss
	    Date dt2=Commonutility.getgDateFormate().parse(sDate2);
	    
	    List<Date> lstDate= Commonutility.getDaysBetweenDates(dt1,dt2);
	    for(Date inst:lstDate)
	    {
	    	System.out.println(Commonutility.getgDateFormate().format(inst));
	    }
	    //Date fistDate=Commonutility.setStartTimeInDate(dt);
	    //Date LastDate=Commonutility.setEndTimeInDate(dt);
	    
	    
	    //Commonutility.getStartAndEndDateTimeInDate(LastDate).toPrint();
	    
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	

	

}
