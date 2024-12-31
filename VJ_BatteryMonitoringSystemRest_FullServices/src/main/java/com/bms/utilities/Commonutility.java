package com.bms.utilities;

import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.bms.miscellaneous.StartDateAndEndDate;

public class Commonutility {

	public static SimpleDateFormat gDateFormate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH means 24 and hh means 12
	
	public static SimpleDateFormat getgDateFormate() {
		return gDateFormate;
	}
	
	public static float byte2float(byte[] data) {
		
		//return new BigInteger(data).floatValue();
		float f=new BigInteger(data).floatValue();
	 return Float.valueOf(String.format(Locale.getDefault(), "%.4f", f));
	}
	
	public static float hex2ieee754tofloat(String data) {
		Long i = Long.parseLong(data, 16);
        Float f = Float.intBitsToFloat(i.intValue());
        
      //  return f;
        return Float.valueOf(String.format(Locale.getDefault(), "%.4f", f));
	}
	
	
	 public static int hex2decimal(String s) 
		{
			s=  s.replaceAll(" ", "");
		    String digits = "0123456789ABCDEF";
		    s = s.toUpperCase();
		    int val = 0;
		    for (int i = 0; i < s.length(); i++) {
		        char c = s.charAt(i);
		        int d = digits.indexOf(c);
		        val = 16*val + d;
		    }
		    return val;
		}
	 
	 public static float hex2float(String s) 
		{
			s=  s.replaceAll(" ", "");
		    String digits = "0123456789ABCDEF";
		    s = s.toUpperCase();
		    float val = 0.0f;
		    for (int i = 0; i < s.length(); i++) {
		        char c = s.charAt(i);
		        float d = digits.indexOf(c);
		        val = 16*val + d;
		    }
		   // return val;
		    
		    return Float.valueOf(String.format(Locale.getDefault(), "%.4f", val));
		}
	
	 public static String hex2ASCII(String hexValue)
		{
		      StringBuilder output = new StringBuilder("");
		      
		      for (int i = 0; i < hexValue.length(); i += 2)
		      {
		    	  
		         String str = hexValue.substring(i, i + 2);
		         
		         output.append((char) Integer.parseInt(str, 16));
		      }
		      return output.toString();
		       
		}
	 
	 
	 public static byte[] hex2Bytes(String str, boolean includeSpace)
	    {
	        if (includeSpace && !str.isEmpty() && str.charAt(str.length() -1) != ' ')
	        {
	            str += " ";
	        }
	        int cnt = includeSpace ? 3 : 2;
	        if (str.length() == 0 || str.length() % cnt != 0)
	        {
	            throw new RuntimeException("Not hex string");
	        }
	        byte[] buffer = new byte[str.length() / cnt];
	        char c;
	        for (int bx = 0, sx = 0; bx < buffer.length; ++bx, ++sx)
	        {
	            c = str.charAt(sx);
	            buffer[bx] = (byte)((c > '9' ? (c > 'Z' ? (c - 'a' + 10) : (c - 'A' + 10)) : (c - '0')) << 4);
	            c = str.charAt(++sx);
	            buffer[bx] |= (byte)(c > '9' ? (c > 'Z' ? (c - 'a' + 10) : (c - 'A' + 10)) : (c - '0'));
	            if (includeSpace)
	            {
	                ++sx;
	            }
	        }
	        return buffer;
	    }
	 
	 
	 static   String hex2Binary(String hex) {
	        int i = Integer.parseInt(hex, 16);
	        String bin = Integer.toBinaryString(i);
	        return bin;
	    }
	    
