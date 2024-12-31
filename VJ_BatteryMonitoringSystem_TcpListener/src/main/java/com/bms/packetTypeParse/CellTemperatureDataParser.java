package com.bms.packetTypeParse;

import java.util.ArrayList;
import java.util.List;

import com.bms.pojo.CellTemperatureDTO;
import com.bms.utilities.Commonutility;


public class CellTemperatureDataParser {

	
	public List<CellTemperatureDTO> parse(String strRawData)
	{

		
		List<CellTemperatureDTO> lstCellTemperatureDTO=new ArrayList<CellTemperatureDTO>();
		
		int cellCount=strRawData.length()/4;
		
		int bytecount=0;
		for(int i=0;i<cellCount;i++)
		{		CellTemperatureDTO _cellTemperatureDTO=new CellTemperatureDTO();
			_cellTemperatureDTO.setCellNumber(i+1);
			_cellTemperatureDTO.setCellTemperature(Commonutility.hex2decimal(Commonutility.getSubstringStartAndEndIndex(bytecount,(bytecount+=2),strRawData))); //removed divede 100 value.
		
			System.out.println(_cellTemperatureDTO.toString());
			lstCellTemperatureDTO.add(_cellTemperatureDTO);
		}
		return lstCellTemperatureDTO;
	}
	
	
}
