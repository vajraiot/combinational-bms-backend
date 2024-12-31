import React, { useContext } from "react";
import { useTheme } from "@mui/material";
import { ColorModeContext, tokens } from "../../../theme";
import { Box, IconButton, TextField, Autocomplete, useTheme } from "@mui/material";
import { AppContext } from "../../../services/AppContext";
import{fetchMonthlyBatteryandChargerdetails} from "../../../services/apiService"
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

import SearchIcon from "@mui/icons-material/Search";

import 'react-datetime/css/react-datetime.css';
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
  month:"Month",
  chargeOrDischargeCycle: "Charge/Discharge Cycle",
  cumulativeAHIn: "Cumulative AH In",
  cumulativeAHOut: "Cumulative AH Out",
  totalChargingEnergy: "Total Charging Energy",
  totalDischargingEnergy: "Total Discharging Energy",
  batteryRunHours: "Battery Run Hours",
};

const Monthly = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);
  const { data = [] } = useContext(AppContext);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("dayWiseDate");
  
  const {
    siteOptions,
    serialNumberOptions,
    siteId,
    serialNumber,
    year,
    month,
    setSiteId,
    setSerialNumber,
    setYear,
    setMonth,
    setData
  } = useContext(AppContext);
  const handleSearch = async () => {
    if (siteId && serialNumber && year && month) {
      try {
         // Clear previous device data

        // Fetch the report data
        const result = await fetchMonthlyBatteryandChargerdetails(
          serialNumber,
          siteId,
          year,
          month
        );
        

        setData(result); // Store the report data
      } catch (error) {
        console.error("Error during search:", error);
      }
    } else {
      console.error("Please select all fields.");
    }
  };
  const handleRequestSort = (property) => {
    const isAscending = orderBy === property && order === "asc";
    setOrder(isAscending ? "desc" : "asc");
    setOrderBy(property);
  };

  const handleLogout = () => {
    if (onLogout) {
      onLogout(); 
    }
    navigate("/"); 
    
  };
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const formatData = (data) => {
    if (!data || data.length === 0) return [];

    const formatToTime = (seconds) => {
      const hrs = Math.floor(seconds / 3600);
      const mins = Math.floor((seconds % 3600) / 60);
      const secs = seconds % 60;
      return `${hrs.toString().padStart(2, "0")}:${mins
        .toString()
        .padStart(2, "0")}:${secs.toString().padStart(2, "0")}`;
    };

    const formatToTwoDecimals = (value) =>
      value !== null && value !== undefined
        ? parseFloat(value).toFixed(2)
        : "-";

    const formattedData = data.map((row) => {
     
      const {
        month,
        batteryRunHours,
        chargeOrDischargeCycle,
        cumulativeAHIn,
        cumulativeAHOut,
        totalChargingEnergy,
        totalDischargingEnergy,
      } = row;

      return {
        month:(month),
        batteryRunHours: formatToTime(batteryRunHours || 0), // Convert seconds to HH:MM:SS format
        chargeOrDischargeCycle: (chargeOrDischargeCycle) , // Keep as-is if integer
        cumulativeAHIn: formatToTwoDecimals(cumulativeAHIn), // Limit to 2 decimal places
        cumulativeAHOut: formatToTwoDecimals(cumulativeAHOut), // Limit to 2 decimal places
        totalChargingEnergy: formatToTwoDecimals(totalChargingEnergy), // Limit to 2 decimal places
        totalDischargingEnergy: formatToTwoDecimals(totalDischargingEnergy), // Limit to 2 decimal places
      };
    });

    // Calculate total batteryRunHours in seconds for the last row
    const totalBatteryRunHours = data.reduce(
      (sum, row) => sum + (row.batteryRunHours || 0),
      0
    );

    // Add a summary row at the end
    // formattedData.push({
    //   dayWiseDate: "Total", // Label for the last row
    //   chargeOrDischargeCycle: "-",
    //   cumulativeAHIn: "-",
    //   cumulativeAHOut: "-",
    //   totalChargingEnergy: "-",
    //   totalDischargingEnergy: "-",
    //   batteryRunHours: formatToTime(totalBatteryRunHours), // Total in HH:MM:SS
    // });

    return formattedData;
  };

  const sortedData = (data) => {
    return [...data].sort((a, b) => {
      if (order === "asc") {
        return a[orderBy] > b[orderBy] ? 1 : -1;
      }
      return a[orderBy] < b[orderBy] ? 1 : -1;
    });
  };

  const formattedData = formatData(data);
  const displayedData = sortedData(formattedData);


  return (
    <Box display="grid" gridTemplateColumns="repeat(2, 1fr)" p={2} gap={2} >
      
      {/* Grid 1: Site ID, Serial Number, Year and Month, Search */}
      <Box display="grid" gridTemplateColumns="repeat(5, 1fr)" gap={2}>
        <Autocomplete
          disablePortal
          disableClearable
          options={siteOptions.map((site) => site.siteId)}
          value={siteId}
          onChange={(event, newValue) => setSiteId(newValue)}
          renderInput={(params) => <TextField {...params} label="Site ID" />}
          sx={{ width: 200 }}
        />

        <Autocomplete
          disablePortal
          disableClearable
          options={serialNumberOptions}
          value={serialNumber}
          onChange={(event, newValue) => setSerialNumber(newValue)}
          renderInput={(params) => <TextField {...params} label="Serial Number" />}
          sx={{ width: 200 }}
        />

<TextField
          label="Month"
          type="month"
          value={month ? `${year}-${month}` : ""}
          onChange={(e) => {
            // Extract the month number from the input value and store only the month (e.g., 12 for December)
            const selectedDate = e.target.value; // "YYYY-MM"
            const selectedMonth = selectedDate.split("-")[1]; // Extract month number (e.g., "12")
            setMonth(selectedMonth); // Set only the month (e.g., "12")
            setYear(selectedDate.split("-")[0]); // Set the year (e.g., "2024")
          }}
          sx={{ width: 200 }}
          InputLabelProps={{
            shrink: true,
          }}
        />

        <IconButton onClick={handleSearch}>
          <SearchIcon />
        </IconButton>
      </Box>

      {/* Grid 2: Color Mode Toggle, Notification Icon, Logout Icon */}
      <Box display="flex" justifyContent="flex-end" alignItems="center" marginLeft="10px">
        <IconButton onClick={colorMode.toggleColorMode}>
          {theme.palette.mode === "dark" ? (
            <DarkModeOutlinedIcon />
          ) : (
            <LightModeOutlinedIcon />
          )}
        </IconButton>
        <IconButton>
          <NotificationsOutlinedIcon />
        </IconButton>
        <IconButton onClick={handleLogout}>
          <LogoutOutlinedIcon />
        </IconButton>
      </Box>
      
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
              <TableBody>
                {displayedData
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row, index) => (
                    <TableRow
                      key={index}
                      sx={{
                        "&:hover": { backgroundColor: "#e1e2fe" }, // Hover effect
                      }}
                    >
                      {Object.values(row).map((value, idx) => (
                        <TableCell key={idx}>{value}</TableCell>
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
      </Box>
    
  );

  
};

export default Monthly;
