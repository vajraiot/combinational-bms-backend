package com.bms.hibernate.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bms.entity.BMSAlarms;
import com.bms.entity.DeviceData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.util.HibernateUtil;


public class BMSAlarmsService {
	
	
	
		public BMSAlarms save(DeviceData _deviceData) {
			BMSAlarms _bMSAlarms=_deviceData.getBMSAlarms();
			Transaction transaction = null;
			
			_bMSAlarms.setDeviceData(_deviceData);
	
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				
				transaction = session.beginTransaction();

				

				// save the student object
				//session.persist(_batteryMonitoringData);
				session.save(_bMSAlarms);
				// commit transaction
				transaction.commit();
				
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			finally{
				return _bMSAlarms;
			}
			
		}
		
		public void parsedOk(Rawdata rawdata)
		{
			if(rawdata.getId()==null)
			{
				return;
			}
			Transaction transaction = null;
			
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();

				
				rawdata.setParsed(true);
				// save the student object
				session.update(rawdata);
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			finally{
				
			}
		}

		public void parsedOk(Rawdata rawdata,Long ftm_packet_id)
		{
			if(rawdata.getId()==null)
			{
				return;
			}
			Transaction transaction = null;
			
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();

				
				rawdata.setParsed(true);
			
				// save the student object
				session.update(rawdata);
				// commit transaction
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			finally{
				
			}
		}

}

