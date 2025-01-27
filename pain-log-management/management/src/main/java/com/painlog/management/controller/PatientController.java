package com.painlog.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.painlog.management.domein.model.InsertPatient;
import com.painlog.management.domein.model.Patient;
import com.painlog.management.domein.service.PatientSearvice;

@RestController
public class PatientController {
    
    @Autowired
    PatientSearvice patientSearvice;

    @PostMapping("/insert")
    public String insert(@RequestBody InsertPatient patient) {
        String result = "";
        if (patientSearvice.insert(patient))
            result = "一件追加しました！";
        else
            result = "追加失敗しました！";
        return result;
    }

    @GetMapping("/select")
    public List<Patient> select() {
        return patientSearvice.select();
    }

    @GetMapping("/search")
    public Patient search(String name, String movement) {
        return patientSearvice.search(name, movement);
    }

    @PostMapping("/update")
    public String update(@RequestBody Patient patient) {
        String result = "";
        if (patientSearvice.update(patient))
            result = "一件更新しました！";
        else
            result = "更新失敗しました！";
        return result;
    }

    @PostMapping("/delete")
    public String delete(@RequestBody int id) {
        String result = "";
        if (patientSearvice.delete(id))
            result = "一件削除しました！";
        else
            result = "削除失敗しました！";
        return result;
    }

}
