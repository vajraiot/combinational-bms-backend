import { Box, Typography,useTheme } from '@mui/material'
import React from 'react'
import { tokens } from "../theme.js"

const CellsData = () => {
    const theme = useTheme();
    const colors = tokens(theme.palette.mode);
  return (
    <Box
    display="flex"
    justifyContent="space-around"
    alignContent="center"
    gap="2px"
    border="1px solid black"
    height="25px"
    >
        <Box>
            <Typography sx={{color: colors.greenAccent[500]}}>Pictorial</Typography>
        </Box>
        <Box>
            <Typography sx={{color: colors.greenAccent[500]}}>Line Graph</Typography>
        </Box>
        <Box>
            <Typography sx={{color: colors.greenAccent[500]}}>Tabular</Typography>
        </Box>
    </Box>
  )
}

export default CellsData