package com.painlog.management.domein.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.painlog.management.domein.model.InsertPatient;
import com.painlog.management.domein.model.Patient;

public interface PatientDao {
    // Insert a single record into the Patient table.
    public int insertOne(InsertPatient patient) throws DataAccessException;
    // Search for the specified name, movement into the Patient table.
    public List<Patient> search(String name, String movement) throws DataAccessException;
    // Select all records into the Patient table.
    public List<Patient> select() throws DataAccessException;
    // Upload a single recoed into the Patient table.
    public int updateOne(Patient patient) throws DataAccessException;
    // Delete a single record into the Patient table.
    public int deleteOne(int id) throws DataAccessException;

}
