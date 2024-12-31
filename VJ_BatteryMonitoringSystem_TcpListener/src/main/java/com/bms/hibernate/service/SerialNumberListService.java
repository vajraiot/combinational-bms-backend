package com.bms.hibernate.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bms.entity.DeviceData;
import com.bms.entity.SerialNumberList;
import com.bms.entity.SiteIdList;
import com.bms.hibernate.util.HibernateUtil;

public class SerialNumberListService {

	public void save(List<DeviceData> _lstDeviceData, SiteIdList _siteIdList) {

		if(!_lstDeviceData.isEmpty())
		for (int i = 0; i < _lstDeviceData.size(); i++) {
			DeviceData instDeviceData = _lstDeviceData.get(i);
			if (instDeviceData == null) {
				continue;
			}
			String strserialNumber = instDeviceData.getSerialNumber();
			SerialNumberList instSerialNumberList = SerialNumberList.builder().siteIdList(_siteIdList)
					.serialNumber(strserialNumber).build();

			SerialNumberList receivedSerialNumberList = fetchSerialNumberList(instSerialNumberList);
			if(receivedSerialNumberList==null)
			{
				saveSerialNumberList(instSerialNumberList);
			}
		}

	}
	
	public SerialNumberList fetchSerialNumberList(SerialNumberList _serialNumberList)
	{
		String strserialNumber=_serialNumberList.getSerialNumber();
		SiteIdList _siteIdList=_serialNumberList.getSiteIdList();
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(SerialNumberList.class);
			 criteria.add(Restrictions.eq("serialNumber",strserialNumber ));
			 criteria.add(Restrictions.eq("siteIdList", _siteIdList));
			 
			 List<SerialNumberList> lstSerialNumberList =  criteria.list();
			 
			 transaction.commit();
			 
			 if(lstSerialNumberList.isEmpty())
			 {
				 return null;
			 }
				 
			  return lstSerialNumberList.get(0);
			} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				
			}
			e.printStackTrace();
			
			return null;
		} 

	}
	
	public SerialNumberList saveSerialNumberList(SerialNumberList _serialNumberList)
	{
			Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.save(_serialNumberList);
			 transaction.commit();
			 
			} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}
		finally {
			return _serialNumberList;
		}
		
	}
	
}
