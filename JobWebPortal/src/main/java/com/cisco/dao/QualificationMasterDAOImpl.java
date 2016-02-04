package com.cisco.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cisco.models.QualificationMaster;;

@Repository
@EnableTransactionManagement
public class QualificationMasterDAOImpl implements QualificationMasterDAO {

  private static final Logger logger = LoggerFactory.getLogger(QualificationMasterDAOImpl.class);

  @Autowired
  private SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sf) {
    this.sessionFactory = sf;
  }

  @Override
  @Transactional
  public long addQualificationMaster(QualificationMaster p) {

    return 0L;
  }

  @Override
  public void updateQualificationMaster(QualificationMaster p) {

  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional
  public List<QualificationMaster> getListOfQualificationMaster() {
    List<QualificationMaster> list = null;
    try {
      Session session = this.sessionFactory.getCurrentSession();
      Query query = session.createQuery("from QualificationMaster");
      list = query.list();
    } catch (Exception e) {
      logger.error("Cannot fetch qualification list error : " + e.getStackTrace());
    }
    return list;
  }

  @Override
  public QualificationMaster getQualificationMasterById(int id) {

    return null;
  }

  @Override
  public void removeQualificationMaster(int id) {

  }
}
