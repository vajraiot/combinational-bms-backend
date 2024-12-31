package com.bms.hibernate.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bms.entity.ChargerMonitoringData;
import com.bms.entity.ChargerStatusData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.util.HibernateUtil;

public class ChargerStatusService {
	public ChargerStatusData save(ChargerMonitoringData _chargermonitoringData) {
		ChargerStatusData _chargerStatus=_chargermonitoringData.getChargerStatusData();
		Transaction transaction = null;
		
		_chargerStatus.setChargerMonitoringData(_chargermonitoringData);

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			
			transaction = session.beginTransaction();

			

			// save the student object
			//session.persist(_batteryMonitoringData);
			session.save(_chargerStatus);
			// commit transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally{
			return _chargerStatus;
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
