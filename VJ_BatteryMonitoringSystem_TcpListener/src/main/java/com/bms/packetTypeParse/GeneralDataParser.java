package com.bms.packetTypeParse;

import java.util.Date;

import com.bms.entity.GeneralData;
import com.bms.utilities.Commonutility;


public class GeneralDataParser {
	
	public GeneralData parse(String strRawData)
	{
		GeneralData rtnGeneralData=null;
		
		String strDate=Commonutility.hex2ASCII(Commonutility.getSubstringbytes(24,10,strRawData));
		String strTime=Commonutility.hex2ASCII(Commonutility.getSubstringbytes(15,8,strRawData));
		Date dateTime=Commonutility.convertBMSDateTimeToDate(strDate, strTime);
		
		if(dateTime==null)
		{
			System.out.println("Error!DateTime Error "+dateTime);
		return null;
		}
		
		int byteNumber=0;
		rtnGeneralData=GeneralData.builder()
		.startPacket(Commonutility.hex2ASCII(Commonutility.getSubstringbytes(0,1,strRawData)))
		.protocalVersion(Commonutility.hex2ASCII(Commonutility.getSubstringbytes(1,1,strRawData)))
		.dataIdentifier(Commonutility.hex2ASCII(Commonutility.getSubstringbytes(2,1,strRawData)))
		
		.siteId(Commonutility.hex2ASCII(Commonutility.getSubstringbytes(4,10,strRawData)))
		.time(strTime)
		.date(strDate)
		.packetDateTime(dateTime)
		.build();
	
		return rtnGeneralData;
				
	}

	
	
	
}
