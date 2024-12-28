package com.lcwd.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaffs() {
        // Implement logic to retrieve staff names from database
        return ResponseEntity.ok(List.of("John Doe", "Jane Smith", "Vinay", "Abhay")); // Replace with actual data from
                                                                                       // database
    }
}
