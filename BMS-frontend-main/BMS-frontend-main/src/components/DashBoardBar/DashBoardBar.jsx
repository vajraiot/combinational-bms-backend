import { Box, IconButton, useTheme, TextField, Autocomplete } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { ColorModeContext, tokens } from "../../theme";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import SearchIcon from "@mui/icons-material/Search";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../../services/AppContext";
import { fetchCommunicationStatus } from "../../services/apiService";

const DashBoardBar = ({ onLogout }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);
  const navigate = useNavigate();

  const {
    siteOptions,
    serialNumberOptions,
    locationNameOptions,
    customerNameOptions,
    siteId,
    serialNumber,
    locationName,
    customerName,
    setSiteId,
    setSerialNumber,
    setLocationName,
    setCustomerName,
    setData,
    setMdata,
  } = useContext(AppContext);

  // Fetch additional data like location and customer names
  useEffect(() => {
    const fetchAdditionalData = async () => {
      try {
        const locations = await fetchLocationNames();
        const customers = await fetchCustomerNames();
        // Assuming locationNameOptions and customerNameOptions are populated from the fetched data
        setLocationNameOptions(locations);
        setCustomerNameOptions(customers);
      } catch (error) {
        console.error("Error fetching additional data:", error);
      }
    };

    fetchAdditionalData();
  }, []);

  const handleSearch = async () => {
    if (siteId && serialNumber && locationName && customerName) {
      try {
        setData(deviceResponse.deviceData); // Store device data

        // Return the fetched data for further usage
        return deviceResponse.deviceData;
      } catch (error) {
        console.error("Error during search:", error);
      }
    } else {
      console.error("Please select Site ID, Serial Number, Location Name, and Customer Name.");
    }
  };

  const handleLogout = () => {
    if (onLogout) {
      onLogout();
    }
    navigate("/"); // Navigate to the home page after logout
  };

  return (
    <Box
      display="grid"
      gridTemplateColumns="repeat(5, auto) 1fr" // Ensure search options and icons are in the same row
      gridTemplateRows="auto"
      gap={2}
      p={1}
    >
      {/* Search Options */}
      <Box display="grid" gridTemplateColumns="repeat(7, auto)" gap={2}>
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
        <Autocomplete
          disablePortal
          disableClearable
          options={locationNameOptions}
          value={locationName}
          onChange={(event, newValue) => setLocationName(newValue)}
          renderInput={(params) => <TextField {...params} label="Location Name" />}
          sx={{ width: 200 }}
        />
        <Autocomplete
          disablePortal
          disableClearable
          options={customerNameOptions}
          value={customerName}
          onChange={(event, newValue) => setCustomerName(newValue)}
          renderInput={(params) => <TextField {...params} label="Customer Name" />}
          sx={{ width: 200 }}
        />
        <IconButton onClick={handleSearch}>
          <SearchIcon />
        </IconButton>
      </Box>

      {/* Icons */}
      <Box display="flex" justifyContent="flex-end" alignItems="center">
        <IconButton onClick={colorMode.toggleColorMode}>
          {theme.palette.mode === "dark" ? <DarkModeOutlinedIcon /> : <LightModeOutlinedIcon />}
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

export default DashBoardBar;
