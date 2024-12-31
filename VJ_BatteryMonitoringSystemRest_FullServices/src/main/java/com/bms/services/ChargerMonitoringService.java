package com.bms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.ChargerStatusData;
import com.bms.entity.GeneralData;
import com.bms.pojo.ChargerDTO;
import com.bms.pojo.ChargerMonitoringDTO;

import com.bms.repositypck.GeneralDataRepos;
@Component
public interface ChargerMonitoringService {
	List<ChargerMonitoringDTO> fetchChargerMonitoringDetailsBySiteId(String siteId);
	

	@Service
	public class ChargerMonitoringServiceClass implements ChargerMonitoringService {

	    @Autowired
	    private GeneralDataRepos generalDataRepos;

	    @Transactional
	    @Override
	    public List<ChargerMonitoringDTO> fetchChargerMonitoringDetailsBySiteId(String siteId) {
	        // Fetch GeneralData by siteId
	        List<GeneralData> generalDataList = generalDataRepos.findTop1BySiteIdOrderByServerTimeDesc(siteId);

	        List<ChargerMonitoringDTO> chargerMonitoringDTOList = new ArrayList<>();

	        for (GeneralData generalData : generalDataList) {
	            if (generalData.getChargerMonitoringData() != null && !generalData.getChargerMonitoringData().isEmpty()) {
	                for (ChargerMonitoringData chargerMonitoringData : generalData.getChargerMonitoringData()) {
	                    ChargerMonitoringDTO chargerMonitoringDTO = new ChargerMonitoringDTO();

	                    // Map ChargerMonitoringData fields
	                    chargerMonitoringDTO.setId(chargerMonitoringData.getId());
	                    chargerMonitoringDTO.setDeviceId(chargerMonitoringData.getDeviceId());
	                    chargerMonitoringDTO.setAcVoltage(chargerMonitoringData.getAcVoltage());
	                    chargerMonitoringDTO.setAcCurrent(chargerMonitoringData.getAcCurrent());
	                    chargerMonitoringDTO.setFrequency(chargerMonitoringData.getFrequency());
	                    chargerMonitoringDTO.setEnergy(chargerMonitoringData.getEnergy());

	                    // Map ChargerStatusData
	                    ChargerStatusData chargerStatusData = chargerMonitoringData.getChargerStatusData();
	                    if (chargerStatusData != null) {
	                        ChargerDTO chargerDTO = new ChargerDTO();
	                        chargerDTO.setInputMains(chargerStatusData.isInputMains());
	                        chargerDTO.setInputFuse(chargerStatusData.isInputFuse());
	                        chargerDTO.setRectifierFuse(chargerStatusData.isRectifierFuse());
	                        chargerDTO.setFilterFuse(chargerStatusData.isFilterFuse());
	                        chargerDTO.setDcVoltageOLN(chargerStatusData.getDcVoltageOLN());
	                        chargerDTO.setOutputFuse(chargerStatusData.isOutputFuse());
	                        chargerDTO.setAcVoltageULN(chargerStatusData.getAcVoltageULN());
	                        chargerDTO.setChargerLoad(chargerStatusData.isChargerLoad());
	                        chargerDTO.setAlarmSupplyFuse(chargerStatusData.isAlarmSupplyFuse());
	                        chargerDTO.setChargerTrip(chargerStatusData.isChargerTrip());
	                        chargerDTO.setOutputMccb(chargerStatusData.isOutputMccb());
//	                        chargerDTO.setAcVoltageC(chargerStatusData.isAcVoltageC());
	                        chargerDTO.setBatteryCondition(chargerStatusData.isBatteryCondition());
	                        chargerDTO.setTestPushButton(chargerStatusData.isTestPushButton());
	                        chargerDTO.setResetPushButton(chargerStatusData.isResetPushButton());

	                        chargerMonitoringDTO.setChargerDTO(chargerDTO);
	                    }

	                    chargerMonitoringDTOList.add(chargerMonitoringDTO);
	                }
	            }
	        }
	        return chargerMonitoringDTOList;
	    }
	}
}
