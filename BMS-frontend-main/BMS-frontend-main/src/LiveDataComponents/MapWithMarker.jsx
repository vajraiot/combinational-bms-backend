import React from "react";
import {
  GoogleMap,
  Marker,
  InfoWindow,
  useLoadScript,
} from "@react-google-maps/api";
import { Card, CardContent, Typography } from "@mui/material";
import { Box } from "@mui/system";

// Map container style
import React from "react";
import { GoogleMap, Marker, InfoWindow, useLoadScript } from "@react-google-maps/api";
import { Card, CardContent, Typography } from "@mui/material";

const containerStyle = {
  width: "100%",
  height: "200px",
};

// Default zoom level for the map
const defaultZoom = 10;

const MapWithMarker = ({ locationName, latitude, longitude, vendorName, batteryAHCapacity }) => {
  const [isInfoWindowOpen, setIsInfoWindowOpen] = React.useState(true);

  // Load the Google Maps API
  // const { isLoaded, loadError } = useLoadScript({
  //   googleMapsApiKey: process.env.AIzaSyDRsvO4B8wU4AtMjhgRkjRx0YVdrfwouN4, // Use environment variable
  // });
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: "AIzaSyDRsvO4B8wU4AtMjhgRkjRx0YVdrfwouN4", // Replace with your Google Maps API key
  });

  // if (loadError) return <p>Error loading map</p>;
  if (!isLoaded) return <p>Loading map...</p>;

  return (
    <GoogleMap
      mapContainerStyle={containerStyle}
      center={{
        lat: latitude,
        lng: longitude,
      }}
      zoom={defaultZoom}
    >
      {/* Marker */}
      <Marker
        position={{
          lat: latitude,
          lng: longitude,
        }}
        onMouseOver={() => setIsInfoWindowOpen(true)}
        onMouseOut={() => setIsInfoWindowOpen(false)}
      >
        {/* Info Window */}
        {isInfoWindowOpen && (
          <InfoWindow
            position={{
              lat: latitude,
              lng: longitude,
            }}
          >
            <Card
              sx={{
                width: 200,
                boxShadow: 3,
                padding: 1,
                backgroundColor: "#f5f5f5",
              }}
            >
              <CardContent>
                <Typography
                  variant="h6"
                  component="div"
                  sx={{
                    fontSize: 14,
                    fontWeight: "bold",
                    color: "#333",
                    marginBottom: 1,
                  }}
                >
                  {locationName}
                </Typography>
                <Typography variant="body2" sx={{ color: "#555" }}>
                  <b>Vendor:</b> {vendorName}
                </Typography>
                <Typography variant="body2" sx={{ color: "#555" }}>
                  <b>Battery:</b> {batteryAHCapacity} AH
                </Typography>
              </CardContent>
            </Card>
          </InfoWindow>
        )}
      </Marker>
    </GoogleMap>
  );
};

export default MapWithMarker;



