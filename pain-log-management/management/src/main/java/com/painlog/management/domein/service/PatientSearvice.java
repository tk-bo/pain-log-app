package com.painlog.management.domein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.painlog.management.domein.repository.PatientDao;
import com.painlog.management.dto.patient.InsertPatient;
import com.painlog.management.dto.patient.Patient;

@Transactional
@Service
public class PatientSearvice {
    @Autowired
    @Qualifier("PatientDaoImpl")
    PatientDao patientDao;

    // Method for adding one item.
    public boolean insert(InsertPatient patient) {
        int rowNumber = patientDao.insertOne(patient);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    // Method for retrieving all records.
    public List<Patient> select() {
        return patientDao.select();
    }

    // Method for retrieving multiple records.
    public List<Patient> search(String name, String movement) {
        return patientDao.search(name, movement);
    }

    // Method for updating one item.
    public boolean update(Patient patient) {
        int rowNumber = patientDao.updateOne(patient);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    // Method for daleting one item.
    public boolean delete(int id) {
        int rowNumber = patientDao.deleteOne(id);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }
}