	    public static   Boolean bitposition(String HexInput,int pos) //0 based.
	    {
	    	
	    	
	    	String input=hex2Binary(HexInput);
	    	
	    	input=StringUtils.leftPad(input, 32, "0");
	    	 StringBuilder sb=new StringBuilder(input);
	    	   sb.reverse();  
	    	   input= sb.toString(); 
	    	String rtnstr= input.substring(pos, pos+1);
	    	Boolean rtnBoolean=Boolean.parseBoolean(rtnstr);
	    	if(Integer.parseInt(rtnstr)==1)
	    	{
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    
	    }
	    
	    public static Date getStartDateOfMonth(String year, String month) {
	        try {
	            int yearInt = Integer.parseInt(year);
	            int monthInt = Integer.parseInt(month);
	            
	            // Set the first day of the month (1st day) and the year
	            Calendar calendar = Calendar.getInstance();
	            calendar.set(Calendar.YEAR, yearInt);
	            calendar.set(Calendar.MONTH, monthInt - 1); // Calendar months are 0-based
	            calendar.set(Calendar.DAY_OF_MONTH, 1); // First day of the month
	            
	            return calendar.getTime();
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid year or month format", e);
	        }
	    }

	    public static Date getEndDateOfMonth(String year, String month) {
	        try {
	            int yearInt = Integer.parseInt(year);
	            int monthInt = Integer.parseInt(month);
	            
	            // Set the last day of the month
	            Calendar calendar = Calendar.getInstance();
	            calendar.set(Calendar.YEAR, yearInt);
	            calendar.set(Calendar.MONTH, monthInt - 1); // Calendar months are 0-based
	            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Last day of the month
	            
	            return calendar.getTime();
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid year or month format", e);
	        }
	    }

	    // Method to format a Date to a String (e.g., "2024-08-01")
	    public static String formatDateToString(Date date) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        return sdf.format(date);
	    }

	    
	    public  static Boolean  isStringContainsAllZeros(String stext) {
	    	 char[] ch = new char[stext.length()];
	    	 
	    	 int cnt=0;
	    	  for (int i = 0; i < stext.length(); i++) { 
	              if(stext.charAt(i)=='0')
	              {
	            	cnt++;
	              }
	          }
	    	  
	    	 if(cnt==stext.length())
	    	 {
	    		 return true;
	    	 }
	    	 else
	    	 {
	    		 return false;
	    	 }
	    	 
		}
	    
	    public static Boolean isStringContainsSpaces(String stext)
	    {
	    
	   	 if (stext.contains(" ")) {
	           return true;
	   	 }
	   	 
	   	 else 
	   	 {
	   		 return false;
	   	 }
	    }
	    
	    public static String getSubstringbytes(int startByte,int numberOfBytes,String strRawData)
	    {
	    	
	    	
	    	int startIndex=startByte*2;
	    	String rtnStrBytes=strRawData.substring(startIndex, startIndex+(numberOfBytes*2));
	    	return rtnStrBytes;
	    	
	    }
	    
	    public static String getSubstringStartAndEndIndex(int startByte,int numberOfBytes,String strRawData)
	    {
	    	
	    	
	    	int startIndex=startByte*2;
	    	String rtnStrBytes=strRawData.substring(startIndex, (numberOfBytes*2));
	    	return rtnStrBytes;
	    	
	    }
	    
