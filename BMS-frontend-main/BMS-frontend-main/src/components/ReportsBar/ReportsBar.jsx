import React, { useContext, useState } from "react";
import { AppContext } from "../../services/AppContext";
import { Box, IconButton, TextField, Autocomplete, useTheme } from "@mui/material";
import { fetchHistoricalBatteryandChargerdetails,fetchDaywiseBatteryandChargerdetails,fetchAlarmsBatteryandChargerdetails } from "../../services/apiService";
import { ColorModeContext, tokens } from "../../theme";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import SearchIcon from "@mui/icons-material/Search";

import 'react-datetime/css/react-datetime.css';



const ReportsBar = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);

  const {
    siteOptions,
    serialNumberOptions,
    siteId,
    serialNumber,
    startDate,
    endDate,
    setSiteId,
    setSerialNumber,
    setStartDate,
    setEndDate,
    setData
  } = useContext(AppContext);

  const [loadingReport, setLoadingReport] = useState(false);

  const handleSearch = async () => {
    if (siteId && serialNumber && startDate && endDate) {
      try {
         // Clear previous device data

        // Fetch the report data
        const result = await fetchHistoricalBatteryandChargerdetails(
          serialNumber,
          siteId,
          startDate,
          endDate
        );
        

        setData(result); // Store the report data
      } catch (error) {
        console.error("Error during search:", error);
      }
    } else {
      console.error("Please select all fields.");
    }
  };
  const handleSearchDaywise = async () => {
    if (siteId && serialNumber && startDate && endDate) {
      try {
         // Clear previous device data

        // Fetch the report data
        const result = await fetchDaywiseBatteryandChargerdetails(
          serialNumber,
          siteId,
          startDate,
          endDate
        );
        

        setData(result); // Store the report data
      } catch (error) {
        console.error("Error during search:", error);
      }
    } else {
      console.error("Please select all fields.");
    }
  };
  const handleSearchAlarms = async () => {
    if (siteId && serialNumber && startDate && endDate) {
      try {
         // Clear previous device data

        // Fetch the report data
        const result = await fetchAlarmsBatteryandChargerdetails(
          serialNumber,
          siteId,
          startDate,
          endDate
        );
        

        setData(result); // Store the report data
      } catch (error) {
        console.error("Error during search:", error);
      }
    } else {
      console.error("Please select all fields.");
    }
  };
  
  const handleLogout = () => {
    if (onLogout) {
      onLogout(); 
    }
    navigate("/"); 
    
  };
  return (
    <Box display="grid" gridTemplateColumns="repeat(2, 1fr)" p={2} gap={2} >
      
      {/* Grid 1: Site ID, Serial Number, Start Date, End Date, Search */}
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
            label="Start Date"
            type="date"
            value={startDate.split('%')[0]}  
            onChange={(e) => {
             
              const updatedDate = e.target.value + '%2000:00:00';
              setStartDate(updatedDate); 
            }}
            sx={{ width: 200 }}
            InputLabelProps={{
              shrink: true,  
            }}
          />

        <TextField
          label="End Date"
          type="date"
          value={endDate.split('%')[0]}
          onChange={(e) => setEndDate(e.target.value +'%2023:59:59')}
          sx={{ width: 200 }}
        
          InputLabelProps={{
            shrink: true,  
          }}
        />

        <IconButton onClick={ handleSearch }>
          <SearchIcon />
        </IconButton>
      </Box>

      {/* Grid 2: Color Mode Toggle, Notification Icon, Logout Icon */}
      <Box
      display="flex"
      justifyContent="flex-end"
      alignItems="center"
      marginLeft="10px"
     
    >
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
    </Box>
  );
};

export default ReportsBar;
