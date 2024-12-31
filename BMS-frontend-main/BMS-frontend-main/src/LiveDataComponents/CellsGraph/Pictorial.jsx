import { Box} from '@mui/material'
import React from 'react'
import Grid from '@mui/material/Grid2';
import CellLayout from './CellLayout';
import {CellThresholdValues} from '../../enums/ThresholdValues'

const Pictorial = ({ cellDataList, chargingStatus }) =>  {
  return (
    <Box style={{ height: '200px', overflowY: 'auto' , flexGrow: 1 }}>
      <Grid container spacing={2} justifyContent="flex-start">
        {cellDataList.map((cell) => (
          <Grid item key={cell.id}>
            <CellLayout
              cellData={cell}
              thresholds={CellThresholdValues}
              // chargingStatus={chargingStatus}
              chargingStatus={true}
            />
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
export default Pictorial