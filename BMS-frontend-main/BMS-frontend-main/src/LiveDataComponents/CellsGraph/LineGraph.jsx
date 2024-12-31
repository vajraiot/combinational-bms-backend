import React from 'react';

import { ResponsiveLine } from "@nivo/line";
import { useTheme } from "@mui/material";
import { tokens } from "../../theme";

const LineGraph = ({ data }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  // Prepare data for Voltage and Temperature
  const chartData = [
    {
      id: "Voltage",
      color: colors.greenAccent[500],
      data: data.map((cell) => ({
        x: `Cell ${cell.cellNumber}`,
        y: cell.cellVoltage, // Keep original voltage value
        raw: cell.cellVoltage, // Store raw value for tooltip
      })),
    },
    {
      id: "Temperature",
      color: colors.blueAccent[500],
      data: data.map((cell) => ({
        x: `Cell ${cell.cellNumber}`,
        y: cell.cellTemperature,
      })),
    },
  ];

  return (
    <div style={{ height: "400px" }}>
      <ResponsiveLine
        data={chartData}
        margin={{ top: 50, right: 60, bottom: 50, left: 60 }}
        xScale={{ type: "point" }}
        yScale={{
          type: "linear",
          min: 0,
          max: 50,
          domain: [0, 50], // Explicitly set the domain for temperature
        }}
        axisTop={null}
        axisRight={{
          orient: "right",
          legend: "Temperature (°C)",
          legendOffset: 40,
          legendPosition: "middle",
          tickValues: 5,
        }}
        axisLeft={null} // Hide Voltage axis
        axisBottom={{
          orient: "bottom",
          legend: "Cell Number",
          legendOffset: 36,
          legendPosition: "middle",
        }}
        theme={{
          // ... (rest of the theme configuration)
        }}
        colors={({ id }) =>
          id === "Voltage" ? colors.greenAccent[500] : colors.blueAccent[500]
        }
        pointSize={10}
        pointColor={{ theme: "background" }}
        pointBorderWidth={2}
        pointBorderColor={{ from: "serieColor" }}
        useMesh={true}
        tooltip={({ point }) => {
          const { id, data } = point;
          const voltage = id === "Voltage" ? data.raw : null;
          const temperature = data.y;

          return (
            <div
              style={{
                background: colors.primary[500],
                color: "#fff",
                padding: "5px",
                borderRadius: "3px",
              }}
            >
              <strong>Temperature:</strong> {temperature}°C<br />
              {voltage && (
                <>
                  <strong>Voltage:</strong> {voltage}V
                </>
              )}
            </div>
          );
        }}
        legends={[
          // ... (legends configuration)
        ]}
      />
    </div>
  );
};

export default LineGraph;


// Usage Example
// Pass the API data to the component
// import CellLineChart from './CellLineChart';
// <CellLineChart data={cellVoltageTemperatureData} />
