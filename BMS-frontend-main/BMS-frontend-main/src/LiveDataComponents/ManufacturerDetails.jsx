import React from 'react'
import { Box, Typography,useTheme } from "@mui/material";
import { tokens } from "../theme.js"

function ManufacturerDetails({siteId,
  serialNumber,
  firstUsedDate,
  batterySerialNumber, batteryBankType, ahCapacity, manifactureName,individualCellVoltage, designVoltage}) {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
    // const rawData = {
    //     voltage: "33 V",
    //     current: "0.4 A",
    //     soc: "100 %",
    //     dod: "0 %",
    //     ambientTemperature: "28.04°C",
    //     'set',
    //     'siteId',
    //     'serialNumber',
    //     'firstUsedDate',
    //     'batterySerialNumber',
    //     'batteryBankType',
    //     'ahCapacity',
    //     'manifactureName',
    //     'individualCellVoltage',
    //     'designVoltage',
    //   };
    
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
            <strong>Manufacturer Details</strong>
          </Typography>
          <Typography variant="h5">
          First Used Date: <span style={{color: colors.greenAccent[500]}}>{firstUsedDate}</span>
          </Typography>
          <Typography variant="h5">
          Battery Serial Number: <span style={{color: colors.greenAccent[500]}}>{batterySerialNumber}</span>
          </Typography>
          <Typography variant="h5">
          Type of Battery Bank: <span style={{color: colors.greenAccent[500]}}>{batteryBankType}</span>
          </Typography>
          <Typography variant="h5">
          Ah Capacity: <span style={{color: colors.greenAccent[500]}}>{ahCapacity}</span>
          </Typography>
          <Typography variant="h5">
          Manifacture Name: <span style={{color: colors.greenAccent[500]}}>{manifactureName}</span>
          </Typography>
          <Typography variant="h5">
          Design Voltage: <span style={{color: colors.greenAccent[500]}}>{designVoltage}</span>
          </Typography>
          <Typography variant="h5">
          Individual Cell Voltage: <span style={{color: colors.greenAccent[500]}}>{individualCellVoltage}</span>
          </Typography>
        </Box>

        </Box>
      );
}

export default ManufacturerDetails