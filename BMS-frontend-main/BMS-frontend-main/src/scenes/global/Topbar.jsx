import { Box, IconButton, useTheme } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { ColorModeContext, tokens } from "../../theme";
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import SearchIcon from "@mui/icons-material/Search";
import { useNavigate } from "react-router-dom"; 
//  import deadline from "../../data/icons/deadline.png"
import { AppContext } from "../../services/AppContext";
import { fetchManufacturerDetails, fetchDeviceDetails, fetchAllSiteIds } from "../../services/apiService";

const Topbar = ({ onLogout }) => {
 


  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);
  const navigate = useNavigate(); 

  const {
    siteOptions,
    serialNumberOptions,
    siteId,
    serialNumber,
    setSiteId,
    setSerialNumber,
    setData,
    setMdata,
  } = useContext(AppContext);

  const handleSearch = async () => {
    if (siteId && serialNumber) {
      try {
        setMdata(null); // Clear previous manufacturer details
        setData([]);    // Clear previous device data
  
        // Fetch details
        const result = await fetchManufacturerDetails(siteId, serialNumber);
        const deviceResponse = await fetchDeviceDetails(siteId, serialNumber);
  
        setMdata(result);                // Store manufacturer details
        setData(deviceResponse.deviceData); // Store device data
        
        // Return the fetched data for further usage
        return deviceResponse.deviceData;
      } catch (error) {
        console.error("Error during search:", error);
      }
    } else {
      console.error("Please select both Site ID and Serial Number.");
    }
  };
  
  const handleLogout = () => {
    if (onLogout) {
      onLogout(); 
    }
    navigate("/"); 
    
  };

  return (
    <Box
    display="grid"
    gridTemplateColumns="repeat(3,auto)"
    gridTemplateRows="auto "
    
    p={2}
  >
    {/* Search Options */}
    <Box
   
      display="grid"
      gridTemplateColumns="repeat(3, auto)"
      gap={2}
      sx={{ marginRight: 2 }}
    >
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


      <IconButton onClick={handleSearch}>
        <SearchIcon />
      </IconButton>
    </Box>
  
    {/* Location and Time */}
    <Box
      display="grid"
      gridTemplateColumns="repeat(3, auto)"
      gap="10px"
      paddingTop="5px"
      marginLeft="0px"
    >
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        sx={{
          border: "1px solid",
          borderColor: colors.grey[500],
          borderRadius: "4px",
          height: "40px",
          width: "150px",
        }}
      >
        Location
      </Box>
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        sx={{
          border: "1px solid",
          borderColor: colors.grey[500],
          borderRadius: "4px",
          height: "40px",
          width: "150px",
        }}
      >
        <IconButton>
          <img
            //  src={deadline}
            alt="Live Time"
            style={{ width: "24px", height: "24px", marginRight: "8px" }}
          />
        </IconButton>
        Time
      </Box>
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        sx={{
          border: "1px solid",
          borderColor: colors.grey[500],
          borderRadius: "4px",
          height: "40px",
          width: "150px",
        }}
      >
        <IconButton>
          <PersonOutlinedIcon />
        </IconButton>
        Customer name
      </Box>
    </Box>
  
    {/* Icons */}
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

export default Topbar;
