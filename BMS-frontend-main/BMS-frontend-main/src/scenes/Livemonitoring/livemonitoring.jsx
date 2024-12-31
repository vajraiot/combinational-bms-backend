import { Box, Paper, Typography, Button, Modal,useTheme, Container} from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { tokens } from "../../theme";
// import StatBox from "../../components/StatBox";
import { Instantaneous } from "../../LiveDataComponents/Instantaneous";
import ManufacturerDetails from "../../LiveDataComponents/ManufacturerDetails";
import DischargeCycleWise from "../../LiveDataComponents/DischargeCycleWise";
import Cummulative from "../../LiveDataComponents/Cummulative";
import ChargeCycleWise from "../../LiveDataComponents/ChargeCycleWise";
import MapWithMarker from "../../LiveDataComponents/MapWithMarker";
import Topbar from "../global/Topbar";
import { AppContext } from "../../services/AppContext";
import CellsData from "../../LiveDataComponents/CellsData";
// import Pictorial from "../../LiveDataComponents/CellsGraph/Pictorial";
import LineGraph from "../../LiveDataComponents/CellsGraph/LineGraph";

const Bar = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const {
    Mdata,
    data,
    location
  }=useContext(AppContext)
 
  // if (!Mdata) return <div> <Topbar /></div>;
  // if (data.length === 0) return <div> <Topbar /></div>;

  const device = data[0];
  if (!device) return <div> <Topbar /></div>;
 console.log(device)
  const {
    id,
    firstUsedDate,
    batterySerialNumber,
    batteryBankType,
    ahCapacity,
    manifactureName,
    designVoltage,
    individualCellVoltage,
  } = Mdata;
  const {
    locationName = "Unknown Location", // Default value if locationName is falsy
    latitude = 0,                     // Default latitude (could use a fallback like 0 or a center point)
    longitude = 0,                    // Default longitude
    vendorName = "Unknown Vendor",    // Default vendor name
    batteryAHCapacity = "Not Specified", // Default battery capacity
  } = location || {}; // Handle case where location itself might be undefined
  
 console.log("name "+locationName);
  const {
    deviceId, 
    bmsManufacturerID, 
    serialNumber, 
    installationDate, 
    cellsConnectedCount, 
    problemCells, 
    cellVoltageTemperatureData, 
    stringvoltage, 
    systemPeakCurrentInChargeOneCycle, 
    averageDischargingCurrent, 
    averageChargingCurrent, 
    ahInForOneChargeCycle, 
    ahOutForOneDischargeCycle, 
    cumulativeAHIn, 
    cumulativeAHOut, 
    chargeTimeCycle, 
    dischargeTimeCycle, 
    totalChargingEnergy, 
    totalDischargingEnergy, 
    everyHourAvgTemp, 
    cumulativeTotalAvgTempEveryHour, 
    chargeOrDischargeCycle, 
    socLatestValueForEveryCycle, 
    dodLatestValueForEveryCycle, 
    systemPeakCurrentInDischargeOneCycle, 
    instantaneousCurrent, 
    ambientTemperature, 
    batteryRunHours, 
    serverTime
  } = device;

  return (
<Box
  sx={{
    display: "grid",
    gridTemplateColumns: "repeat(5, 1fr)",
    gridTemplateRows: "minmax(50px, auto) repeat(3, auto)",
    gap: "10px",
  }}
>
  {/* Navigation */}
  <Box
    sx={{
      gridColumn: "1 / 6",
      gridRow: "1",
      backgroundColor: colors.primary[400],
      border: "1px solid black", // Debug style
      // height: "50px", // Temporary height for visibility
    }}
  >
    <Topbar />

  </Box>

  {/* Cell */}
  <Box
    sx={{
      gridColumn: "1 / 4",
      gridRow: "2",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "100px", // Temporary height for visibility
    }}
  >
    {/* <Pictorial cellDataList={cellVoltageTemperatureData}/> */}
    <LineGraph data={cellVoltageTemperatureData}></LineGraph>

  </Box>

  {/* Map */}
  <Box
    sx={{
      gridColumn: "4 / 6",
      gridRow: "2",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "100px",
    }}
  >
    <MapWithMarker 
    locationName={locationName}
    latitude={latitude}
    longitude={longitude}
    vendorName={vendorName}
    batteryAHCapacity={batteryAHCapacity}
    />

  </Box>

  {/* Manufacture */}
  <Box
    
    sx={{
      gridColumn: "1",
      gridRow: "3",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "50px",
    }}
  >
    <ManufacturerDetails
    firstUsedDate={firstUsedDate}
    batterySerialNumber={batterySerialNumber}
    batteryBankType={batteryBankType}
    serialNumber={serialNumber}
    ahCapacity={ahCapacity}
    manifactureName={manifactureName}
    individualCellVoltage={individualCellVoltage}
    designVoltage={designVoltage}
    />
 
  </Box>

  {/* DisCharge */}
  <Box

    sx={{
      gridColumn: "2",
      gridRow: "3",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "50px",
    }}
  >
    <DischargeCycleWise
      peakDischargeCurrent={systemPeakCurrentInDischargeOneCycle}
      averageDischargingCurrent={averageDischargingCurrent}
      ahOutForOneDischargeCycle={ahOutForOneDischargeCycle}
      totalSeconds={dischargeTimeCycle}
     />
   
  </Box>

  {/* charge */}
  <Box
    
    sx={{
      gridColumn: "3",
      gridRow: "3",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "50px",
    }}
  >
    <ChargeCycleWise 
    PeakChargeCurrent={systemPeakCurrentInChargeOneCycle}  
    AverageChargeCurrent={averageChargingCurrent} 
    AmpereHourIn={ahInForOneChargeCycle} 
    totalSeconds={chargeTimeCycle}
    />
  </Box>

  {/* Instant */}
  <Box
 
    sx={{
      gridColumn: "4",
      gridRow: "3",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "50px",
    }}
  >
    <Instantaneous
     voltage={stringvoltage}
     current={instantaneousCurrent}
     soc={socLatestValueForEveryCycle}
     dod={dodLatestValueForEveryCycle}
     ambientTemperature={ambientTemperature}
     />
  </Box>

  {/* Cumulative */}
  <Box

    sx={{
      gridColumn: "5",
      gridRow: "3",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "50px",
    }}
  >
    <Cummulative 
        chargeDischargeCycles={chargeOrDischargeCycle}  
        ampereHourIn={cumulativeAHIn}  
        ampereHourOut={cumulativeAHOut} 
        chargingEnergy={totalChargingEnergy} 
        dischargingEnergy={totalDischargingEnergy} 
        time={batteryRunHours} />
  
  </Box>

  {/* Alarms */}
  <Box
    sx={{
      gridColumn: "1 / 6",
      gridRow: "4",
      backgroundColor: colors.primary[400],
      border: "1px solid black",
      // height: "50px",
    }}
  >
    {/* <Cumulative /> */}
    Alarms
  </Box>
</Box>


  );
};

