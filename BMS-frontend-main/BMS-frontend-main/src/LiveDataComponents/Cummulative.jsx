import React from 'react'
import { Box, Typography,useTheme } from "@mui/material";
import { tokens } from "../theme.js"

export default function Cummulative({chargeDischargeCycles, ampereHourIn, ampereHourOut, chargingEnergy, dischargingEnergy, time}) {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    // const batteryData = {
    //     chargeDischargeCycles={}: "116",
    //     ampereHourIn={}: "2143.048 Ah",
    //     ampereHourOut={}: "29.1896 Ah",
    //     chargingEnergy={}: "70.3152 kWh",
    //     dischargingEnergy={}: "0.8854 kWh",
    //     batteryRunHours={}: "13:49:30",

    //   };
    const batteryRunHours = (totalSeconds = 0) => {
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
            <Typography variant="h6" mb="10px">
        <strong>Cummulative Data</strong>
      </Typography>
      <Typography variant="h5">
        Charge-Discharge Cycles: <span style={{color: colors.greenAccent[500]}}>{chargeDischargeCycles}</span><strong></strong>
      </Typography>
      <Typography variant="h5">
        Ampere Hour In: <span style={{color: colors.greenAccent[500]}}>{ampereHourIn}</span><strong></strong>
      </Typography>
      <Typography variant="h5">
        Ampere Hour Out: <span style={{color: colors.greenAccent[500]}}>{ampereHourOut}</span><strong></strong>
      </Typography>
      <Typography variant="h5">
        Charging Energy: <span style={{color: colors.greenAccent[500]}}>{chargingEnergy}</span><strong></strong>
      </Typography>
      <Typography variant="h5">
        Discharging Energy: <span style={{color: colors.greenAccent[500]}}>{dischargingEnergy}</span><strong></strong>
      </Typography>
      <Typography variant="h5">
        Battery Run Hours: <span style={{color: colors.greenAccent[500]}}>{batteryRunHours(time)}</span><strong></strong>
      </Typography>
          </Box>
        </Box>
      );
}
