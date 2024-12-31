import React, { useContext } from "react";
import { Box, Typography, Link } from "@mui/material";
import { ColorModeContext } from "../../theme"; // Import the ColorModeContext

const Footer = () => {
  const colorMode = useContext(ColorModeContext); // Access the colorMode from context

  return (
    <Box
      className="footer"
      sx={{
        textAlign: "center",
        padding: 1,
     
      }}
    >
      <Typography variant="body2">
        <Link
          href="https://www.vajraiot.com"
          target="_blank"
          rel="noopener noreferrer"
          color="primary"
        >
          Powered By Vajra IoT Pvt. Ltd.
        </Link>
      </Typography>
    </Box>
  );
};

export default Footer;