export default Bar;
 {/* <Box width="40%" display="grid" gridTemplateRows="1fr 1fr" gap="10px" > <Box backgroundColor={colors.primary[400]} width="100%" > </Box> <Box backgroundColor={colors.primary[400]} width="100%" > </Box> </Box> */} 










//  <Box m="20px">
//  {/* HEADER */}
//  <Box display="flex" justifyContent="space-between" alignItems="center">
//    <Topbar />
//  </Box>

//  {/* GRID & CHARTS */}
//  <Box
//    display="grid"
//    gridTemplateColumns="repeat(5, 1fr)"
//    gridTemplateRows="200px"
//    gap="10px"
//    mb="10px"
//  >
//    {stringvoltage && (
//      <Box
//        backgroundColor={colors.primary[400]}
//        display="flex"
//        alignItems="flex-start"
//        justifyContent="flex-start"
//        // sx={{
//        //   width: "180px", // Controls the width of the chart cell
//        //   height: "150px", // Controls the height of the chart cell
//        // }}
//      >
//        <Instantaneous
//          voltage={stringvoltage}
//          current={instantaneousCurrent}
//          soc={socLatestValueForEveryCycle}
//          dod={dodLatestValueForEveryCycle}
//          ambientTemperature={ambientTemperature}
//        />
//      </Box>
//    )}
//    <Box
//      backgroundColor={colors.primary[400]}
//      display="flex"
//      alignItems="flex-start"
//      justifyContent="flex-start"
//    >
//      <ChargeCycleWise PeakChargeCurrent={systemPeakCurrentInChargeOneCycle}  AverageChargeCurrent={averageChargingCurrent} AmpereHourIn={ahInForOneChargeCycle} totalSeconds={chargeTimeCycle}/>
//    </Box>
//    <Box
//      backgroundColor={colors.primary[400]}
//      display="flex"
//      alignItems="flex-start"
//      justifyContent="flex-start"
//    >
//      <DischargeCycleWise
//        peakDischargeCurrent={systemPeakCurrentInDischargeOneCycle}
//        averageDischargingCurrent={averageDischargingCurrent}
//        ahOutForOneDischargeCycle={ahOutForOneDischargeCycle}
//        totalSeconds={dischargeTimeCycle}
//      />
//    </Box>
//    <Box
//      backgroundColor={colors.primary[400]}
//      display="flex"
//      alignItems="flex-start"
//      justifyContent="flex-start"
//    >
//       <ManufacturerDetails
//        firstUsedDate={firstUsedDate}
//        batterySerialNumber={batterySerialNumber}
//        batteryBankType={batteryBankType}
//        serialNumber={serialNumber}
//        ahCapacity={ahCapacity}
//        manifactureName={manifactureName}
//        individualCellVoltage={individualCellVoltage}
//        designVoltage={designVoltage}
//      />
    
