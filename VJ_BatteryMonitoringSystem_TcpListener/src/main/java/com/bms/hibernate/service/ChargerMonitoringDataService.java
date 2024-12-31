package com.bms.hibernate.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.DeviceData;
import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.util.HibernateUtil;

public class ChargerMonitoringDataService {
	public ChargerMonitoringData save(GeneralData _generalData,ChargerMonitoringData _batteryChargerMonitoringData) {

		_batteryChargerMonitoringData.setGeneralData(_generalData);
		Transaction transaction = null;
		
//		_batteryMonitoringData.setAmbientTemperature(2.35f);

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			
			transaction = session.beginTransaction();

			
			session.saveOrUpdate(_batteryChargerMonitoringData);
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally{
			return _batteryChargerMonitoringData;
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
