import React, { useEffect, useState, useContext } from 'react';
import { fetchManfacurareDetailsBatteryandChargerdetails } from '../../../services/apiService';
import { ColorModeContext, tokens } from "../../../theme";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import { Box, IconButton, TextField, useTheme, Grid } from "@mui/material";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  CircularProgress,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

const VendorInfo = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [newSite, setNewSite] = useState({
    siteId: '',
    serialNumber: '',
    firstUsedDate: '',
    batterySerialNumber: '',
    batteryBankType: '',
    ahCapacity: '',
    manifactureName: '',
    designVoltage: '',
    individualCellVoltage: '',
  });

  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);

  const [openDialog, setOpenDialog] = useState(false);
  const [editingSiteId, setEditingSiteId] = useState(null);

  const columnMappings = {
    siteId: 'Site ID',
    serialNumber: 'Serial Number',
    firstUsedDate: 'First Used Date',
    batterySerialNumber: 'Battery Serial Number',
    batteryBankType: 'Battery Bank Type',
    ahCapacity: 'AH Capacity',
    manifactureName: 'Manufacturer Name',
    designVoltage: 'Design Voltage',
    individualCellVoltage: 'Individual Cell Voltage',
    actions: 'Actions', 
  };

  useEffect(() => {
    const getData = async () => {
      try {
        const result = await fetchManfacurareDetailsBatteryandChargerdetails();
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

  const handleAddSite = () => {
    const newSiteData = {
      id: data.length + 1,
      siteLocation: {
        ...newSite,
      },
      servertime: new Date().toISOString(),
    };

    setData((prevData) => [...prevData, newSiteData]);

    setNewSite({
        siteId: '',
        serialNumber: '',
        firstUsedDate: '',
        batterySerialNumber: '',
        batteryBankType: '',
        ahCapacity: '',
        manifactureName: '',
        designVoltage: '',
        individualCellVoltage: '',
    });

    setOpenDialog(false);
  };

  const handleDialogOpen = (siteId = null) => {
    if (siteId) {
      const siteToEdit = data.find((site) => site.siteId === siteId);
      setNewSite(siteToEdit.siteLocation);
      setEditingSiteId(siteId);
    } else {
      setNewSite({
        siteId: '',
        serialNumber: '',
        firstUsedDate: '',
        batterySerialNumber: '',
        batteryBankType: '',
        ahCapacity: '',
        manifactureName: '',
        designVoltage: '',
        individualCellVoltage: '',
      });
      setEditingSiteId(null);
    }
    setOpenDialog(true);
  };

  const handleDialogClose = () => {
    setOpenDialog(false);
  };

  const handleDelete = async (siteId, serialNumber) => {
    debugger; // Pause execution for debugging
    try {
        console.log("Attempting to delete site with siteId:", siteId, "and serialNumber:", serialNumber);

        const response = await ManufacturerDeleteSite(siteId, serialNumber);

        if (response && response.value !== 0) {
            const updatedData = data.filter((site) => site.siteId !== siteId);

            setData(updatedData);
            alert("Site deleted successfully!");
        } else {
            alert(response.message || "Failed to delete site. SiteId or serialNumber might be incorrect.");
        }
    } catch (error) {
        console.error("Error while deleting site:", error.response || error.message || error);
        alert("Failed to delete site. Please try again later.");
    }
};



  const handleEdit = () => {
    const updatedData = data.map((site) =>
      site.id === editingSiteId
        ? {
            ...site,
            siteId: newSite.siteId,
            serialNumber: newSite.serialNumber,
            firstUsedDate: newSite.firstUsedDate,
            batterySerialNumber: newSite.batterySerialNumber,
            batteryBankType: newSite.batteryBankType,
            ahCapacity: newSite.ahCapacity,
            manifactureName: newSite.manifactureName,
            designVoltage: newSite.designVoltage,
            individualCellVoltage: newSite.individualCellVoltage,
            serverTime: new Date().toISOString(),
          }
        : site
    );
    setData(updatedData);
    setOpenDialog(false);
    setEditingSiteId(null);
  };
  
  

  if (loading) return <CircularProgress />;
  if (error) return <div>Error: {error}</div>;

  const filteredData = data.filter((site) => {
    return site.siteLocation && Object.keys(columnMappings).every(
      (key) => {
        if (key === 'id' || key === 'servertime' || key === 'actions') {
          return true;
        }
        return site.siteLocation[key] !== null && site.siteLocation[key] !== undefined;
      }
    );
  });

  return (
    <Box>
    <Box display="grid" gridTemplateColumns="repeat(2, 1fr)" p={2} gap={2} >
      
      {/* Grid 1: Site ID, Serial Number, Start Date, End Date, Search */}
      <Box display="grid" gridTemplateColumns="repeat(5, 1fr)" gap={2}>
      <Button
            onClick={() => handleDialogOpen()}
            variant="contained"
            color="secondary"
          >
            Add Site
          </Button>
       
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

    <TableContainer component={Paper}>
    <Table>
      <TableHead>
        <TableRow>
          {Object.keys(columnMappings).map((key) => (
            <TableCell key={key}>{columnMappings[key]}</TableCell>
          ))}
        </TableRow>
      </TableHead>
      <TableBody>
        {data.map((site) => (
          <TableRow key={site.id}>
            {Object.keys(columnMappings).map((key) => (
              <TableCell key={key}>
                {key === 'actions' ? (
                  <Box display="flex" gap={1}>
                    <IconButton
                      color="secondary"
                      onClick={() => handleDialogOpen(site.siteId)}
                    >
                      <EditIcon />
                    </IconButton>
                    <IconButton
                      color="secondary"
                      onClick={() => handleDelete(site.siteId, site.serialNumber)}  // Pass both siteId and serialNumber
                    >
                      <DeleteIcon />
                    </IconButton>

                  </Box>
                ) : key === 'serverTime' ? (
                  new Date(site[key]).toLocaleString()
                ) : (
                  site[key]
                )}
              </TableCell>
            ))}
          </TableRow>
        ))}
      </TableBody>
    </Table>
  </TableContainer>

      <Dialog open={openDialog} onClose={handleDialogClose}>
      <DialogTitle>{editingSiteId ? 'Edit Site' : 'Add New Site'}</DialogTitle>
        <DialogContent>
        <TextField
                label="Site ID"
                name="siteId"
                value={newSite.siteId}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="Serial Number"
                name="serialNumber"
                value={newSite.serialNumber}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="First Used Date"
                name="firstUsedDate"
                value={newSite.firstUsedDate}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="Battery Serial Number"
                name="batterySerialNumber"
                value={newSite.batterySerialNumber}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="Battery Bank Type"
                name="batteryBankType"
                value={newSite.batteryBankType}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="AH Capacity"
                name="ahCapacity"
                value={newSite.ahCapacity}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="Manufacturer Name"
                name="manifactureName"
                value={newSite.manifactureName}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="Design Voltage"
                name="designVoltage"
                value={newSite.designVoltage}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
            <TextField
                label="Individual Cell Voltage"
                name="individualCellVoltage"
                value={newSite.individualCellVoltage}
                onChange={handleInputChange}
                margin="normal"
                fullWidth
            />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose} color="primary">
            Cancel
          </Button>
          <Button
            onClick={editingSiteId ? handleEdit : handleAddSite}
            color="primary"
          >
            {editingSiteId ? 'Save' : 'Add'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default VendorInfo;