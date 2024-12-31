import React, { useEffect, useState, useContext } from 'react';
import { fetchSiteDetailsBatteryandChargerdetails ,updateSiteLocation,deleteSite,addSiteLocation} from '../../../services/apiService';
import  { AppContext } from "../../../services/AppContext";
import { ColorModeContext, tokens } from "../../../theme";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import { Box, IconButton, TextField, useTheme, Button, Dialog, DialogActions, DialogContent, DialogTitle, Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import L from 'leaflet';

const SiteLocationTable = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  const [newSite, setNewSite] = useState({
    siteId:'',
    locationName: '',
    latitude: '',
    longitude: '',
    vendorName: '',
    batteryAHCapacity: '',
  });

  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);

  const [openDialog, setOpenDialog] = useState(false);
  const [editingSiteId, setEditingSiteId] = useState(null);
  const {
    siteOptions,
    siteId,
    setSiteId
  } = useContext(AppContext);

  const columnMappings = {
    siteId:'Site ID',
    locationName: 'Location Name',
    latitude: 'Latitude',
    longitude: 'Longitude',
    vendorName: 'Vendor Name',
    batteryAHCapacity: 'Battery AH Capacity',
    actions: 'Actions',
  };
  useEffect(() => {
    const getData = async () => {
      try {
        const result = await fetchSiteDetailsBatteryandChargerdetails();
        setData(result);
      } catch (err) {
        setError(err.message || 'An error occurred');
      } finally {
        setLoading(false);
      }
    };

    getData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewSite((prevSite) => ({
      ...prevSite,
      [name]: value,
    }));
  };

  const handleLogout = () => {
    if (onLogout) {
      onLogout();
    }
    navigate("/");
  };
  const handleDialogClose = () => {
    setOpenDialog(false);
  };
  const handleAddSite = async () => { 
    try {
      debugger;
      const newSiteData = {
        siteId: newSite.siteId,
        siteLocation: {
          locationName: newSite.locationName,
          latitude: parseFloat(newSite.latitude),
          longitude: parseFloat(newSite.longitude),
          vendorName: newSite.vendorName,
          batteryAHCapacity: newSite.batteryAHCapacity,
        },
      };
  
      const response = await addSiteLocation(newSiteData.siteId, newSiteData.siteLocation);
  
      if (response) {
        const addedSite = {
          id: response.id || data.length + 1, 
          siteId: newSiteData.siteId,
          siteLocation: newSiteData.siteLocation,
          servertime: new Date().toISOString(),
        };
  
        setData((prevData) => [...prevData, addedSite]);
  
        setNewSite({
          siteId: '',
          locationName: '',
          latitude: '',
          longitude: '',
          vendorName: '',
          batteryAHCapacity: '',
        });
  
        setOpenDialog(false);
        alert("Site location added successfully.");
      } else {
        throw new Error("Invalid response from server");
      }
    } catch (error) {
      console.error("Error while adding new site location:", error);
      alert("Failed to add site location. Please check your input and try again.");
    }
  };

  const handleDialogOpen = (siteId = null) => {
    if (siteId) {
      const siteToEdit = data.find((site) => site.siteId === siteId);
      setNewSite(siteToEdit.siteLocation);
      setEditingSiteId(siteId);
    } else {
      setNewSite({
        siteId: '',
        locationName: '',
        latitude: '',
        longitude: '',
        vendorName: '',
        batteryAHCapacity: '',
      });
      setEditingSiteId(null);
    }
    setOpenDialog(true);
  };

  const handleDelete = async (siteId) => {
    try {
      console.log("Attempting to delete site with siteId:", siteId);  // Log to verify the siteId
  
      // Ensure siteId is correct by checking in the console
      // Call the deleteSite API with the correct siteId to delete the site from the backend
      const response = await deleteSite(siteId);
  
      if (response && response.value !== 0) {
        // If the API call is successful and response value is not 0, update the local state
        const updatedData = data.filter((site) => site.siteId !== siteId);  // Make sure you're comparing site.siteId
  
        // Update the state with the new data (after deleting the site)
        setData(updatedData);
        // Optionally, show a success message or notification to the user
        alert("Site deleted successfully!");  // Example success message
      } else {
        // If response is unsuccessful or siteId is not found, show an error message
        alert("Failed to delete site. SiteId might be incorrect.");
      }
    } catch (error) {
      console.error("Error while deleting site:", error);
      // Optionally, show an error message to the user
      alert("Failed to delete site. Please try again later.");
    }
  };
  
  

 const handleEdit = async () => {
  try {
    const updatedSiteLocation = {
      ...newSite,  
    };
    const siteId = editingSiteId;  
    const response = await updateSiteLocation(siteId, updatedSiteLocation);

    if (response) {

      const updatedSites = await fetchSites(); 

   
      setData(updatedSites);

      
      setOpenDialog(false);
      setEditingSiteId(null);
    }
  } catch (error) {
    console.error("Error while updating site location:", error); 
  }
};


  if (loading) return <CircularProgress />;
  if (error) return <div>Error: {error}</div>;

  const filteredData = data.filter((site) => {
    return site.siteLocation && Object.keys(columnMappings).every(
      (key) => {
        // Always allow 'id', 'servertime', and 'actions' columns
        if (key === 'id' || key === 'servertime' || key === 'actions' || key === 'siteId') {
          return true;
        }
        // Check if the key exists in siteLocation and is not null or undefined
        return site.siteLocation[key] !== null && site.siteLocation[key] !== undefined;
      }
    );
  });
  
  return (
    <Box>
      {/* Top Bar */}
      <Box display="grid" gridTemplateColumns="1fr 2fr" p={2} gap={2}>
        {/* Left: Add Site Button */}
        <Box display="flex" justifyContent="flex-start">
          <Button onClick={() => handleDialogOpen()} variant="contained" color="secondary">
            Add Site
          </Button>
        </Box>

        {/* Right: Color Mode Toggle, Notifications, Logout */}
        <Box display="flex" justifyContent="flex-end" alignItems="center">
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

      {/* Main Content: Grid Layout with Table and Map */}
      <Grid container spacing={3}>
        {/* Left: Site Table */}
        <Grid item xs={12} md={6.5}>
            <TableContainer component={Paper} style={{ maxHeight: "500px", overflowY: "auto" }}>
            <Table>
                <TableHead>
                <TableRow>
                    {Object.keys(columnMappings).map((key) => (
                    <TableCell
                        key={key}
                        style={{
                        position: "sticky",
                        top: 0,
                        backgroundColor: "#1976d2",
                        fontWeight: "bold",
                        zIndex: 1,
                       
                        }}
                    >
                        {columnMappings[key]}
                    </TableCell>
                    ))}
                </TableRow>
                </TableHead>
                <TableBody>
                {filteredData.map((site) => (
                    <TableRow key={site.siteId}> {/* Use site.siteId for unique key */}
                    {Object.keys(columnMappings).map((key) => {
                        if (key === 'actions') {
                        return (
                            <TableCell key={key}>
                            <IconButton color="secondary" variant="contained" onClick={() => handleDialogOpen(site.siteId)}>
                                <EditIcon />
                            </IconButton>
                            <IconButton color="secondary" variant="contained" onClick={() => handleDelete(site.siteId)}>
                                <DeleteIcon />
                            </IconButton>
                            </TableCell>
                        );
                        }
                        // Handle 'siteId' specifically
                        if (key === 'siteId') {
                        return (
                            <TableCell key={key}>
                            {site.siteId} {/* Display siteId */}
                            </TableCell>
                        );
                        }
                        // For other columns, check siteLocation for their values
                        return (
                        <TableCell key={key}>
                            {key === 'id' || key === 'servertime' ? site[key] : String(site.siteLocation[key])}
                        </TableCell>
                        );
                    })}
                    </TableRow>
                ))}
                </TableBody>
            </Table>
            </TableContainer>
      </Grid> 

        {/* Right: Map Section */}
    <Grid item xs={12} md={5.5}>
        <MapContainer center={[20.5937, 78.9629]} zoom={5} style={{ height: "500px", width: "100%" }}>
            <TileLayer
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {filteredData.map((site) => (
              site.siteLocation.latitude && site.siteLocation.longitude && (
                <Marker
                  key={site.id}
                  position={[parseFloat(site.siteLocation.latitude), parseFloat(site.siteLocation.longitude)]}
                  icon={new L.Icon({
                    iconUrl: 'https://cdn.jsdelivr.net/gh/pointhi/leaflet-color-markers/img/marker-icon-2x-blue.png',
                    iconSize: [25, 41],
                    iconAnchor: [12, 41],
                    popupAnchor: [1, -34],
                    shadowSize: [41, 41],
                  })}
                >
                  <Popup>
                    <strong>{site.siteLocation.locationName}</strong><br />
                    Latitude: {site.siteLocation.latitude}<br />
                    Longitude: {site.siteLocation.longitude}<br />
                    Vendor: {site.siteLocation.vendorName}
                  </Popup>
                </Marker>
              )
            ))}
          </MapContainer>
        </Grid>
      </Grid>

      {/* Add/Edit Site Dialog */}
      <Dialog open={openDialog} onClose={handleDialogClose}>
  <DialogTitle>{editingSiteId ? 'Edit Site' : 'Add New Site'}</DialogTitle>
  <DialogContent>
    {/* Dropdown for Site ID */}
    <FormControl fullWidth margin="normal">
  <InputLabel id="site-id-label">Site ID</InputLabel>
  <Select
    labelId="site-id-label"
    name="siteId"
    value={newSite.siteId}
    onChange={handleInputChange}
  >
    {siteOptions.length > 0 ? (
      siteOptions.map((siteId) => (
        <MenuItem key={siteId} value={siteId}>
          {siteId}
        </MenuItem>
      ))
    ) : (
      <MenuItem value="" disabled>
        Loading...
      </MenuItem>
    )}
  </Select>
</FormControl>

    
    {/* Text Fields */}
    <TextField
      label="Location Name"
      name="locationName"
      value={newSite.locationName}
      onChange={handleInputChange}
      margin="normal"
      fullWidth
    />
    <TextField
      label="Latitude"
      name="latitude"
      value={newSite.latitude}
      onChange={handleInputChange}
      margin="normal"
      fullWidth
    />
    <TextField
      label="Longitude"
      name="longitude"
      value={newSite.longitude}
      onChange={handleInputChange}
      margin="normal"
      fullWidth
    />
    <TextField
      label="Vendor Name"
      name="vendorName"
      value={newSite.vendorName}
      onChange={handleInputChange}
      margin="normal"
      fullWidth
    />
    <TextField
      label="Battery AH Capacity"
      name="batteryAHCapacity"
      value={newSite.batteryAHCapacity}
      onChange={handleInputChange}
      margin="normal"
      fullWidth
    />
  </DialogContent>
  <DialogActions>
    <Button onClick={handleDialogClose} color="primary">
      Cancel
    </Button>
    <Button onClick={editingSiteId ? handleEdit : handleAddSite} color="primary">
      {editingSiteId ? 'Save' : 'Add'}
    </Button>
  </DialogActions>
</Dialog>

    </Box>
  );
};

export default SiteLocationTable;
