package com.cisco.dao;

import java.util.List;

import com.cisco.models.QualificationMaster;

public interface QualificationMasterDAO {
  public long addQualificationMaster(QualificationMaster p);

  public void updateQualificationMaster(QualificationMaster p);

  /**
   * This method gets list of available qualifications from quilification_master
   * table
   * 
   * @return List of QualificationMaster
   */
  public List<QualificationMaster> getListOfQualificationMaster();

  public QualificationMaster getQualificationMasterById(int id);

  public void removeQualificationMaster(int id);
}
