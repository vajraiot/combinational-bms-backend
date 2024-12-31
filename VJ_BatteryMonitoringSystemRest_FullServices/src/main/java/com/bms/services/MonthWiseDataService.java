package com.bms.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.entity.DeviceData;
import com.bms.pojo.DayWiseDataDTO;
import com.bms.pojo.MonthWiseDataDTO;
import com.bms.repositypck.DeviceDataRepos;
import com.bms.repositypck.SiteIdListRepos;
import com.bms.utilities.ApplicationUtility;
import com.bms.utilities.Commonutility;
import com.bms.utilities.Commonutility.StartAndEndTimeInDate;

import lombok.extern.slf4j.Slf4j;

public interface MonthWiseDataService {

    List<DayWiseDataDTO> getDayWiseReport(String siteId, String serialNumber, String strStartDate, String strEndDate);

    List<MonthWiseDataDTO> generateMonthlyReport(String siteId, String serialNumber, String year, String month);

    @Service
 
    @Slf4j
    public class MonthWiseDataServiceImpl implements MonthWiseDataService {

        @Autowired
        private SiteIdListRepos siteIdListRepos;

        @Autowired
        private DeviceDataRepos deviceDataRepos;

        @Override
        public List<DayWiseDataDTO> getDayWiseReport(String siteId, String serialNumber, String strStartDate, String strEndDate) {
            List<DayWiseDataDTO> resultList = new ArrayList<>();
            try {
                // Convert the string dates to Date objects
                Date startDate = Commonutility.convertStringDateToDateFormate(strStartDate);
                Date endDate = Commonutility.convertStringDateToDateFormate(strEndDate);

                // Get all dates between the given range
                List<Date> datesInRange = Commonutility.getDaysBetweenDates(startDate, endDate);
                Long id = 0L;

                // Loop through all dates in the range and fetch day-wise data
                for (Date currentDate : datesInRange) {
                    DayWiseDataDTO dayData = getDayWiseReport(siteId, serialNumber, currentDate);
                    if (dayData != null) {
                        dayData.setId(id++);
                        resultList.add(dayData);
                    }
                }
            } catch (Exception e) {
                log.error("Exception occurred while processing getDayWiseReport: {}", e.getMessage(), e);
            }
            return resultList;
        }

        public DayWiseDataDTO getDayWiseReport(String siteId, String serialNumber, Date date) {
            try {
                // Get the start and end times for the current date
                StartAndEndTimeInDate startAndEndTimes = Commonutility.getStartAndEndDateTimeInDate(date);

                // Fetch the first and last record for the given date range
                Optional<DeviceData> firstRecord = deviceDataRepos.findTop1BySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdAsc(
                        serialNumber, siteId, startAndEndTimes.getStartTimeInDate(), startAndEndTimes.getEndTimeInDate());

                Optional<DeviceData> lastRecord = deviceDataRepos.findTop1BySerialNumberAndGeneralDataSiteIdAndGeneralDataPacketDateTimeBetweenOrderByIdDesc(
                        serialNumber, siteId, startAndEndTimes.getStartTimeInDate(), startAndEndTimes.getEndTimeInDate());

                if (!firstRecord.isPresent() || !lastRecord.isPresent()) {
                    return null;
                }

                // Process the data for the day and return the result
                return ApplicationUtility.processingDayWiseData(firstRecord.get(), lastRecord.get());
            } catch (Exception e) {
                log.error("Exception occurred while processing single getDayWiseReport: {}", e.getMessage(), e);
                return null;
            }
        }
        

        @Override
        public List<MonthWiseDataDTO> generateMonthlyReport(String siteId, String serialNumber, String year, String month) {
            List<MonthWiseDataDTO> monthlyReports = new ArrayList<>(); // Create a list to hold monthly reports

            try {
                // Create a single month report
                MonthWiseDataDTO monthWiseDataDTO = new MonthWiseDataDTO();
                monthWiseDataDTO.setMonth(year + "-" + month); // Set month label

                // Get the start and end dates for the month
                Date startDate = Commonutility.getStartDateOfMonth(year, month);
                Date endDate = Commonutility.getEndDateOfMonth(year, month);

                String strStartDate = Commonutility.formatDateToString(startDate);
                String strEndDate = Commonutility.formatDateToString(endDate);

                log.info("Generating monthly report for Site ID: {}, Serial Number: {}, Year: {}, Month: {}", siteId, serialNumber, year, month);

                // Get all dates in the month
                List<Date> datesInMonth = Commonutility.getDaysBetweenDates(startDate, endDate);

                // Accumulate data for each day in the month
                for (Date currentDate : datesInMonth) {
                    // Fetch day-wise data for the current date
                    DayWiseDataDTO dailyReport = getDayWiseReport(siteId, serialNumber, currentDate);

                    // Skip if no data for the current date
                    if (dailyReport == null) {
                        log.info("No data found for date: {}", Commonutility.formatDateToString(currentDate));
                        continue;
                    }

                    // Accumulate day-wise data into the monthly totals
                    monthWiseDataDTO.accumulate(dailyReport);
                }

                // After accumulating, clear the dayWiseReports to exclude them from the final result
                monthWiseDataDTO.setDayWiseReports(new ArrayList<>()); // Set it to an empty list

                // Add the accumulated monthly data to the list
                monthlyReports.add(monthWiseDataDTO);

            } catch (Exception e) {
                log.error("Exception occurred while generating the monthly report: {}", e.getMessage(), e);
            }

            return monthlyReports; // Return the list of monthly reports
        }


    }
}
