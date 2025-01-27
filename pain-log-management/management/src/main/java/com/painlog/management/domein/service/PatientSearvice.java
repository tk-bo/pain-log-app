package com.painlog.management.domein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.painlog.management.domein.model.InsertPatient;
import com.painlog.management.domein.model.Patient;
import com.painlog.management.domein.repository.PatientDao;

@Transactional
@Service
public class PatientSearvice {
    @Autowired
    @Qualifier("PatientDaoJdbc")
    PatientDao dao;

    // Method for adding one item.
    public boolean insert(InsertPatient patient) {
        int rowNumber = dao.insertOne(patient);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    // Method for retrieving all records.
    public List<Patient> select() {
        return dao.select();
    }

    // Method for retrieving multiple records.
    public Patient search(String name, String movement) {
        return dao.search(name, movement);
    }

    // Method for updating one item.
    public boolean update(Patient patient) {
        int rowNumber = dao.updateOne(patient);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }

    // Method for daleting one item.
    public boolean delete(int id) {
        int rowNumber = dao.deleteOne(id);
        boolean result = false;
        if (rowNumber > 0) {
            result = true;
        }
        return result;
    }
}
