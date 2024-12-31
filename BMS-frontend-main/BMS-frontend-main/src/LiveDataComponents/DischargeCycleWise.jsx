import React, { useEffect } from 'react'
import { Box, Typography,useTheme } from "@mui/material";
import { tokens } from "../theme.js"

export default function DischargeCycleWise({
  peakDischargeCurrent,
  averageDischargingCurrent,
  ahOutForOneDischargeCycle,
  totalSeconds,
}) {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const dischargeTime = (totalSeconds = 0) => {
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
    <Box>
      <Box
        display="flex-column"
        justifyContent="center"
        alignItems="center"
        mt="10px"
        ml="8px"
      >
        <Typography variant="h6" mb="10px">
          <strong>Discharge-Cycle-Wise</strong>
        </Typography>
        <Typography variant="h5">
          Peak Discharge Current:{" "}
          <span style={{ color: colors.greenAccent[500] }}>
            {peakDischargeCurrent}
          </span>{" "}
          <strong></strong>
        </Typography>
        <Typography variant="h5">
          Avg Discharge Current:{" "}
          <span style={{ color: colors.greenAccent[500] }}>
            {averageDischargingCurrent}
          </span>
        </Typography>
        <Typography variant="h5">
          AH Out Discharge:{" "}
          <span style={{ color: colors.greenAccent[500] }}>
            {ahOutForOneDischargeCycle}
          </span>
        </Typography>
        <Typography variant="h5">
          Discharge Time:{" "}
          <span style={{ color: colors.greenAccent[500] }}>
            {dischargeTime(totalSeconds)}
          </span>{" "}
          <strong></strong>
        </Typography>
      </Box>
    </Box>
  );
}

