package com.bms.hibernate.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.bms.entity.GeneralData;
import com.bms.entity.SiteIdList;
import com.bms.hibernate.util.HibernateUtil;

public class SiteIdLIstService {

	public void saveSiteIdAndDeviceId(GeneralData _generalData) {
		try {
			if (_generalData == null) {
				return;
			}
			String strSiteId = _generalData.getSiteId();
			SiteIdList instSiteIdList = saveOrAddSiteId(strSiteId);
			if (instSiteIdList == null) {
				System.out.println("instSiteIdList is Null");
				return;
			}

//			new SerialNumberListService().save(_generalData.getDeviceData(), instSiteIdList);
		} catch (Exception ex) {
			System.out.println("exception occured in saveSiteIdAndDeviceId method" + ex.fillInStackTrace());
		}

	}
	public SiteIdList saveOrAddSiteId(String strSiteId)
	{

		SiteIdList instSiteIdList=new SiteIdList();
		
		instSiteIdList.setSiteId(strSiteId);
		
		instSiteIdList=saveOrAddSiteId(instSiteIdList);
	  return instSiteIdList;
	
		
	}

	public SiteIdList saveOrAddSiteId(SiteIdList _siteIdList)
	{
		try {
		SiteIdList instSiteIdList= fetchSiteidInstance(_siteIdList);
		if(instSiteIdList==null)
		{
			instSiteIdList=saveSiteId(_siteIdList);
		}
		
		return instSiteIdList;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in saveOrAddSiteId:"+ex.toString());
			return null;
		}
	}
	public SiteIdList saveSiteId(SiteIdList _siteIdList)
	{
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			session.save(_siteIdList);
			 transaction.commit();
			 
			} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}
		finally {
			return _siteIdList;
		}
	}
	public SiteIdList fetchSiteidInstance(SiteIdList _siteIdList) {
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			
			 Criteria criteria = session.createCriteria(SiteIdList.class);
			 criteria.add(Restrictions.eq("siteId", _siteIdList.getSiteId()));
			 List<SiteIdList> lstSiteIdList =  criteria.list();
			 transaction.commit();
			 
			 if(lstSiteIdList.isEmpty())
			 {
				 return null;
			 }
				 
			  return lstSiteIdList.get(0);
			} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				
			}
			e.printStackTrace();
			
			return null;
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
