import React, { useState, useEffect, useContext } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";
import { fetchCommunicationStatus } from "../../services/apiService";
import { Box, Grid, Dialog, DialogTitle, DialogContent, DialogActions, Button, Typography, AppBar, Toolbar, IconButton } from "@mui/material";
import "leaflet/dist/leaflet.css";
import DashBoardBar from "../../components/DashBoardBar/DashBoardBar";
import { AppContext } from "../../services/AppContext";
import L from "leaflet"; // Import leaflet for custom icons

const Dashboard = () => {
  const [selectedStatus, setSelectedStatus] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);
  const [data, setData] = useState([]);
  const [data1, setData1] = useState([]);
  const [data2, setData2] = useState([]);
  const [marginMinutes, setMarginMinutes] = useState(15);
  const [mapMarkers, setMapMarkers] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Fetching communication status data
        const data = await fetchCommunicationStatus(marginMinutes);
        console.log("Fetched Communication Data:", data);
        setData(data);
        let communicatingCount = 0;
        let nonCommunicatingCount = 0;
        let socCount = 0;
        let temperatureHighCount = 0;
        let stringCurrentHighCount = 0;
        let stringVoltageHighCount = 0;
        let stringVoltageLowCount = 0;
  
        data.forEach(item => {
          if (item.statusType === 1) communicatingCount++;
          else if (item.statusType === 0) nonCommunicatingCount++;
  
          const bmsalarms = item?.generalData?.deviceData[0]?.bmsalarms;
          if (bmsalarms) {
            const transformedBmsAlarms = [
              { name: "String Voltage High", value: bmsalarms?.stringVoltageLHN ? 2 : 0 },
              { name: "String Voltage Low", value: bmsalarms?.stringVoltageLHN ? 1 : 0 },
              { name: "SOC", value: bmsalarms?.socLN ? true : false },
              { name: "Ambient Temperature High", value: bmsalarms?.ambientTemperatureHN ? true : false },
              { name: "String Current High", value: bmsalarms?.stringCurrentHN ? true : false },
            ];
  
            transformedBmsAlarms.forEach(alarm => {
              if (alarm.name === "SOC" && alarm.value === true) socCount++;
              if (alarm.name === "Ambient Temperature High" && alarm.value === true) temperatureHighCount++;
              if (alarm.name === "String Current High" && alarm.value === true) stringCurrentHighCount++;
              if (alarm.name === "String Voltage High" && alarm.value === 2) stringVoltageHighCount++;
              if (alarm.name === "String Voltage Low" && alarm.value === 1) stringVoltageLowCount++;
            });
          }
        });
  
        const transformedData1 = [
          { name: "Communicating", value: communicatingCount },
          { name: "Non-Communicating", value: nonCommunicatingCount },
        ];
        setData1(transformedData1);
  
        const transformedData2 = [
          { name: "SOC Count", value: socCount },
          { name: "Temperature High Count", value: temperatureHighCount },
          { name: "String Current High Count", value: stringCurrentHighCount },
          { name: "String Voltage High Count", value: stringVoltageHighCount },
          { name: "String Voltage Low Count", value: stringVoltageLowCount },
        ];
        setData2(transformedData2);
  
        // Fetching site coordinates for markers
      
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
  
    fetchData();
  }, [marginMinutes]);
  

  const handlePieClick = (data) => {
    setSelectedStatus(data.name);
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setSelectedStatus(null);
  };

  // Gradient colors for pie charts
  const COLORS = ["#28a745", "#f39c12", "#17a2b8", "#6c757d", "#007bff", "#d9534f"];

  return (
    <Box className="dashboard-container" sx={{ padding: 1 }}>
      <DashBoardBar />
      <Grid container spacing={3}>
        {/* Left Side - Map */}
        <Grid item xs={12} md={8}>
          <Box my={2}>
            <Box
              className="map-container"
              sx={{
                height: 450,
                border: "2px solid #ccc",
                borderRadius: "8px",
                overflow: "hidden",
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
              }}
            >
               <MapContainer center={[20.5937, 78.9629]} zoom={5} style={{ height: "500px", width: "100%" }}>
            <TileLayer
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {mapMarkers.map((site) => (
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
            </Box>
          </Box>
        </Grid>

        {/* Right Side - Pie Charts */}
        <Grid item xs={12} md={4}>
          <Box my={4}>
            <Grid container spacing={2} direction="column" alignItems="center">
              {/* Communication Status Pie Chart */}
              <Grid item>
                <Box display="flex" justifyContent="center" alignItems="center">
                  <PieChart width={200} height={200}>
                    <Pie
                      data={data1}
                      dataKey="value"
                      nameKey="name"
                      cx="50%"
                      cy="50%"
                      outerRadius={80}
                      label
                      onClick={handlePieClick}
                    >
                      {data1.map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                      ))}
                    </Pie>
                    <Tooltip />
                  </PieChart>
                  <Box ml={1}>
                    {data1.map((entry, index) => (
                      <Box key={index} display="flex" alignItems="center">
                        <Box width={10} height={10} bgcolor={COLORS[index % COLORS.length]} borderRadius="50%" mr={1} />
                        <Typography variant="body2">{entry.name}</Typography>
                      </Box>
                    ))}
                  </Box>
                </Box>
              </Grid>

              {/* BMS Alarms Pie Chart */}
              <Grid item>
                <Box display="flex" justifyContent="center" alignItems="center">
                  {data2.length > 0 && !data2.every(entry => entry.value === 0) ? (
                    <PieChart width={200} height={200}>
                      <Pie
                        data={data2}
                        dataKey="value"
                        nameKey="name"
                        cx="50%"
                        cy="50%"
                        outerRadius={80}
                        label
                        onClick={handlePieClick}
                      >
                        {data2.map((entry, index) => (
                          <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                        ))}
                      </Pie>
                      <Tooltip />
                    </PieChart>
                  ) : (
                    <Typography variant="body1">No data available for BMS alarms.</Typography>
                  )}
                  <Box ml={1}>
                    {data2.map((entry, index) => (
                      <Box key={index} display="flex" alignItems="center">
                        <Box width={10} height={10} bgcolor={COLORS[index % COLORS.length]} borderRadius="50%" mr={1} />
                        <Typography variant="body2">{entry.name}</Typography>
                      </Box>
                    ))}
                  </Box>
                </Box>
              </Grid>
            </Grid>
          </Box>
        </Grid>
      </Grid>

      {/* Dialog */}
      <Dialog open={openDialog} onClose={handleCloseDialog} fullWidth maxWidth="sm">
        <DialogTitle>Details for {selectedStatus}</DialogTitle>
        <DialogContent>
          <Typography variant="body1" gutterBottom>
            Detailed information about <strong>{selectedStatus}</strong> will be displayed here.
          </Typography>
          <Typography variant="body2">Add more details as needed...</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog} color="primary">
            Close
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default Dashboard;
