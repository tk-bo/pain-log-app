package com.painlog.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.painlog.management.domein.model.InsertPatient;
import com.painlog.management.domein.model.Patient;
import com.painlog.management.domein.service.PatientSearvice;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
    
    @Autowired
    PatientSearvice patientSearvice;

    @PostMapping("/insert")
    public ResponseEntity<Map<String, String>> insert(@RequestBody InsertPatient patient) {
        boolean success = patientSearvice.insert(patient);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "一件追加しました！" : "追加失敗しました！");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/select")
    public List<Patient> select() {
        return patientSearvice.select();
    }

    @GetMapping("/search")
    public List<Patient> search(String name, String movement) {
        return patientSearvice.search(name, movement);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> update(@RequestBody Patient patient) {
        boolean success = patientSearvice.update(patient);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "一件更新しました！" : "更新失敗しました！");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> delete(@RequestBody Integer id) {
        boolean success = patientSearvice.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", success ? "一件削除しました！" : "削除失敗しました！");
        return ResponseEntity.ok(response);
    }
}
