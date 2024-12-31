import React from 'react';
import { Box, Typography } from '@mui/material';
import './Header.css';

const Header = () => {
  return (
    <Box >
      <Typography className='header'>
      <h1 className='header-title'>Battery and Charger monitoring System</h1>
      </Typography>
    </Box>
  );
};

export default Header;
