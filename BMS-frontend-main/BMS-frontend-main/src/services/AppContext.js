import React, { createContext, useState, useEffect } from "react";
import { fetchAllSiteIds, fetchDeviceDetails, fetchManufacturerDetails } from "./apiService";

import React, { createContext, useState, useEffect } from "react";

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
  const [siteOptions, setSiteOptions] = useState([]); // Options for site IDs
  const [serialNumberOptions, setSerialNumberOptions] = useState([]); // Serial number options for the selected site
  const [siteId, setSiteId] = useState(""); // Selected Site ID
  const [serialNumber, setSerialNumber] = useState(""); // Selected Serial Number
  const [data, setData] = useState([]); // Generic state for device data
  const [Mdata, setMdata] = useState(null); // Manufacturer details
  const[location,setLocation]=useState(null);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [year, setYear] = useState('');
  const [month, setMonth] = useState('');


  // Fetch site options on load
  useEffect(() => {
    const fetchOptions = async () => {
      try {
        const data = await fetchAllSiteIds();
        setSiteOptions(data);
        console.log("site options"+data)
      } catch (error) {
        console.error("Error fetching site options:", error);
      }
    };
    fetchOptions();
  }, []);

  const handleSiteIdChange = (selectedSiteId) => {
  
    setSiteId(selectedSiteId); // Update siteId
    setSerialNumber(""); // Clear previous serialNumber

    const selectedSite = siteOptions.find((site) => site.siteId === selectedSiteId);

    if (selectedSite) {
      // Populate Serial Number options dynamically
      setSerialNumberOptions(
        selectedSite.lstSerialNumberList.map((item) => item.serialNumber)
      );
      setLocation(selectedSite.siteLocation)
    } else {
      setSerialNumberOptions([]); // Clear if no site matches
    }
  };

  const contextValue = {
    siteOptions,
    serialNumberOptions,
    siteId,
    serialNumber,
    setSiteId: handleSiteIdChange,
    setSerialNumber,
    data,
    setData,
    Mdata,
    setMdata,
    location,
    startDate,
    setStartDate,
    endDate,
    setEndDate,
    year,
    setYear,
    month,
    setMonth
  };

  return <AppContext.Provider value={contextValue}>{children}</AppContext.Provider>;
};

