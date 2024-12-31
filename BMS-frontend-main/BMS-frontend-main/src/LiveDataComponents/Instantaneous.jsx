import React from 'react'
import { Box, Typography,useTheme } from "@mui/material";
import { tokens } from "../theme.js"
// import BarChart from '../charts/BarChart.js';
// import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, LabelList } from "recharts";

export const Instantaneous = ({voltage,current,soc,dod,ambientTemperature}) => {
    const theme =useTheme();
    const colors=tokens(theme.palette.mode);
    // const rawData = {
    //     voltage: "33 V",
    //     current: "0.4 A",
    //     soc: "100 %",
    //     dod: "0 %",
    //     ambientTemperature: "28.04°C",
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
            <Typography variant="h6" mb="10px" >
              <strong>Instantaneous Data</strong>
            </Typography>
            <Typography variant="h5" >
             Voltage: <span style={{color: colors.greenAccent[500]}}>{voltage}</span> 
            </Typography>
            <Typography variant="h5" > 
            Current: <span style={{color: colors.greenAccent[500]}}>{current}</span><strong></strong> 
            </Typography>
            <Typography variant="h5" >
            State Of Charge (SOC): <span style={{color: colors.greenAccent[500]}}>{soc}</span><strong></strong> 
            </Typography>
            <Typography variant="h5" >
            Depth of Discharge (DOD):<span style={{color: colors.greenAccent[500]}}>{dod}</span><strong></strong> 
            </Typography>
            <Typography variant="h5" >
             Ambient Temperature:<span style={{color: colors.greenAccent[500]}}>{ambientTemperature}</span> <strong></strong> 
            </Typography>
          </Box>
        </Box>
      );
}
// export const Instantaneous = ({ voltage, current, soc, dod, ambientTemperature }) => {
//   const theme = useTheme();
//   const colors = tokens(theme.palette.mode);

//   const rawData = {
//     voltage: parseFloat(voltage),
//     current: parseFloat(current),
//     soc: parseFloat(soc),
//     dod: parseFloat(dod),
//     ambientTemperature: parseFloat(ambientTemperature),
//   };

//   const chartData = [
//     { name: "Voltage", value: rawData.voltage },
//     { name: "Current", value: rawData.current },
//     { name: "SOC", value: rawData.soc },
//     { name: "DOD", value: rawData.dod },
//     { name: "Ambient Temperature", value: rawData.ambientTemperature },
//   ];

//   return (
//     <Box>
//       <Typography variant="h6" mb="10px" align="center">
//         <strong>Instantaneous Data</strong>
//       </Typography>

//       <ResponsiveContainer width="100%" height={300}>
//         <BarChart data={chartData}>
//           <CartesianGrid strokeDasharray="3 3" />
//           <XAxis dataKey="name" />
//           <YAxis />
//           <Tooltip />
//           <Legend />
//           <Bar dataKey="value" fill={colors.greenAccent[500]}>
//             <LabelList dataKey="value" position="top" />
//           </Bar>
//         </BarChart>
//       </ResponsiveContainer>

//       <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center" mt="10px" ml="8px">
//         <Typography variant="h5" mt="10px">
//           Voltage: <span style={{ color: colors.greenAccent[500] }}>{voltage}</span>
//         </Typography>
//         <Typography variant="h5">
//           Current: <span style={{ color: colors.greenAccent[500] }}>{current}</span>
//         </Typography>
//         <Typography variant="h5">
//           State Of Charge (SOC): <span style={{ color: colors.greenAccent[500] }}>{soc}</span>
//         </Typography>
//         <Typography variant="h5">
//           Depth of Discharge (DOD): <span style={{ color: colors.greenAccent[500] }}>{dod}</span>
//         </Typography>
//         <Typography variant="h5">
//           Ambient Temperature: <span style={{ color: colors.greenAccent[500] }}>{ambientTemperature}</span>
//         </Typography>
//       </Box>
//     </Box>
//   );
// };

// export const Instantaneous = ({ voltage, current, soc, dod, ambientTemperature }) => {
//   const instantaneousData = {
//     Voltage: {voltage},
//     Current: {current},
//     SOC: {soc},
//     DOD: {dod},
//     "Ambient Temperature": {ambientTemperature},
//   };

//   return (
//     <div>
//       <h2>Instantaneous Data</h2>
//       <BarChart data={instantaneousData} />
//     </div>
//   );
// };

