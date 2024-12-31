import axios from "axios";

const BASE_URL = "http://localhost:51470";



export const fetchAllSiteIds = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getAllSiteIdWithSerialNumbers`);
    return response.data;
  } catch (error) {
    console.error("Error fetching site data:", error);
    throw error;
  }
};
export const fetchCommunicationStatus = async (marginMinutes) => {
  try {
    const response = await axios.get(
      `${BASE_URL}/getCommnStatus`, 
      { params: { marginMinutes } }
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching communication status:", error);
    throw error;
  }
};

export const fetchManufacturerDetails = async (siteId, serialNumber) => {
  try {
    const response = await axios.get(
      `${BASE_URL}/getManufacturerDetailsBySiteIdAndSerialNumber?siteId=${siteId}&serialNumber=${serialNumber}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching manufacturer details:", error);
    throw error;
  }
};
export const fetchDeviceDetails = async (siteId, serialNumber) => {
    try {
      const response = await axios.get(
        `${BASE_URL}/getGeneralDataBySiteIdAndSerialNumber?siteId=${siteId}&serialNumber=${serialNumber}`
      );
      return response.data;
    } catch (error) {
      console.error("Error fetching manufacturer details:", error);
      throw error;
    }
  };

  export const fetchHistoricalBatteryandChargerdetails =async (serialNumber,siteId,strStartDate,strEndDate)=>{
    try{
      const response =await axios.get(
        `${BASE_URL}/getHistoricalStringDataBySiteidAndSerialNumberBetweenDateswithPg?siteId=${siteId}&serialNumber=${serialNumber}
                                                                            &strStartDate=${strStartDate}&strEndDate=${strEndDate}`
      );
      return response.data;
    }
    catch(error){
      console.error("Error fetching Historical String details",error);
      throw error;
    }

    }
    export const fetchDaywiseBatteryandChargerdetails =async (serialNumber,siteId,strStartDate,strEndDate)=>{
      try{
        const response =await axios.get(
          `${BASE_URL}/getDaywiseReports?siteId=${siteId}&serialNumber=${serialNumber}
                                                                              &strStartDate=${strStartDate}&strEndDate=${strEndDate}`
        );
        return response.data;
      }
      catch(error){
        console.error("Error fetching Daywise String details",error);
        throw error;
      }
  
      }
      export const fetchAlarmsBatteryandChargerdetails =async (serialNumber,siteId,strStartDate,strEndDate)=>{
        try{
          const response =await axios.get(
            `${BASE_URL}/getHistoricalAlarmsDataBySiteidAndSerialNumberBetweenDateswithPg?siteId=${siteId}&serialNumber=${serialNumber}
                                                                                &strStartDate=${strStartDate}&strEndDate=${strEndDate}`
          );
          return response.data;
        }
        catch(error){
          console.error("Error fetching Alarms String details",error);
          throw error;
        }
    
        }
        export const fetchMonthlyBatteryandChargerdetails =async (serialNumber,siteId,year,month)=>{
          try{
            const response =await axios.get(
              `${BASE_URL}/getMonthlyReports?siteId=${siteId}&serialNumber=${serialNumber}
                                                                                  &year=${year}&month=${month}`
            );
            return response.data;
          }
          catch(error){
            console.error("Error fetching Alarms String details",error);
            throw error;
          }
      
          }


          export const fetchSiteDetailsBatteryandChargerdetails = async () => {
            try {
              const response = await axios.get(`${BASE_URL}/getAllSiteIdWithSerialNumbers`);
              console.log("Full Response: ", response);  // Log the full response to inspect its structure
              return response.data;
            } catch (error) {
              console.error("Error in fetching site details: ", error);
              throw error;
            }
          };

          export const addSiteLocation = async (siteId, siteLocation) => {
            try {
             
              const payload = {
                siteId: siteId, 
                siteLocation: siteLocation  
              };
          
              // Send the POST request to add a new site location
              const response = await axios.post(`${BASE_URL}/postAddNewLocationToSiteId`, payload);
          
              // Log and return the response
              console.log("Response from addSiteLocation:", response.data);
              return response.data;
            } catch (error) {
              console.error("Error while adding new site location:", error);
              throw error;
            }
          };
          
          export const updateSiteLocation = async (siteId, siteLocation) => {
            try {
              const payload = {
                siteId: siteId,  
                siteLocation: siteLocation 
              };
              const response = await axios.put(`${BASE_URL}/updateSiteLocationToSiteId`, payload);
              console.log("Full Response: ", response);
              return response.data;
            } catch (error) {
              console.error("Error updating site location: ", error);
              throw error;  
            }
          };
          
          
          
          export const deleteSite = async (siteId) => {
            try {
                const api = `${BASE_URL}/deleteSiteLocationToSiteId`;
                const response = await axios.delete(api, {
                    params: { siteId }, 
                });
        
                console.log("Delete Response: ", response.data);
                return response.data;
            } catch (error) {
                console.error("Error deleting site:", error);
                throw error;
            }
        };
        
          

          export const fetchManfacurareDetailsBatteryandChargerdetails = async () => {
            try {
              const response = await axios.get(`${BASE_URL}/getAllManufacturerDetails`);
              console.log("Full Response: ", response);  // Log the full response to inspect its structure
              return response.data;
            } catch (error) {
              console.error("Error in fetching site details: ", error);
              throw error;
            }
          };
          export const ManufacturerDeleteSite = async (siteId, serialNumber) => {
            try {
                const api = `${BASE_URL}/deleteManufacturerDetailsBySiteIdAndSerialNumber`;
                console.log("Making DELETE request to:", api, "with params:", { siteId, serialNumber });
                
                const response = await axios.delete(api, {
                    params: { siteId, serialNumber },
                });
        
                console.log("Delete API Response:", response.data);
                return response.data;
            } catch (error) {
                console.error("Error in ManufacturerDeleteSite API call:", error.response || error.message || error);
                throw error; // Propagate error to handleDelete
            }
        };
        
        

          

           
