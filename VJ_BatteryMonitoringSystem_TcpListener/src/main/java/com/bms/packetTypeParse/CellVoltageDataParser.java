package com.bms.packetTypeParse;

import java.util.ArrayList;
import java.util.List;

import com.bms.pojo.CellVoltageDTO;
import com.bms.utilities.Commonutility;


public class CellVoltageDataParser {
	
	public List<CellVoltageDTO> parse(String strRawData)
	{
		
		
		List<CellVoltageDTO> lstCellVoltageDTO=new ArrayList<CellVoltageDTO>();
		
		int cellCount=strRawData.length()/4;
		
		int bytecount=0;
		for(int i=0;i<cellCount;i++)
		{
			CellVoltageDTO _cellVoltageDTO=new CellVoltageDTO();
			_cellVoltageDTO.setCellNumber(i+1);
			_cellVoltageDTO.setCellVoltage(Commonutility.hex2decimal(Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=2),strRawData))/1000f);
		
			System.out.println(_cellVoltageDTO.toString());
			lstCellVoltageDTO.add(_cellVoltageDTO);
		}
		return lstCellVoltageDTO;
	}

	
	
	
}
