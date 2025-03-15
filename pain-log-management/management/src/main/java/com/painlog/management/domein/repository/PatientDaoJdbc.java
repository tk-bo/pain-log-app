package com.painlog.management.domein.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.painlog.management.dto.patient.InsertPatient;
import com.painlog.management.dto.patient.Patient;

@Repository("PatientDaoJdbc")
public class PatientDaoJdbc implements PatientDao {

    @Autowired
    JdbcTemplate jdbc;

    // Insert a single record into the Patient table.
    @Override
    public int insertOne(InsertPatient patient) throws DataAccessException {
        int rowNumber = jdbc.update(
            "INSERT INTO mydatabase.patients(date, " + "name, " + "movement, " + "vas, " + "memo)"
                        + " VALUES(?, ?, ?, ?, ?)",
            patient.getDate(), patient.getName(), patient.getMovement(), patient.getVas(), patient.getMemo());
        return rowNumber;
    }

    // Search for the specified name, movement into the Patient table.
    @Override
    public List<Patient> search(String name, String movement) throws DataAccessException {
        String sql = "SELECT * FROM mydatabase.patients WHERE name LIKE ? AND movement LIKE ?";
        Object[] params = {"%" + name + "%", "%" + movement + "%"};

        List<Patient> patients = jdbc.query(sql, params, (rs, rowNum) -> {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            Date sqlDate = rs.getDate("date");
            patient.setDate(sqlDate.toLocalDate());
            patient.setName(rs.getString("name"));
            patient.setMovement(rs.getString("movement"));
            patient.setVas(rs.getInt("vas"));
            patient.setMemo(rs.getString("memo"));

            return patient;
        });

        return patients;
    }


    // Select all records into the Patient table.
    @Override
    public List<Patient> select() throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM mydatabase.patients");
        List<Patient> patientList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            Patient patient = new Patient();
            patient.setId((Integer) map.get("id"));
            Date sqlDate = (Date) map.get("date");
            patient.setDate(sqlDate.toLocalDate()); 
            patient.setName((String) map.get("name"));
            patient.setMovement((String) map.get("movement"));
            patient.setVas((Integer) map.get("vas"));
            patient.setMemo((String) map.get("memo"));
            patientList.add(patient);
        }
        return patientList;
    }

    // Upload a single recoed into the Patient table.
    @Override
    public int updateOne(Patient patient) throws DataAccessException {
        int rowNumber = jdbc.update(
            "Update mydatabase.patients" + " SET" + " date = ?," + " name = ?," + " movement = ?," + " vas = ?," + " memo = ?" + " WHERE id = ?",
            patient.getDate(), patient.getName(), patient.getMovement(), patient.getVas(), patient.getMemo(), patient.getId());
        return  rowNumber;
    }
    
    // Delete a single record into the Patient table.
    @Override
    public int deleteOne(int id) throws DataAccessException {
        int rowNumber = jdbc.update(
            "DELETE FROM mydatabase.patients WHERE id = ?", id);
        return rowNumber;
    }
}
