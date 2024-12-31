package com.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "charger_status")
public class ChargerStatusData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charger_status_id")
    private Long id;

    @Column(name = "input_mains")
    private boolean inputMains;

    @Column(name = "input_fuse")
    private boolean inputFuse;

    @Column(name = "rectifier_fuse")
    private boolean rectifierFuse;

    @Column(name = "filter_fuse")
    private boolean filterFuse;

    @Column(name = "dc_voltage_oln")
    private int dcVoltageOLN;

    @Column(name = "output_fuse")
    private boolean outputFuse;

    @Column(name = "ac_voltage_uln")
    private int acVoltageULN;
   
    @Column(name = "charger_load")
    private boolean chargerLoad;

    @Column(name = "alarm_supply_fuse")
    private boolean alarmSupplyFuse;

    @Column(name = "charger_trip")
    private boolean chargerTrip;

    @Column(name = "output_mccb")
    private boolean outputMccb;

   

    @Column(name = "battery_condition")
    private boolean batteryCondition;

    @Column(name = "test_push_button")
    private boolean testPushButton;

    @Column(name = "reset_push_button")
    private boolean resetPushButton;

    @OneToOne
    @JoinColumn(name = "charger_data_id")
    private ChargerMonitoringData chargerMonitoringData;
    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "server_time", nullable = true, insertable = true, updatable = false)
    private Date serverTime;

    @PrePersist
    protected void onInsert() {
        serverTime = new Date();
    }
}