//    </Box>
//    <Box
//      backgroundColor={colors.primary[400]}
//      display="flex"
//      alignItems="flex-start"
//      justifyContent="flex-start"
//    >
//      <Cummulative chargeDischargeCycles={chargeOrDischargeCycle}  ampereHourIn={cumulativeAHIn}  ampereHourOut={cumulativeAHOut} chargingEnergy={totalChargingEnergy} dischargingEnergy={totalDischargingEnergy} time={batteryRunHours}/>
//    </Box>
//  </Box>

//  {/* ROW 2 */}
//  {/* <Box display="flex" justifyContent="space-between" alignItems="center">
//    <Box
//      width="60%"
//      height="250px"
//      backgroundColor={colors.primary[400]}
//      marginRight="10px"
//      mt="0px"
//      padding="100px"
//    >
//      <Typography variant="h4" mb="10px">Cells Data</Typography>
//    </Box>
//    <Box
//      width="40%"
//      display="flex"
//      flexDirection="column"
//      alignItems="center"
//      justifyContent="space-between"
//      gap="10px"
//    >
//      <Box
//        backgroundColor={colors.primary[400]}
//        width="100%"
//        height="125px"
//        padding="50px"
//      >
//        <Typography variant="h4" mb="10px">Charger Status</Typography>
//      </Box>
//      <Box
//        backgroundColor={colors.primary[400]}
//        width="100%"
//        height="125px"
//        padding="50px"
//      >
//        <Typography variant="h4" mb="10px">Battery Alarms</Typography>
//      </Box>
//    </Box>
//  </Box> */}
//  <Container>
//  <Box 
//    display="grid" 
//    gridTemplateColumns="8fr 4fr" 
//    gridTemplateRows="200px"
//    gap={2}
//    sx={{ mb: 8 }}
//  >
//    {/* First Row */}
//    <Box 
//    backgroundColor={colors.primary[400]}
//      sx={{ 
//        height: "250px", 
//      }}
//    >
//      First Grid
//    </Box>
//    <Box 
//    backgroundColor={colors.primary[400]}
//      sx={{ 
//        height: "250px", 
//      }}
//    >
//      Second Grid
//    </Box>
//  </Box>
 
//  {/* Add more rows */}
//  <Box 
//    display="grid" 
//    gridTemplateColumns="6fr 6fr" 
//    gridTemplateRows="200px"
//    gap="20pxs"
//    sx={{ mb: 2 }}
//  >
//    <Box 
//    backgroundColor={colors.primary[400]}
//      sx={{ 
//        height: "250px", 
//        mb: 2
//      }}
//    >
//      First Grid - Row 2
//    </Box>
//    <Box 
//    backgroundColor={colors.primary[400]}
//      sx={{ 
//        height: "250px",
//        mb: 2 
//      }}
//    >
//      Second Grid - Row 2
//    </Box>
//  </Box>
// </Container>
// </Box>