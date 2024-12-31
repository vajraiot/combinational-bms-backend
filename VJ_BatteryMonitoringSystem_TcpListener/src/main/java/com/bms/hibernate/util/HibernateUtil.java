package com.bms.hibernate.util;

import java.util.Properties;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.bms.entity.BMSAlarms;
import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.ChargerStatusData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.entity.SerialNumberList;
import com.bms.entity.SiteIdList;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		ResourceBundle rb = ResourceBundle.getBundle("hibernate");

		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, rb.getString("driverClassName"));
				settings.put(Environment.URL, rb.getString("url"));
				settings.put(Environment.USER, rb.getString("username"));
				settings.put(Environment.PASS, rb.getString("password"));
				settings.put(Environment.DIALECT, rb.getString("database_platform"));
				settings.put(Environment.SHOW_SQL, rb.getString("show-sql"));
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, rb.getString("ddl-auto").trim());
				settings.put(Environment.FORMAT_SQL,rb.getString("format-sql"));
				
				
				
				configuration.setProperties(settings);
//                configuration.addAnnotatedClass(Student.class);
				
				configuration.addAnnotatedClass(Rawdata.class);
				
				//configuration.addAnnotatedClass(RawdataParsed.class);
				
				configuration.addAnnotatedClass(DeviceData.class);
				configuration.addAnnotatedClass(BMSAlarms.class);
				
				configuration.addAnnotatedClass(ChargerMonitoringData.class);
				configuration.addAnnotatedClass(ChargerStatusData.class);
				
				configuration.addAnnotatedClass(BMSAlarms.class);
				
				
				configuration.addAnnotatedClass(CellVoltageTemperatureData.class);
				configuration.addAnnotatedClass(GeneralData.class);
				configuration.addAnnotatedClass(SiteIdList.class);
				configuration.addAnnotatedClass(SerialNumberList.class);
				
				/*************************************/
	
				configuration.addAnnotatedClass(Rawdata.class);
				
				
				
				/*************************************/
				

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				 System.err.println("Initial SessionFactory creation failed." + e);
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