	    public  static Date convertBMSDateTimeToDate(String strDate,String strTime)
	    {
	    	Date rtnDate=null;
	    	//her date :17/12/2019 time:16:34:00
	    	  SimpleDateFormat gDateFormate= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    	  String formDateTimeStr=strDate+" "+strTime;
	    	  try {
	    		  rtnDate =gDateFormate.parse(formDateTimeStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				
			}
	    	  
	    	  return rtnDate;
	    	  
	    	  
	    }
	    
	    public static Date convertStringDateToDateFormate(String sDateTime) {
	  		Date rtnDate;
	  		if(sDateTime=="")
	  		{
	  			return null;
	  		}
	  		if (sDateTime == null)
	  			return null;

	  		// String dateformate="dd/MM/yyyy HH:mm:ss";
	  		String dateformate = "yyyy-MM-dd HH:mm:ss"; // ex:2019-10-01 16:53:54.4670000
	  		try {
	  			rtnDate = new SimpleDateFormat(dateformate).parse(sDateTime);
	  		} catch (ParseException e) {
	  			// TODO Auto-generated catch block
	  			// e.printStackTrace();
	  			rtnDate = null;
	  			return rtnDate;
	  		}

	  		return rtnDate;

	  	}
	    
	    public  static Date getSubtracteMinuteFromCurrentTime(int subtractingMinutes)
	    {
	    	Calendar calendar = Calendar.getInstance();
			// calendar.add(Calendar.HOUR_OF_DAY, -1);
			calendar.add(Calendar.MINUTE, -(subtractingMinutes));
			
			Date subtractedDateTime = calendar.getTime();
			return subtractedDateTime;
			
	    }
	    public static boolean CheckIfDateIsLatestOne(Date dt,int marginMinutes)
	    {//ex:if will check if time exist in last in minutes(marginMinutes).
	    	try {
	    	Date marginDate= getSubtracteMinuteFromCurrentTime(marginMinutes);
	    	return dt.after(marginDate);
	    	}
	    	catch(Exception ex)
	    	{
	    		System.out.println("exception occured in CheckIfLatestOne method:"+ex.toString());
	    		return false;
	    	}
	    }
	    
	    public static Boolean comparingTwoDatesOnly(Date date1,Date date2)
		{
			
			
			
			if((date1.getYear()==date2.getYear()) && (date1.getMonth()==date2.getMonth()) && (date1.getDate()==date2.getDate()))
						{
				return true;
			}
			else
			{
				return false;
			}
			
		}
	    public static String formatDateToMonth(Date date) {
	        if (date == null) {
	            return "";
	        }
	        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
	        return formatter.format(date);
	    }
		public static Date getDateOnly(Date _date)
		{  
			Date rtnDate = new Date();
			rtnDate.setDate(_date.getDate());
			rtnDate.setYear(_date.getYear());
			rtnDate.setMonth(_date.getMonth());
			
			rtnDate.setHours(0);
			rtnDate.setMinutes(0);
			rtnDate.setSeconds(0);
			
			return rtnDate;
			
		}
		
		/*input:2020-11-12 20:14:12 (date formate)
		 * output:2020-11-12 20:14:12 2020-11-13 20:14:12 2020-11-14 20:14:12 (in date formate)
		 * */
		public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
		{ List<Date> dates = new ArrayList<Date>();
			try {
			  if (startdate.compareTo(enddate) > 0) { 
				  
		            // When Date d1 > Date d2 
		            System.out.println("Date1 is after Date2"); 
		            return null;
		        } 
			  
		   
		    Calendar calendar = new GregorianCalendar();
		    calendar.setTime(startdate);

		    while (calendar.getTime().before(enddate))
		    {
		        Date result = calendar.getTime();
		        dates.add(result);
		        calendar.add(Calendar.DATE, 1);
		    }
		    dates.add(enddate);
		    return dates;
			}
			catch(Exception ex)
			{
				return  new ArrayList<Date>();
			}
		}
		
		public static List<Date> fistAndLastDatesInMonth(int year,int month)
		{
			List<Date> rtnDate=new ArrayList<Date>();
			
			 Calendar cal1 = Calendar.getInstance();
			 cal1.set(year, (month-1), 1,0,0,0); //month is zero based thats why her +1 added.
			
		      int res = cal1.getActualMaximum(Calendar.DATE);
		      
		      
		      Calendar cal2 = Calendar.getInstance();
			cal2.set(year, (month-1), res,23,59,59); //month is zero based thats why her +1 added.
				 
		      rtnDate.add(cal1.getTime());
		      rtnDate.add(cal2.getTime());
		      System.out.println(rtnDate.get(0));
		      System.out.println(rtnDate.get(1));
		      
			return rtnDate;
		}
		
		public static List<StartDateAndEndDate> WeeklystartAndEnddate(Date startDate,Date endDate)
		{ startDate=getDateOnly(startDate);
		  endDate=getDateOnly(endDate);
			List<StartDateAndEndDate> rtnStartDateAndEndDate=new ArrayList<StartDateAndEndDate>();
			
			List<Date> _lstDates= getDaysBetweenDates(startDate,endDate);
			
			/*for(Date dt:_lstDates)
			{
				System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format( dt ));
			}*/
		
			if(_lstDates.size()<7)
			{
				StartDateAndEndDate _startDateAndEndDate =new StartDateAndEndDate( Commonutility.setStartTimeInDate( _lstDates.get(0)),Commonutility.setEndTimeInDate( _lstDates.get(_lstDates.size()-1)));
				rtnStartDateAndEndDate.add(_startDateAndEndDate);
				return rtnStartDateAndEndDate;
			}
			int nunmberofWeeks=_lstDates.size()/7;
			int remainDaysInWeek=_lstDates.size()%7;
			for(int d=0;d<nunmberofWeeks;d++)
			{
			
				StartDateAndEndDate _startDateAndEndDate =new StartDateAndEndDate(Commonutility.setStartTimeInDate( _lstDates.get(d*7)),Commonutility.setEndTimeInDate( _lstDates.get((d*7)+6)));
				rtnStartDateAndEndDate.add(_startDateAndEndDate);
			
				if(d==(nunmberofWeeks-1)&&(remainDaysInWeek!=0))
				{
					_startDateAndEndDate =new StartDateAndEndDate(Commonutility.setStartTimeInDate( _lstDates.get((d*7)+6+1)),Commonutility.setEndTimeInDate( _lstDates.get(_lstDates.size()-1)));
					rtnStartDateAndEndDate.add(_startDateAndEndDate);
				}
				
				
			
			}
					
			return rtnStartDateAndEndDate;
		}
		
		public static  Boolean isLocationInsideTheLimit(double actualLocation,double baseLocation,double limitLine)
		{
			
			double baseMinLocation = baseLocation - limitLine;
			double baseMaxLocation = baseLocation + limitLine;
			if (actualLocation >= baseMinLocation && actualLocation <= baseMaxLocation) {

				return true;
			} else {

				return false;
			}
		}
		
		public static long differentBetweenTwoDatesInSeconds(Date d1,Date d2)
		{
			long  seconds = (d2.getTime()-d1.getTime())/1000;
			if(seconds<0)
			{
				seconds=((-1)*seconds);
			}
			return seconds;
		}
		
		public static Date setStartTimeInDate(Date dt)
		{
			 Calendar cal1 = Calendar.getInstance();
			Date rtnDate=(Date) dt.clone(); //cloned object inorder to preserve input data
			// cal1.set(dt.getYear(),dt.getMonth(),dt.getDate(),0,0,0);
			rtnDate.setHours(00);
			rtnDate.setSeconds(00);
			rtnDate.setMinutes(00);
			
			 return rtnDate;
		}
		
		public static Date setEndTimeInDate(Date dt)
		{
			 Calendar cal1 = Calendar.getInstance();
			 Date rtnDate=(Date) dt.clone(); //cloned object inorder to preserve input data
			// cal1.set(dt.getYear(),dt.getMonth(),dt.getDate(),0,0,0);
			rtnDate.setHours(23);
			rtnDate.setSeconds(59);
			rtnDate.setMinutes(59);
			
			 return rtnDate;
		}
		
		public static StartAndEndTimeInDate getStartAndEndDateTimeInDate(Date dt)
		{
			//StartAndEndTimeInDate rtnStartAndEndTimeInDate=new StartAndEndTimeInDate();
		Commonutility.StartAndEndTimeInDate rtnStartAndEndTimeInDate= new Commonutility().new StartAndEndTimeInDate();
			
			
			rtnStartAndEndTimeInDate.setStartTimeInDate(setStartTimeInDate(dt));
			rtnStartAndEndTimeInDate.setEndTimeInDate(setEndTimeInDate(dt));
			return rtnStartAndEndTimeInDate;
		}
		public static boolean isDateExistingBetweenDates(Date CheckingDate,Date fistDate,Date LastDate)
		{
		//	Date a, b;   // assume these are set to something
			// d;      // the date in question

			return fistDate.compareTo(CheckingDate) * CheckingDate.compareTo(LastDate) > 0;	
		}
		
		public static Date addOrReduceSeconds(Date now,int scnds)
		{
		return 	DateUtils.addSeconds(now, scnds);
		}
		
		  public static String getMonthName(int monthIndex) {
		        //since this is zero based, 11 = December
		        if (monthIndex < 0 || monthIndex > 11 ) {
		            throw new IllegalArgumentException(monthIndex + " is not a valid month index.");
		        }
		        return new DateFormatSymbols().getMonths()[monthIndex].toString();
		    }
		
		
	public static String secondsTohhmmss(int s)
	{
		//int s=123456;
		int sec = s % 60;
	    int min = (s / 60)%60;
	    int hours = (s/60)/60;
        
	    String strSec=(sec<10)?"0"+Integer.toString(sec):Integer.toString(sec);
	    String strmin=(min<10)?"0"+Integer.toString(min):Integer.toString(min);
	    String strHours=(hours<10)?"0"+Integer.toString(hours):Integer.toString(hours);
	    
	    return (strHours + ":" + strmin + ":" + strSec);
	}
	
	public  static String floatPrecision(float fltValue,int floatLimit)
	{
		//String.format("%.2f", inst.getDissolvedOxygen());
         String strFloatLimit="%."+floatLimit+"f";
         
         return String.format(strFloatLimit, fltValue);

		
	}
	
	public class StartAndEndTimeInDate{ // this class for to store start and end datetime for specific date.  
		private Date startTimeInDate;
		private Date endTimeInDate;
		public Date getStartTimeInDate() {
			return startTimeInDate;
		}
		public void setStartTimeInDate(Date startTimeInDate) {
			this.startTimeInDate = startTimeInDate;
		}
		public Date getEndTimeInDate() {
			return endTimeInDate;
		}
		public void setEndTimeInDate(Date endTimeInDate) {
			this.endTimeInDate = endTimeInDate;
		}
		
		public StartAndEndTimeInDate() {
		
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "StartAndEndTimeInDate [startTimeInDate=" + startTimeInDate + ", endTimeInDate=" + endTimeInDate
					+ "]";
		}
		
		public void toPrint()
		{
			try {
           SimpleDateFormat lclSimpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH means 24 and hh means 12
				System.out.println("startTimeInDate :"+lclSimpleDateFormat.format(startTimeInDate)
				+" endTimeInDate:"+lclSimpleDateFormat.format(endTimeInDate));
			}
			catch(Exception ex)
			{
				System.out.println("NULL");
			}
		}
		
		
		
		
	}
}
