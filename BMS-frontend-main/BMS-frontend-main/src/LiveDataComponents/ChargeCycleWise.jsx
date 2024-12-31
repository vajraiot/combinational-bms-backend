import React from 'react'
import { Box, Typography,useTheme } from "@mui/material";
import { tokens } from "../theme.js"

function ChargeCycleWise({PeakChargeCurrent, AverageChargeCurrent, AmpereHourIn,totalSeconds}) {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    // const rawData = {
    //     PeakChargeCurrent	: "41 A",
    //     AverageChargeCurrent	:"0.4647 A",
    //     AmpereHourIn	:"121.5937 Ah",
    //     ChargeTime	:"261:36:50"
    //   };
    const ChargeTime = (totalSeconds = 0) => {
      try {
        const hours = Math.floor(totalSeconds / 3600);
        const minutes = Math.floor((totalSeconds % 3600) / 60);
        const seconds = totalSeconds % 60;
  
        // Format hours, minutes, and seconds with leading zeros
        const hr = hours < 10 ? "0" + hours : hours;
        const mn = minutes < 10 ? "0" + minutes : minutes;
        const sc = seconds < 10 ? "0" + seconds : seconds;
  
        return `${hr}:${mn}:${sc}`;
      } catch (error) {
        return "--";
      }
    };
    
      return (
        <Box 
        >
          <Box  
            display="flex-column" 
            justifyContent="center" 
            alignItems="center" 
            mt="10px"
            ml="8px"
           >
            <Typography variant="h6" mb="10px" >
              <strong>Charge-Cycle-Wise</strong>
            </Typography>
            <Typography variant="h5" >
            Peak Charge Current:  <span style={{color: colors.greenAccent[500]}}>{PeakChargeCurrent}</span><strong></strong> 
            </Typography>
            <Typography variant="h5" > 
            Avg Charge Current  <span style={{color: colors.greenAccent[500]}}>{AverageChargeCurrent}</span><strong></strong> 
            </Typography>
            <Typography variant="h5" >
            Ampere HourIn:  <span style={{color: colors.greenAccent[500]}}>{AmpereHourIn}</span><strong></strong> 
            </Typography>
            <Typography variant="h5" >
            Charge Time:  <span style={{color: colors.greenAccent[500]}}>{ChargeTime(totalSeconds)}</span><strong></strong> 
            </Typography>
          </Box>
        </Box>
      );
}

export default ChargeCycleWise