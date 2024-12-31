package com.bms.hibernate.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bms.entity.CellVoltageTemperatureData;
import com.bms.entity.DeviceData;
import com.bms.entity.Rawdata;
import com.bms.hibernate.util.HibernateUtil;


public class CellVoltageTemperatureDataService {
	
	
	
		public void save(DeviceData _deviceData) {
			List<CellVoltageTemperatureData>  _lstCellVoltageData=_deviceData.getCellVoltageTemperatureData();
			
		//	_lstCellVoltageData=_batteryMonitoringData.getCellVoltageData();
			for(CellVoltageTemperatureData inst:_lstCellVoltageData)
			{

				inst.setDeviceData(_deviceData);
			}
			
			Transaction transaction = null;
			

	
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				
				transaction = session.beginTransaction();

				

				// save the student object
				//session.persist(_batteryMonitoringData);
				System.out.println("_lstCellVoltageData size is:"+_lstCellVoltageData.size());
				
					
					for ( int i=0; i<_lstCellVoltageData.size(); i++ ) {
						CellVoltageTemperatureData inst2=_lstCellVoltageData.get(i);
					    session.save(inst2);
					    if ( i % 20 == 0 ) { //20, same as the JDBC batch size
					        //flush a batch of inserts and release memory:
					        session.flush();
					        session.clear();
					    }
					}
				
				
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

