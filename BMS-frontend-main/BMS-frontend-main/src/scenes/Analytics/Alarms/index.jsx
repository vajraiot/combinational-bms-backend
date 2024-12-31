import React, { useContext } from "react";
import { useTheme } from "@mui/material";
import { ColorModeContext, tokens } from "../../../theme";
import { AppContext } from "../../../services/AppContext";
import ReportsBar from "../../../components/ReportsBar/ReportsBar";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  TablePagination,
  TableSortLabel,
} from "@mui/material";

const columnMappings = {
  siteId: "Site ID",
  serialNumber: "Serial Number",
  bankCycle: "Bank Status",
  ambientTemperature: "Ambient Temperature",
  soc: "State Of Charge",
  stringVoltage: "String Voltage",
  stringCurrent: "String Current",
  bmsSedCommunication: "BMS Sed Communication",
  cellCommunication: "Cell Communication",
  cellVoltage: "Cell Voltage",
  cellTemperature: "Cell Temperature",
  buzzer: "Buzzer",
  ebStatus: "EB Status",
  packetDateTime: "Packet DateTime",
  inputMains: "Input Mains",
  inputFuse: "Input Fuse",
  rectifierFuse: "Rectifier Fuse",
  filterFuse: "Filter Fuse",
  dcVoltageOLN: "DC Voltage OLN",
  outputFuse: "Output Fuse",
  acUnderVoltage: "AC Under Voltage",
  chargerLoad: "Charger Load",
  alarmSupplyFuse: "Alarm Supply Fuse",
  chargerTrip: "Charger Trip",
  outputMccb: "Output MCCB",
  acVoltageC: "AC Voltage C",
  batteryCondition: "Battery Condition",
  testPushButton: "Test Push Button",
  resetPushButton: "Reset Push Button",
};

const Alarms = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);
  const { data = [] } = useContext(AppContext);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("siteId");

  const handleRequestSort = (property) => {
    const isAscending = orderBy === property && order === "asc";
    setOrder(isAscending ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };
  const dataArray = data.content; 
  const combineAlarmsData = (dataArray) => {
    if (!dataArray || dataArray.length === 0) return [];
  
    const combinedData = {};
  
    // Step 1: Combine objects with the same id
    dataArray.forEach((current) => {
      const { id, serverTime,bmsalarmsString, ...rest } = current;  
      if (!combinedData[current.id]) {
        combinedData[current.id] = { ...rest }; 
      } else {
       
        combinedData[current.id] = { ...combinedData[current.id], ...rest };
      }
    });
  
   
    const rows = Object.values(combinedData);
  
   
    return rows.map((row) => {
      const { packetDateTime, ...rest } = row; 
      return { packetDateTime, ...rest }; 
    });
  };
  
  
  const sortedData = (dataArray) => {
    return [...dataArray].sort((a, b) => {
      if (order === "asc") {
        return a[orderBy] > b[orderBy] ? 1 : -1;
      }
      return a[orderBy] < b[orderBy] ? 1 : -1;
    });
  };
 
  const formattedData = combineAlarmsData(dataArray);
  const displayedData = sortedData(formattedData);
  
  console.log(formattedData); 
  return (
    <div>
      <ReportsBar />

      <Typography variant="h4" gutterBottom>
        Alarms Reports
      </Typography>

      {formattedData && formattedData.length > 0 ? (
        <>
          <TableContainer
            component={Paper}
            sx={{ marginTop: 1, overflowX: "auto" }}
          >
            <Table>
              {/* Table Header */}
              <TableHead>
                <TableRow>
                  {Object.keys(formattedData[0]).map((key) => (
                    <TableCell
                      key={key}
                      sx={{
                        fontWeight: "bold",
                        backgroundColor: "#1976d2", // Blue header
                        color: "#ffffff", // White text
                      }}
                    >
                      <TableSortLabel
                        active={orderBy === key}
                        direction={orderBy === key ? order : "asc"}
                        onClick={() => handleRequestSort(key)}
                        aria-label={`Sort by ${columnMappings[key] || key}`}
                      >
                        {columnMappings[key] || key} {/* Map column names */}
                      </TableSortLabel>
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>

              {/* Table Body */}
              <TableBody sx={{overflowY:'auto'}}>
                {displayedData
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row, index) => (
                    <TableRow
                      key={index}
                      sx={{
                        "&:hover": { backgroundColor: "#e1e2fe" }, // Hover effect
                      }}
                    >
                      {/* Render each value in the row */}
                      {Object.entries(row).map(([key, value], idx) => (
                        <TableCell
                          key={idx}
                          sx={{ border: '1px solid #ccc' }} // Ensure visibility
                        >
                          {key === 'dcVoltageOLN'
                            ? (value === 0 ? 'Low' : value === 1 ? 'Normal' : value === 2 ? 'Over' : value) // Custom mapping for dcVoltageOLN
                            : typeof value === 'boolean'
                            ? value
                              ? 'Fail' // If true, show 'Fail'
                              : 'Normal' // If false, show 'Normal'
                            : value !== undefined && value !== null
                            ? value // Otherwise, just show the actual value
                            : 'No Data'}
                        </TableCell>
                      ))}
                    </TableRow>
                  ))}
              </TableBody>



            </Table>
          </TableContainer>

          {/* Pagination */}
          <TablePagination
            rowsPerPageOptions={[5, 10, 15]}
            component="div"
            count={formattedData.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </>
      ) : (
        <Typography variant="body1" sx={{ marginTop: 2 }}>
          No data available
        </Typography>
      )}
    </div>
  );
};

export default Alarms;
