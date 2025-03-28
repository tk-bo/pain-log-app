package com.painlog.management.dto.patient;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Patient {
    private int id;
    private LocalDate date;
    private String name;
    private String movement;
    private int vas;
    private String memo;
}
