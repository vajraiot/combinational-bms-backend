package com.bms.miscellaneous;

public interface TrackingFTMSData {

	public Long getId();

	public String getLmpRawdata();

	public String getHeader();

	public String getImeiNumber();

	public double getLatitude();

	public String getLatitudeDirection();

	/*
	 * private Long id;
	 * 
	 * 
	 * 
	 * private String lmpRawdata;
	 * 
	 * 
	 * private String header;
	 * 
	 * 
	 * 
	 * private String imeiNumber;
	 * 
	 * 
	 * private double latitude;
	 * 
	 * 
	 * private String latitudeDirection;
	 * 
	 * 
	 * private double longnitude;
	 * 
	 * @Column(name="longnitude_direction",nullable=true) private String
	 * longnitudeDirection;
	 * 
	 * 
	 * @Column(name="lh_packet_status", nullable=true) private String
	 * lhPacketStatus;
	 * 
	 * @Column(name="packet_date_time", nullable=true) private Date packetDateTime;
	 * 
	 * // -------------------------------------------
	 * 
	 * @Column(name="sim_details", nullable=true) private String simDetails;
	 * 
	 * @Column(name="device_iP", nullable=true) private String deviceIP;
	 * 
	 * @Column(name="sim_internationl_number", nullable=true) private String
	 * sIMInternationlNumber;
	 * 
	 * @Column(name="firmware_version", nullable=true) private String
	 * firmwareVersion;
	 * 
	 * 
	 * @Column(name="vehicle_mode", nullable=true) private String vehicleMode;
	 * 
	 * 
	 * @Column(name="vehicle_reg", nullable=true) private String vehicleReg;
	 * 
	 * 
	 * @Column(name="gps_fix", nullable=true) private Boolean gpsFix;
	 * 
	 * 
	 * @Column(name="speed", nullable=true) //ex;25.6 private float speed;
	 * 
	 * 
	 * @Column(name="numberof_satelites", nullable=true) private int
	 * numberofSatelites;
	 * 
	 * 
	 * @Column(name="altitude", nullable=true) private float altitude;
	 * 
	 * 
	 * @Column(name="network_operator_name", nullable=true) private String
	 * networkOperatorName;
	 * 
	 * @Column(name="gsm_signal_strength", nullable=true) private int
	 * gsmSignalStrength;
	 * 
	 * 
	 * @Column(name="rfid", nullable=true) private String rfid;
	 * 
	 * // ***********************fms Data************************
	 * 
	 * @Column(name="fms_rawdata", columnDefinition="TEXT" , nullable=true) private
	 * String fmsRawdata;
	 * 
	 * 
	 * @Column(name="version", nullable=true) private String version;
	 * 
	 * @Column(name="siteid", nullable=true) private String siteid;
	 * 
	 * @Column(name="dg_mnfg_id", nullable=true) private String dgMnfgId;
	 * 
	 * @Column(name="serial_number", nullable=true) private String serialNumber;
	 * 
	 * @Column(name="fuel_level_mm", nullable=true) private String fuelLevelMm;
	 * 
	 * 
	 * @Column(name="fuel_level_ltrs", nullable=true) private float fuelLevelLtrs;
	 * 
	 * @Column(name="fuel_level_perc", nullable=true) private String fuelLevelPerc;
	 * 
	 * @Column(name="status_bits", nullable=true) private String statusBits;
	 * 
	 * @Column(name="dg_on_pfc_status", nullable=true) private boolean
	 * dgOnPfcStatus;
	 * 
	 * @Column(name="vibration_status", nullable=true) private boolean
	 * vibrationStatus;
	 * 
	 * @Column(name="dg_on_detected", nullable=true) private boolean dgOnDetected;
	 * 
	 * 
	 * @Column(name="low_fuel_level", nullable=true) private boolean lowFuelLevel;
	 * 
	 * @Column(name="fuel_theft_active", nullable=true) private boolean
	 * fuelTheftActive;
	 * 
	 * @Column(name="fuel_refill_active", nullable=true) private boolean
	 * fuelRefillActive;
	 * 
	 * 
	 * @Column(name="battery_voltage", nullable=true) private float batteryVoltage;
	 * 
	 * @Column(name="cumulative_dg_run_mnts", nullable=true) private String
	 * cumulativeDgRunMnts;
	 * 
	 * @Column(name="dg_date_time", nullable=true) private String dgDateTime;
	 * 
	 * 
	 * @Column(name="total_consumption_in_ltrs", nullable=true) private float
	 * totalConsumptionInLtrs;
	 * 
	 * @Column(name="total_fule_filled_in_ltrs", nullable=true) private float
	 * totalFuleFilledInLtrs;
	 * 
	 * @Column(name="total_fillings", nullable=true) private float totalFillings;
	 * 
	 * 
	 * @Column(name="total_fuel_stolen_in_ltrs", nullable=true) private float
	 * totalFuelstolenInLtrs;
	 * 
	 * @Column(name="total_theft", nullable=true) private float totalTheft;
	 * 
	 * @Column(name="device1", nullable=true) private boolean device1;
	 * 
	 * 
	 * 
	 * @Column(name="device2", nullable=true) private boolean device2;
	 * 
	 * 
	 * 
	 * @Column(name="device3", nullable=true) private boolean device3;
	 * 
	 * 
	 * @Column(name="device4", nullable=true) private boolean device4;
	 */
}
