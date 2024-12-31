package com.bms.hibernate.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.bms.entity.GeneralData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.util.HibernateUtil;

public class GeneralDataService {

	public GeneralData save(GeneralData _generalData) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction

			transaction = session.beginTransaction();

			// save the student object
			// session.persist(_batteryMonitoringData);
			session.save(_generalData);
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			return _generalData;
		}

	}

	public void parsedOk(Rawdata rawdata) {
		if (rawdata.getId() == null) {
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
		} finally {

		}
	}

	public void parsedOk(Rawdata rawdata, Long ftm_packet_id) {
		if (rawdata.getId() == null) {
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
		} finally {

		}
	}

	
	public GeneralData getLatestGeneralData() {
		
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			
			String strQuery="from GeneralData g order by g.serverTime desc";
			Query query=session.createQuery(strQuery);  
			query.setFirstResult(0);  
			query.setMaxResults(1);
			
			List<GeneralData> lstGeneralData=query.list();		
			
			transaction.commit();
			
			if(!lstGeneralData.isEmpty())
			{
				return lstGeneralData.get(0);
			}
			else
			{
				return null;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		} finally {

		}
	}

}
