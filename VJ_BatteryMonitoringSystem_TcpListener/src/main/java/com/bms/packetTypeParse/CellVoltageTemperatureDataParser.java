package com.bms.packetTypeParse;

import java.util.ArrayList;
import java.util.List;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.pojo.CellTemperatureDTO;
import com.bms.pojo.CellVoltageDTO;


public class CellVoltageTemperatureDataParser {
	
	public List<CellVoltageTemperatureData> parse(String strCellVoltages,String strCellTemp)
	{
		List<CellVoltageTemperatureData> lstRtnCellVoltageTemperatureData=new ArrayList<CellVoltageTemperatureData>();
		
		
		List<CellVoltageDTO> lstCellVoltageDTO=new CellVoltageDataParser().parse(strCellVoltages);
		List<CellTemperatureDTO> lstCellTemperatureDTO=new CellTemperatureDataParser().parse(strCellTemp);
		
		
		for(int i=0;i<lstCellVoltageDTO.size();i++)
		{
			CellVoltageTemperatureData instCellVoltageTemperatureData=new CellVoltageTemperatureData();
			instCellVoltageTemperatureData.setCellNumber(lstCellVoltageDTO.get(i).getCellNumber());
			
			instCellVoltageTemperatureData.setCellVoltage(lstCellVoltageDTO.get(i).getCellVoltage());
			instCellVoltageTemperatureData.setCellTemperature(lstCellTemperatureDTO.get(i).getCellTemperature());
		
			lstRtnCellVoltageTemperatureData.add(instCellVoltageTemperatureData);
		}
		return lstRtnCellVoltageTemperatureData;
	}

	
	
	
}
