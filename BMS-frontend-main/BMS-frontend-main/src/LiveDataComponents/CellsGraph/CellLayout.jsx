import { Paper,Typography,useTheme } from '@mui/material'
import React from 'react'
import {CellLegends} from '../../enums/ThresholdValues';
import { tokens } from '../../theme';
import thunder from '../../assets/images/png/VoltageWhite.png';
const CellLayout = ({ cellData, thresholds, chargingStatus }) => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    // 
    
    const determineLegend = () => {
      if (cellData.cellVoltage===-1  || cellData.cellTemperature === -1) {
        return CellLegends.CommnFail;
      } else if (
        cellData.cellVoltage <= thresholds.LowVoltage &&
        cellData.cellVoltage > thresholds.BatteryAboutToDie
      ) {
        return CellLegends.BatteryLowVoltage;
      } else if (
        cellData.cellVoltage <= thresholds.BatteryAboutToDie &&
        cellData.cellVoltage > thresholds.OpenBattery
      ) {
        return CellLegends.BatteryAboutToDie;
      } else if (cellData.cellVoltage <= thresholds.OpenBattery) {
        return CellLegends.OpenBattery;
      } else if (cellData.cellVoltage >= thresholds.HighVoltage) {
        return CellLegends.BatteryHighVoltage;
      } else if (cellData.cellTemperature >= thresholds.HighTemperature) {
        return CellLegends.BatteryHighTemperature;
      } else {
        return chargingStatus
          ? CellLegends. LegendCharging
          : CellLegends.LegendDisCharging;
      }
    };
  
    const legend = determineLegend();
  
    return (
        <Paper
        elevation={8}
      style={{ 
        // padding: '16px', 
        // margin: '16px', 
        border: '0.5px #c39b9b solid',
        // width: '61px',
        textAlign: 'center',  
        cursor: 'pointer'
      }}
    >
         <img 
        src={`${legend}`} 
        alt="Cell Legend" 
        style={{ width: '50px',height: '20px' }} 
      />
      <Typography variant="h5" gutterBottom>
        Cell  {cellData.cellNumber}
            
      </Typography>
      <Typography variant="h5">
     
           <img src={thunder} alt="img" style={{width: '12px', height: '12px'}}/> <span style={{ color: colors.greenAccent[500] }}>
                {cellData.cellVoltage}V
            </span>
      </Typography>
      <Typography variant="h5">
        
            <span style={{ color: colors.greenAccent[500] }}>
                {cellData.cellTemperature}°C
            </span>
      </Typography>
     
    </Paper>
    );
  };

export default CellLayout