package com.cisco.dao;

import java.util.List;

import com.cisco.models.Qualification;  

public interface QualificationDAO {

	public long addQualification(Qualification p);

	public void updateQualification(Qualification p);

	public List<Qualification> listQualification();

	public Qualification getQualificationById(int id);

	public void removeQualification(int id);

}
